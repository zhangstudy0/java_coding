package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import Client.Client_choose.CET4ActionListener;
import Client.Client_choose.CET6ActionListener;
import Client.Client_choose.PoemsActionListener;

import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

public class Client_choose extends JFrame {

	private JPanel contentPane;
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	public JLabel lblNewLabel;
	public JLabel lblNewLabel_1;
	public JLabel lblNewLabel_2;
	public JButton Poems;
	public JButton CET6;
	public JButton CET4;
	public JLabel imgLabel;
	
	public String IP,Name,Port;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Client_choose frame = new Client_choose();
//					frame.setVisible(true);
//					frame.setTitle("游戏选择");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Client_choose() {
		
	}
	public Client_choose(String IP,String Port,String Name) {
		this.IP = IP;
		this.Port = Port;
		this.Name = Name;
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 482, 478);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		
//		panel = new JPanel();
//		panel.setBorder(new TitledBorder(null, "四级词汇", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		panel.setBounds(16, 17, 442, 100);
//		contentPane.add(panel);
//		panel.setLayout(null);
//		
//		textArea = new JTextArea("系统会随机抽取四级英文单词，玩家需要从四个选项中挑选出正确的出来，当生命值为0时游戏失败");
//		textArea.setBounds(10, 21, 268, 69);
//		panel.add(textArea);
//		textArea.setLineWrap(isBackgroundSet());
//		textArea.setOpaque(false);
////		textArea.setEnabled(false);
//		
//		CET4 = new JButton("\u7ACB\u5373\u5F00\u59CB\uFF01");
//		CET4.addActionListener(new CET4ActionListener());
//		CET4.setBounds(335, 67, 97, 23);
//		panel.add(CET4);
//		
//		panel_1 = new JPanel();
//		panel_1.setLayout(null);
//		panel_1.setBorder(new TitledBorder(null, "六级词汇", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		panel_1.setBounds(16, 138, 442, 100);
//		contentPane.add(panel_1);
//		
//		textArea_1 = new JTextArea("系统会随机抽取六级英文单词，玩家需要从四个选项中挑选出正确的出来，当生命值为0时游戏失败");
//		textArea_1.setBounds(10, 21, 268, 69);
//		panel_1.add(textArea_1);
//		textArea_1.setLineWrap(isBackgroundSet());
//		textArea_1.setOpaque(false);
//		textArea_1.setEnabled(false);
//		
//		CET6 = new JButton("\u7ACB\u5373\u5F00\u59CB\uFF01");
//		CET6.addActionListener(new CET6ActionListener());
//		CET6.setBounds(335, 67, 97, 23);
//		panel_1.add(CET6);
//		
//		panel_2 = new JPanel();
//		panel_2.setLayout(null);
//		panel_2.setBorder(new TitledBorder(null, "诗词比拼", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		panel_2.setBounds(16, 264, 442, 100);
//		contentPane.add(panel_2);
//		
//		textArea_2 = new JTextArea("系统会随机抽取设置好的带有空缺的诗词，玩家需要填补上正确的诗句，当生命值为0时游戏失败");
//		textArea_2.setBounds(10, 21, 268, 69);
//		panel_2.add(textArea_2);
//		textArea_2.setLineWrap(isBackgroundSet());
//		textArea_2.setOpaque(false);
//		textArea_2.setEnabled(false);
//		
//		Poems = new JButton("立即开始！");
//		Poems.addActionListener(new PoemsActionListener());
//		Poems.setBounds(335, 67, 97, 23);
//		panel_2.add(Poems);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon imgdefault = new ImageIcon("bgimg\\004.png");
		
		panel = new JPanel();
		this.panel.setOpaque(false);
		panel.setBorder(new TitledBorder(null, "四级词汇", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(16, 17, 528, 121);
		contentPane.add(panel);
		panel.setLayout(null);
//		textArea.setEnabled(false);
		
		CET4 = new JButton("\u7ACB\u5373\u5F00\u59CB\uFF01");
		this.CET4.setBackground(SystemColor.inactiveCaption);
		this.CET4.setFont(new Font("黑体", Font.PLAIN, 18));
		CET4.addActionListener(new CET4ActionListener());
		CET4.setBounds(377, 39, 136, 46);
		panel.add(CET4);
		
		this.lblNewLabel = new JLabel("<html><body>\u7CFB\u7EDF\u4F1A\u968F\u673A\u62BD\u53D6\u56DB\u7EA7\u82F1\u6587\u5355\u8BCD\uFF0C\u73A9\u5BB6\u9700\u8981\u4ECE\u56DB\u4E2A\u9009\u9879\u4E2D\u6311\u9009\u51FA\u6B63\u786E\u7684\u51FA\u6765\uFF0C\u5F53\u751F\u547D\u503C\u4E3A0\u65F6\u6E38\u620F\u5931\u8D25<body></html>");
		this.lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		this.lblNewLabel.setBounds(42, 28, 320, 78);
		this.panel.add(this.lblNewLabel);
		
		panel_1 = new JPanel();
		this.panel_1.setOpaque(false);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "六级词汇", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(15, 153, 528, 121);
		contentPane.add(panel_1);
		
		CET6 = new JButton("\u7ACB\u5373\u5F00\u59CB\uFF01");
		this.CET6.setBackground(SystemColor.inactiveCaption);
		this.CET6.setFont(new Font("黑体", Font.PLAIN, 18));
		CET6.addActionListener(new CET6ActionListener());
		CET6.setBounds(378, 39, 135, 45);
		panel_1.add(CET6);
		
		this.lblNewLabel_1 = new JLabel("<html><body>\u7CFB\u7EDF\u4F1A\u968F\u673A\u62BD\u53D6\u516D\u7EA7\u82F1\u6587\u5355\u8BCD\uFF0C\u73A9\u5BB6\u9700\u8981\u4ECE\u56DB\u4E2A\u9009\u9879\u4E2D\u6311\u9009\u51FA\u6B63\u786E\u7684\u51FA\u6765\uFF0C\u5F53\u751F\u547D\u503C\u4E3A0\u65F6\u6E38\u620F\u5931\u8D25</body></html>");
		this.lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		this.lblNewLabel_1.setBounds(34, 25, 329, 81);
		this.panel_1.add(this.lblNewLabel_1);
		
		panel_2 = new JPanel();
		this.panel_2.setOpaque(false);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "诗词比拼", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(16, 289, 528, 118);
		contentPane.add(panel_2);
		
		Poems = new JButton("立即开始！");
		this.Poems.setBackground(SystemColor.inactiveCaption);
		this.Poems.setFont(new Font("黑体", Font.PLAIN, 18));
		Poems.addActionListener(new PoemsActionListener());
		Poems.setBounds(378, 36, 135, 48);
		panel_2.add(Poems);
		
		this.lblNewLabel_2 = new JLabel("<html><body>\u7CFB\u7EDF\u4F1A\u968F\u673A\u62BD\u53D6\u8BBE\u7F6E\u597D\u7684\u5E26\u6709\u7A7A\u7F3A\u7684\u8BD7\u8BCD\uFF0C\u73A9\u5BB6\u9700\u8981\u586B\u8865\u4E0A\u6B63\u786E\u7684\u8BD7\u53E5\uFF0C\u5F53\u751F\u547D\u503C\u4E3A0\u65F6\u6E38\u620F\u5931\u8D25</body></html>");
		this.lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		this.lblNewLabel_2.setBounds(37, 15, 326, 87);
		this.panel_2.add(this.lblNewLabel_2);
		
		imgLabel = new JLabel(imgdefault);
		this.imgLabel.setBounds(0, -200, 550, 650);
		this.contentPane.add(this.imgLabel);
	}
	public class CET4ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null,"点击确定即可开始游戏！");
			ClientForm game = null;
			try {
				game = new ClientForm(IP,Port,Name,1);
				game.setVisible(true);
				game.setTitle("四级词汇对战");
				setVisible(false);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public class CET6ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null,"点击确定即可开始游戏！");
			ClientForm game = null;
			try {
				game = new ClientForm(IP,Port,Name,2);
				game.setVisible(true);
				game.setTitle("六级词汇对战");
				setVisible(false);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	public class PoemsActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null,"点击确定即可开始游戏！");
			ClientForm game = null;
			try {
				game = new ClientForm(IP,Port,Name,3);
				game.setVisible(true);
				game.setTitle("古诗对战");
				setVisible(false);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

