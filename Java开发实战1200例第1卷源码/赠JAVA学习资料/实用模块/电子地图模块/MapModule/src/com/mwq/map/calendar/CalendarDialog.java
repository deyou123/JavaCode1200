/*
 * CalendarDialog.java
 *
 * Created on 2008年6月28日, 下午2:54
 */
package com.mwq.map.calendar;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.table.JTableHeader;

/**
 *版权所有：明日科技有限公司
 * @author  Administrator
 */
public class CalendarDialog extends javax.swing.JDialog {

    private static final int YEAR;
    private static final int MONTH;
    private static final int DAY;
    private static int year;
    private static int month;
    private static final int[] daysOfMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private MTableModel tableModel;
    private static final DateFormat dateFormat = DateFormat.getDateInstance();
    

    static {
        Calendar today = Calendar.getInstance();
        YEAR = year = today.get(Calendar.YEAR);
        MONTH = month = today.get(Calendar.MONTH) + 1;
        DAY = today.get(Calendar.DAY_OF_MONTH);
        judgeLeapYear();
    }

    public static int getYEAR() {
        return YEAR;
    }

    public static int getMONTH() {
        return MONTH;
    }

    public static int getDAY() {
        return DAY;
    }

    public static int getYear() {
        return year;
    }

    public static int getMonth() {
        return month;
    }

