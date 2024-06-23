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
//			循环监听客户端的数据
			while((str = br.readLine())!=null) {				
				ServerMG.getClientMG().setLogTxt(str);		
				String [] commands = str.split("\\|");

				if(commands[0].equals("LOGIN")) {
//					1、处理新用户登录的LOGIN信息，LOGIN|UserName
					String user = commands[1];  
					this.setName(user);  //当前新用户名保留在SocketThread中
					this.choose = commands[2];
//			         2、将当前登录的SocketThread信息放入Arraylist中  
					ServerMG.getClientMG().addlist(this);
					
//					 1、得到所有在线用户信息名称，发回给客户端：USERLISTS|user1_user2_user3 
					ServerMG.getClientMG().getOnlineNames(this);

//			         3、将当前登录用户的信息（用户名），发送给已经在线的其他用户：ADD|userName  
					
					ServerMG.getClientMG().sendNewUsertoAll(this);
				}
				else if(commands[0].equals("MSG")) {
				   //聊天  格式MSG|SenderName|RecName|MSGInfo 
				   String Sender= commands[1];
				   String RecName =commands[2];  //接收者姓名，即需要转发的对象
				   String sMSG = commands[3];
				   if(RecName.equals("ALL")) {
					   ServerMG.getClientMG().sendMsgtoAll(sMSG,this);
				   }
				   else {
					   //根据接收者的姓名，得到目标Socket
					   SocketThread st = ServerMG.getClientMG().getSocketThreadByName(RecName);
//					       转发信息到目标客户端，格式：MSG|SenderName|MSGInfo
					   st.sendMSG("MSG|"+Sender+"|"+sMSG);  //完成消息的转发
				   }
				   
				  
				}
				else if(commands[0].equals("OFFLINE")) {
					String username = commands[1];
					String sMSG = "DEL|" + username;
//					this.setName(username);
					//发送下线信息
					ServerMG.getClientMG().sendOfflineAll(sMSG, this);
					//删除当前的socketThread
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
					String sRecName =commands[2];//被查询的人的名字		
					
					SocketThread st = ServerMG.getClientMG().getSocketThreadByName(sSender);
					
					st.sendMSG("chaxun|"+sRecName+"|"+ServerMG.getClientMG().getSocketThreadByName(sRecName).tureNumber+"|"+ServerMG.getClientMG().getSocketThreadByName(sRecName).bloodNumber+"|"+ServerMG.getClientMG().getSocketThreadByName(sRecName).choose);  //完成消息的发送
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
