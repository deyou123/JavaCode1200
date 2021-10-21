/*
 * PrintFrame.java
 *
 * Created on 2008年7月30日, 上午8:52
 */
package com.mwq.print;

import com.mwq.print.frame.ProgressBarDialog;
import com.mwq.print.mwing.ImageLabel;
import com.mwq.print.mwing.PaperPanel;
import com.mwq.print.mwing.ParagraphTextArea;
import com.mwq.print.mwing.TablePanel;
import com.mwq.print.tool.InstancePool;
import com.mwq.print.tool.ScreenSize;
import java.awt.Component;
import java.awt.Image;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.Rectangle;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.BufferedImage;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class PrintFrame extends javax.swing.JFrame {

    /** Creates new form PrintFrame */
    public PrintFrame() {
        initComponents();
        InstancePool.setBasePanel(basePanel);
        InstancePool.setPagePanel(pagePanel);
        InstancePool.setAlignPanel(alignPanel);
        InstancePool.setWaterPanel(waterPanel);
        InstancePool.setBackPanel(backPanel);
        addPapersToViewPanel();
        addAdjustmentListenerForVerticalScrollBar();
        ScreenSize.centered(this);
    }

    //为垂直滚动条添加滚动事件监听器，以处理按钮是否可用
    private void addAdjustmentListenerForVerticalScrollBar() {
        JScrollBar verticalScrollBar =
                previewScrollPane.getVerticalScrollBar();// 获得滚动面板的垂直滚动条对象

        verticalScrollBar.addAdjustmentListener(new AdjustmentListener() {// 为滚动条添加调整事件监听器

            public void adjustmentValueChanged(AdjustmentEvent e) {// 滚动条位置发生变化时执行

                Rectangle visibleRect = viewPanel.getVisibleRect();// 获得面板的可视区域

                int height = viewPanel.getPreferredSize().height;// 获得面板的首选高度

                if (visibleRect.y == 0) {// 滚动条在最上方

                    firstButton.setEnabled(false);
                    previousButton.setEnabled(false);
                    if (height < visibleRect.height) {// 面板内容全部可见

                        nextButton.setEnabled(false);
                        lastButton.setEnabled(false);
                    } else {
                        nextButton.setEnabled(true);
                        lastButton.setEnabled(true);
                    }
                } else {
                    firstButton.setEnabled(true);
                    previousButton.setEnabled(true);
                    if (visibleRect.y == height - visibleRect.height) {// 滚动条在最下方

                        nextButton.setEnabled(false);
                        lastButton.setEnabled(false);
                    } else {
                        nextButton.setEnabled(true);
                        lastButton.setEnabled(true);
                    }
                }
            }
        });
    }

    private void addPapersToViewPanel() {
        final ProgressBarDialog progressBarDialog = new ProgressBarDialog(null, true);// 创建进度条对话框

        new Thread() {// 创建并开启线程

            @Override
            public void run() {// 重构该方法
                //打印内容

                Map contentMap = DataForm.getContentMap();
                Iterator keyIt = contentMap.keySet().iterator();

                //打印页面
                PaperPanel paper = new PaperPanel();
                PaperPanel.setPage(1);
                paper.initBackground();
                paper = new PaperPanel();

                papersPanel.add(paper);

                //页面属性
                final int X = PaperPanel.LeftSpace + PaperPanel.LeftBorder + 1;
                final int Y = PaperPanel.UpSpace + PaperPanel.UpBorder + 1;
                final int WIDTH = PaperPanel.Width - X - PaperPanel.RightSpace - PaperPanel.RightBorder;
                final int HEIGHT = PaperPanel.Height - Y - PaperPanel.DownSpace - PaperPanel.DownBorder;
                int y = Y;

                //遍例打印内容
                while (keyIt.hasNext()) {
                    if (y == -1) {
                        paper = new PaperPanel();
                        papersPanel.add(paper);
                        y = Y;
                    }

                    Object value = contentMap.get(keyIt.next());
                    if (value instanceof String) {// 文本

                        String text = "    " + value.toString();// 添加首行空格

                        ParagraphTextArea paragraph = new ParagraphTextArea(text, WIDTH);// 创建文本区域对象

                        int paragraphHeight = (int) paragraph.getPreferredSize().getHeight();// 获得文本段落的高度

                        paragraph.setSize(WIDTH, paragraphHeight);// 设置文本段落的大小

                        BufferedImage paragraphBF = new BufferedImage(WIDTH, paragraphHeight,
                                BufferedImage.TYPE_INT_RGB);
                        paragraph.paint(paragraphBF.getGraphics());// 绘制文本段落

                        int interceptY = 0;// 定义截取坐标

                        while (interceptY < paragraphHeight) {
                            if (y == -1) {// 创建一个新的打印页

                                paper = new PaperPanel();
                                papersPanel.add(paper);
                                y = Y;
                            }
                            int interceptHeight = HEIGHT;// 默认截取高度为整页高度

                            if (y != Y) {// 在页面中已经打印了部分内容

                                interceptHeight = HEIGHT - (y - Y);
                            }
                            int spareHeight = paragraphHeight - interceptY;// 文本段落的剩余高度

                            boolean hasSpare = false;// 默认为无剩余

                            if (spareHeight < interceptHeight) {// 有剩余

                                interceptHeight = spareHeight;
                                hasSpare = true;
                            }
                            interceptHeight = getInterceptHeight(paragraphBF, interceptY, interceptHeight);// 获得截取高度

                            BufferedImage subimage = paragraphBF.getSubimage(0, interceptY, WIDTH, interceptHeight);// 截取

                            JLabel paragraphLabel = new JLabel(new ImageIcon(subimage));// 创建标签

                            paragraphLabel.setBounds(X, y, WIDTH, interceptHeight);// 设置位置

                            paper.add(paragraphLabel);// 添加到打印页面

                            interceptY += interceptHeight;// 累加截取坐标

                            if (hasSpare) {// 有剩余文本段落未添加

                                y += interceptHeight;
                            } else {// 无剩余文本段落未添加

                                y = -1;
                            }
                        }
                    } else {
                        Object[] info = (Object[]) value;
                        if (info.length == 2) {// 图象

                            String[] imgInfo = (String[]) info;// 获得图象信息数组

                            BufferedImage imgBF = null;
                            try {
                                imgBF = ImageIO.read(new File(imgInfo[0]));
                            } catch (IOException ex) {
                                Logger.getLogger(PrintFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            int imgWidth = imgBF.getWidth();// 图象宽度

                            int imgHeight = imgBF.getHeight();// 图象高度

                            // 根据打印设置缩放图象
                            Enumeration zoomEnumeration = alignPanel.getZoomEnumeration();
                            while (zoomEnumeration.hasMoreElements()) {
                                JRadioButton radioButton = (JRadioButton) zoomEnumeration.nextElement();
                                if (radioButton.isSelected()) {
                                    String text = radioButton.getText();// 获得缩放方式

                                    if (text.equals("充满")) {
                                        if (imgWidth != WIDTH) {
                                            imgBF = getScaledBF(WIDTH, imgBF);// 缩放至与页面同宽

                                            imgWidth = imgBF.getWidth();// 获得缩放后的宽度

                                            imgHeight = imgBF.getHeight();// 获得缩放后的高度

                                        }
                                    }
                                    if (text.equals("自定义")) {
                                        if (imgWidth < WIDTH) {// 图片宽度小于页面宽度

                                            Enumeration minEnumeration = alignPanel.getMinEnumeration();
                                            while (minEnumeration.hasMoreElements()) {
                                                JRadioButton minRadioButton = (JRadioButton) minEnumeration.nextElement();
                                                if (minRadioButton.isSelected()) {
                                                    String minText = minRadioButton.getText();// 获得小图片的缩放方式

                                                    if (minText.equals("充满")) {
                                                        imgBF = getScaledBF(WIDTH, imgBF);// 缩放至与页面同宽

                                                        imgWidth = imgBF.getWidth();// 获得缩放后的宽度

                                                        imgHeight = imgBF.getHeight();// 获得缩放后的高度

                                                    }
                                                    if (minText.equals("自定义")) {
                                                        imgWidth = (int) (imgBF.getWidth() * alignPanel.getMinPresent());// 计算宽度

                                                        imgBF = getScaledBF(imgWidth, imgBF);// 缩放图片

                                                        imgHeight = imgBF.getHeight();// 获得高度

                                                    }
                                                    break;
                                                }
                                            }
                                        } else {
                                            Enumeration maxEnumeration = alignPanel.getMaxEnumeration();
                                            while (maxEnumeration.hasMoreElements()) {
                                                JRadioButton maxRadioButton = (JRadioButton) maxEnumeration.nextElement();
                                                if (maxRadioButton.isSelected()) {
                                                    String maxText = maxRadioButton.getText();// 获得缩放方式

                                                    if (maxText.equals("充满")) {
                                                        imgBF = getScaledBF(WIDTH, imgBF);// 缩放至与页面同宽

                                                        imgWidth = imgBF.getWidth();// 获得缩放后的宽度

                                                        imgHeight = imgBF.getHeight();// 获得缩放后的高度

                                                    }
                                                    if (maxText.equals("自定义")) {
                                                        imgWidth = (int) (imgBF.getWidth() * alignPanel.getMaxPresent());// 计算宽度

                                                        imgBF = getScaledBF(imgWidth, imgBF);// 缩放图片

                                                        imgHeight = imgBF.getHeight();// 获得高度

                                                    }
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    break;
                                }
                            }


                            if (contentMap.size() == 1 && imgHeight < HEIGHT) {// 只有一张图片，并且高度小于页面高度

                                ImageLabel imageLabel = new ImageLabel(imgBF, imgInfo[1]);// 创建图片标签

                                imageLabel.setBounds(X, Y, WIDTH, HEIGHT);// 设置位置

                                paper.add(imageLabel);// 添加至页面

                            } else {
                                double page = (double) (y - Y + imgHeight) / HEIGHT;// 计算需要的页数

                                if (page <= 1) {// 只需要一页

                                    ImageLabel imageLabel = new ImageLabel(imgBF, imgInfo[1]);// 创建图片标签

                                    int h = (int) imageLabel.getPreferredSize().getHeight();// 获得需要的高度

                                    imageLabel.setBounds(X, y, WIDTH, h);// 设置位置

                                    paper.add(imageLabel);// 添加至页面

                                    if (page < 1) {
                                        y += h;
                                    } else {
                                        y = -1;
                                    }
                                } else {
                                    int imgY = 0;// 截取图片的纵坐标索引
                                    // 填充当前页的剩余部分

                                    int spareHeight = HEIGHT - (y - Y);
                                    BufferedImage subimage = imgBF.getSubimage(0, imgY, imgWidth, spareHeight);
                                    ImageLabel imageLabel = new ImageLabel(subimage, null);
                                    imageLabel.setBounds(X, y, WIDTH, spareHeight);
                                    paper.add(imageLabel);
                                    imgY += spareHeight;
                                    // 截取整页图片
                                    for (int p = 2; p <= page; p++) {
                                        paper = new PaperPanel();
                                        papersPanel.add(paper);
                                        subimage = imgBF.getSubimage(0, imgY, imgWidth, HEIGHT);
                                        imageLabel = new ImageLabel(subimage, null);
                                        imageLabel.setBounds(X, Y, WIDTH, HEIGHT);
                                        paper.add(imageLabel);
                                        imgY += HEIGHT;
                                    }
                                    // 添加剩余的图片
                                    if ((int) page == page) {
                                        y = -1;
                                    } else {
                                        paper = new PaperPanel();
                                        papersPanel.add(paper);
                                        subimage = imgBF.getSubimage(0, imgY, imgWidth, imgHeight - imgY);
                                        imageLabel = new ImageLabel(subimage, imgInfo[1]);
                                        int h = (int) imageLabel.getPreferredSize().getHeight();
                                        imageLabel.setBounds(X, Y, WIDTH, h);
                                        paper.add(imageLabel);
                                        y = Y + h;
                                    }
                                }
                            }
                        } else {// 表格

                            TablePanel tablePanel = new TablePanel(info);// 创建表格面板

                            tablePanel.continueTable(HEIGHT - (y - Y));
                            int h = tablePanel.getPreferredSize().height;// 获得面板高度

                            tablePanel.setBounds(X, y, WIDTH, h);
                            paper.add(tablePanel);
                            while (tablePanel.hasContinueTable()) {
                                paper = new PaperPanel();
                                papersPanel.add(paper);
                                tablePanel = new TablePanel();
                                tablePanel.continueTable(HEIGHT);
                                h = tablePanel.getPreferredSize().height;
                                tablePanel.setBounds(X, Y, WIDTH, h);
                                paper.add(tablePanel);
                            }
                            y = Y + h;
                        }
                    }
                }
                progressBarDialog.dispose();// 销毁进度条对话框

            }
        }.start();

        progressBarDialog.setVisible(true);// 显示进度条对话框

    }

    private BufferedImage getScaledBF(int width, BufferedImage imgBF) {
        int height = imgBF.getHeight() * width / imgBF.getWidth();
        Image img = imgBF.getScaledInstance(width, height, BufferedImage.SCALE_DEFAULT);
        imgBF = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        imgBF.getGraphics().drawImage(img, 0, 0, null);
        return imgBF;
    }

    private int getInterceptHeight(BufferedImage bufferedImage, int interceptY, int interceptHeight) {
        for (; interceptHeight > 0; interceptHeight--) {// 递减截取高度

            boolean isWhiteInLine = true;// 默认在该像素行上没有文本

            int y = interceptY + interceptHeight - 1;
            for (int x = 0; x < bufferedImage.getWidth(); x++) {// 遍历该像素行的所有像素列

                if (bufferedImage.getRGB(x, y) != 0XFFFFFFFF) {// 判断指定像素点的RGB值

                    isWhiteInLine = false;// 在该像素行上有文本

                    break;
                }
            }
            if (isWhiteInLine) {// 如果在该像素行上没有文本，则跳出循环

                break;
            }
        }
        return interceptHeight;// 返回截取高度

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        previewPanel = new javax.swing.JPanel();
        previewScrollPane = new javax.swing.JScrollPane();
        viewPanel = new javax.swing.JPanel();
        papersPanel = new javax.swing.JPanel();
        previewButtonPanel = new javax.swing.JPanel();
        firstButton = new javax.swing.JButton();
        previousButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        lastButton = new javax.swing.JButton();
        setupPanel = new javax.swing.JPanel();
        setupTabbedPane = new javax.swing.JTabbedPane();
        basePanel = new com.mwq.print.tabbedPane.BasePanel();
        pagePanel = new com.mwq.print.tabbedPane.PagePanel();
        alignPanel = new com.mwq.print.tabbedPane.AlignPanel();
        waterPanel = new com.mwq.print.tabbedPane.WaterPanel();
        backPanel = new com.mwq.print.tabbedPane.BackPanel();
        printPanel = new com.mwq.print.tabbedPane.PrintPanel();
        setupButtonPanel = new javax.swing.JPanel();
        submitButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("万能打印");
        getContentPane().setLayout(new java.awt.BorderLayout(5, 5));

        previewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("打印预览"));
        previewPanel.setLayout(new java.awt.BorderLayout());

        papersPanel.setLayout(new java.awt.GridLayout(0, 1, 0, 10));
        viewPanel.add(papersPanel);

        previewScrollPane.setViewportView(viewPanel);

        previewPanel.add(previewScrollPane, java.awt.BorderLayout.CENTER);

        firstButton.setText("顶端");
        firstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstButtonActionPerformed(evt);
            }
        });
        previewButtonPanel.add(firstButton);

        previousButton.setText("向上");
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });
        previewButtonPanel.add(previousButton);

        nextButton.setText("向下");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        previewButtonPanel.add(nextButton);

        lastButton.setText("底端");
        lastButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastButtonActionPerformed(evt);
            }
        });
        previewButtonPanel.add(lastButton);

        previewPanel.add(previewButtonPanel, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(previewPanel, java.awt.BorderLayout.CENTER);

        setupPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("打印设置"));
        setupPanel.setLayout(new java.awt.BorderLayout());

        setupTabbedPane.setFocusable(false);
        setupTabbedPane.addTab("基本", basePanel);
        setupTabbedPane.addTab("页码", pagePanel);
        setupTabbedPane.addTab("缩放与对齐", alignPanel);
        setupTabbedPane.addTab("水印", waterPanel);
        setupTabbedPane.addTab("背景", backPanel);
        setupTabbedPane.addTab("打印", printPanel);

        setupPanel.add(setupTabbedPane, java.awt.BorderLayout.EAST);

        setupButtonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        submitButton.setText("确定");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });
        setupButtonPanel.add(submitButton);

        printButton.setText("打印");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });
        setupButtonPanel.add(printButton);

        setupPanel.add(setupButtonPanel, java.awt.BorderLayout.SOUTH);

        getContentPane().add(setupPanel, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>                        

private void firstButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
// TODO add your handling code here:
    previewScrollPane.getVerticalScrollBar().setValue(0);
}                                           

