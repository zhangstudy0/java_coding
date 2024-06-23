package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketThread extends Thread {
	Socket socket = null;
	BufferedReader br = null;
	PrintWriter pw  = null;
	int tureNumber = 0;
	int falseNumbre = 0;
	int bloodNumber = 20;
	String choose ;
	public SocketThread(Socket socket) {		
		this.socket = socket;
	}	
	public void run() {	
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8")));			
			String str ="";
//			ѭ�������ͻ��˵�����
			while((str = br.readLine())!=null) {				
				ServerMG.getClientMG().setLogTxt(str);		
				String [] commands = str.split("\\|");

				if(commands[0].equals("LOGIN")) {
//					1���������û���¼��LOGIN��Ϣ��LOGIN|UserName
					String user = commands[1];  
					this.setName(user);  //��ǰ���û���������SocketThread��
					this.choose = commands[2];
//			         2������ǰ��¼��SocketThread��Ϣ����Arraylist��  
					ServerMG.getClientMG().addlist(this);
					
//					 1���õ����������û���Ϣ���ƣ����ظ��ͻ��ˣ�USERLISTS|user1_user2_user3 
					ServerMG.getClientMG().getOnlineNames(this);

//			         3������ǰ��¼�û�����Ϣ���û����������͸��Ѿ����ߵ������û���ADD|userName  
					
					ServerMG.getClientMG().sendNewUsertoAll(this);
				}
				else if(commands[0].equals("MSG")) {
				   //����  ��ʽMSG|SenderName|RecName|MSGInfo 
				   String Sender= commands[1];
				   String RecName =commands[2];  //����������������Ҫת���Ķ���
				   String sMSG = commands[3];
				   if(RecName.equals("ALL")) {
					   ServerMG.getClientMG().sendMsgtoAll(sMSG,this);
				   }
				   else {
					   //���ݽ����ߵ��������õ�Ŀ��Socket
					   SocketThread st = ServerMG.getClientMG().getSocketThreadByName(RecName);
//					       ת����Ϣ��Ŀ��ͻ��ˣ���ʽ��MSG|SenderName|MSGInfo
					   st.sendMSG("MSG|"+Sender+"|"+sMSG);  //�����Ϣ��ת��
				   }
				   
				  
				}
				else if(commands[0].equals("OFFLINE")) {
					String username = commands[1];
					String sMSG = "DEL|" + username;
//					this.setName(username);
					//����������Ϣ
					ServerMG.getClientMG().sendOfflineAll(sMSG, this);
					//ɾ����ǰ��socketThread
					ServerMG.getClientMG().deletelist(this);
					
				}
				else if(commands[1].equals("False")||commands[1].equals("True")) {
					String username = commands[0];
					if(commands[1].equals("False")) {
						ServerMG.getClientMG().getSocketThreadByName(username).falseNumbre++;
						ServerMG.getClientMG().getSocketThreadByName(username).bloodNumber--;
						//System.out.println(ServerMG.getClientMG().getSocketThreadByName(username).falseNumbre);
					}
					if(commands[1].equals("True")) {
						ServerMG.getClientMG().getSocketThreadByName(username).tureNumber++;
						//System.out.println(ServerMG.getClientMG().getSocketThreadByName(username).tureNumber);
					}
					
				}
				else if(commands[0].equals("chaxun")) {
					String sSender = commands[1];
					String sRecName =commands[2];//����ѯ���˵�����		
					
					SocketThread st = ServerMG.getClientMG().getSocketThreadByName(sSender);
					
					st.sendMSG("chaxun|"+sRecName+"|"+ServerMG.getClientMG().getSocketThreadByName(sRecName).tureNumber+"|"+ServerMG.getClientMG().getSocketThreadByName(sRecName).bloodNumber+"|"+ServerMG.getClientMG().getSocketThreadByName(sRecName).choose);  //�����Ϣ�ķ���
				}
				
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {			
			try {
				if (pw != null) pw.close();
				if (br != null) br.close();
				if (socket != null) socket.close();					
			} catch (IOException e) {					
				e.printStackTrace();
			}	
		}		
	}
	public void sendMSG(String send) {
		pw.println(send);
		pw.flush();
	}
	public void close() {
		try {
			if (pw != null) pw.close();
			if (br != null) br.close();
			if (socket != null) socket.close();					
		} catch (IOException e) {					
			e.printStackTrace();
		}	
	}
}
