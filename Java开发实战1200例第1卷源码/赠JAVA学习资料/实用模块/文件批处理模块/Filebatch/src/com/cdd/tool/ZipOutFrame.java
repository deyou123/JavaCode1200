/*
 * ZipFrame.java
 *
 * Created on 2008年7月23日, 下午2:29
 */

package com.cdd.tool;

import javax.swing.JOptionPane;

import com.fileBath.util.MyFileChooser;
/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class ZipOutFrame extends javax.swing.JFrame  implements Runnable{

    /** Creates new form ZipFrame */
    public ZipOutFrame() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        messageLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        frontjLabel = new javax.swing.JLabel();
        fronzipTextField = new javax.swing.JTextField();
        liulanjButton = new javax.swing.JButton();
        saveLabel = new javax.swing.JLabel();
        saveTextField = new javax.swing.JTextField();
        pathButton1 = new javax.swing.JButton();
        zipinjButton = new javax.swing.JButton();
        exitjButton = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        messageLabel.setText("文件解压缩板块可根据用户选择对象进行解压缩：");

        frontjLabel.setText("压缩文件：");

        liulanjButton.setText("浏览");
        liulanjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                liulanjButtonActionPerformed(evt);
            }
        });

        saveLabel.setText("保存地址：");

        pathButton1.setText("浏览");
        pathButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pathButton1ActionPerformed(evt);
            }
        });

        zipinjButton.setText("解压");
        zipinjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zipinjButtonActionPerformed(evt);
            }
        });

        exitjButton.setText("退出");
        exitjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(saveLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(frontjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fronzipTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saveTextField)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(zipinjButton)
                                .addGap(48, 48, 48)
                                .addComponent(exitjButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pathButton1, 0, 0, Short.MAX_VALUE)
                            .addComponent(liulanjButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(29, 29, 29)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(liulanjButton)
                            .addComponent(fronzipTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(frontjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pathButton1)
                            .addComponent(saveTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(exitjButton)
                            .addComponent(zipinjButton))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>      
private void exitjButtonActionPerformed(java.awt.event.ActionEvent evt) {
            dispose();
}

private void zipinjButtonActionPerformed(java.awt.event.ActionEvent evt) {
	try {
		if(fronzipTextField.getText().equals("")||saveTextField.getText().equals("")){
			 int index = JOptionPane.showConfirmDialog(this, "请将信息添加完整", "消息对话框", JOptionPane.YES_NO_OPTION);
			 if(index == JOptionPane.YES_OPTION){			 
				 return;
			 }
			 if(index == JOptionPane.NO_OPTION){
				 return;
			 }
		}
			Thread thread = new Thread(this);
			thread.start();
			} catch (Exception e) {
		e.printStackTrace();
	}
}

private void liulanjButtonActionPerformed(java.awt.event.ActionEvent evt) {
	MyFileChooser myfile = new MyFileChooser();
	myfile.getOpenFileChooser();
	String strPath = myfile.getFolderPath();
	if(strPath.equals("")){
		return;
	}
	if(!strPath.endsWith(".zip")){
		int index = JOptionPane.showConfirmDialog(this, 
				"请选择zip文件进行解压缩", "消息对话框", JOptionPane.YES_NO_OPTION);
		if(index == JOptionPane.YES_OPTION){
			fronzipTextField.setText("");
			fronzipTextField.requestFocus();			
		}
		if(index == JOptionPane.NO_OPTION){
			return;
		}
	}
	else {
		fronzipTextField.setText(strPath);
	}
}

private void pathButton1ActionPerformed(java.awt.event.ActionEvent evt) {
	MyFileChooser myfile = new MyFileChooser();
	myfile.getFolder();
	String strPath = myfile.getFolderPath();
	saveTextField.setText(strPath);
}
    private javax.swing.JButton exitjButton;
    private javax.swing.JLabel frontjLabel;
    private javax.swing.JTextField fronzipTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton liulanjButton;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JButton pathButton1;
    private javax.swing.JLabel saveLabel;
    private javax.swing.JTextField saveTextField;
    private javax.swing.JButton zipinjButton;
public void run() {
		MyOutZipMessage outZipMessage = new MyOutZipMessage();
		outZipMessage.setVisible(true);
	    com.cdd.uitl.TestZip testzip = new com.cdd.uitl.TestZip();	
	    testzip.unzip(fronzipTextField.getText(), saveTextField.getText());	
	    outZipMessage.setVisible(false);
		JOptionPane.showMessageDialog(this, "文件解压完毕提示", "消息对话框", JOptionPane.WARNING_MESSAGE);				
}
}
