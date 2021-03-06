package com.lzw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PreviewFileDialog extends JFrame {
    
    private JPanel contentPane;
    private JFileChooser fileChooser;
    private ImagePreviewer imageLabel;
    private ImagePreviewer previewer;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PreviewFileDialog frame = new PreviewFileDialog();
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
    public PreviewFileDialog() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 629, 428);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        
        JButton chooseButton = new JButton(
                "\u9009\u62E9\u56FE\u7247\u6587\u4EF6");
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        panel.add(chooseButton);
        
        imageLabel = new ImagePreviewer((JFileChooser) null);
        contentPane.add(imageLabel, BorderLayout.CENTER);
        initFileChooser();
    }
    
    /**
     * ????????????????
     */
    private void initFileChooser() {
        fileChooser = new JFileChooser();// ??????????????
        previewer = new ImagePreviewer(fileChooser);// ????????????????
        fileChooser.setFileFilter(new FileNameExtensionFilter("????????", "jpg",
                "gif", "png"));
        // ????????????????????????????
        fileChooser.addPropertyChangeListener("SelectedFileChangedProperty",
                new PropertyChangeListener() {
                    public void propertyChange(PropertyChangeEvent evt) {
                        // ????????????????????????????
                        previewer.setImageFile((File) evt.getNewValue());
                    }
                });
        fileChooser.setAccessory(previewer);
    }
    
    /**
     * ??????????????????????????????
     * 
     * @param e
     */
    protected void do_button_actionPerformed(ActionEvent e) {
        int option = fileChooser.showOpenDialog(this);// ??????????????????
        if (option == JFileChooser.APPROVE_OPTION) {
            // ??????????????????
            File file = fileChooser.getSelectedFile();
            // ??????????????
            imageLabel.setImageFile(file);
        }
    }
}

/**
 * ??????????????????
 * 
 * @author ??????
 */
class ImagePreviewer extends JLabel {
    public ImagePreviewer(JFileChooser chooser) {
        // ????????
        setPreferredSize(new Dimension(200, 200));
        setHorizontalAlignment(JLabel.CENTER);// ????????
        setBorder(new LineBorder(Color.GRAY));// ????????
        setOpaque(true);// ??????????
        setBackground(Color.WHITE);// ??????????
        setText("????????????");// ????????
    }
    
    /**
     * ??????????????????
     * 
     * @param file
     */
    public void setImageFile(File file) {
        setText("");// ??????????????????????
        if (file == null) {// ????????????????
            setText("????????????");// ????????????????
            return;// ????????
        }
        // ????????????
        ImageIcon icon = new ImageIcon(file.getPath());
        if (icon.getIconWidth() > getWidth()) {// ????????????
            icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(),
                    -1, Image.SCALE_DEFAULT));
        }
        setIcon(icon);// ??????????????
        repaint();// ????????????
    }
}