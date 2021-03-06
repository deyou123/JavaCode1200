import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class LazyButton extends JFrame {
    
    private JPanel contentPane;
    private JButton button;
    private Timer timer;
    
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
                    LazyButton frame = new LazyButton();
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
    public LazyButton() {
        setTitle("\u5EF6\u8FDF\u751F\u6548\u7684\u6309\u94AE");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                do_this_windowOpened(e);
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 372, 395);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(18, 50, 318, 224);
        contentPane.add(scrollPane);
        
        JTextArea textArea = new JTextArea();// ??????????????
        textArea.setLineWrap(true);// ????????
        StringBuilder sb = new StringBuilder();// ????????????????
        // ??????????????
        Scanner scan = new Scanner(getClass().getResourceAsStream("lzw.txt"));
        while (scan.hasNext()) {// ??????????????
            String string = (String) scan.nextLine();// ????????????
            sb.append(string + "\n");// ??????????????????????????????
        }
        textArea.setText(sb.toString());// ??????????????????????????????????
        textArea.setSelectionStart(0);// ????????????????????????????
        textArea.setSelectionEnd(0);
        scrollPane.setViewportView(textArea);
        // ????????????
        JLabel lblJava = new JLabel("Java????????????????");
        lblJava.setFont(new Font("SansSerif", Font.PLAIN, 24));// ????????????
        lblJava.setHorizontalAlignment(SwingConstants.CENTER);// ????????????
        lblJava.setBounds(18, 6, 318, 32);
        contentPane.add(lblJava);
        // ????????????
        button = new JButton("??????10????");
        button.setEnabled(false);// ??????????????????
        button.setBounds(59, 286, 124, 30);
        contentPane.add(button);
        // ????????????
        JButton button_1 = new JButton("????");
        button_1.setBounds(195, 286, 90, 30);
        contentPane.add(button_1);
    }
    
    protected void do_this_windowOpened(WindowEvent e) {// ????????????????????
        timer = new Timer(1000, new ActionListener() {// ????timer????????????????????????
                    int tNum = 10;// ??????????????
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        button.setText("??????" + --tNum + "????");// ??????????????????
                        if (tNum <= 0) {// ??????????????????????????????????timer????
                            button.setEnabled(true);
                            timer.stop();
                        }
                    }
                });
        timer.start();// ????timer????
    }
}
