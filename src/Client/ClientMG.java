package Client;

import java.io.IOException;
import java.net.Socket;

public class ClientMG {
	private static final ClientMG clientmg =new ClientMG();
	private ClientMG() {}
	public static ClientMG getClientMG() {
		return clientmg;
	}
	
	private ClientForm win;
	public void  setWinForm(ClientForm w) {
		this.win =  w;
	}
	
	public void setLogTxt(String str) {
		//win.textLog.append(str+"\n");
	}	
	public void setPaneTxt(String str) {
		win.textPane.setText(str);
	}	
	public void clearLogTxt() {
		//win.textLog.setText("");
	}
	
	public void addItems(String [] users) {
		//��ӵ��ͻ��˵�List�ؼ��С�
		for(int i=0;i<users.length;i++) {
			addItem(users[i]);
		}		
	}
	public void addItem(String user) {
		win.itemUsers.addElement(user);
	}
	
	public void clearItems() {
		win.itemUsers.clear();
	}
	public void removeItem(String sName) {
		win.itemUsers.removeElement(sName);
	}
	
	
	SocketThread thd;
	public boolean connect(String IP,int port, String username, int choose) {		
		try {
			Socket socket =new Socket(IP, port);  //��ʼ�����ӷ�����	
			thd =new SocketThread(socket,username,choose);
			thd.start();
			return true;
		} catch (IOException e) {			
			e.printStackTrace();
			return false;
		} 		
	}
	public void close(String username) {
			thd.socketQuit();
	}
	//�õ���ǰ�û���
	public String getCurrThdName() {
		if (thd!=null)
			return thd.getName().trim();
		else
			return "";
	}
	public void sendMSG(String str) {
		if (thd!=null)
			thd.sendMSG(str);
	}
	public void sendTrue() {
		if(thd!=null) {
			thd.sendMSG(thd.getName()+"|"+"True");
		}
	}
	public void sendFalse() {
		if(thd!=null) {
			thd.sendMSG(thd.getName()+"|"+"False");
		}
	}
}
