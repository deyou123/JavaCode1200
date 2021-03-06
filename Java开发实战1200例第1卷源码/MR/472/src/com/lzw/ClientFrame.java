package com.lzw;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.zip.ZipInputStream;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.UIManager;

public class ClientFrame extends JFrame {
    
    private JPanel contentPane;
    private Socket socket;
    private JTextField hostField;
    private JList list;
    private JTextArea infoArea;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientFrame frame = new ClientFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame.
     */
    public ClientFrame() {
        setTitle("\u4EE5\u538B\u7F29\u683C\u5F0F\u4F20\u8F93\u7F51\u7EDC\u6570\u636E");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 673, 399);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(0, 0));
        
        JLabel label = new JLabel("\u670D\u52A1\u5668\uFF1A");
        panel.add(label, BorderLayout.WEST);
        
        hostField = new JTextField();
        hostField.setText("127.0.0.1");
        panel.add(hostField);
        hostField.setColumns(10);
        
        JButton linkButton = new JButton("\u8FDE\u63A5");
        linkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_linkButton_actionPerformed(e);
            }
        });
        panel.add(linkButton, BorderLayout.EAST);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        infoArea = new JTextArea();
        infoArea.setLineWrap(true);
        scrollPane.setViewportView(infoArea);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setPreferredSize(new Dimension(160, 100));
        contentPane.add(scrollPane_1, BorderLayout.WEST);
        
        list = new JList();
        scrollPane_1.setViewportView(list);
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                do_list_valueChanged(e);
            }
        });
    }
    
    /**
     * ??????????????????????????
     * 
     * @param e
     */
    protected void do_list_valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
            return;
        Object value = list.getSelectedValue();
        if (value == null)
            return;
        String bookName = value.toString();
        infoArea.setText("");
        try {
            // ????socket????????
            OutputStream outputStream = socket.getOutputStream();
            // ??socket????????
            outputStream.write((bookName + "\n").getBytes());
            // ????ZIP??????
            ZipInputStream zis = new ZipInputStream(socket.getInputStream());
            char[] data = new char[1024];// ????????
            int readNum;
            zis.getNextEntry();// ??????????ZIP????
            // ??ZIP????????????????????????????
            InputStreamReader ir = new InputStreamReader(zis);
            while ((readNum = ir.read(data)) > 0) {// ????????
                // ??????????????????????
                infoArea.append(new String(data, 0, readNum));
            }
            infoArea.select(0, 0);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * ??????????????????????
     * 
     * @param e
     */
    protected void do_linkButton_actionPerformed(ActionEvent e) {
        try {
            String host = hostField.getText();// ??????????????????????
            socket = new Socket(host, 1598);// ????socket????
            final ObjectInputStream ois = new ObjectInputStream(socket
                    .getInputStream());// ????socket????????????
            list.setModel(new AbstractListModel() {// ????JList??????????
                        // ????socket??????????????????????????????????
                        String[] values = (String[]) ois.readObject();
                        
                        public int getSize() {
                            return values.length;
                        }
                        
                        public Object getElementAt(int index) {
                            return values[index];
                        }
                    });
        } catch (UnknownHostException e1) {// ????????????????
            JOptionPane.showMessageDialog(null, "??????????????????");
            return;
        } catch (SocketException e1) {// ????socket????
            JOptionPane.showMessageDialog(null, "??????????????????");
            return;
        } catch (IOException e11) {// ????????????????
            e11.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
