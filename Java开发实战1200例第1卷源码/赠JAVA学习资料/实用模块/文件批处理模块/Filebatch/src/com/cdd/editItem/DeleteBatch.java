package com.cdd.editItem;
import java.awt.Rectangle;
import java.awt.event.*;
import java.io.File;
import java.util.*;

import javax.swing.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import com.cdd.uitl.FileHeald;
import com.fileBath.util.MyFileChooser;

/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class DeleteBatch extends javax.swing.JFrame {
   
    /** Creates new form DeleteBatch */
    public DeleteBatch() {
        initComponents();
    }
	    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        contentjPanel = new javax.swing.JPanel();
        messageLabel = new javax.swing.JLabel();
        pathLabel = new javax.swing.JLabel();
        pathTextField = new javax.swing.JTextField();
        PathButton = new javax.swing.JButton();
        propeyLabel = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        dateLabel = new javax.swing.JLabel();
        fromTextField = new javax.swing.JTextField();
        toLabel = new javax.swing.JLabel();
        toTextField = new javax.swing.JTextField();
        listScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        deletejButton = new javax.swing.JButton();
        closeButton = new javax.swing.JToggleButton();
        fileName = new Vector(1);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        messageLabel.setText("批量删除部分，根据不同条件实现删除");
        pathLabel.setText("文件路径");
        pathTextField.setText("");
        PathButton.setText("浏览");
        PathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PathButtonActionPerformed(evt);
            }
        });
        propeyLabel.setText("扩展名：");
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel
        		(new String[] { " ",".doc", ".mp3", ".txt", ".avi", ".exe",".zip",".rar",".JPG",".tem"}));
        dateLabel.setText("日期");
        fromTextField.setText("");
        toLabel.setText("至：");
        toTextField.setText("");
fromTextField.addFocusListener(new java.awt.event.FocusListener(){
	public void focusGained(FocusEvent e) {				
		
	}
	public void focusLost(FocusEvent e) {
		fouseLisenter(e);				
	}        	
});
        toTextField.addFocusListener(new java.awt.event.FocusListener(){
			public void focusGained(FocusEvent e) {	
				
			}

			public void focusLost(FocusEvent e) {
				tofouseLisenter(e);				
			}        	
        });
        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listScrollPane1.setViewportView(jList1);
        deletejButton.setText("删除");
        deletejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletejButtonActionPerformed(evt);
            }
        });
