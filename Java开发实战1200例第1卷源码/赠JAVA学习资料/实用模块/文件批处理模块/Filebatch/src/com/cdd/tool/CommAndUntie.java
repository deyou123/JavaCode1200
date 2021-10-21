package com.cdd.tool;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;
import java.util.*;
import javax.swing.JComboBox;
import com.fileBath.util.*;
/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class CommAndUntie extends JFrame {
	static File cunDir=new File("C:\\");
	static File[] fl;
	static File[] chuanFile;
	static  File fenGeFile;
	static  File cunMuLu;
	private JFrame jFrame = null; 
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JLabel jLabel = null;
	private JTextField jTextField = null;
	private JButton jButton = null;
	private JLabel jLabel1 = null;
	private JTextField jTextField1 = null;
	private JButton jButton1 = null;
	private JLabel sizeMessagejLabel = null;
	private JTextField commSize = null;
	private JLabel fileSizejLabel = null;
	private JButton commjButton = null;
	private JButton jButton3 = null;
	private JButton openFilejButton = null;
	private JButton jButton5 = null;
	private JFileChooser jFileChooser = null;
	private JFileChooser jFileChooser1 = null;
	private JScrollPane jScrollPane = null;
	private JTextArea jTextArea = null;
	private JButton exitjButton = null;
	private JLabel jLabel41 = null;
	private JComboBox jComboBox = null;
	private JTextField pathTextField = null;
	private JButton jButton7 = null;
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame("分割/合并模块");
			jFrame.setSize(new Dimension(361, 274));
			jFrame.setContentPane(getJTabbedPane());
			jFrame.setVisible(true);
			jFrame.setLocation(300,280);
			jFrame.setDefaultCloseOperation(jFrame.HIDE_ON_CLOSE);
		}
		return jFrame;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("分割", getJPanel());
			jTabbedPane.addTab("合并", getJPanel1());
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			fileSizejLabel = new JLabel();
			fileSizejLabel.setBounds(new Rectangle(283, 106, 51, 18));
			fileSizejLabel.setText("MB");
			sizeMessagejLabel = new JLabel();
			sizeMessagejLabel.setBounds(new Rectangle(11, 103, 87, 28));
			sizeMessagejLabel.setText("分割大小");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(11, 60, 83, 28));
			jLabel1.setText("存储目录");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("源文件");
			jLabel.setBounds(new Rectangle(11, 16, 76, 33));
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(jLabel, gridBagConstraints);
			jPanel.add(getJTextField(), null);
			jPanel.add(getJButton(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(getJTextField1(), null);
			jPanel.add(getJButton1(), null);
			jPanel.add(sizeMessagejLabel, null);
			jPanel.add(getJTextField2(), null);
			jPanel.add(fileSizejLabel, null);
			jPanel.add(getJButton2(), null);
			jPanel.add(getJButton3(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(getopenFilejButton(), null);
			jPanel1.add(getJButton5(), null);
			jPanel1.add(getJScrollPane(), null);
			jPanel1.add(getJTextField3(), null);
			jPanel1.add(getJButton7(), null);
			
		}
		return jPanel1;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(105, 16, 165, 25));
		}
		return jTextField;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton("浏览");
			jButton.setBounds(new Rectangle(285, 16, 65, 25));
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJFileChooser();
					jFileChooser.setMultiSelectionEnabled(false);
					jFileChooser.setFileSelectionMode(0);
					int state=jFileChooser.showOpenDialog(null);
					System.out.println("state="+state);
					if(state==0){
						fenGeFile=jFileChooser.getSelectedFile();
						jTextField.setText(fenGeFile.getAbsolutePath());
					}		
		  					
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setBounds(new Rectangle(105, 60, 165, 25));
		}
		return jTextField1;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton("浏览");
			jButton1.setBounds(new Rectangle(285, 60, 65, 30));
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJFileChooser();
					jFileChooser.setMultiSelectionEnabled(false);
					jFileChooser.setFileSelectionMode(1);
					int state=jFileChooser.showSaveDialog(null);
					if(state==0){
						cunMuLu=jFileChooser.getSelectedFile();
						jTextField1.setText(cunMuLu.getAbsolutePath());
					}
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jTextField2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField2() {
		if (commSize == null) {
			commSize = new JTextField(5);
			commSize.setBounds(new Rectangle(105, 104, 165, 25));
		}
		return commSize;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
private JButton getJButton2() {
	if (commjButton == null) {
		commjButton = new JButton("分割");
		commjButton.setBounds(new Rectangle(60, 152, 80, 28));
		commjButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				int a = 0;
				if ((fenGeFile == null) || (cunMuLu == null)) {
					JOptionPane.showMessageDialog(jFrame, "请将文件信息添加完整",
							"消息对话框", JOptionPane.WARNING_MESSAGE);
					return;
				}
				try {
					a = Integer.parseInt(commSize.getText());
					FengGeHeBing fgb = new FengGeHeBing();
					fgb.fenGe(fenGeFile, cunMuLu, a);
					JOptionPane.showMessageDialog(jFrame, "文件分割成功",
							"消息对话框", JOptionPane.WARNING_MESSAGE);
				} catch (NumberFormatException ee) {
					JOptionPane.showMessageDialog(jFrame, 
							"请输入分割文件的大小为数字", "信息对话框", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
	return commjButton;
}

	/**
	 * This method initializes jButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton("退出");
			jButton3.setBounds(new Rectangle(191, 152, 85,28));
			jButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jFrame.dispose();
				}
			});
		}
		return jButton3;
	}
	
	/**
	 * This method initializes jButton4	
	 * 	
	 * @return javax.swing.JButton	
	 */
private JButton getopenFilejButton() {
	if (openFilejButton == null) {
		openFilejButton = new JButton("打开");
		openFilejButton.setBounds(new Rectangle(8, 155, 85, 35));
		openFilejButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				getJFileChooser1();
				jFileChooser1.setFileSelectionMode(0);
				jFileChooser1.setMultiSelectionEnabled(true);
				int state=jFileChooser1.showOpenDialog(null);
				String s="";
				if(state==0){
					fl=jFileChooser1.getSelectedFiles();
					int[] st=new int[fl.length];
					for(int i=0;i<fl.length;i++){
						s=s+fl[i].getName()+"\r\n";
						jTextArea.setText(s);
					}
					Arrays.sort(st);						
					chuanFile=new File[st.length];
					for(int i=0;i<st.length;i++){
						String strPath = fl[i].getAbsolutePath();
						int fristPath = strPath.indexOf(".");
						int lasePath = strPath.lastIndexOf(".");
						String newPath = strPath.substring(fristPath, lasePath);
						chuanFile[i]=new File(fl[i].getParent()+"\\"+(i+1)+newPath+".tem");
					}
				}
			}
		});
	}
	return openFilejButton;
}

	/**
	 * This method initializes jButton5	
	 * 	
	 * @return javax.swing.JButton	
	 */
private JButton getJButton5() {
	if (jButton5 == null) {
		jButton5 = new JButton("合并");
		jButton5.setBounds(new Rectangle(113, 155, 85, 35));
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				try{
				FengGeHeBing fgb = new FengGeHeBing();
				String substr  = null;
				for(int i = 0;i<chuanFile.length;i++){
					String str = chuanFile[i].getAbsolutePath();
					System.out.println("STR "+str);
					int strFrist = str.indexOf(".");
					int strLase = str.lastIndexOf(".");
					substr = str.substring(strFrist, strLase);						
				}					
				fgb.heBing(chuanFile,cunDir,substr);
				jTextArea.setForeground(Color.red);
			}
				catch (Exception ee) {						
			}
		}
	});
}
	return jButton5;
}

	/**
	 * @param args
	 */
	public CommAndUntie(){
		getJFrame();
	}
	/**
	 * This method initializes jFileChooser	
	 * 	
	 * @return javax.swing.JFileChooser	
	 */
	private JFileChooser getJFileChooser() {
		if (jFileChooser == null) {
			jFileChooser = new JFileChooser();
			jFileChooser.setBounds(new Rectangle(5, 204, 500, 326));
		}
		return jFileChooser;
	}

	/**
	 * This method initializes jFileChooser1	
	 * 	
	 * @return javax.swing.JFileChooser	
	 */
	private JFileChooser getJFileChooser1() {
		if (jFileChooser1 == null) {
			jFileChooser1 = new JFileChooser();
			jFileChooser1.setFileSelectionMode(0);
			jFileChooser1.setBounds(new Rectangle(5, 193, 500, 326));
		}
		return jFileChooser1;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane.setBounds(new Rectangle(15, 34, 325, 105));
			jScrollPane.setViewportView(getJTextArea());
			
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
		}
		return jTextArea;
	}


	/**
	 * This method initializes jTextField3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField3() {
		if (pathTextField == null) {
			pathTextField = new JTextField();
			pathTextField.setText(cunDir.getAbsolutePath());
			pathTextField.setBounds(new Rectangle(110, 5, 50, 22));
		}
		return pathTextField;
	}

	/**
	 * This method initializes jButton7	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton7() {
		if (jButton7 == null) {
			jButton7 = new JButton();
			jButton7.setBounds(new Rectangle(1, 6, 105, 18));
			jButton7.setText("存放目录：");
			jButton7.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJFileChooser1();
					jFileChooser1.setFileSelectionMode(1);
					jFileChooser1.setMultiSelectionEnabled(false);
					int state=jFileChooser1.showSaveDialog(null);
					if(state==0){
						cunDir=jFileChooser1.getSelectedFile();
						pathTextField.setText(cunDir.getAbsolutePath());
					}
				}
			});
		}
		return jButton7;
	}
	
	public static void main(String[] args) {
		CommAndUntie comm = new CommAndUntie();
	}
}
