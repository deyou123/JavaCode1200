package com.lzw;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextField;

import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

public class ReplaceFileText extends JFrame {
    
    private JPanel contentPane;
    private JTextField fileField;
    private JTextField searchTextField;
    private JTextField replaceTextField;
    private File file;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReplaceFileText frame = new ReplaceFileText();
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
    public ReplaceFileText() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 501, 184);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(10, 91));
        contentPane.add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 81, 0, 0, 66, 0 };
        gbl_panel.rowHeights = new int[] { 23, 0, 0, 0, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0,
                Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE };
        panel.setLayout(gbl_panel);
        
        JButton button = new JButton("\u9009\u62E9\u6587\u4EF6");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        GridBagConstraints gbc_button = new GridBagConstraints();
        gbc_button.anchor = GridBagConstraints.NORTHWEST;
        gbc_button.insets = new Insets(0, 0, 5, 5);
        gbc_button.gridx = 0;
        gbc_button.gridy = 0;
        panel.add(button, gbc_button);
        
        fileField = new JTextField();
        fileField.setEditable(false);
        GridBagConstraints gbc_fileField = new GridBagConstraints();
        gbc_fileField.gridwidth = 3;
        gbc_fileField.insets = new Insets(0, 0, 5, 0);
        gbc_fileField.fill = GridBagConstraints.HORIZONTAL;
        gbc_fileField.gridx = 1;
        gbc_fileField.gridy = 0;
        panel.add(fileField, gbc_fileField);
        fileField.setColumns(10);
        
        JLabel label = new JLabel("\u641C\u7D22\u6587\u672C\uFF1A");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.anchor = GridBagConstraints.EAST;
        gbc_label.insets = new Insets(0, 0, 5, 5);
        gbc_label.gridx = 0;
        gbc_label.gridy = 1;
        panel.add(label, gbc_label);
        
        searchTextField = new JTextField();
        GridBagConstraints gbc_searchTextField = new GridBagConstraints();
        gbc_searchTextField.gridwidth = 3;
        gbc_searchTextField.insets = new Insets(0, 0, 5, 0);
        gbc_searchTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_searchTextField.gridx = 1;
        gbc_searchTextField.gridy = 1;
        panel.add(searchTextField, gbc_searchTextField);
        searchTextField.setColumns(10);
        
        JLabel label_1 = new JLabel("\u66FF\u6362\u4E3A\uFF1A");
        GridBagConstraints gbc_label_1 = new GridBagConstraints();
        gbc_label_1.anchor = GridBagConstraints.EAST;
        gbc_label_1.insets = new Insets(0, 0, 5, 5);
        gbc_label_1.gridx = 0;
        gbc_label_1.gridy = 2;
        panel.add(label_1, gbc_label_1);
        
        replaceTextField = new JTextField();
        GridBagConstraints gbc_replaceTextField = new GridBagConstraints();
        gbc_replaceTextField.gridwidth = 3;
        gbc_replaceTextField.insets = new Insets(0, 0, 5, 0);
        gbc_replaceTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_replaceTextField.gridx = 1;
        gbc_replaceTextField.gridy = 2;
        panel.add(replaceTextField, gbc_replaceTextField);
        replaceTextField.setColumns(10);
        
        JPanel panel_1 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.gridwidth = 4;
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 3;
        panel.add(panel_1, gbc_panel_1);
        
        JButton replaceButton = new JButton("\u66FF\u6362");
        replaceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_replaceButton_actionPerformed(e);
            }
        });
        panel_1.add(replaceButton);
        
        JButton openfileButton = new JButton("\u6253\u5F00\u6587\u4EF6");
        openfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_2_actionPerformed(e);
            }
        });
        panel_1.add(openfileButton);
    }
    
    /**
     * ????????????????????????
     * 
     * @param e
     */
    protected void do_button_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser("./");// ??????????????
        // ????????????????????
        chooser.setFileFilter(new FileNameExtensionFilter("????????", "txt",
                "java", "php", "html", "htm"));
        // ????????????????
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // ??????????????????
        int option = chooser.showOpenDialog(this);
        // ??????????????????????????????????
        if (option != JFileChooser.APPROVE_OPTION)
            return;
        // ??????????????????????
        file = chooser.getSelectedFile();
        // ????????????????????
        fileField.setText(file.toString());
    }
    
    /**
     * ??????????????????????
     * 
     * @param e
     */
    protected void do_replaceButton_actionPerformed(ActionEvent event) {
        String searchText = searchTextField.getText();// ????????????
        String replaceText = replaceTextField.getText();// ????????????
        if (searchText.isEmpty())
            return;
        try {
            FileReader fis = new FileReader(file);// ??????????????
            char[] data = new char[1024];// ????????????????
            int rn = 0;
            StringBuilder sb = new StringBuilder();// ????????????????
            while ((rn = fis.read(data)) > 0) {// ??????????????????????????
                String str = String.valueOf(data, 0, rn);
                sb.append(str);
            }
            fis.close();// ??????????
            // ????????????????????????????????????
            String str = sb.toString().replace(searchText, replaceText);
            FileWriter fout = new FileWriter(file);// ??????????????
            fout.write(str.toCharArray());// ????????????????????????????
            fout.close();// ??????????
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "????????");
    }
    
    /**
     * ????????????????????????????
     * 
     * @param e
     */
    protected void do_button_2_actionPerformed(ActionEvent e) {
        try {
            if (file == null)
                return;
            Desktop.getDesktop().edit(file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