private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
// TODO add your handling code here:
    Rectangle visibleRect = viewPanel.getVisibleRect();
    int scrollToY = visibleRect.y - visibleRect.height + 50;
    previewScrollPane.getVerticalScrollBar().setValue(scrollToY);
}                                              

private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
// TODO add your handling code here:
    Rectangle visibleRect = viewPanel.getVisibleRect();
    int scrollToY = visibleRect.y + visibleRect.height - 50;
    previewScrollPane.getVerticalScrollBar().setValue(scrollToY);
}                                          

private void lastButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
// TODO add your handling code here:
    previewScrollPane.getVerticalScrollBar().setValue(
            previewScrollPane.getPreferredSize().height);
}                                          

private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
// TODO add your handling code here:
    papersPanel.removeAll();
    addPapersToViewPanel();
    SwingUtilities.updateComponentTreeUI(previewScrollPane);
}                                            

private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
// TODO add your handling code here:
submitButtonActionPerformed(null);// 刷新打印预览
HashPrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();// 创建打印属性集对象
// 确定打印纸张
int paperIndex = basePanel.getPaperIndex();
switch (paperIndex) {
    case 0:
        attributeSet.add(MediaSizeName.ISO_A2);
        break;
    case 1:
        attributeSet.add(MediaSizeName.ISO_A3);
        break;
    case 2:
        attributeSet.add(MediaSizeName.ISO_A4);
        break;
    case 3:
        attributeSet.add(MediaSizeName.ISO_A5);
        break;
    case 4:
        attributeSet.add(MediaSizeName.ISO_B3);
        break;
    case 5:
        attributeSet.add(MediaSizeName.ISO_B4);
        break;
    case 6:
        attributeSet.add(MediaSizeName.ISO_B5);
        break;
    default:
        attributeSet.add(MediaSizeName.ISO_A4);
}
PageFormat pageFormat = new PageFormat();// 创建页面格式化对象
Paper p = new Paper();// 创建纸张对象
if (basePanel.getVertical()) {// 纵向打印
    attributeSet.add(OrientationRequested.PORTRAIT);// 纵向打印
    pageFormat.setOrientation(PageFormat.PORTRAIT);// 纵向打印
    p.setSize(PaperPanel.Width, PaperPanel.Height);// 设置纸张大小
    int areaWidth = PaperPanel.Width 
            - PaperPanel.LeftSpace - PaperPanel.RightSpace + 42;// 计算打印区域宽度
    int areaHeight = PaperPanel.Height 
            - PaperPanel.UpSpace - PaperPanel.DownSpace + 42;// 计算打印区域高度
    p.setImageableArea(PaperPanel.LeftSpace - 20, PaperPanel.UpSpace - 20, 
            areaWidth, areaHeight);// 设置纸张的打印区域
} else {// 横向打印
    attributeSet.add(OrientationRequested.LANDSCAPE);// 横向打印
    pageFormat.setOrientation(PageFormat.LANDSCAPE);// 横向打印
    p.setSize(PaperPanel.Height, PaperPanel.Width);// 设置纸张大小
    int areaWidth = PaperPanel.Height 
            - PaperPanel.UpSpace - PaperPanel.DownSpace + 42;// 计算打印区域宽度
    int areaHeight = PaperPanel.Width 
            - PaperPanel.LeftSpace - PaperPanel.RightSpace + 42;// 计算打印区域高度
    p.setImageableArea(PaperPanel.LeftSpace - 20, PaperPanel.UpSpace - 20, 
            areaWidth, areaHeight);// 设置纸张的打印区域
}
pageFormat.setPaper(p);// 将纸张对象设置到页面格式化对象
PrinterJob printerJob = PrinterJob.getPrinterJob();// 获得打印控制类对象
if (printerJob.printDialog(attributeSet)) {// 显示打印对话框
    Book book = new Book();// 创建打印文档对象
    Printable paper;// 声明打印页对象
    Component[] components = papersPanel.getComponents();// 打印页对象集
    int pageAmount = components.length;// 打印页数
    int printShare = printPanel.getPrintShare();// 打印份数
    String bluePrint = printPanel.getBluePrint();// 获得打印方案
    if (bluePrint.equals("ShareForehand")) {// 逐份顺序打印
        for (int share = 0; share < printShare; share++) {// 遍历份
            for (int page = 0; page <pageAmount; page++) {// 遍历页
                paper = (Printable) components[page];// 获得打印页
                book.append(paper, pageFormat);// 添加到打印文档
            }
        }
    } else if (bluePrint.equals("PageBackhander")) {// 逐页逆序打印
        for (int page = pageAmount - 1; page >= 0; page--) {// 遍历页
            paper = (Printable) components[page];// 获得打印页
            book.append(paper, pageFormat, printShare);// 添加到打印文档
        }
    } else if (bluePrint.equals("ShareBackhander")) {// 逐份逆序打印
        for (int share = 0; share < printShare; share++) {// 遍历份
            for (int page = pageAmount-1; page>=0; page--) {// 遍历页
                paper = (Printable) components[page];// 获得打印页
                book.append(paper, pageFormat);// 添加到打印文档
            }
        }
    } else {// 逐页顺序打印
        for (int page = 0; page < pageAmount; page++) {// 遍历页
            paper = (Printable) components[page];// 获得打印页
            book.append(paper, pageFormat, printShare);// 添加到打印文档
        }
    }
    printerJob.setPageable(book);// 设置打印文档
    try {
        printerJob.print();// 执行打印任务
    } catch (PrinterException ex) {
        Logger.getLogger(PrintFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
}
}                                           

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PrintFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PrintFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PrintFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(PrintFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PrintFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private com.mwq.print.tabbedPane.AlignPanel alignPanel;
    private com.mwq.print.tabbedPane.BackPanel backPanel;
    private com.mwq.print.tabbedPane.BasePanel basePanel;
    private javax.swing.JButton firstButton;
    private javax.swing.JButton lastButton;
    private javax.swing.JButton nextButton;
    private com.mwq.print.tabbedPane.PagePanel pagePanel;
    private javax.swing.JPanel papersPanel;
    private javax.swing.JPanel previewButtonPanel;
    private javax.swing.JPanel previewPanel;
    private javax.swing.JScrollPane previewScrollPane;
    private javax.swing.JButton previousButton;
    private javax.swing.JButton printButton;
    private com.mwq.print.tabbedPane.PrintPanel printPanel;
    private javax.swing.JPanel setupButtonPanel;
    private javax.swing.JPanel setupPanel;
    private javax.swing.JTabbedPane setupTabbedPane;
    private javax.swing.JButton submitButton;
    private javax.swing.JPanel viewPanel;
    private com.mwq.print.tabbedPane.WaterPanel waterPanel;
    // End of variables declaration                   
}