    /** Creates new form CalendarDialog */
    public CalendarDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initModule();
        initTableModel();
    }

    public static void judgeLeapYear() {
        if (getYear() % 100 == 0) {
            daysOfMonth[2] = (getYear() % 400 == 0 ? 29 : 28);
        } else {
            daysOfMonth[2] = (getYear() % 4 == 0 ? 29 : 28);
        }
    }

    private void initModule() {
        yearTextField.setText(year + "");
        monthTextField.setText(month + "");
        todayLabel.setText("今天是：" + YEAR + "-" + MONTH + "-" + DAY + "  ");
    }

    private void initTableModel() {
        int row = 0;// 行索引

        int col = 0;// 列索引

        MButton button = null;// 声明一个按钮对象

        try {
            dateFormat.parse(year + "-" + month + "-1");// 解析当月1号

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar firstDayOfMonth = dateFormat.getCalendar();// 获得Calendar类的对象

        int dayOfWeek = firstDayOfMonth.get(Calendar.DAY_OF_WEEK) - 1;// 获得为一周的第几天

        if (dayOfWeek == 0) {// 为周日

            dayOfWeek = 7;// 按中国习惯放在最后

        }

        // 上个月
        int lastMonthDays = 31;// 默认上个月为31天

        if (month > 2) {
            lastMonthDays = daysOfMonth[month - 1];// 获得上个月的具体天数

        }
        // 用上个月的最后几天填充本周的开始几天
        for (int day = lastMonthDays - dayOfWeek + 2; day <= lastMonthDays; day++) {
            button = new MButton(day);// 创建按钮对象

            button.setEnabled(false);// 设置按钮不可用

            tableModel.setValueAt(button, row, col);// 设置到表格模型的相应位置

            // 计算索引值
            if (col == 6) {
                row++;
                col = 0;
            } else {
                col++;
            }
        }
        // 当月
        for (int day = 1; day <= daysOfMonth[month]; day++) {
            button = new MButton(day);// 创建按钮对象

            if (col > 4) {
                if (col == 5) {// 为星期六

                    button.setForeground(Color.GREEN);// 设置按钮的前景色

                } else {// 为星期日

                    button.setForeground(Color.RED);// 设置按钮的前景色

                }
            }
            if (day == DAY) {
                if (year == YEAR && month == MONTH) {// 为当天

                    button.setForeground(Color.ORANGE);// 设置按钮的前景色

                }
            }
            tableModel.setValueAt(button, row, col);// 设置到表格模型的相应位置

            // 计算索引值
            if (col == 6) {
                row++;
                col = 0;
            } else {
                col++;
            }
        }
        // 下个月
        int nextMonthDays = 42 - (row * 7 + col);// 计算需要的天数
        // 用下个月的开始几天填充表格的剩余单元格

        for (int day = 1; day <= nextMonthDays; day++) {
            button = new MButton(day);// 创建按钮对象

            button.setEnabled(false);// 设置按钮不可用

            tableModel.setValueAt(button, row, col);// 设置到表格模型的相应位置

            // 计算索引值
            if (col == 6) {
                row++;
                col = 0;
            } else {
                col++;
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        borderPanel = new javax.swing.JPanel();
        operatePanel = new javax.swing.JPanel();
        subYearButton = new javax.swing.JButton();
        subMonthButton = new javax.swing.JButton();
        yearTextField = new javax.swing.JTextField();
        yearLabel = new javax.swing.JLabel();
        monthTextField = new javax.swing.JTextField();
        monthLabel = new javax.swing.JLabel();
        addMonthButton = new javax.swing.JButton();
        addYearButton = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        todayPanel = new javax.swing.JPanel();
        todayLabel = new javax.swing.JLabel();
        todayButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("万年历");
        setModal(true);
        setUndecorated(true);

        borderPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        borderPanel.setLayout(new java.awt.BorderLayout());

        subYearButton.setText("<<");
        subYearButton.setMargin(new java.awt.Insets(0, 5, 0, 5));
        subYearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subYearButtonActionPerformed(evt);
            }
        });
        operatePanel.add(subYearButton);

        subMonthButton.setText("<");
        subMonthButton.setMargin(new java.awt.Insets(0, 8, 0, 8));
        subMonthButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMonthButtonActionPerformed(evt);
            }
        });
        operatePanel.add(subMonthButton);

        yearTextField.setColumns(6);
        yearTextField.setFont(new java.awt.Font("宋体", 1, 12));
        yearTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        yearTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                yearTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                yearTextFieldFocusLost(evt);
            }
        });
        yearTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                yearTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                yearTextFieldKeyTyped(evt);
            }
        });
        operatePanel.add(yearTextField);

        yearLabel.setText("年");
        operatePanel.add(yearLabel);

        monthTextField.setColumns(4);
        monthTextField.setFont(new java.awt.Font("宋体", 1, 12));
        monthTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        monthTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                monthTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                monthTextFieldFocusLost(evt);
            }
        });
        monthTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                monthTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                monthTextFieldKeyTyped(evt);
            }
        });
        operatePanel.add(monthTextField);

        monthLabel.setText("月");
        operatePanel.add(monthLabel);

        addMonthButton.setText(">");
        addMonthButton.setMargin(new java.awt.Insets(0, 8, 0, 8));
        addMonthButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMonthButtonActionPerformed(evt);
            }
        });
        operatePanel.add(addMonthButton);

        addYearButton.setText(">>");
        addYearButton.setMargin(new java.awt.Insets(0, 5, 0, 5));
        addYearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addYearButtonActionPerformed(evt);
            }
        });
        operatePanel.add(addYearButton);

        borderPanel.add(operatePanel, java.awt.BorderLayout.PAGE_START);

        scrollPane.setPreferredSize(new java.awt.Dimension(260, 130));

        String[] columnNames = {"一", "二", "三", "四", "五", "六", "日"};
        Object[][] tableDatas = new Object[6][7];

        tableModel = new MTableModel(columnNames, tableDatas);
        table.setModel(tableModel);
        table.setRowHeight(20);
        table.setRowSelectionAllowed(false);
        table.setDefaultRenderer(JButton.class, new MTableCell());
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("", Font.BOLD, 12));
        tableHeader.setBackground(Color.ORANGE);
        scrollPane.setViewportView(table);

        borderPanel.add(scrollPane, java.awt.BorderLayout.CENTER);

        todayLabel.setText("2008-08-08");
        todayPanel.add(todayLabel);

        todayButton.setText("...");
        todayButton.setMargin(new java.awt.Insets(0, 3, 0, 3));
        todayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todayButtonActionPerformed(evt);
            }
        });
        todayPanel.add(todayButton);

        borderPanel.add(todayPanel, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(borderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(borderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>                        

private void subYearButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
// TODO add your handling code here:    
    yearTextField.setText(--year + "");
    judgeLeapYear();
    initTableModel();
    SwingUtilities.updateComponentTreeUI(table);
    System.out.println("subYearButtonActionPerformed");
}                                             

private void subMonthButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
// TODO add your handling code here:
    if (getMonth() == 1) {
        yearTextField.setText(--year + "");
        judgeLeapYear();
        month = 12;
    } else {
        month--;
    }
    monthTextField.setText(getMonth() + "");
    initTableModel();
    SwingUtilities.updateComponentTreeUI(table);
    System.out.println("subMonthButtonActionPerformed");
}                                              

private void addMonthButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
// TODO add your handling code here:    
    if (month == 12) {
        yearTextField.setText(++year + "");
        judgeLeapYear();
        month = 1;
    } else {
        month++;
    }
    monthTextField.setText(month + "");
    initTableModel();
    SwingUtilities.updateComponentTreeUI(table);
    System.out.println("addMonthButtonActionPerformed");
}                                              

