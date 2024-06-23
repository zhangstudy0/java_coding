package Server;

import java.util.ArrayList;

public class ServerMG {

	private static final ServerMG servermg =new ServerMG();
	private ServerMG() {}
	public static ServerMG getClientMG() {
		return servermg;
	}
	//主界面的操作控制
	private ServerForm win;
	//注册窗体
	public void  setWinForm(ServerForm w) {
		this.win =  w;
	}
	//写入操作日志
	public void setLogTxt(String str) {
		win.textLog.append(str+"\n");
	}
	
	ArrayList<SocketThread> userlist =new ArrayList<>();
	
//	添加用户
	public synchronized void addlist(SocketThread thd) {
		userlist.add(thd);
	}
	//清空用户列表
	public void clearList() {
		userlist.clear();
	}
	//删除某个用户
	public synchronized void deletelist(SocketThread thd) {
		userlist.remove(thd);
	}
	
	//得到所有在线用户信息名称，发回给客户端：
	public void getOnlineNames(SocketThread st) {
		//给客户端：USERLISTS|user1_user2_user3
		if(userlist.size()>0) {  //排除第一个登录用户的操作
			String Users="";
			for(int i=0;i<userlist.size();i++) {
				SocketThread s=userlist.get(i);
				if (s.equals(st))  //排除自身的名称
					continue;
				Users+=s.getName()+"_";
			}	
			if(!Users.equals(""))
				st.sendMSG("USERLISTS|"+Users);
				System.out.println("USERLISTS|"+Users);
		}		
	}
	
	//将新上线的用户信息发送给其他用户
	public void sendNewUsertoAll(SocketThread st) {
		//ADD|userName
		for(int i=0;i<userlist.size();i++) {
			SocketThread s=userlist.get(i);
			if (s.equals(st))  //排除自身的名称
				continue;
			s.sendMSG("ADD|"+st.getName());
		}			
	}
	
	//通过Name用户名查找目标
	public SocketThread getSocketThreadByName(String user) {
		
		for(int i=0;i<userlist.size();i++) {
			SocketThread s=userlist.get(i);
			if(s.getName().equals(user)) {
				return s;
			}
		}
		return null;
	}
	
	//发送给所有人，但是要排除自身,格式：MSG|SenderName|MSGInfo
	public void sendMsgtoAll(String msg,SocketThread st) {
		for(int i=0;i<userlist.size();i++){
			SocketThread s=userlist.get(i);
			if(!s.equals(st)){
				s.sendMSG("MSG|"+st.getName()+"|"+msg);
			}
			
		}

	}
	
	//发送给所有人某用户的下线消息，但是要排除自身,格式：DEL|username
	public void sendOfflineAll(String msg,SocketThread st) {
		for(int i=0;i<userlist.size();i++){
			SocketThread s=userlist.get(i);
			if(!s.equals(st)){
				s.sendMSG("DEL|"+st.getName());
			}
			
		}

	}
	//发送给所有人关闭消息
	public void sendClosetoAll() {
		for(int i=0;i<userlist.size();i++){
			SocketThread s=userlist.get(i);
			s.sendMSG("CLOSE");
			s.close();
		}
	}
	
}
