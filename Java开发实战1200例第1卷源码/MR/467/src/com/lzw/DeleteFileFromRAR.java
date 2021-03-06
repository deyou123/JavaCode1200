package com.lzw;

import static java.lang.Runtime.getRuntime;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.FlowLayout;
import javax.swing.ListSelectionModel;

public class DeleteFileFromRAR extends JFrame {
    
    private JPanel contentPane;
    private JTextField rarFileField;
    private File rarFile;
    private JTable table;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DeleteFileFromRAR frame = new DeleteFileFromRAR();
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
    public DeleteFileFromRAR() {
        setTitle("\u4ECERAR\u538B\u7F29\u5305\u4E2D\u5220\u9664\u6587\u4EF6");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 508, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 5));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(5, 0));
        
        JLabel lblrar = new JLabel("\u3000\u9009\u62E9RAR\u6587\u6863\uFF1A");
        panel.add(lblrar, BorderLayout.WEST);
        
        JButton browseButton = new JButton("\u6D4F\u89C8");
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_browseButton_actionPerformed(e);
            }
        });
        panel.add(browseButton, BorderLayout.EAST);
        
        rarFileField = new JTextField();
        panel.add(rarFileField, BorderLayout.CENTER);
        rarFileField.setColumns(10);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new TitledBorder(new BevelBorder(
                BevelBorder.LOWERED, null, null, null, null),
                "\u538B\u7F29\u6587\u4EF6\u5217\u8868\uFF1A",
                TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(
                        0, 0, 0)));
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
                "\u6587\u4EF6\u540D\u79F0", "\u5927\u5C0F",
                "\u538B\u7F29\u540E\u5927\u5C0F", "\u538B\u7F29\u7387",
                "\u65F6\u95F4" }));
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        scrollPane.setViewportView(table);
        
        JPanel panel_1 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
        flowLayout.setHgap(30);
        contentPane.add(panel_1, BorderLayout.SOUTH);
        
        JButton delButton = new JButton("\u5220\u9664");
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_delButton_actionPerformed(e);
            }
        });
        panel_1.add(delButton);
        
        JButton closeButton = new JButton("\u5173\u95ED");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_closeButton_actionPerformed(e);
            }
        });
        panel_1.add(closeButton);
    }
    
    /**
     * ??????????????????????
     * 
     * @param e
     */
    protected void do_browseButton_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();// ??????????????
        chooser.setFileFilter(new FileNameExtensionFilter("RAR????", "rar"));
        chooser.setAcceptAllFileFilterUsed(false);
        int option = chooser.showOpenDialog(this);// ??????????????????
        if (option != JFileChooser.APPROVE_OPTION)
            return;
        rarFile = chooser.getSelectedFile();// ??????????rar????
        rarFileField.setText(rarFile.toString());// ????rar????????????
        resolveFileList();
    }
    
    /**
     * ??????????????????????????
     */
    private void resolveFileList() {
        try {
            // ????????????????????????????????????????????
            Process process = getRuntime()
                    .exec("rar v -c- \"" + rarFile + "\"");
            process.getOutputStream().close();// ??????????????
            Scanner sc = new Scanner(process.getInputStream());
            int count = 0;// ??????????
            // ????????????????
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            Vector<String> row = new Vector<String>();// ??????????????
            do {
                String line = sc.nextLine();// ??????????????????????
                // ????????????????
                if (line.contains("----------------------")) {
                    count = (count == 0 ? count + 1 : -1);
                    continue;
                }
                if (count == 0)// ????????????
                    continue;
                if (count == -1)// ??????????????????
                    break;
                if (++count % 2 == 0) {// ????????????
                    row.add(line);
                } else {// ????????????????
                    // ????????????????????????
                    String[] split = line.trim().split("\\s+");
                    for (String string : split) {// ????????????????
                        row.add(string);// ????????????????????????????????
                    }
                    // ??????????????????????????
                    model.addRow(row.toArray());
                    row.clear();// ??????????????????????????????????????
                }
            } while (sc.hasNext());
            process.getInputStream().close();// ??????????
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * ??????????????????????
     * 
     * @param e
     */
    protected void do_closeButton_actionPerformed(ActionEvent e) {
        dispose();
    }
    
    /**
     * ??????????????????????
     * 
     * @param e
     */
    protected void do_delButton_actionPerformed(ActionEvent e) {
        // ????????????????
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int selectedRow = table.getSelectedRow();// ??????????????????
        if(selectedRow<0)return;
        // ????????????????????
        String path = model.getValueAt(selectedRow, 0).toString();
        try {
            // ????rar????????
            Process exec = getRuntime().exec(
                    "rar d -c- \"" + rarFile + "\" " + path);
            // ??????????????
            Scanner scan = new Scanner(exec.getInputStream());
            while (scan.hasNext()) {// ??????????????
                scan.nextLine();// ??????????????
            }
            scan.close();// ??????????
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        resolveFileList();// ??????????????????????
    }
}
