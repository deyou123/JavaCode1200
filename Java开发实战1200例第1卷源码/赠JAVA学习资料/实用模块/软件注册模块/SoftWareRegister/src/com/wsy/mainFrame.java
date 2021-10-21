package com.wsy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.util.CreatecdIcon;
import com.util.ReadWriteRegisty;
/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class mainFrame extends JFrame {

	/**
	 * @param args
	 */
	int ZHUCE=30;
	JLabel zhuce=new JLabel();
	public static void main(String[] args) {
		// TODO 自动生成方法存根
		new mainFrame().setVisible(true);
	}

	public mainFrame() {
		super();
		try {
			UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
		ButtonGroup bg = new ButtonGroup();
		final JRadioButton b1, b2;
		JButton b3;
		final JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(150);

		JPanel rightPanel = new JPanel();

		ImageIcon icon = CreatecdIcon.add("11.jpg");
		JLabel jl = new JLabel();
		jl.setLayout(null);

		jl.setIcon(icon);
		jl.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		b1 = new JRadioButton("我有一个序列号，我想使用该软件");
		b1.setBackground(new Color(234, 241, 247));
		b1.setSelected(true);
		b2 = new JRadioButton("我想试用该软件");
		jl.add(b1);
		b1.setBounds(40, 60, 260, 30);
		jl.add(b2);
		b2.setBackground(new Color(234, 241, 247));
		b2.setBounds(40, 100, 200, 30);
		bg.add(b1);
		bg.add(b2);
		b3 = new JButton("【继续】");
		jl.add(b3);
		b3.setBounds(200, 180, 90, 20);
		jl.add(zhuce);
		zhuce.setBounds(50, 130, 200, 30);
		int days = 30;//试用允许使用的天数
		final SimpleDateFormat dateFormate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final DateFormat df=DateFormat.getDateInstance();
			//判断是否首次使用该软件
			if(ReadWriteRegisty.ReadValue("myprograme", "programe1").equals("注册表中没有该值")){
				//ZHUCE=-1代表试用时间满，ZHUCE=-2代表已注册。其余数字代表该软件使用的天数。
				String[] key={"programe1","programe2","programe3"};//节点第一个字母不要设置为大写，否则在注册表中会添加一个“\”字样。
				String d=dateFormate.format(new Date());
				Object[] value={d,d,"sign1"};
				//注册表中programe1键值代表首次运行本软件时间
				//Programe2键值代表当前运行软件时间
				//Programe3代表当前软件状态，sign1代表软件在试用期中，sign2代表软件试用期满，sign3代表已经注册，sign4代表注册期满。
				ReadWriteRegisty.WriteValue("myprograme", key,value);
			}
			else{
				try {
					//如果修改系统日期，希望继续使用该软件
					if(new Date().compareTo(df.parse(ReadWriteRegisty.ReadValue("myprograme", "programe2")))<=0){//比较两个时间的先后
						JOptionPane.showMessageDialog(mainFrame.this, "软件已经试用到期，如果您想继续使用，请购买序列号进行注册使用");
						ReadWriteRegisty.WriteValue("myprograme", "programe3", "sign2");
						ZHUCE=-1;
						
					}
				} catch (ParseException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
				if(ZHUCE>0||ReadWriteRegisty.ReadValue("myprograme", "programe3").equals("sign1")){
					try {
						ReadWriteRegisty.WriteValue("myprograme", "programe2", dateFormate.format(new Date()));
					
						SimpleDateFormat df2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date date_start=df2.parse(new Date().toLocaleString()); 
						Date date_end=df2.parse(ReadWriteRegisty.ReadValue("myprograme", "programe1"));
						Calendar cal_start=Calendar.getInstance();
						cal_start.setTime(date_start);
						Calendar cal_end=Calendar.getInstance();
						cal_end.setTime(date_end);
						//System.out.println("两个时间的相差天数为："+getDifferenceDays(cal_start,cal_end));

						ZHUCE=days-getDifferenceDays(cal_start,cal_end);
					
					} catch (ParseException e) {
				// TODO 自动生成 catch 块
						e.printStackTrace();
					}
				}
			}
			if(ZHUCE<=0||ReadWriteRegisty.ReadValue("myprograme", "programe3").equals("sign2")){
				ZHUCE=0;
				//ReadWriteRegisty.WriteValue("myprograme", "programe3", "sign2");
			}
			zhuce.setText("（剩余"+ZHUCE+"天）");
		

		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// TODO 自动生成方法存根
				if (b1.isSelected()) {
					// TODO 自动生成方法存根
					setVisible(false);
					new RegisterFrame().setVisible(true);
					
				}
				if (b2.isSelected()) {

					if(ReadWriteRegisty.ReadValue("myprograme","programe3").equals("sign2")){
						JOptionPane.showMessageDialog(mainFrame.this, "软件已经试用到期，如果您想继续使用，请购买序列号进行注册使用");
						return;
					}
					if(ZHUCE==0){
						JOptionPane.showMessageDialog(mainFrame.this, "软件已经试用到期，如果您想继续使用，请购买序列号进行注册使用");
						ReadWriteRegisty.WriteValue("myprograme", "programe3", "sign2");
						return;
					}
				}
			}
		});
		rightPanel.add(jl, new Integer(Integer.MIN_VALUE));
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(null);
		leftPanel.setBackground(Color.WHITE);
		JLabel image = new JLabel();
		ImageIcon icon2 = CreatecdIcon.add("00002.jpg");
		image.setIcon(icon2);
		JLabel letter1 = new JLabel();
		letter1
				.setText("<html><i>关于注册</i><br>注册需要用户名与注册码，用户可以在软件包装处进行寻找。<br><br>如果您不想激活该软件，可以在试用状态下使用，并且可以在试用期内随时激活本软件。</html>");
		JLabel letter2 = new JLabel();
		leftPanel.add(image);
		image.setBounds(20, 20, 130, 100);
		leftPanel.add(letter1);
		letter1.setBounds(10, 120, 130, 200);
		leftPanel.add(letter2);
		letter2.setBounds(10, 200, 130, 50);

		splitPane.setLeftComponent(leftPanel);
		splitPane.setRightComponent(rightPanel);
		getContentPane().add(splitPane);
		setSize(new Dimension(570, 400));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("软件注册模块");
	}
	public static int getDifferenceDays(Calendar d1,Calendar d2){
		if(d1.after(d2)){
			java.util.Calendar swap=d1;
			d1=d2;
			d2=swap;
		}
		int days=d2.get(java.util.Calendar.DAY_OF_YEAR)-d1.get(java.util.Calendar.DAY_OF_YEAR);
		int y2=d2.get(java.util.Calendar.YEAR);
		if(d1.get(java.util.Calendar.YEAR)!=y2){
			do{
				days+=d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
				d1.add(java.util.Calendar.YEAR, 1);
			}while(d1.get(java.util.Calendar.YEAR)!=y2);
		}
		return days;
	}

}
