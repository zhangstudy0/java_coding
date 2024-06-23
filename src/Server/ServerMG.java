package Server;

import java.util.ArrayList;

public class ServerMG {

	private static final ServerMG servermg =new ServerMG();
	private ServerMG() {}
	public static ServerMG getClientMG() {
		return servermg;
	}
	//������Ĳ�������
	private ServerForm win;
	//ע�ᴰ��
	public void  setWinForm(ServerForm w) {
		this.win =  w;
	}
	//д�������־
	public void setLogTxt(String str) {
		win.textLog.append(str+"\n");
	}
	
	ArrayList<SocketThread> userlist =new ArrayList<>();
	
//	����û�
	public synchronized void addlist(SocketThread thd) {
		userlist.add(thd);
	}
	//����û��б�
	public void clearList() {
		userlist.clear();
	}
	//ɾ��ĳ���û�
	public synchronized void deletelist(SocketThread thd) {
		userlist.remove(thd);
	}
	
	//�õ����������û���Ϣ���ƣ����ظ��ͻ��ˣ�
	public void getOnlineNames(SocketThread st) {
		//���ͻ��ˣ�USERLISTS|user1_user2_user3
		if(userlist.size()>0) {  //�ų���һ����¼�û��Ĳ���
			String Users="";
			for(int i=0;i<userlist.size();i++) {
				SocketThread s=userlist.get(i);
				if (s.equals(st))  //�ų����������
					continue;
				Users+=s.getName()+"_";
			}	
			if(!Users.equals(""))
				st.sendMSG("USERLISTS|"+Users);
				System.out.println("USERLISTS|"+Users);
		}		
	}
	
	//�������ߵ��û���Ϣ���͸������û�
	public void sendNewUsertoAll(SocketThread st) {
		//ADD|userName
		for(int i=0;i<userlist.size();i++) {
			SocketThread s=userlist.get(i);
			if (s.equals(st))  //�ų����������
				continue;
			s.sendMSG("ADD|"+st.getName());
		}			
	}
	
	//ͨ��Name�û�������Ŀ��
	public SocketThread getSocketThreadByName(String user) {
		
		for(int i=0;i<userlist.size();i++) {
			SocketThread s=userlist.get(i);
			if(s.getName().equals(user)) {
				return s;
			}
		}
		return null;
	}
	
	//���͸������ˣ�����Ҫ�ų�����,��ʽ��MSG|SenderName|MSGInfo
	public void sendMsgtoAll(String msg,SocketThread st) {
		for(int i=0;i<userlist.size();i++){
			SocketThread s=userlist.get(i);
			if(!s.equals(st)){
				s.sendMSG("MSG|"+st.getName()+"|"+msg);
			}
			
		}

	}
	
	//���͸�������ĳ�û���������Ϣ������Ҫ�ų�����,��ʽ��DEL|username
	public void sendOfflineAll(String msg,SocketThread st) {
		for(int i=0;i<userlist.size();i++){
			SocketThread s=userlist.get(i);
			if(!s.equals(st)){
				s.sendMSG("DEL|"+st.getName());
			}
			
		}

	}
	//���͸������˹ر���Ϣ
	public void sendClosetoAll() {
		for(int i=0;i<userlist.size();i++){
			SocketThread s=userlist.get(i);
			s.sendMSG("CLOSE");
			s.close();
		}
	}
	
}
