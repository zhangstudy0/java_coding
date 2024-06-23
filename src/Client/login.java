package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.SystemColor;

public class login extends JFrame {

	private JPanel contentPane;
	public JLabel IPLabel;
	public JPanel panel;
	public JLabel PortLabel;
	public JLabel NameLabel;
	public JTextField IPtxt;
	public JTextField Porttxt;
	public JTextField Nametxt;
	public JButton btnNewButton;
	public JLabel imgLabel;
	public  static String IPaddress;
	public  static String Portnumber;
	public  static String Username;

	/**
	 * Launch the application.
	 */
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
					login frame = new login();
					frame.setVisible(true);
					frame.setTitle("登录");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setBackground(SystemColor.desktop);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 295);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon imgdefault = new ImageIcon("bgimg\\004.png");
		
		panel = new JPanel();
		this.panel.setOpaque(false);
		this.panel.setBackground(SystemColor.window);
		this.panel.setForeground(Color.BLACK);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(0, 0, 504, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		
		IPLabel = new JLabel("IP：");
		this.IPLabel.setFont(new Font("黑体", Font.PLAIN, 19));
		IPLabel.setBounds(76, 43, 91, 18);
		panel.add(IPLabel);
		
		
		PortLabel = new JLabel("端口号：");
		this.PortLabel.setFont(new Font("黑体", Font.PLAIN, 19));
		PortLabel.setBounds(76, 161, 91, 30);
		panel.add(PortLabel);
		
		
		NameLabel = new JLabel("用户名：");
		this.NameLabel.setFont(new Font("黑体", Font.PLAIN, 19));
		NameLabel.setBounds(76, 97, 91, 30);
		panel.add(NameLabel);
		
		
		IPtxt = new JTextField("127.0.0.1");
		this.IPtxt.setFont(new Font("宋体", Font.PLAIN, 19));
		IPtxt.setBounds(157, 33, 164, 38);
		panel.add(IPtxt);
		IPtxt.setColumns(10);
		IPaddress = IPtxt.getText();
		
		
		Porttxt = new JTextField("9000");
		this.Porttxt.setFont(new Font("宋体", Font.PLAIN, 19));
		Porttxt.setBounds(157, 158, 96, 36);
		panel.add(Porttxt);
		Porttxt.setColumns(10);
		Portnumber = Porttxt.getText();
		
		Nametxt = new JTextField("admin");
		this.Nametxt.setFont(new Font("宋体", Font.PLAIN, 19));
		Nametxt.setBounds(160, 94, 161, 36);
		panel.add(Nametxt);
		Nametxt.setColumns(10);
		Username = Nametxt.getText();
		
		btnNewButton = new JButton("登录");
		this.btnNewButton.setBackground(new Color(176, 196, 222));
		this.btnNewButton.setFont(new Font("黑体", Font.PLAIN, 19));
		btnNewButton.addActionListener(new BtnNewButtonActionListener());
		btnNewButton.setBounds(361, 88, 101, 48);
		btnNewButton.setBorder(BorderFactory.createRaisedBevelBorder()); 
		panel.add(btnNewButton);         
		imgLabel = new JLabel(imgdefault);
		this.imgLabel.setBounds(0, -200, 504, 450);
		this.contentPane.add(this.imgLabel);
	}
	private class BtnNewButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
//			JOptionPane jOptionPane=new JOptionPane();
//			jOptionPane.setBackground(new Color(0,0,0));
//			jOptionPane.showMessageDialog(jOptionPane, "信息已获取!");
			
			JOptionPane.showMessageDialog(null,"信息已获取！");
			
			Client_choose choose = new Client_choose(IPtxt.getText().trim(),Porttxt.getText().trim(),Nametxt.getText().trim());
			choose.setVisible(true);
			setVisible(false);
		}
	}
}