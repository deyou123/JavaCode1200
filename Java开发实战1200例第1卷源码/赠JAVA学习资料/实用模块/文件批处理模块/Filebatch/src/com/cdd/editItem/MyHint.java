/*
 * MyHint.java
 *
 * Created on 2008年9月3日, 下午3:45
 */

package com.cdd.editItem;

/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class MyHint extends javax.swing.JFrame {

    /** Creates new form MyHint */
    public MyHint() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        backjPanel = new MyJPanel();
        messagejLabel = new javax.swing.JLabel();
        closejButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        messagejLabel.setText("文件复制中，请稍后。。。");
        setTitle("提示信息");
        closejButton.setText("退出");
        closejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closejButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout backjPanelLayout = new javax.swing.GroupLayout(backjPanel);
        backjPanel.setLayout(backjPanelLayout);
        backjPanelLayout.setHorizontalGroup(
            backjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backjPanelLayout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(closejButton)
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backjPanelLayout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addComponent(messagejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        backjPanelLayout.setVerticalGroup(
            backjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backjPanelLayout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(messagejLabel)
                .addGap(27, 27, 27)
                .addComponent(closejButton)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backjPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

private void closejButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
		dispose();
}                                            

    /**
    * @param args the command line arguments
    */


    // Variables declaration - do not modify
    private MyJPanel backjPanel;
    private javax.swing.JButton closejButton;
    private javax.swing.JLabel messagejLabel;
    // End of variables declaration

}
