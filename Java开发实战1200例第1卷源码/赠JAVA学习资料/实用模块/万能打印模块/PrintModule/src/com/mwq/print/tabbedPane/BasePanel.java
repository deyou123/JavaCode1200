/*
 * BasePanel.java
 *
 * Created on 2008年7月24日, 上午11:07
 */
package com.mwq.print.tabbedPane;

import com.mwq.print.frame.PaperSizeDialog;
import com.mwq.print.mwing.PaperPanel;
import java.text.NumberFormat;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class BasePanel extends javax.swing.JPanel {

    private static final NumberFormatter NUMBERFORMATTER;
    

    static {
        NUMBERFORMATTER = new NumberFormatter(NumberFormat.getIntegerInstance());
        NUMBERFORMATTER.setAllowsInvalid(false);
//        NUMBERFORMATTER.setMinimum(5);
//        NUMBERFORMATTER.setMaximum(50);
    }

    /** Creates new form BasePanel */
    public BasePanel() {
        initComponents();

        spaceRadioButtonActionPerformed(null);
        borderRadioButtonActionPerformed(null);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        printModeButtonGroup = new javax.swing.ButtonGroup();
        spaceButtonGroup = new javax.swing.ButtonGroup();
        borderButtonGroup = new javax.swing.ButtonGroup();
        borderAllButtonGroup = new javax.swing.ButtonGroup();
        borderUpButtonGroup = new javax.swing.ButtonGroup();
        borderDownButtonGroup = new javax.swing.ButtonGroup();
        borderLeftButtonGroup = new javax.swing.ButtonGroup();
        borderRightButtonGroup = new javax.swing.ButtonGroup();
        paperLabel = new javax.swing.JLabel();
        paperComboBox = new javax.swing.JComboBox();
        modeLabel = new javax.swing.JLabel();
        verticalRadioButton = new javax.swing.JRadioButton();
        horizontalRadioButton = new javax.swing.JRadioButton();
        spacePanel = new javax.swing.JPanel();
        spaceAllRadioButton = new javax.swing.JRadioButton();
        spaceAllFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        spaceOnlyRadioButton = new javax.swing.JRadioButton();
        spaceOnlyPanel = new javax.swing.JPanel();
        spaceUpLabel = new javax.swing.JLabel();
        spaceUpFormattedTextField = new javax.swing.JFormattedTextField();
        spaceDownLabel = new javax.swing.JLabel();
        spaceDownFormattedTextField = new javax.swing.JFormattedTextField();
        spaceLeftLabel = new javax.swing.JLabel();
        spaceLeftFormattedTextField = new javax.swing.JFormattedTextField();
        spaceRightLabel = new javax.swing.JLabel();
        spaceRightFormattedTextField = new javax.swing.JFormattedTextField();
        borderPanel = new javax.swing.JPanel();
        borderAllRadioButton = new javax.swing.JRadioButton();
        borderAllPanel = new javax.swing.JPanel();
        borderAllYesRadioButton = new javax.swing.JRadioButton();
        borderAllNoRadioButton = new javax.swing.JRadioButton();
        borderOnlyRadioButton = new javax.swing.JRadioButton();
        borderOnlyPanel = new javax.swing.JPanel();
        borderUpLabel = new javax.swing.JLabel();
        borderUpYesRadioButton = new javax.swing.JRadioButton();
        borderUpNoRadioButton = new javax.swing.JRadioButton();
        borderDownLabel = new javax.swing.JLabel();
        borderDownYesRadioButton = new javax.swing.JRadioButton();
        borderDownNoRadioButton = new javax.swing.JRadioButton();
        borderLeftLabel = new javax.swing.JLabel();
        borderLeftYesRadioButton = new javax.swing.JRadioButton();
        borderLeftNoRadioButton = new javax.swing.JRadioButton();
        borderRightLabel = new javax.swing.JLabel();
        borderRightYesRadioButton = new javax.swing.JRadioButton();
        borderRightNoRadioButton = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(255, 255, 255));

        paperLabel.setText("打印纸张：");

        paperComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A2（420*594毫米）", "A3（297*420毫米）", "A4（210*297毫米）", "A5（148*210毫米）", "B3（364*514毫米）", "B4（257*364毫米）", "B5（182*257毫米）", "自定义" }));
        paperComboBox.setSelectedIndex(2);
        paperComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                paperComboBoxItemStateChanged(evt);
            }
        });
        paperComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paperComboBoxActionPerformed(evt);
            }
        });

        modeLabel.setText("打印方式：");

        verticalRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        printModeButtonGroup.add(verticalRadioButton);
        verticalRadioButton.setSelected(true);
        verticalRadioButton.setText("纵向");

        horizontalRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        printModeButtonGroup.add(horizontalRadioButton);
        horizontalRadioButton.setText("横向");

        spacePanel.setBackground(new java.awt.Color(255, 255, 255));
        spacePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("页边距"));

        spaceAllRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        spaceButtonGroup.add(spaceAllRadioButton);
        spaceAllRadioButton.setSelected(true);
        spaceAllRadioButton.setText("统一");
        spaceAllRadioButton.setFocusPainted(false);
        spaceAllRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spaceRadioButtonActionPerformed(evt);
            }
        });

        spaceAllFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(NUMBERFORMATTER));
        spaceAllFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        spaceAllFormattedTextField.setText("20");

        jLabel1.setText("（单位：毫米）");

        spaceOnlyRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        spaceButtonGroup.add(spaceOnlyRadioButton);
        spaceOnlyRadioButton.setText("单设");
        spaceOnlyRadioButton.setFocusPainted(false);
        spaceOnlyRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spaceRadioButtonActionPerformed(evt);
            }
        });

        spaceOnlyPanel.setBackground(new java.awt.Color(255, 255, 255));
        spaceOnlyPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        spaceUpLabel.setText("上：");

        spaceUpFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(NUMBERFORMATTER));
        spaceUpFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        spaceUpFormattedTextField.setText("25");

        spaceDownLabel.setText("下：");

        spaceDownFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(NUMBERFORMATTER));
        spaceDownFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        spaceDownFormattedTextField.setText("25");

        spaceLeftLabel.setText("左：");

        spaceLeftFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(NUMBERFORMATTER));
        spaceLeftFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        spaceLeftFormattedTextField.setText("20");

        spaceRightLabel.setText("右：");

        spaceRightFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(NUMBERFORMATTER));
        spaceRightFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        spaceRightFormattedTextField.setText("20");

        javax.swing.GroupLayout spaceOnlyPanelLayout = new javax.swing.GroupLayout(spaceOnlyPanel);
        spaceOnlyPanel.setLayout(spaceOnlyPanelLayout);
        spaceOnlyPanelLayout.setHorizontalGroup(
            spaceOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(spaceOnlyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(spaceOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spaceUpLabel)
                    .addComponent(spaceDownLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(spaceOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spaceUpFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spaceDownFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(spaceOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(spaceOnlyPanelLayout.createSequentialGroup()
                        .addComponent(spaceLeftLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(spaceLeftFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(spaceOnlyPanelLayout.createSequentialGroup()
                        .addComponent(spaceRightLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(spaceRightFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        spaceOnlyPanelLayout.setVerticalGroup(
            spaceOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(spaceOnlyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(spaceOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(spaceOnlyPanelLayout.createSequentialGroup()
                        .addGroup(spaceOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spaceUpLabel)
                            .addComponent(spaceUpFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(spaceOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spaceDownLabel)
                            .addComponent(spaceDownFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(spaceOnlyPanelLayout.createSequentialGroup()
                        .addGroup(spaceOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spaceLeftLabel)
                            .addComponent(spaceLeftFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(spaceOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spaceRightLabel)
                            .addComponent(spaceRightFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout spacePanelLayout = new javax.swing.GroupLayout(spacePanel);
        spacePanel.setLayout(spacePanelLayout);
        spacePanelLayout.setHorizontalGroup(
            spacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(spacePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(spacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(spacePanelLayout.createSequentialGroup()
                        .addComponent(spaceAllRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spaceAllFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(spacePanelLayout.createSequentialGroup()
                        .addComponent(spaceOnlyRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spaceOnlyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        spacePanelLayout.setVerticalGroup(
            spacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(spacePanelLayout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(spacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spaceAllRadioButton)
                    .addComponent(jLabel1)
                    .addComponent(spaceAllFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(spacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spaceOnlyRadioButton)
                    .addComponent(spaceOnlyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        borderPanel.setBackground(new java.awt.Color(255, 255, 255));
        borderPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("页边框"));

        borderAllRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        borderButtonGroup.add(borderAllRadioButton);
        borderAllRadioButton.setSelected(true);
        borderAllRadioButton.setText("统一");
        borderAllRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borderRadioButtonActionPerformed(evt);
            }
        });

        borderAllPanel.setBackground(new java.awt.Color(255, 255, 255));
        borderAllPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        borderAllYesRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        borderAllButtonGroup.add(borderAllYesRadioButton);
        borderAllYesRadioButton.setText("有");

        borderAllNoRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        borderAllButtonGroup.add(borderAllNoRadioButton);
        borderAllNoRadioButton.setSelected(true);
        borderAllNoRadioButton.setText("无");

        javax.swing.GroupLayout borderAllPanelLayout = new javax.swing.GroupLayout(borderAllPanel);
        borderAllPanel.setLayout(borderAllPanelLayout);
        borderAllPanelLayout.setHorizontalGroup(
            borderAllPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borderAllPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(borderAllYesRadioButton)
                .addGap(18, 18, 18)
                .addComponent(borderAllNoRadioButton)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        borderAllPanelLayout.setVerticalGroup(
            borderAllPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borderAllPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(borderAllPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(borderAllYesRadioButton)
                    .addComponent(borderAllNoRadioButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        borderOnlyRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        borderButtonGroup.add(borderOnlyRadioButton);
        borderOnlyRadioButton.setText("单设");
        borderOnlyRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borderRadioButtonActionPerformed(evt);
            }
        });

        borderOnlyPanel.setBackground(new java.awt.Color(255, 255, 255));
        borderOnlyPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        borderUpLabel.setText("上：");

        borderUpYesRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        borderUpButtonGroup.add(borderUpYesRadioButton);
        borderUpYesRadioButton.setText("有");

        borderUpNoRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        borderUpButtonGroup.add(borderUpNoRadioButton);
        borderUpNoRadioButton.setSelected(true);
        borderUpNoRadioButton.setText("无");

        borderDownLabel.setText("下：");

        borderDownYesRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        borderDownButtonGroup.add(borderDownYesRadioButton);
        borderDownYesRadioButton.setText("有");

        borderDownNoRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        borderDownButtonGroup.add(borderDownNoRadioButton);
        borderDownNoRadioButton.setSelected(true);
        borderDownNoRadioButton.setText("无");

        borderLeftLabel.setText("左：");

        borderLeftYesRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        borderLeftButtonGroup.add(borderLeftYesRadioButton);
        borderLeftYesRadioButton.setText("有");

        borderLeftNoRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        borderLeftButtonGroup.add(borderLeftNoRadioButton);
        borderLeftNoRadioButton.setSelected(true);
        borderLeftNoRadioButton.setText("无");

        borderRightLabel.setText("右：");

        borderRightYesRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        borderRightButtonGroup.add(borderRightYesRadioButton);
        borderRightYesRadioButton.setText("有");

        borderRightNoRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        borderRightButtonGroup.add(borderRightNoRadioButton);
        borderRightNoRadioButton.setSelected(true);
        borderRightNoRadioButton.setText("无");

        javax.swing.GroupLayout borderOnlyPanelLayout = new javax.swing.GroupLayout(borderOnlyPanel);
        borderOnlyPanel.setLayout(borderOnlyPanelLayout);
        borderOnlyPanelLayout.setHorizontalGroup(
            borderOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borderOnlyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(borderOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(borderUpLabel)
                    .addGroup(borderOnlyPanelLayout.createSequentialGroup()
                        .addGroup(borderOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(borderDownLabel)
                            .addComponent(borderLeftLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(borderOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(borderOnlyPanelLayout.createSequentialGroup()
                                .addComponent(borderDownYesRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(borderDownNoRadioButton))
                            .addGroup(borderOnlyPanelLayout.createSequentialGroup()
                                .addComponent(borderUpYesRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(borderUpNoRadioButton))
                            .addGroup(borderOnlyPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(borderOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(borderOnlyPanelLayout.createSequentialGroup()
                                        .addComponent(borderLeftYesRadioButton)
                                        .addGap(18, 18, 18)
                                        .addComponent(borderLeftNoRadioButton))
                                    .addGroup(borderOnlyPanelLayout.createSequentialGroup()
                                        .addComponent(borderRightYesRadioButton)
                                        .addGap(18, 18, 18)
                                        .addComponent(borderRightNoRadioButton))))))
                    .addComponent(borderRightLabel))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        borderOnlyPanelLayout.setVerticalGroup(
            borderOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borderOnlyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(borderOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(borderOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(borderUpLabel)
                        .addComponent(borderUpYesRadioButton)
                        .addComponent(borderUpNoRadioButton))
                    .addGroup(borderOnlyPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(borderOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(borderDownLabel)
                            .addComponent(borderDownYesRadioButton)
                            .addComponent(borderDownNoRadioButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(borderOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(borderLeftLabel)
                    .addComponent(borderLeftYesRadioButton)
                    .addComponent(borderLeftNoRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(borderOnlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(borderRightLabel)
                    .addComponent(borderRightYesRadioButton)
                    .addComponent(borderRightNoRadioButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout borderPanelLayout = new javax.swing.GroupLayout(borderPanel);
        borderPanel.setLayout(borderPanelLayout);
        borderPanelLayout.setHorizontalGroup(
            borderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(borderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(borderAllRadioButton)
                    .addComponent(borderOnlyRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(borderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(borderOnlyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(borderAllPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        borderPanelLayout.setVerticalGroup(
            borderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borderPanelLayout.createSequentialGroup()
                .addGroup(borderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(borderPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(borderAllRadioButton))
                    .addGroup(borderPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(borderAllPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(borderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(borderPanelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(borderOnlyRadioButton))
                    .addGroup(borderPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(borderOnlyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spacePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(paperLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paperComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(modeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(verticalRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(horizontalRadioButton))
                    .addComponent(borderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paperLabel)
                    .addComponent(paperComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modeLabel)
                    .addComponent(verticalRadioButton)
                    .addComponent(horizontalRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spacePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(borderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

private void spaceRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
// TODO add your handling code here:
    boolean spaceOnly = false;
    if (spaceOnlyRadioButton.isSelected()) {
        spaceOnly = true;
    }
    spaceAllFormattedTextField.setEnabled(!spaceOnly);

    spaceUpLabel.setEnabled(spaceOnly);
    spaceUpFormattedTextField.setEnabled(spaceOnly);
    spaceDownLabel.setEnabled(spaceOnly);
    spaceDownFormattedTextField.setEnabled(spaceOnly);
    spaceLeftLabel.setEnabled(spaceOnly);
    spaceLeftFormattedTextField.setEnabled(spaceOnly);
    spaceRightLabel.setEnabled(spaceOnly);
    spaceRightFormattedTextField.setEnabled(spaceOnly);
}                                                

private void borderRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                  
// TODO add your handling code here:
    boolean borderOnly = false;
    if (borderOnlyRadioButton.isSelected()) {
        borderOnly = true;
    }
    borderAllYesRadioButton.setEnabled(!borderOnly);
    borderAllNoRadioButton.setEnabled(!borderOnly);

    borderUpLabel.setEnabled(borderOnly);
    borderUpYesRadioButton.setEnabled(borderOnly);
    borderUpNoRadioButton.setEnabled(borderOnly);
    borderDownLabel.setEnabled(borderOnly);
    borderDownYesRadioButton.setEnabled(borderOnly);
    borderDownNoRadioButton.setEnabled(borderOnly);
    borderLeftLabel.setEnabled(borderOnly);
    borderLeftYesRadioButton.setEnabled(borderOnly);
    borderLeftNoRadioButton.setEnabled(borderOnly);
    borderRightLabel.setEnabled(borderOnly);
    borderRightYesRadioButton.setEnabled(borderOnly);
    borderRightNoRadioButton.setEnabled(borderOnly);
}                                                 

private void paperComboBoxActionPerformed(java.awt.event.ActionEvent evt) {                                              
// TODO add your handling code here:
    if (paperComboBox.getSelectedItem().equals("自定义")) {// 选中自定义选项

        PaperSizeDialog dialog = new PaperSizeDialog(null, true);// 创建对话框对象

        dialog.setVisible(true);// 显示对话框

        PaperPanel.setPaperSize(dialog.getPaperWidth(), dialog.getPaperHeight());// 获得纸张大小

        dialog.dispose();// 销毁对话框

    }
}                                             

private void paperComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {                                               
// TODO add your handling code here:
}                                              

    // Variables declaration - do not modify                     
    private javax.swing.ButtonGroup borderAllButtonGroup;
    private javax.swing.JRadioButton borderAllNoRadioButton;
    private javax.swing.JPanel borderAllPanel;
    private javax.swing.JRadioButton borderAllRadioButton;
    private javax.swing.JRadioButton borderAllYesRadioButton;
    private javax.swing.ButtonGroup borderButtonGroup;
    private javax.swing.ButtonGroup borderDownButtonGroup;
    private javax.swing.JLabel borderDownLabel;
    private javax.swing.JRadioButton borderDownNoRadioButton;
    private javax.swing.JRadioButton borderDownYesRadioButton;
    private javax.swing.ButtonGroup borderLeftButtonGroup;
    private javax.swing.JLabel borderLeftLabel;
    private javax.swing.JRadioButton borderLeftNoRadioButton;
    private javax.swing.JRadioButton borderLeftYesRadioButton;
    private javax.swing.JPanel borderOnlyPanel;
    private javax.swing.JRadioButton borderOnlyRadioButton;
    private javax.swing.JPanel borderPanel;
    private javax.swing.ButtonGroup borderRightButtonGroup;
    private javax.swing.JLabel borderRightLabel;
    private javax.swing.JRadioButton borderRightNoRadioButton;
    private javax.swing.JRadioButton borderRightYesRadioButton;
    private javax.swing.ButtonGroup borderUpButtonGroup;
    private javax.swing.JLabel borderUpLabel;
    private javax.swing.JRadioButton borderUpNoRadioButton;
    private javax.swing.JRadioButton borderUpYesRadioButton;
    private javax.swing.JRadioButton horizontalRadioButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel modeLabel;
    private javax.swing.JComboBox paperComboBox;
    private javax.swing.JLabel paperLabel;
    private javax.swing.ButtonGroup printModeButtonGroup;
    private javax.swing.JFormattedTextField spaceAllFormattedTextField;
    private javax.swing.JRadioButton spaceAllRadioButton;
    private javax.swing.ButtonGroup spaceButtonGroup;
    private javax.swing.JFormattedTextField spaceDownFormattedTextField;
    private javax.swing.JLabel spaceDownLabel;
    private javax.swing.JFormattedTextField spaceLeftFormattedTextField;
    private javax.swing.JLabel spaceLeftLabel;
    private javax.swing.JPanel spaceOnlyPanel;
    private javax.swing.JRadioButton spaceOnlyRadioButton;
    private javax.swing.JPanel spacePanel;
    private javax.swing.JFormattedTextField spaceRightFormattedTextField;
    private javax.swing.JLabel spaceRightLabel;
    private javax.swing.JFormattedTextField spaceUpFormattedTextField;
    private javax.swing.JLabel spaceUpLabel;
    private javax.swing.JRadioButton verticalRadioButton;
    // End of variables declaration                   

    public boolean isBorderAll() {
        return borderAllRadioButton.isSelected();
    }

    public boolean isBorderAllYes() {
        return borderAllYesRadioButton.isSelected();
    }

    public boolean isBorderDownYes() {
        return borderDownYesRadioButton.isSelected();
    }

    public boolean isBorderLeftYes() {
        return borderLeftYesRadioButton.isSelected();
    }

    public boolean isBorderRightYes() {
        return borderRightYesRadioButton.isSelected();
    }

    public boolean isBorderUpYes() {
        return borderUpYesRadioButton.isSelected();
    }

    public boolean getVertical() {
        return verticalRadioButton.isSelected();
    }

    public int getPaperIndex() {
        return paperComboBox.getSelectedIndex();
    }

    public boolean isSpaceAll() {
        return spaceAllRadioButton.isSelected();
    }

    public int getSpaceAll() {
        return Integer.valueOf(spaceAllFormattedTextField.getText());
    }

    public int getSpaceDown() {
        return Integer.valueOf(spaceDownFormattedTextField.getText());
    }

    public int getSpaceLeft() {
        return Integer.valueOf(spaceLeftFormattedTextField.getText());
    }

    public int getSpaceRight() {
        return Integer.valueOf(spaceRightFormattedTextField.getText());
    }

    public int getSpaceUp() {
        return Integer.valueOf(spaceUpFormattedTextField.getText());
    }
}
