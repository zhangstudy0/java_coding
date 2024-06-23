package Server;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Server.serverListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Color;

public class ServerForm extends JFrame {

	private JPanel contentPane;
	public JPanel panel;
	public JLabel label;
	public JTextField textPort;
	public JButton btnStart;
	public JButton btnEnd;
	public JScrollPane scrollPane;
	public JTextArea textLog;

	ServerSocket server = null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
		            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		                if ("Nimbus".equals(info.getName())) {
		                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		                    break;
		                }
		            }
		        }catch(Exception e) {
		        	e.printStackTrace();
		        }
				try {
					ServerForm frame = new ServerForm();
					frame.setVisible(true);
					ServerMG.getClientMG().setWinForm(frame);   //将窗体对象传入管理 类
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ServerForm() {
		setTitle("\u670D\u52A1\u5668\u7AEF");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 577);
		contentPane = new JPanel();
		this.contentPane.setBackground(SystemColor.textHighlightText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		this.panel.setBackground(SystemColor.textHighlightText);
		this.panel.setFont(new Font("宋体", Font.PLAIN, 18));
		panel.setBorder(new TitledBorder(null, "\u914D\u7F6E\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(15, 15, 488, 74);
		contentPane.add(panel);
		panel.setLayout(null);
		
		label = new JLabel("\u7AEF\u53E3\uFF1A");
		this.label.setFont(new Font("黑体", Font.PLAIN, 19));
		label.setBounds(15, 29, 57, 25);
		panel.add(label);
		
		textPort = new JTextField();
		this.textPort.setFont(new Font("宋体", Font.PLAIN, 19));
		textPort.setText("9000");
		textPort.setBounds(87, 25, 82, 34);
		panel.add(textPort);
		textPort.setColumns(10);
		
		btnStart = new JButton("\u5F00\u542F\u670D\u52A1");
		this.btnStart.setBackground(new Color(176, 196, 222));
		this.btnStart.setFont(new Font("黑体", Font.PLAIN, 18));
		btnStart.addActionListener(new BtnStartActionListener());
		btnStart.setBounds(204, 22, 118, 39);
		panel.add(btnStart);
		
		btnEnd = new JButton("\u5173\u95ED\u670D\u52A1");
		this.btnEnd.setBackground(new Color(176, 196, 222));
		this.btnEnd.setFont(new Font("黑体", Font.PLAIN, 18));
		btnEnd.addActionListener(new BtnEndActionListener());
		btnEnd.setBounds(337, 22, 124, 39);
		panel.add(btnEnd);
		
		scrollPane = new JScrollPane();
		this.scrollPane.setForeground(new Color(0, 255, 0));
		this.scrollPane.setBackground(SystemColor.textHighlightText);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new TitledBorder(null, "\u6D88\u606F\u8BB0\u5F55", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(15, 94, 488, 428);
		contentPane.add(scrollPane);
		
		textLog = new JTextArea();
		this.textLog.setForeground(new Color(0, 0, 0));
		this.textLog.setBackground(SystemColor.window);
		textLog.setEditable(false);
		textLog.setLineWrap(true);
		scrollPane.setViewportView(textLog);
	}
	serverListener slThd=null;
	private class BtnStartActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//开启服务
			
			int PORT = Integer.parseInt(textPort.getText().trim());   //监听端口号	
			try {
				server =new ServerSocket(PORT);
				ServerMG.getClientMG().setLogTxt("服务器开启...");			
				slThd=new serverListener(server);
				slThd.start();				
				
			} catch (IOException e) {				
				e.printStackTrace();
			} 
		}
	}
	private class BtnEndActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//关闭服务
			ServerMG.getClientMG().sendClosetoAll();//发送关闭信息
			ServerMG.getClientMG().clearList();//清空ArrayList
			serverListener.stopListener();
		}
	}

}
