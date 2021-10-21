/*
 * ShowSignDialog.java
 *版权所有：明日科技有限公司
 * Created on 2008年6月28日, 下午2:29
 */
package com.mwq.map.frame;

import com.mwq.map.dao.Dao;
import com.mwq.map.tool.InstancePool;
import com.mwq.map.tool.MapProcessor;
import com.mwq.map.tool.ScreenSize;
import java.util.Vector;

/**
 *
 * @author  Administrator
 */
public class ShowSignDialog extends javax.swing.JDialog {

    private static final Dao dao = Dao.getInstance();
    private static final MapProcessor mapProcessor = InstancePool.getMapProcessor();

    /** Creates new form ShowSignDialog */
    public ShowSignDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initInfo();
        ScreenSize.centered(this);
    }

    private void initInfo() {
        Vector sign = dao.selectClickSignV(mapProcessor.getRightClickToMapX(), mapProcessor.getRightClickToMapY());
        titleTextField.setText(sign.get(2).toString());
        sortTextField.setText(sign.get(4).toString());
        dateTextField.setText(sign.get(7).toString());
        remarkTextArea.setText(sign.get(8).toString());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        titleTextField = new javax.swing.JTextField();
        sortLabel = new javax.swing.JLabel();
        sortTextField = new javax.swing.JTextField();
        dateLabel = new javax.swing.JLabel();
        dateTextField = new javax.swing.JTextField();
        remarkLabel = new javax.swing.JLabel();
        remarkScrollPane = new javax.swing.JScrollPane();
        remarkTextArea = new javax.swing.JTextArea();
        exitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("标记信息");
        setResizable(false);

        titleLabel.setText("标记名称：");

        titleTextField.setFocusable(false);

        sortLabel.setText("所属类型：");

        sortTextField.setFocusable(false);

        dateLabel.setText("创建日期：");

        dateTextField.setFocusable(false);

        remarkLabel.setText("标记说明：");

        remarkTextArea.setColumns(20);
        remarkTextArea.setLineWrap(true);
        remarkTextArea.setRows(5);
        remarkTextArea.setFocusable(false);
        remarkScrollPane.setViewportView(remarkTextArea);

        exitButton.setText("退出");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(titleTextField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sortLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sortTextField))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateLabel)
                            .addComponent(remarkLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateTextField)
                            .addComponent(exitButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(remarkScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel)
                    .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortLabel)
                    .addComponent(sortTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLabel)
                    .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(remarkLabel)
                    .addComponent(remarkScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exitButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
// TODO add your handling code here:
    this.dispose();
}                                          

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ShowSignDialog dialog = new ShowSignDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField dateTextField;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel remarkLabel;
    private javax.swing.JScrollPane remarkScrollPane;
    private javax.swing.JTextArea remarkTextArea;
    private javax.swing.JLabel sortLabel;
    private javax.swing.JTextField sortTextField;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField titleTextField;
    // End of variables declaration                   
}
