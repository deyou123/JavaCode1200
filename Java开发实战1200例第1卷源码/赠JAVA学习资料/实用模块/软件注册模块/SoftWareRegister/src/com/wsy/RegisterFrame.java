package com.wsy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.encrypt.decrypt;
import com.encrypt.encrypt;
import com.util.CreatecdIcon;
import com.util.ReadWriteRegisty;
import com.util.RegisterMark;
/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class RegisterFrame extends JFrame{
	mainFrame m=new mainFrame();
	static String s="";
	private Clipboard clipbd=getToolkit().getSystemClipboard();
	final JTextField t1,t2,t3,t4;
	JLabel jl ;
	JPanel JTextJP;
	public RegisterFrame() {
		// TODO 自动生成构造函数存根
		super();
		ButtonGroup bg=new ButtonGroup();
		JRadioButton b1,b2;
		JButton b3;
		final JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(150);
		
		JPanel rightPanel=new JPanel();
		
		
		ImageIcon icon = CreatecdIcon.add("11.jpg");
        jl= new JLabel();
        jl.setLayout(null);
        JLabel j1=new JLabel("<html><font=14>请输入您的用户名与序列号</font></html>");
        jl.add(j1);
        j1.setBounds(10, 20,160 , 30);
        jl.setIcon(icon);

        JLabel userNameJ=new JLabel("用户名：");
        final JTextField userNameT=new JTextField("wsy",40);
        jl.add(userNameJ);
        jl.add(userNameT);
        userNameJ.setBounds(80, 80, 100, 25);
        userNameT.setBounds(200, 80, 100, 25);
        t1=new JTextField(10);
        t2=new JTextField(10);
        t3=new JTextField(10);
        t4=new JTextField(10);
        JTextJP=new JPanel();
        JTextJP.setBackground(new Color(234, 241, 247));
        JTextJP.setLayout(null);
        JTextJP.add(t1);
        JTextJP.add(t2);
        JTextJP.add(t3);
        JTextJP.add(t4);
        t1.setBounds(10, 15, 50, 25);
        t2.setBounds(60,15, 50, 25);
        t3.setBounds(110,15, 50, 25);
        t4.setBounds(160,15, 50, 25);
        t1.addMouseListener(new JCopy());
        t2.addMouseListener(new JCopy());
        t3.addMouseListener(new JCopy());
        t4.addMouseListener(new JCopy());
        jl.add(JTextJP);
        JTextJP.setBounds(80, 130, 220, 50);
        JButton back=new JButton("【后退】");
        JButton forwards=new JButton("【前进】");
        jl.add(back);
        jl.add(forwards);
        back.setBounds(130, 200, 90, 20);
        forwards.setBounds(230, 200,90, 20);
        back.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent arg0) {
        		// TODO 自动生成方法存根
        		setVisible(false);
        		m.setVisible(true);
        	}
        });
        forwards.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent arg0) {
        		// TODO 自动生成方法存根
        		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        		s=userNameT.getText().trim();
        		String t1Text=t1.getText().trim();
        		String t2Text=t2.getText().trim();
        		String t3Text=t3.getText().trim();
        		String t4Text=t4.getText().trim();
        		if(t1Text.length()!=0&&t2Text.length()!=0&&t3Text.length()!=0&&t4Text.length()!=0){
        			if(s!=null){
        				String[] a=RegisterMark.getRegister(s).split("-");
        				if(!a[0].equals(t1Text)||!a[1].equals(t2Text)||!a[2].equals(t3Text)||!a[3].equals(t4Text)){
        					JOptionPane.showMessageDialog(RegisterFrame.this, "用户名与注册码不匹配，请确定后重新输入");
        					t1.setText("");
        					t2.setText("");
        					t3.setText("");
        					t4.setText("");
        					return;
        				}
        				else{
        					String register="";
        					for(int i=0;i<a.length;i++){
        						register+=a[i];
        					}
        					BigInteger codeStringBigint=new encrypt().Execencrypt(RegisterMark.getMAC(),register);
        					System.out.println(ReadWriteRegisty.ReadValue("myprograme", "register1").equals("注册表中没有该值"));
        					if(ReadWriteRegisty.ReadValue("myprograme", "register1").equals("注册表中没有该值")){
        						ReadWriteRegisty.WriteValue("myprograme", "register1", String.valueOf(codeStringBigint));
        						ReadWriteRegisty.WriteValue("myprograme", "registertime", sf.format(new Date()));
        						ReadWriteRegisty.WriteValue("myprograme", "registertimetest", sf.format(new Date()));
        						ReadWriteRegisty.WriteValue("myprograme", "programe3", "sign3");
        					}else{
        						
        						if(!new decrypt().Execdecrypt().contains(RegisterMark.getMAC())){
        							JOptionPane.showMessageDialog(RegisterFrame.this, "一个注册码只能在唯一一台计算机上使用");
        							return;
        						}
        						else{
        							
        							try {
        								if(new Date().compareTo(sf.parse(ReadWriteRegisty.ReadValue("myprograme", "registertimetest")))<0){
        									JOptionPane.showMessageDialog(RegisterFrame.this, "您的软件时间使用已经到期，如果希望继续使用，请注册");
        									ReadWriteRegisty.WriteValue("myprograme", "programe3", "sign1");
        									return;
        								}
        								else
        									ReadWriteRegisty.WriteValue("myprograme", "registertimetest", sf.format(new Date()));
        							} catch (ParseException e1) {
        								// TODO 自动生成 catch 块
        								e1.printStackTrace();
        							}
        								try{
        									Calendar cal_start=Calendar.getInstance();
        									Calendar cal_end=Calendar.getInstance();
        									cal_start.setTime(new Date());
        									cal_end.setTime(sf.parse(ReadWriteRegisty.ReadValue("myprograme", "programe1")));
        									System.out.println(cal_start.get(Calendar.YEAR));
        									System.out.println(cal_end.get(Calendar.YEAR));
        									if(new Date().before(sf.parse(ReadWriteRegisty.ReadValue("myprograme", "registertime")))){
        										JOptionPane.showMessageDialog(RegisterFrame.this, "您的软件时间使用已经到期，如果希望继续使用，请注册");
        										ReadWriteRegisty.WriteValue("myprograme", "programe3", "sign1");
        										return ;
        									}
        									if(cal_start.get(Calendar.YEAR)-cal_end.get(Calendar.YEAR)>=1||cal_start.get(Calendar.YEAR)-cal_end.get(Calendar.YEAR)<0){
        										ReadWriteRegisty.WriteValue("myprograme", "programe3", "sign2");
        										JOptionPane.showMessageDialog(RegisterFrame.this, "您的软件时间使用已经到期，如果希望继续使用，请注册");
        										ReadWriteRegisty.WriteValue("myprograme", "programe3", "sign1");
        										return;
        									}
        								}catch(Exception e){
        									e.printStackTrace();
        								}
        						}
        					}
        					JOptionPane.showMessageDialog(RegisterFrame.this, "软件注册成功，您可以使用该软件");
        				}
        			}
        		}else{
        			JOptionPane.showMessageDialog(RegisterFrame.this, "请填写注册码与用户名");
        		}
        	}
        });
        jl.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		
        rightPanel.add(jl,new Integer(Integer.MIN_VALUE));
		
		
		JPanel leftPanel=new JPanel();
		leftPanel.setLayout(null);
		leftPanel.setBackground(Color.WHITE);
		JLabel image=new JLabel();
		ImageIcon icon2=CreatecdIcon.add("00002.jpg");
		image.setIcon(icon2);
		JLabel letter1=new JLabel();
		letter1.setText("<html>请您填写序列号</html>");
		JLabel letter2=new JLabel();
		leftPanel.add(image);
		image.setBounds(20, 20, 130, 100);
		leftPanel.add(letter1);
		letter1.setBounds(10, 120, 130, 200);
		leftPanel.add(letter2);
		letter2.setBounds(10, 200, 130, 50);
		
		splitPane.setLeftComponent(leftPanel);
		splitPane.setRightComponent(rightPanel);
		getContentPane().add(splitPane);
		setSize(new Dimension(570,400));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		m.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("软件注册模块");
	}
	class JCopy extends MouseAdapter{
		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO 自动生成方法存根
			super.mouseDragged(arg0);
		}
		public void  mouseClicked(MouseEvent arg0) {
			// TODO 自动生成方法存根
			super.mouseClicked(arg0);
			//System.out.println(arg0.getButton());//鼠标按键getButton()方法返回1表示按了左键盘，2表示按了中键盘，3表示按了右键盘
			if(arg0.getButton()==3){
				final JPopupMenu popup=new JPopupMenu();
				JMenuItem itemcut=new JMenuItem("Cut");
				popup.add(itemcut);
				popup.addSeparator();
				JMenuItem itemcopy=new JMenuItem("Copy");
				popup.add(itemcopy);
				itemcopy.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						// TODO 自动生成方法存根
						t1.selectAll();
						t2.selectAll();
						t3.selectAll();
						t4.selectAll();
						JTextJP.requestFocus(true);
						System.out.println(JTextJP.requestFocusInWindow());
						String t1Selection=t1.getSelectedText();
						String t2Selection=t2.getSelectedText();
						String t3Selection=t3.getSelectedText();
						String t4Selection=t4.getSelectedText();
						popup.setVisible(false);
						String IntegrationString =t1Selection.concat("-").concat(t2Selection).concat("-").concat(t3Selection).concat("-").concat(t4Selection);
						if(IntegrationString==null)
							return;
						StringSelection clipString=new StringSelection(IntegrationString);
						clipbd.setContents(clipString, clipString);
						
					}
				});
				popup.addSeparator();
				JMenuItem itempaste=new JMenuItem("Paste");
				popup.add(itempaste);
				itempaste.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						// TODO 自动生成方法存根
						Transferable clipData=clipbd.getContents(RegisterFrame.this);
						try{
							String clipString=(String)clipData.getTransferData(DataFlavor.stringFlavor);
							if(clipString.contains("-")){
								String [] a=clipString.split("-");
								for(int i=0;i<a.length;i++)
								System.out.println(a[i]);
								t1.setText(a[0]);
								t2.setText(a[1]);
								t3.setText(a[2]);
								t4.setText(a[3]);
							}else{
								JOptionPane.showMessageDialog(RegisterFrame.this, "您粘贴的注册码格式不正确，请重新粘贴");
							}
							popup.setVisible(false);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				});
				popup.show(arg0.getComponent(), arg0.getX(), arg0.getY());
			}
		}
	}
}
