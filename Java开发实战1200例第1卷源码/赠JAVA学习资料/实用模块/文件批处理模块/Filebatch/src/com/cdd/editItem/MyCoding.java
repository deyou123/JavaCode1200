/*
 * MyCoding.java
 *
 * Created on 2008年7月25日, 下午12:53
 */

package com.cdd.editItem;

import javax.swing.JOptionPane;
import com.cdd.uitl.FileHeald;
import com.fileBath.util.MyFileChooser;

/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class MyCoding extends javax.swing.JFrame {

    /** Creates new form MyCoding */
    public MyCoding() {
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

        jPanel1 = new javax.swing.JPanel();
        messagejLabel = new javax.swing.JLabel();
        filePathTextField = new javax.swing.JTextField();
        fileButton = new javax.swing.JButton();
        fileSaveTextField = new javax.swing.JTextField();
        filesaveButton = new javax.swing.JButton();
        changLabel = new javax.swing.JLabel();
        endingComboBox = new javax.swing.JComboBox();
        okjButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        shuomingLabel = new javax.swing.JLabel();
        fristjLabel = new javax.swing.JLabel();
        secondLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        memjLabel = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        messagejLabel.setText("请选择要转换格式的文件路径：");

        fileButton.setText("选择");
        fileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileButtonActionPerformed(evt);
            }
        });

        filesaveButton.setText("选择");
        filesaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filesaveButtonActionPerformed(evt);
            }
        });

        changLabel.setText("转变后编码：");
        endingComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "GBK", "GB2312", "UTF-8", "Big5","ISO8859-1"}));
        endingComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endingComboBoxActionPerformed(evt);
            }
        });

        okjButton.setText("转换");
        okjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okjButtonActionPerformed(evt);
            }
        });

        shuomingLabel.setText("说明：");

        fristjLabel.setText("1、虽然不对原文件进行修改，但仍然建议首先备份文件内容。");

        secondLabel.setText("2、文件转码后可能会出现乱码问题。");

        jLabel1.setText("3、转码后的文件以原文件名加“change”结束。");

        memjLabel.setText("扩展名：");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { ".doc", ".txt",".java" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fristjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                    .addComponent(messagejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(changLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(endingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(memjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fileSaveTextField)
                            .addComponent(filePathTextField))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(filesaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(okjButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE))
                    .addComponent(shuomingLabel)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(secondLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(messagejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filePathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileSaveTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filesaveButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(memjLabel)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(okjButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shuomingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fristjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(secondLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

private void fileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileButtonActionPerformed
	MyFileChooser myfile = new MyFileChooser();
	myfile.getFolder();
	String strPath = myfile.getFolderPath();
	filePathTextField.setText(strPath);
}

private void filesaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filesaveButtonActionPerformed
	MyFileChooser myfile = new MyFileChooser();
	myfile.getFolder();
	String strPath = myfile.getFolderPath();
	fileSaveTextField.setText(strPath);
}

private void okjButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_okjButtonActionPerformed
	if (filePathTextField.getText().equals("")) {
		int n = JOptionPane.showConfirmDialog(this, "没有指定文件路径", "确认对话框",
				JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			filePathTextField.requestFocus();
			return;
		}
		if (n == JOptionPane.NO_OPTION) {
			return;
		}
	}
	if (fileSaveTextField.getText().equals("")) {
		int n = JOptionPane.showConfirmDialog(this, "没有指定文件保存路径", "确认对话框",
				JOptionPane.YES_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			filePathTextField.requestFocus();
			return;
		}
		if (n == JOptionPane.NO_OPTION) {
			return;
		}
	}
	FileHeald fileheald = new FileHeald();
	java.util.List list = fileheald
			.getFileList(filePathTextField.getText());
	int bi = 1;
	if (!list.isEmpty() && (list.size() > 0)) {
		for (int i = 0; i < list.size(); i++) {
			java.io.File file = (java.io.File) list.get(i);
			if (file.getAbsoluteFile().toString().endsWith(
					jComboBox1.getSelectedItem().toString())) {
				String filePath = file.getAbsolutePath(); // 获取文件完整路径
				int index1 = filePath.lastIndexOf("\\"); // 获取最后一次出现“\\”索引位置
				int index2 = filePath.lastIndexOf("."); // 获取最后一次出现“.”索引位置
				String fileName = filePath.substring(index1, index2); // 获取文件名
				String backFile = filePath.substring(index2, filePath
						.length());
				fileheald.setEnd(filePath, fileSaveTextField.getText()
						+ fileName + "change" + backFile, endingComboBox
						.getSelectedItem().toString());
				bi = 2;
			}
		}
	}
	if (bi == 1) {
		JOptionPane.showMessageDialog(this, "没有符合条件的文件", "信息对话框",
				JOptionPane.WARNING_MESSAGE);
		return;
	}
	JOptionPane.showMessageDialog(this, "文件编码格式转换完毕", "提示对话框",
			JOptionPane.WARNING_MESSAGE);
}

private void endingComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endingComboBoxActionPerformed

}

private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

}

   // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel changLabel;
    private javax.swing.JComboBox endingComboBox;
    private javax.swing.JButton fileButton;
    private javax.swing.JTextField filePathTextField;
    private javax.swing.JTextField fileSaveTextField;
    private javax.swing.JButton filesaveButton;
    private javax.swing.JLabel fristjLabel;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel memjLabel;
    private javax.swing.JLabel messagejLabel;
    private javax.swing.JButton okjButton;
    private javax.swing.JLabel secondLabel;
    private javax.swing.JLabel shuomingLabel;
    // End of variables declaration//GEN-END:variables

}
