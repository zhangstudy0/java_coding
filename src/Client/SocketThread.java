package Client;

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
	int choose ;
	public SocketThread(Socket socket,String sName,int choose) {
		super(sName);
		this.socket = socket;
		this.choose = choose;
	}
	public void run() {	
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8")));			
			String str ="";
//			发送登录信息：LOGIN|Username   
			String sUser = this.getName();
			String sSend = "LOGIN|"+sUser+"|"+choose;  //登录信息
			sendMSG(sSend);
			
			while((str = br.readLine())!=null) {		//			循环监听客户端的数据		
//				ClientMG.getClientMG().setLogTxt(str);	
				String [] commands = str.split("\\|");

				if(commands[0].equals("USERLISTS")) {
//				处理USERLISTS命令：所有在线用户的用户名 ,格式：USERLISTS|user1_user2_user3 
					String sUsers=commands[1];  //所有用户信息
					String [] users =sUsers.split("\\_");
					ClientMG.getClientMG().addItems(users);
				}
				else if(commands[0].equals("ADD")) {
//					处理添加用户的信息，格式：ADD|Username 
					String username=commands[1]; 
					ClientMG.getClientMG().addItem(username);
				}
				else if(commands[0].equals("MSG")) {
//					处理服务器转发过来的信息，格式：MSG|SenderName|MSGInfo
					String sender = commands[1]; 
					String msg =  commands[2];
					ClientMG.getClientMG().setLogTxt("["+sender+"说：]");
					ClientMG.getClientMG().setLogTxt(msg);
				}
				else if(commands[0].equals("DEL")) {
					String username = commands[1];
					ClientMG.getClientMG().removeItem(username);
					ClientMG.getClientMG().setLogTxt("["+username+":]");
					ClientMG.getClientMG().setLogTxt("已下线");
				}
				else if(commands[0].equals("CLOSE")) {
					ClientMG.getClientMG().setLogTxt("服务器关闭");
					ClientMG.getClientMG().close(this.getName());//关闭clientchat
					ClientMG.getClientMG().clearItems();//清空在线用户列表
				}
				else if(commands[0].equals("chaxun")) {
					String name = commands[1];
					//System.out.println(name+"\n六级词汇大战"+"\n答对的个数："+commands[2]+"\n剩余血量："+commands[3]+commands[4]);
					if(commands[4].equals("2"))
						ClientMG.getClientMG().setPaneTxt(name+"\n六级词汇大战"+"\n答对的个数："+commands[2]+"\n剩余血量："+commands[3]);
					if(commands[4].equals("1"))
						ClientMG.getClientMG().setPaneTxt(name+"\n四级词汇大战"+"\n答对的个数："+commands[2]+"\n剩余血量："+commands[3]);
					if(commands[4].equals("3"))
						ClientMG.getClientMG().setPaneTxt(name+"\n古诗词大战"+"\n答对的个数："+commands[2]+"\n剩余血量："+commands[3]);
					
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
	public void socketQuit() {
		try {
			if (pw != null) pw.close();
			if (br != null) br.close();
			if (socket != null) socket.close();					
		} catch (IOException e) {					
			e.printStackTrace();
		}	
	}
}