private void addYearButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
// TODO add your handling code here:
    yearTextField.setText(++year + "");
    judgeLeapYear();
    initTableModel();
    SwingUtilities.updateComponentTreeUI(table);
    System.out.println("addYearButtonActionPerformed");
}                                             

private void todayButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
// TODO add your handling code here:
    year = getYEAR();
    month = getMONTH();
    yearTextField.setText(getYear() + "");
    monthTextField.setText(getMonth() + "");
    judgeLeapYear();
    initTableModel();
    SwingUtilities.updateComponentTreeUI(table);
}                                           

private void yearTextFieldFocusGained(java.awt.event.FocusEvent evt) {                                          
// TODO add your handling code here:
    yearTextField.setText(null);
}                                         

private void yearTextFieldFocusLost(java.awt.event.FocusEvent evt) {                                        
// TODO add your handling code here:
    if (yearTextField.getText().length() == 0) {
        yearTextField.setText(getYear() + "");
    }
}                                       

private void yearTextFieldKeyTyped(java.awt.event.KeyEvent evt) {                                       
// TODO add your handling code here:
    int digit = yearTextField.getText().length();
    if (digit < 4) {
        String num = (digit == 0 ? "123456789" : "0123456789");
        if (num.indexOf(evt.getKeyChar()) < 0) {
            evt.consume();
        }
    } else {
        evt.consume();
    }
}                                      

private void yearTextFieldKeyReleased(java.awt.event.KeyEvent evt) {                                          
// TODO add your handling code here:
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        year = Integer.valueOf(yearTextField.getText());
        judgeLeapYear();
        initTableModel();
        SwingUtilities.updateComponentTreeUI(table);
    }
}                                         

private void monthTextFieldFocusGained(java.awt.event.FocusEvent evt) {                                           
// TODO add your handling code here:    
    monthTextField.setText(null);
}                                          

private void monthTextFieldFocusLost(java.awt.event.FocusEvent evt) {                                         
// TODO add your handling code here:    
    if (monthTextField.getText().length() == 0) {
        monthTextField.setText(getMonth() + "");
    }
}                                        

private void monthTextFieldKeyTyped(java.awt.event.KeyEvent evt) {                                        
// TODO add your handling code here:    
    String input = monthTextField.getText();
    switch (input.length()) {
        case 0:
            if ("123456789".indexOf(evt.getKeyChar()) < 0) {
                evt.consume();
            }
            break;
        case 1:
            if (input.equals("1")) {
                if ("012".indexOf(evt.getKeyChar()) < 0) {
                    evt.consume();
                }
            } else {
                evt.consume();
            }
            break;
        default:
            evt.consume();
    }
}                                       

private void monthTextFieldKeyReleased(java.awt.event.KeyEvent evt) {                                           
// TODO add your handling code here:
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        month = Integer.valueOf(monthTextField.getText());
        initTableModel();
        SwingUtilities.updateComponentTreeUI(table);
    }
}                                          

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                CalendarDialog dialog = new CalendarDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton addMonthButton;
    private javax.swing.JButton addYearButton;
    private javax.swing.JPanel borderPanel;
    private javax.swing.JLabel monthLabel;
    private javax.swing.JTextField monthTextField;
    private javax.swing.JPanel operatePanel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JButton subMonthButton;
    private javax.swing.JButton subYearButton;
    private javax.swing.JTable table;
    private javax.swing.JButton todayButton;
    private javax.swing.JLabel todayLabel;
    private javax.swing.JPanel todayPanel;
    private javax.swing.JLabel yearLabel;
    private javax.swing.JTextField yearTextField;
    // End of variables declaration                   
}