jComboBox1.addItemListener(new java.awt.event.ItemListener(){
	public void itemStateChanged(ItemEvent e) {
		selectItem(e);				
	}        	
});
        closeButton.setText("关闭");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout contentjPanelLayout = new javax.swing.GroupLayout(contentjPanel);
        contentjPanel.setLayout(contentjPanelLayout);
        contentjPanelLayout.setHorizontalGroup(
            contentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentjPanelLayout.createSequentialGroup()
                .addGroup(contentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentjPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(listScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentjPanelLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentjPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(contentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(propeyLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pathLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                            .addComponent(dateLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentjPanelLayout.createSequentialGroup()
                                .addComponent(pathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(PathButton))
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(contentjPanelLayout.createSequentialGroup()
                                .addComponent(fromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(toLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(toTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(contentjPanelLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(deletejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
        );
        contentjPanelLayout.setVerticalGroup(
            contentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentjPanelLayout.createSequentialGroup()
                .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PathButton))
                .addGap(29, 29, 29)
                .addGroup(contentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(propeyLabel)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(contentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLabel)
                    .addComponent(fromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toLabel)
                    .addComponent(toTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(listScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deletejButton, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentjPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(contentjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

/**
 * @param evt
 */
private void selectItem(java.awt.event.ItemEvent evt) {
	FileHeald fileHeald = new FileHeald();
	if (!pathTextField.getText().equals("")) {
		List list = fileHeald.getFileList(pathTextField.getText());
		if (!list.isEmpty() && list.size() > 0) {
			fileName.removeAllElements();
			jList1.removeAll();
			jList1.revalidate();
			for (int i = 0; i < list.size(); i++) {
				File str = (File) list.get(i);
				String sPath = str.getAbsolutePath();
				if (sPath.endsWith(jComboBox1.getSelectedItem().toString())) {
					addList(str.getAbsolutePath());
				}
			}
		} else {
			fileName.removeAllElements();
			jList1.removeAll();
			jList1.revalidate();
			JOptionPane.showMessageDialog(this, "没有符合条件的文件", "信息对话框",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
    //文本框失去焦点触发事�件   
private void fouseLisenter(java.awt.event.FocusEvent fvt){
	String fromstr = fromTextField.getText();
	String fromt = "\\d{4}-\\d{2}-\\d{2}";		
	if(!fromstr.matches(fromt)&&!(fromTextField.getText().equals(""))){
		 int messageindex = JOptionPane.showConfirmDialog(this, 
				 "日期格式为：'2001-12-12'", "确定对话",JOptionPane.YES_NO_OPTION);
		 if(messageindex == JOptionPane.YES_OPTION){
			 fromTextField.setText("");
			 fromTextField.requestFocus();
		 }
		 else if(messageindex == JOptionPane.NO_OPTION){						
		 }
	}

else if(!pathTextField.getText().equals("")&&!(fromTextField.getText().equals(""))
		&&!(toTextField.getText().equals(""))){
	FileHeald fileHeald = new FileHeald();
	List list = fileHeald.getFileList(pathTextField.getText());
	if(!list.isEmpty() &&list.size()>0){
		fileName.removeAllElements();
		jList1.removeAll();
		jList1.revalidate();
		for(int i = 0 ;i<list.size();i++){
			File str = (File)list.get(i);
			String modifDate = new Date(str.lastModified())
			.toLocaleString();
			boolean boolfrom =fileHeald.isDateBefore(modifDate,toTextField.getText()+" 00:00:00");
			boolean boolto = fileHeald.isDateBefore(fromTextField.getText()+" 00:00:00",
					modifDate);	 
			String sPath = str.getAbsolutePath();	    				
			if(boolfrom == true && boolto == true ){
				jList1.removeAll();
				addList(str.getAbsolutePath());
			}	    					
		}    		
	}
	else{
		fileName.removeAllElements();
		jList1.removeAll();
		jList1.revalidate();
	}
}	
}
    //”结束�”“”“”“”“时间焦点监听事件
    private void tofouseLisenter(java.awt.event.FocusEvent fvt){
    	String fromstr = toTextField.getText();;
		String fromt = "\\d{4}-\\d{2}-\\d{2}";		
		if(!fromstr.matches(fromt)&&!(toTextField.getText().equals(""))){
			 int messageindex = JOptionPane.showConfirmDialog(this, 
					 "日期格式�为：2001-12-12'", "确定对话框�",JOptionPane.YES_NO_OPTION);
			 if(messageindex == JOptionPane.YES_OPTION){
				 toTextField.setText("");
				 toTextField.requestFocus();
				 
			 }
			 else if(messageindex == JOptionPane.NO_OPTION){			
			 }
		}		
		else if(!pathTextField.getText().equals("")&&!(fromTextField.getText().equals(""))
				&&!(toTextField.getText().equals(""))){
			FileHeald fileHeald = new FileHeald();
	    	List list = fileHeald.getFileList(pathTextField.getText());
	    	if(!list.isEmpty() &&list.size()>0){
	    		fileName.removeAllElements();
	    		jList1.removeAll();
	    		jList1.revalidate();
	    		for(int i = 0 ;i<list.size();i++){
	    			File str = (File)list.get(i);
	    			String modifDate = new Date(str.lastModified())
					.toLocaleString();
	    			boolean boolfrom =fileHeald.isDateBefore(modifDate,toTextField.getText()+" 00:00:00");
	    			boolean boolto = fileHeald.isDateBefore(fromTextField.getText()+" 00:00:00",
	    					modifDate);	 
	    			String sPath = str.getAbsolutePath();	    				
	    			if(boolfrom == true && boolto == true ){
	    				jList1.removeAll();
	    				addList(str.getAbsolutePath());
	    			}	    					
	    		}    		
	    	}
	    	else{
	    		fileName.removeAllElements();
	    		jList1.removeAll();
	    		jList1.revalidate();
	    	}
		}
    }
private void deletejButtonActionPerformed(java.awt.event.ActionEvent evt) {
   int messageindex = JOptionPane.showConfirmDialog(this, "确定要删除文件吗？�", "确定对话",JOptionPane.YES_NO_OPTION);
   if(messageindex == JOptionPane.YES_OPTION){
	if(fileName.size() >0){
            	 for(int i = 0;i<fileName.size();i++){
            		 String str =  fileName.get(i).toString();
            		 File file = new File(str);
            		 file.delete();            		 
            	 }
            	 validate();
             }
   }
   else if(messageindex == JOptionPane.NO_OPTION ){	   
   }
}
private void PathButtonActionPerformed(java.awt.event.ActionEvent evt) {
	MyFileChooser myfile = new MyFileChooser();
	myfile.getFolder();
	String strPath = myfile.getFolderPath();
	pathTextField.setText(strPath);
	FileHeald fileHeald = new FileHeald();
	if(!pathTextField.getText().equals("")){
	List list = fileHeald.getFileList(pathTextField.getText());
	fileName.removeAllElements();
	jList1.removeAll();
	jList1.revalidate();
	if(!list.isEmpty() &&list.size()>0){
		for(int i = 0 ;i<list.size();i++){
			File str = (File)list.get(i);
			String sPath = str.getAbsolutePath();
			if(sPath.endsWith(jComboBox1.getSelectedItem().toString())){
			addList(str.getAbsolutePath());
			}
		}
	}
	else{
		fileName.removeAllElements();
		jList1.removeAll();
		jList1.revalidate();
	}
	}
}
//向容器添加信息
private void addList(String vf) {	
	fileName.addElement(vf);
	jList1.setListData(fileName);
}
private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {
      dispose();
}
    
    /**
     * @param args the command line arguments
     */
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton PathButton;
    private javax.swing.JToggleButton closeButton;
    private javax.swing.JPanel contentjPanel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JButton deletejButton;
    private javax.swing.JTextField fromTextField;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane listScrollPane1;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel pathLabel;
    private javax.swing.JTextField pathTextField;
    private javax.swing.JLabel propeyLabel;
    private javax.swing.JLabel toLabel;
    private javax.swing.JTextField toTextField;
    private Vector fileName;
    // End of variables declaration//GEN-END:variables
    
}
