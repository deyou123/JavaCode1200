package com.mingrisoft.time;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class PermanentCalendar extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -5939002611918837793L;
    private JPanel contentPane;
    private JTable table;
    private JLabel currentMonthLabel;
    private Calendar calendar = new GregorianCalendar();
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PermanentCalendar frame = new PermanentCalendar();
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
    public PermanentCalendar() {
        setTitle("\u516C\u5386\u4E07\u5E74\u5386");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 282);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel YMPanel = new JPanel();
        contentPane.add(YMPanel, BorderLayout.NORTH);
        YMPanel.setLayout(new GridLayout(1, 3, 5, 10));
        
        JPanel lastMonthPanel = new JPanel();
        YMPanel.add(lastMonthPanel);
        
        JButton lastMonthButton = new JButton("\u4E0A\u4E2A\u6708");
        lastMonthButton.setFont(new Font("????????", Font.PLAIN, 16));
        lastMonthButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_lastMonthButton_actionPerformed(e);
            }
        });
        lastMonthPanel.add(lastMonthButton);
        
        JPanel currentMonthPanel = new JPanel();
        YMPanel.add(currentMonthPanel);
        currentMonthPanel.setLayout(new BoxLayout(currentMonthPanel, BoxLayout.X_AXIS));
        
        currentMonthLabel = new JLabel("");
        currentMonthLabel.setFont(new Font("????????", Font.PLAIN, 20));
        currentMonthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentMonthPanel.add(currentMonthLabel);
        
        JPanel nextMonthPanel = new JPanel();
        YMPanel.add(nextMonthPanel);
        
        JButton nextMonthButton = new JButton("\u4E0B\u4E2A\u6708");
        nextMonthButton.setFont(new Font("????????", Font.PLAIN, 16));
        nextMonthButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_nextMonthButton_actionPerformed(e);
            }
        });
        nextMonthPanel.add(nextMonthButton);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable();
        table.setFont(new Font("????????", Font.PLAIN, 18));
        table.getTableHeader().setFont(new Font("????????", Font.PLAIN, 20));
        table.setRowHeight(25);
        table.setCellSelectionEnabled(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);
        
        currentMonthLabel.setText(updateLabel(0));
        updateTable(calendar);
    }
    
    private void updateTable(Calendar calendar) {
        String[] weeks = new DateFormatSymbols().getShortWeekdays();// ????????????????????????
        String[] realWeeks = new String[7];// ????????????????????????????????
        for (int i = 1; i < weeks.length; i++) {// weeks????????????????????????????????1????????
            realWeeks[i - 1] = weeks[i].substring(2, 3);// ????????????????????????
        }
        int today = calendar.get(Calendar.DATE);// ????????????
        int monthDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);// ????????????????
        calendar.set(Calendar.DAY_OF_MONTH, 1); // ??????????????????????
        int weekday = calendar.get(Calendar.DAY_OF_WEEK);// ??????????????????????
        int firstDay = calendar.getFirstDayOfWeek(); // ????????????????????????
        int whiteDay = weekday - firstDay; // ??????????????????????????????????
        Object[][] days = new Object[6][7];// ??????????????????????????????????
        for (int i = 1; i <= monthDays; i++) {// ????????????????????????????????????????
            days[(i - 1 + whiteDay) / 7][(i - 1 + whiteDay) % 7] = i;
        }// ????????????????????????????????????????????????????????????
        DefaultTableModel model = (DefaultTableModel) table.getModel();// ??????????????????
        model.setDataVector(days, realWeeks);// ????????????????????????
        table.setModel(model);// ????????????
        table.setRowSelectionInterval(0, (today - 1 + whiteDay) / 7);// ????????????
        table.setColumnSelectionInterval(0, (today - 1 + whiteDay) % 7);// ????????????
    }
    
    private String updateLabel(int increment) {
        calendar.add(Calendar.MONTH, increment);// ??????????????increment??
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy??MM??");// ??????????????
        return formatter.format(calendar.getTime());// ????????????????????
    }
    
    protected void do_lastMonthButton_actionPerformed(ActionEvent e) {
        currentMonthLabel.setText(updateLabel(-1));
        updateTable(calendar);
    }
    
    protected void do_nextMonthButton_actionPerformed(ActionEvent e) {
        currentMonthLabel.setText(updateLabel(1));
        updateTable(calendar);
    }
}
