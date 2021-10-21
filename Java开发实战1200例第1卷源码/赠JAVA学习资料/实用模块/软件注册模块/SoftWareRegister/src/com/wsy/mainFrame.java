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
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class mainFrame extends JFrame {

	/**
	 * @param args
	 */
	int ZHUCE=30;
	JLabel zhuce=new JLabel();
	public static void main(String[] args) {
		// TODO �Զ����ɷ������
		new mainFrame().setVisible(true);
	}

	public mainFrame() {
		super();
		try {
			UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO �Զ����� catch ��
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
		b1 = new JRadioButton("����һ�����кţ�����ʹ�ø����");
		b1.setBackground(new Color(234, 241, 247));
		b1.setSelected(true);
		b2 = new JRadioButton("�������ø����");
		jl.add(b1);
		b1.setBounds(40, 60, 260, 30);
		jl.add(b2);
		b2.setBackground(new Color(234, 241, 247));
		b2.setBounds(40, 100, 200, 30);
		bg.add(b1);
		bg.add(b2);
		b3 = new JButton("��������");
		jl.add(b3);
		b3.setBounds(200, 180, 90, 20);
		jl.add(zhuce);
		zhuce.setBounds(50, 130, 200, 30);
		int days = 30;//��������ʹ�õ�����
		final SimpleDateFormat dateFormate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final DateFormat df=DateFormat.getDateInstance();
			//�ж��Ƿ��״�ʹ�ø����
			if(ReadWriteRegisty.ReadValue("myprograme", "programe1").equals("ע�����û�и�ֵ")){
				//ZHUCE=-1��������ʱ������ZHUCE=-2������ע�ᡣ�������ִ�������ʹ�õ�������
				String[] key={"programe1","programe2","programe3"};//�ڵ��һ����ĸ��Ҫ����Ϊ��д��������ע����л����һ����\��������
				String d=dateFormate.format(new Date());
				Object[] value={d,d,"sign1"};
				//ע�����programe1��ֵ�����״����б����ʱ��
				//Programe2��ֵ����ǰ�������ʱ��
				//Programe3����ǰ���״̬��sign1����������������У�sign2�����������������sign3�����Ѿ�ע�ᣬsign4����ע��������
				ReadWriteRegisty.WriteValue("myprograme", key,value);
			}
			else{
				try {
					//����޸�ϵͳ���ڣ�ϣ������ʹ�ø����
					if(new Date().compareTo(df.parse(ReadWriteRegisty.ReadValue("myprograme", "programe2")))<=0){//�Ƚ�����ʱ����Ⱥ�
						JOptionPane.showMessageDialog(mainFrame.this, "����Ѿ����õ��ڣ�����������ʹ�ã��빺�����кŽ���ע��ʹ��");
						ReadWriteRegisty.WriteValue("myprograme", "programe3", "sign2");
						ZHUCE=-1;
						
					}
				} catch (ParseException e) {
					// TODO �Զ����� catch ��
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
						//System.out.println("����ʱ����������Ϊ��"+getDifferenceDays(cal_start,cal_end));

						ZHUCE=days-getDifferenceDays(cal_start,cal_end);
					
					} catch (ParseException e) {
				// TODO �Զ����� catch ��
						e.printStackTrace();
					}
				}
			}
			if(ZHUCE<=0||ReadWriteRegisty.ReadValue("myprograme", "programe3").equals("sign2")){
				ZHUCE=0;
				//ReadWriteRegisty.WriteValue("myprograme", "programe3", "sign2");
			}
			zhuce.setText("��ʣ��"+ZHUCE+"�죩");
		

		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// TODO �Զ����ɷ������
				if (b1.isSelected()) {
					// TODO �Զ����ɷ������
					setVisible(false);
					new RegisterFrame().setVisible(true);
					
				}
				if (b2.isSelected()) {

					if(ReadWriteRegisty.ReadValue("myprograme","programe3").equals("sign2")){
						JOptionPane.showMessageDialog(mainFrame.this, "����Ѿ����õ��ڣ�����������ʹ�ã��빺�����кŽ���ע��ʹ��");
						return;
					}
					if(ZHUCE==0){
						JOptionPane.showMessageDialog(mainFrame.this, "����Ѿ����õ��ڣ�����������ʹ�ã��빺�����кŽ���ע��ʹ��");
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
				.setText("<html><i>����ע��</i><br>ע����Ҫ�û�����ע���룬�û������������װ������Ѱ�ҡ�<br><br>��������뼤������������������״̬��ʹ�ã����ҿ���������������ʱ��������</html>");
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
		setTitle("���ע��ģ��");
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
