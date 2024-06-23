package Client;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.w3c.dom.css.RGBColor;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;

import Client.ClientMG;
import Client.SocketThread;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;

import javax.swing.JTextPane;
public class ClientForm extends JFrame {

	private JPanel contentPane;
	public JPanel panel;
	public JButton btnLogin;
	public JButton btnClose;
	
	//PrintWriter pw=null;
//	ClientChat sThd;
	public JLabel label_1;
	public JTextField textUser;
	public JScrollPane scrollPane_1;
	public JList listUser;
	/**
	 * Launch the application.
	 */
	public  static String user;
	public  static String ip;
	public  static String Port;
	public  static int choose;
	DefaultListModel<String> itemUsers;
	public JLabel lblDefault;
	public JButton button1;
	public JButton button2;
	public JButton button3;
	public JButton button4;
	public JProgressBar progressBar;
	public JLabel label_2;
	public JLabel imgLabel;

	
	Vector<String> words = new Vector<String>();
    String[] dic,dic1,dic2,dic3; // 
    
    int wordIndex = 0; // 
    AudioClip clip1,clip2;
    private int blood=20;
    
    StringBuffer filePath = new StringBuffer("C:\\clientInfo\\");	//用户测试信息存储位置
    File file = null;	
    PrintStream psCorrect = null;	//存储正确单词信息
    PrintStream psFalse = null;		//存储错误单词信息
    
