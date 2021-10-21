package com.wsy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.KeyboardFocusManager;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.util.CreatecdIcon;
import com.util.RegisterMark;
/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class RegisterTradeMark extends JFrame{

	private final class PopAction extends MouseAdapter {
		private final JPopupMenu popup;

		private PopAction(JPopupMenu popup) {
			this.popup = popup;
		}

		public void mouseReleased(MouseEvent arg0) {
			// TODO 自动生成方法存根
			super.mouseReleased(arg0);
			if(arg0.isPopupTrigger()){
				popup.show(arg0.getComponent(), arg0.getX(), arg0.getY());
			}
		}
	}
	private JTextField t4;
	private JTextField t3;
	private JTextField t2;
	private JTextField t1;
	private JTextField userName;
	/**
	 * @param args
	 */
	private Clipboard clipbd=getToolkit().getSystemClipboard();
	public static void main(String[] args) {
		// TODO 自动生成方法存根
		new RegisterTradeMark().setVisible(true);
	}
	public RegisterTradeMark() {
		super();
		getContentPane().setBackground(new Color(240, 255, 255));
		try {
			UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		setTitle("注册机");
		getContentPane().setLayout(null);
		
		final JLabel label = new JLabel();
		label.setBounds(10, 26, 52, 18);
		label.setText("用户名：");
		getContentPane().add(label);

		userName = new JTextField();
		userName.setBounds(68, 24, 147, 22);
		getContentPane().add(userName);

		final JLabel label_1 = new JLabel();
		label_1.setBounds(10, 66, 52, 18);
		label_1.setText("注册码：");
		getContentPane().add(label_1);

		final JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 255));
		panel.setLayout(null);
		panel.setBounds(60, 50, 261, 68);
		getContentPane().add(panel);

		t1 = new JTextField();
		t1.setBounds(10, 21, 48, 22);
		panel.add(t1);

		t2 = new JTextField();
		t2.setBounds(65, 21, 48, 22);
		panel.add(t2);

		t3 = new JTextField();
		t3.setBounds(119, 21, 48, 22);
		panel.add(t3);

		t4 = new JTextField();
		t4.setBounds(173, 21, 49, 22);
		panel.add(t4);
		final JPopupMenu popup=new JPopupMenu();
		JMenuItem itemcut=new JMenuItem("Cut");
		popup.add(itemcut);
		popup.addSeparator();
		JMenuItem itemcopy=new JMenuItem("Copy");
		popup.add(itemcopy);
		popup.addSeparator();
		JMenuItem itempaste=new JMenuItem("Paste");
		popup.add(itempaste);
		t1.addMouseListener(new PopAction(popup));
		t2.addMouseListener(new PopAction(popup));
		t3.addMouseListener(new PopAction(popup));
		t4.addMouseListener(new PopAction(popup));
		//设置整个页面组件焦点从后向前
		this.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
		itemcut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成方法存根
				t1.selectAll();
				t2.selectAll();
				t3.selectAll();
				t4.selectAll();
				String selectT1=t1.getText().trim();
				String selectT2=t2.getText().trim();
				String selectT3=t3.getText().trim();
				String selectT4=t4.getText().trim();
				if(selectT1==null||selectT2==null||selectT3==null||selectT4==null)
					return;
				String selection=selectT1.concat("-").concat(selectT2).concat("-").concat(selectT3).concat("-").concat(selectT4);
				StringSelection clipString=new StringSelection(selection);
				clipbd.setContents(clipString, clipString);
				t1.replaceSelection("");
				t2.replaceSelection("");
				t3.replaceSelection("");
				t4.replaceSelection("");
			}
		});
		itemcopy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成方法存根
				panel.requestFocus();
				System.out.println(panel.requestFocusInWindow());
				t1.selectAll();
				t2.selectAll();
				t3.selectAll();
				t4.selectAll();
				String selectT1=t1.getText().trim();
				String selectT2=t2.getText().trim();
				String selectT3=t3.getText().trim();
				String selectT4=t4.getText().trim();
				if(selectT1==null||selectT2==null||selectT3==null||selectT4==null)
					return;
				String selection=selectT1.concat("-").concat(selectT2).concat("-").concat(selectT3).concat("-").concat(selectT4);
				StringSelection clipString=new StringSelection(selection);
				clipbd.setContents(clipString, clipString);
			}
		});
		
		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				String userNameT=userName.getText().trim();
				String s=RegisterMark.getRegister(userNameT);
				String a[]=s.split("-");
				t1.setText(a[0]);
				t2.setText(a[1]);
				t3.setText(a[2]);
				t4.setText(a[3]);
			}
		});
		itempaste.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成方法存根
				Transferable clipData=clipbd.getContents(RegisterTradeMark.this);
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
						JOptionPane.showMessageDialog(RegisterTradeMark.this, "您粘贴的注册码格式不正确，请重新粘贴");
					}
					popup.setVisible(false);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		button.setText("生成注册码");
		button.setBounds(93, 135, 106, 28);
		getContentPane().add(button);
		setSize(new Dimension(350,200));
		setIconImage(CreatecdIcon.add("ico.jpg").getImage());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
}
