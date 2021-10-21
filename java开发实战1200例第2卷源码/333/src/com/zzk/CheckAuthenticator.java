package com.zzk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CheckAuthenticator extends Authenticator {
    class UserPassDialog extends JDialog {// 用于显示对话框的内部类
        private JPasswordField pf_pwd;// 密码框
        private JTextField tf_user;// 用户名文本框
        
        public UserPassDialog() {
            super();
            getContentPane().setLayout(null);
            setTitle("身份验证对话框");
            setBounds(100, 100, 253, 165);
            
            final JLabel label = new JLabel();
            label.setText("用户名称：");
            label.setBounds(10, 25, 66, 18);
            getContentPane().add(label);
            
            tf_user = new JTextField();
            tf_user.setBounds(82, 23, 146, 22);
            getContentPane().add(tf_user);
            
            final JLabel label_1 = new JLabel();
            label_1.setText("用户密码：");
            label_1.setBounds(10, 63, 66, 18);
            getContentPane().add(label_1);
            
            pf_pwd = new JPasswordField();
            pf_pwd.setBounds(82, 61, 146, 22);
            getContentPane().add(pf_pwd);
            
            final JButton btn_ok = new JButton();
            btn_ok.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    setVisible(false);
                }
            });
            btn_ok.setText("确  定");
            btn_ok.setBounds(20, 89, 92, 28);
            getContentPane().add(btn_ok);
            
            final JButton btn_return = new JButton();
            btn_return.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    setVisible(false);
                }
            });
            btn_return.setText("返  回");
            btn_return.setBounds(132, 89, 92, 28);
            getContentPane().add(btn_return);
            //
        }
        
    }
    
    public PasswordAuthentication getPasswordAuthentication() {
        UserPassDialog dialog = new UserPassDialog();// 创建对话框
        dialog.setModal(true);// 设置为模式对话框
        dialog.setVisible(true);// 显示对话框
        String user = dialog.tf_user.getText().trim();// 获得用户名
        String pwd = new String(dialog.pf_pwd.getPassword());// 获得密码
        dialog.tf_user.setText(null);// 清空用户名文本框
        dialog.pf_pwd.setText(null);// 清空密码框
        return new PasswordAuthentication(user, pwd);// 返回PasswordAuthentication对象
    }
}