    public Boolean Bflag=true;
    public JButton changePic;
	public static int ran=0;
	public static int flag=0;
	public JPanel panel_2;
	public JButton button;
	public JTextPane textPane;
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//		            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//		                if ("Nimbus".equals(info.getName())) {
//		                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//		                    break;
//		                }
//		            }
//		        }catch(Exception e) {
//		        	e.printStackTrace();
//		        }
//				try {
//					//nickName = JOptionPane.showInputDialog("请输入登录用户名：");
//					ClientForm frame = new ClientForm();
//					frame.setVisible(true);
//					ClientMG.getClientMG().setWinForm(frame);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public ClientForm() {
		
	}
	  
	ClientForm(String IP,String Port,String Name,int choose) throws FileNotFoundException {
		this.ip = IP;
		this.Port = Port;
		this.user = Name;
		this.choose = choose;
		ClientMG.getClientMG().setWinForm(this);
		setTitle("用户端");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 824);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//		ImageIcon img4 = new ImageIcon("bgimg\\004.png");//默认背景图片
		
		panel = new JPanel();
		this.panel.setOpaque(false);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u767B\u5F55\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(15, 7, 561, 92);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnLogin = new JButton("\u767B\u5F55");
		this.btnLogin.setFont(new Font("黑体", Font.PLAIN, 18));
		btnLogin.addActionListener(new BtnLoginActionListener());
		btnLogin.setBounds(284, 25, 116, 42);
		panel.add(btnLogin);
		
		btnClose = new JButton("\u5173\u95ED");
		this.btnClose.setFont(new Font("黑体", Font.PLAIN, 18));
		btnClose.addActionListener(new BtnCloseActionListener());
		btnClose.setBounds(430, 25, 116, 42);
		panel.add(btnClose);
		
		label_1 = new JLabel("用户名:");
		this.label_1.setFont(new Font("宋体", Font.PLAIN, 21));
		label_1.setBounds(15, 39, 101, 28);
		panel.add(label_1);
		
		textUser = new JTextField();
		this.textUser.setFont(new Font("宋体", Font.PLAIN, 24));
		textUser.setBounds(104, 33, 123, 34);
		panel.add(textUser);
		textUser.setColumns(10);
		
		textUser.setText(user);
		
		scrollPane_1 = new JScrollPane();
		this.scrollPane_1.setOpaque(false);
		scrollPane_1.setBorder(new TitledBorder(null, "\u5728\u7EBF\u7528\u6237", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_1.setBounds(412, 114, 164, 473);
		contentPane.add(scrollPane_1);
		
		itemUsers=new DefaultListModel<String>();
		listUser = new JList(itemUsers);
//		this.listUser.setOpaque(false);
		this.listUser.setBackground(SystemColor.control);
		scrollPane_1.setViewportView(listUser);
		ImageIcon imgdefault = new ImageIcon("bgimg\\004.png");
		ImageIcon imagedefault = new ImageIcon("bgimg\\changebtn.png"); 
//		try {
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.mac.MacLookAndFeel");
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (InstantiationException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IllegalAccessException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (UnsupportedLookAndFeelException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		this.panel_2 = new JPanel();
		this.panel_2.setBorder(new TitledBorder(null, "\u67E5\u8BE2\u7528\u6237\u6210\u7EE9", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panel_2.setOpaque(false);
		this.panel_2.setBounds(412, 601, 164, 174);
		this.contentPane.add(this.panel_2);
		this.panel_2.setLayout(null);
		
		this.button = new JButton("查询");
		this.button.setFont(new Font("黑体", Font.PLAIN, 16));
		this.button.addActionListener(new ButtonActionListener());
		this.button.setBounds(27, 25, 110, 40);
//		this.button.setBorder(BorderFactory.createRaisedBevelBorder()); 
		this.panel_2.add(this.button);
		
		textPane = new JTextPane();
		textPane.setBounds(15, 80, 134, 79);
		this.panel_2.add(this.textPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBorder(new TitledBorder(null, "\u5F00\u59CB\u6311\u6218\uFF01", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(15, 114, 383, 661);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblDefault = new JLabel("default");
		if(choose != 3)
			this.lblDefault.setFont(new Font("Georgia", Font.PLAIN, 31));
		else
			this.lblDefault.setFont(new Font("宋体", Font.PLAIN, 24));
		lblDefault.setHorizontalAlignment(SwingConstants.CENTER);
		lblDefault.setHorizontalTextPosition(SwingConstants.LEADING);
		lblDefault.setBounds(80, 84, 192, 41);
		panel_1.add(lblDefault);
		
		button1 = new JButton("New button");
		this.button1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		this.button1.addActionListener(new Button1ActionListener());
		button1.setBounds(52, 169, 228, 67);
//		button1.setBackground(new Color(220,220,220));
		button1.setBorder(BorderFactory.createRaisedBevelBorder()); 
		
		button1.addMouseListener(new Button1listener());
		panel_1.add(button1);
		
		button2 = new JButton("New button");
		this.button2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button2.setBounds(52, 275, 228, 67);
//		button2.setBackground(new Color(220,220,220));
		button2.setBorder(BorderFactory.createRaisedBevelBorder()); 
		button2.addMouseListener(new Button2ActionListener());
		panel_1.add(button2);
		
		button3 = new JButton("New button");
		this.button3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button3.setBounds(52, 377, 228, 62);
//		button3.setBackground(new Color(220,220,220));
		button3.setBorder(BorderFactory.createRaisedBevelBorder()); 
		button3.addMouseListener(new Button3ActionListener());
		panel_1.add(button3);
		
		button4 = new JButton("New button");
		this.button4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button4.setBounds(52, 477, 228, 62);
//		button4.setBackground(new Color(220,220,220));
		button4.setBorder(BorderFactory.createRaisedBevelBorder()); 
		button4.addMouseListener(new Button_2ActionListener());
		panel_1.add(button4);
		
		
		button1.setBackground(new Color(217,232,245));
		button2.setBackground(new Color(217,232,245));
		button3.setBackground(new Color(217,232,245));
		button4.setBackground(new Color(217,232,245));
		
		label_2 = new JLabel("\u8840\u91CF");
		label_2.setBounds(325, 43, 72, 18);
		panel_1.add(label_2);
		this.changePic = new JButton(imagedefault);
		this.changePic.setBounds(15, 608, 44, 38);
		panel_1.add(this.changePic);
		this.changePic.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		this.changePic.addActionListener(new ChangePicActionListener());
		this.changePic.setContentAreaFilled(false); 
		
		progressBar = new JProgressBar();
		this.progressBar.setBackground(SystemColor.control);
		this.progressBar.setOpaque(true);
		progressBar.setMaximum(20);
		progressBar.setString("");
		progressBar.setOrientation(SwingConstants.VERTICAL);
		progressBar.setStringPainted(true);
		progressBar.setBounds(335, 72, 21, 559);
		progressBar.setForeground(new Color(242, 56, 90));
		panel_1.add(progressBar);
		this.progressBar.setForeground(new Color(255, 99, 71));
		this.progressBar.setValue(100);
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.mac.MacLookAndFeel");
//			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(progressBar);
		imgLabel = new JLabel(imgdefault);
		this.imgLabel.setBounds(0, 0, 591, 830);
		this.contentPane.add(this.imgLabel);
		
//		this.changePic = new JButton("");
//		this.changePic.addActionListener(new ChangebtnActionListener());
//		this.changePic.setBounds(15, 569, 57, 62);
//		panel_1.add(this.changePic);
		BufferedReader br;
		if(choose == 2) {
			br = new BufferedReader(new FileReader("CET6.txt"));
		}else if(choose == 1) {
			br = new BufferedReader(new FileReader("CET4.txt"));
		}else {
			br = new BufferedReader(new FileReader("Poems.txt"));
		}
			
		
		Date date= new Date();//创建一个时间对象，获取到当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
		String currentTime = sdf.format(date);//将当前时间格式化为需要的类型
		
		
		//错题
		filePath.append(user);
        file = new File(filePath.toString());
        if (!file.exists())
            file.mkdir();

        filePath.append("\\");
        psCorrect = new PrintStream(new FileOutputStream(new File(filePath.toString() + "CorrectWord.txt"), true));
        psFalse = new PrintStream(new FileOutputStream(new File(filePath.toString() + "FalseWord.txt"), true));
        psFalse.println("在"+currentTime+"这次测试中你这些单词做错了噢！");
        psCorrect.println("在"+currentTime+"这次测试中你这些单词做对啦！");
        
		
	    int life = 20;
	    String str = null;
	    //音频
	    File musicFile1 = new File("13386.wav");
	    File musicFile2 = new File("false.wav");
	    URI uri1 = musicFile1.toURI();
	    URI uri2 = musicFile2.toURI();
        //读入
        try {
			while (null != (str = br.readLine())) {
			    words.add(str);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // 打乱
        for (int i = 0; i < words.size(); i++) {
            String tmp = words.get(i);
            int randIndex = (int) (Math.random() * (words.size() - i) + i);
            words.set(i, words.get(randIndex));
            words.set(randIndex, tmp);
        }
       
        //new progressBarThread().start();
        
        URL url1,url2;
		try {
			  url1 = uri1.toURL();
			  url2 = uri2.toURL();
			  clip1 = Applet.newAudioClip(url1);
			  clip2 = Applet.newAudioClip(url2);
			  
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void init() {//更换壁纸
		Vector<ImageIcon> img = new Vector<ImageIcon>();
		ImageIcon img1 = new ImageIcon("bgimg\\001.png");//要设置的背景图片
		ImageIcon img2 = new ImageIcon("bgimg\\002.png");//要设置的背景图片
		ImageIcon img3 = new ImageIcon("bgimg\\003.png");//要设置的背景图片
		ImageIcon img4 = new ImageIcon("bgimg\\004.png");//要设置的背景图片
		ImageIcon img5 = new ImageIcon("bgimg\\005.png");//要设置的背景图片
		ImageIcon img6 = new ImageIcon("bgimg\\006.png");//要设置的背景图片
		ImageIcon img7 = new ImageIcon("bgimg\\007.png");//要设置的背景图片
		ImageIcon img8 = new ImageIcon("bgimg\\008.png");//要设置的背景图片
		img.add(img1);
		img.add(img2);
		img.add(img3);
		img.add(img4);
		img.add(img5);
		img.add(img6);
		img.add(img7);
		img.add(img8);

		this.getContentPane().remove(imgLabel);
		imgLabel = new JLabel(img.get(ran));//将背景图放在标签里。
		
		if(ran<7) {
			ran+=1;
		}else {
			ran=0;
		}
//		JLabel imgLabel = new JLabel(img.get((int)(Math.random()*5)));//将背景图放在标签里。
//		this.getLayeredPane().remove(imgLabel);
		this.getContentPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//将背景标签添加到jfram的LayeredPane面板里。
		imgLabel.setBounds(0, 0,600,830);	
	}
		
	private void Init() {
		
		dic = words.get(wordIndex++ % words.size()).split(" ");
		//System.out.print((wordIndex-1-(int)Math.random()*100));
		dic1 = words.get(((wordIndex+1+(int)(Math.random()*100)) % words.size())).split(" ");
		dic2 = words.get(((wordIndex+1+(int)(Math.random()*100)) % words.size())).split(" ");
		dic3 = words.get(((wordIndex+1+(int)(Math.random()*100)) % words.size())).split(" ");
		
		lblDefault.setText(dic[0]);
		int	x=(1+(int)(Math.random()*4));
		//System.out.println(x);
		if(x==1) {
			button1.setText(dic[1]);
			button2.setText(dic1[1]);
			button3.setText(dic2[1]);
			button4.setText(dic3[1]);
		}else if(x==2) {
			button1.setText(dic1[1]);
			button2.setText(dic[1]);
			button3.setText(dic2[1]);
			button4.setText(dic3[1]);
		}else if(x==3) {
			button1.setText(dic1[1]);
			button2.setText(dic2[1]);
			button3.setText(dic[1]);
			button4.setText(dic3[1]);
		}else{
			button1.setText(dic1[1]);
			button2.setText(dic2[1]);
			button3.setText(dic3[1]);
			button4.setText(dic[1]);
		}	
	}
	private void blood() {
		if(blood == 0) {
			Bflag=false;
			String username = ClientMG.getClientMG().getCurrThdName() ;
			String strSend = "OFFLINE" +"|"+ username;
			ClientMG.getClientMG().sendMSG(strSend);
			JOptionPane.showMessageDialog(null, "游戏结束");
			  psCorrect.close();
              psFalse.close();
			System.exit(0);
		}
	}
	private class progressBarThread extends Thread{//线程控制进度条
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(Bflag) {
				if(blood<=20 && blood>=0) {
					progressBar.setValue(blood);
					progressBar.setForeground(new Color(000,000,000));
					progressBar.setString(String.valueOf(blood));
				}
			}
		}
	}
	private void exitmethod() {
		button1.setText("default");
		button2.setText("default");
		button3.setText("default");
		button4.setText("default");
		lblDefault.setText("default");
		Bflag=false;
		progressBar.setValue(0);
		progressBar.setString("");
		
		textUser.setText("");
		textPane.setText("");
		psCorrect.close();
        psFalse.close();
	}
	private class BtnLoginActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//登录
		
	
		//String user=textUser.getText().trim();  //用户名默认具有唯一性
		if (ClientMG.getClientMG().connect(ip, Integer.parseInt(Port), user,choose))
			ClientMG.getClientMG().setLogTxt("用户登录成功");
		else {
			ClientMG.getClientMG().setLogTxt("用户登录失败");
		}
		button1.setBackground(new Color(217,232,245));
		button2.setBackground(new Color(217,232,245));
		button3.setBackground(new Color(217,232,245));
		button4.setBackground(new Color(217,232,245));
		Init();//答题的初始化
		new progressBarThread().start();
		}
	}
	private class BtnCloseActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//退出登录
			String username = ClientMG.getClientMG().getCurrThdName() ;
			String strSend = "OFFLINE" +"|"+ username;
			ClientMG.getClientMG().sendMSG(strSend);
			//textSend.setText("");
			
			exitmethod();
			ClientMG.getClientMG().clearItems();//清空在线用户
			ClientMG.getClientMG().close(username);//关闭clientchat
			
			
			
			
		}
	}
	
	private class Button_2ActionListener implements MouseListener {//4

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(dic[1]==button4.getText()) {
//				button4.setBackground(Color.blue);
				button4.setBackground(new Color(165,255,163));
				clip1.play();
				psCorrect.println(words.get(wordIndex - 1));
				ClientMG.getClientMG().sendTrue();
			}else {
//				button4.setBackground(Color.red);
				button4.setBackground(new Color(255,150,141));
				clip2.play();
				ClientMG.getClientMG().sendFalse();
				psFalse.println(words.get(wordIndex - 1));
				blood--;
				blood();
				System.out.print(blood);
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			try {
				Thread.currentThread().sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			button4.setBackground(new Color(217,232,245));
			button4.setForeground(Color.black);
			Init();
		}
		
		
	}
	private class Button1listener implements  MouseListener{//1
		

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if(dic[1]==button1.getText()) {
//				button1.setBackground(Color.blue);
				button1.setBackground(new Color(165,255,163));
				clip1.play();
				psCorrect.println(words.get(wordIndex - 1));
				ClientMG.getClientMG().sendTrue();
			}else {
//				button1.setBackground(Color.red);
				button1.setBackground(new Color(255,150,141));
				clip2.play();
				ClientMG.getClientMG().sendFalse();
				psFalse.println(words.get(wordIndex - 1));
				blood--;
				blood();
			}
//			try {
//				
//				Thread.currentThread().sleep(500);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
			
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			try {
				Thread.currentThread().sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			button1.setBackground(new Color(217,232,245));
			button1.setForeground(Color.black);
			Init();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
	}
	private class Button2ActionListener implements MouseListener {//2
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(dic[1]==button2.getText()) {
//			button2.setBackground(Color.blue);
			button2.setBackground(new Color(165,255,163));
			psCorrect.println(words.get(wordIndex - 1));
			clip1.play();
			ClientMG.getClientMG().sendTrue();
		}else {
//			button2.setBackground(Color.red);
			button2.setBackground(new Color(255,150,141));
			psFalse.println(words.get(wordIndex - 1));
			clip2.play();
			ClientMG.getClientMG().sendFalse();
			blood--;
			blood();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			Thread.currentThread().sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		button2.setBackground(new Color(217,232,245));
		button2.setForeground(Color.black);
		Init();
	}
	}
	private class Button3ActionListener implements MouseListener {//3
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if(dic[1]==button3.getText()) {
//				button3.setBackground(Color.blue);
				button3.setBackground(new Color(165,255,163));
				clip1.play();
				psCorrect.println(words.get(wordIndex - 1));
				ClientMG.getClientMG().sendTrue();
			}else {
//				button3.setBackground(Color.red);
				button3.setBackground(new Color(255,150,141));
				clip2.play();
				psFalse.println(words.get(wordIndex - 1));
				ClientMG.getClientMG().sendFalse();
				blood--;
				blood();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			try {
				Thread.currentThread().sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			button3.setBackground(new Color(217,232,245));
			button3.setForeground(Color.black);
			Init();
		}
	}
	private class ChangePicActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			init();
		}
	}
	private class Button1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String sSender = ClientMG.getClientMG().getCurrThdName() ; //不建议这样得到发送者的名称 textUser.getText().trim();
			String sRecName = "";				
			sRecName = listUser.getSelectedValue().toString().trim();
			String strSend ="chaxun"+"|"+sSender+"|"+sRecName;
			ClientMG.getClientMG().sendMSG(strSend);
		}
	}
}
