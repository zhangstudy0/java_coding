package Server;

import java.net.*;

import Server.SocketThread;
import Server.ServerMG;
public class serverListener extends Thread {
	ServerSocket server;
	volatile static boolean bFlag=true;
	public serverListener(ServerSocket server) {
		this.server = server;
	}
	
	public void run() {
		while(bFlag) {
			try {
				Socket socket = server.accept();   //������������ʼ,����ʽ����					
				ServerMG.getClientMG().setLogTxt("�ͻ������ӣ�"+socket);
				SocketThread thd = new SocketThread(socket);
				thd.start();
			} catch (Exception e) {
				e.printStackTrace();
			}				
		}
	}
	public static void stopListener() {
		bFlag=false;
	}
}
