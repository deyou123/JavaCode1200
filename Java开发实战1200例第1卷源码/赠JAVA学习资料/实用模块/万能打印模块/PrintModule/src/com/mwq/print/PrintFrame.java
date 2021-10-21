/*
 * PrintFrame.java
 *
 * Created on 2008��7��30��, ����8:52
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
 *��Ȩ���У����տƼ����޹�˾
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

    //Ϊ��ֱ��������ӹ����¼����������Դ���ť�Ƿ����
    private void addAdjustmentListenerForVerticalScrollBar() {
        JScrollBar verticalScrollBar =
                previewScrollPane.getVerticalScrollBar();// ��ù������Ĵ�ֱ����������

        verticalScrollBar.addAdjustmentListener(new AdjustmentListener() {// Ϊ��������ӵ����¼�������

            public void adjustmentValueChanged(AdjustmentEvent e) {// ������λ�÷����仯ʱִ��

                Rectangle visibleRect = viewPanel.getVisibleRect();// ������Ŀ�������

                int height = viewPanel.getPreferredSize().height;// ���������ѡ�߶�

                if (visibleRect.y == 0) {// �����������Ϸ�

                    firstButton.setEnabled(false);
                    previousButton.setEnabled(false);
                    if (height < visibleRect.height) {// �������ȫ���ɼ�

                        nextButton.setEnabled(false);
                        lastButton.setEnabled(false);
                    } else {
                        nextButton.setEnabled(true);
                        lastButton.setEnabled(true);
                    }
                } else {
                    firstButton.setEnabled(true);
                    previousButton.setEnabled(true);
                    if (visibleRect.y == height - visibleRect.height) {// �����������·�

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
        final ProgressBarDialog progressBarDialog = new ProgressBarDialog(null, true);// �����������Ի���

        new Thread() {// �����������߳�

            @Override
            public void run() {// �ع��÷���
                //��ӡ����

                Map contentMap = DataForm.getContentMap();
                Iterator keyIt = contentMap.keySet().iterator();

                //��ӡҳ��
                PaperPanel paper = new PaperPanel();
                PaperPanel.setPage(1);
                paper.initBackground();
                paper = new PaperPanel();

                papersPanel.add(paper);

                //ҳ������
                final int X = PaperPanel.LeftSpace + PaperPanel.LeftBorder + 1;
                final int Y = PaperPanel.UpSpace + PaperPanel.UpBorder + 1;
                final int WIDTH = PaperPanel.Width - X - PaperPanel.RightSpace - PaperPanel.RightBorder;
                final int HEIGHT = PaperPanel.Height - Y - PaperPanel.DownSpace - PaperPanel.DownBorder;
                int y = Y;

                //������ӡ����
                while (keyIt.hasNext()) {
                    if (y == -1) {
                        paper = new PaperPanel();
                        papersPanel.add(paper);
                        y = Y;
                    }

                    Object value = contentMap.get(keyIt.next());
                    if (value instanceof String) {// �ı�

                        String text = "    " + value.toString();// ������пո�

                        ParagraphTextArea paragraph = new ParagraphTextArea(text, WIDTH);// �����ı��������

                        int paragraphHeight = (int) paragraph.getPreferredSize().getHeight();// ����ı�����ĸ߶�

                        paragraph.setSize(WIDTH, paragraphHeight);// �����ı�����Ĵ�С

                        BufferedImage paragraphBF = new BufferedImage(WIDTH, paragraphHeight,
                                BufferedImage.TYPE_INT_RGB);
                        paragraph.paint(paragraphBF.getGraphics());// �����ı�����

                        int interceptY = 0;// �����ȡ����

                        while (interceptY < paragraphHeight) {
                            if (y == -1) {// ����һ���µĴ�ӡҳ

                                paper = new PaperPanel();
                                papersPanel.add(paper);
                                y = Y;
                            }
                            int interceptHeight = HEIGHT;// Ĭ�Ͻ�ȡ�߶�Ϊ��ҳ�߶�

                            if (y != Y) {// ��ҳ�����Ѿ���ӡ�˲�������

                                interceptHeight = HEIGHT - (y - Y);
                            }
                            int spareHeight = paragraphHeight - interceptY;// �ı������ʣ��߶�

                            boolean hasSpare = false;// Ĭ��Ϊ��ʣ��

                            if (spareHeight < interceptHeight) {// ��ʣ��

                                interceptHeight = spareHeight;
                                hasSpare = true;
                            }
                            interceptHeight = getInterceptHeight(paragraphBF, interceptY, interceptHeight);// ��ý�ȡ�߶�

                            BufferedImage subimage = paragraphBF.getSubimage(0, interceptY, WIDTH, interceptHeight);// ��ȡ

                            JLabel paragraphLabel = new JLabel(new ImageIcon(subimage));// ������ǩ

                            paragraphLabel.setBounds(X, y, WIDTH, interceptHeight);// ����λ��

                            paper.add(paragraphLabel);// ��ӵ���ӡҳ��

                            interceptY += interceptHeight;// �ۼӽ�ȡ����

                            if (hasSpare) {// ��ʣ���ı�����δ���

                                y += interceptHeight;
                            } else {// ��ʣ���ı�����δ���

                                y = -1;
                            }
                        }
                    } else {
                        Object[] info = (Object[]) value;
                        if (info.length == 2) {// ͼ��

                            String[] imgInfo = (String[]) info;// ���ͼ����Ϣ����

                            BufferedImage imgBF = null;
                            try {
                                imgBF = ImageIO.read(new File(imgInfo[0]));
                            } catch (IOException ex) {
                                Logger.getLogger(PrintFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            int imgWidth = imgBF.getWidth();// ͼ����

                            int imgHeight = imgBF.getHeight();// ͼ��߶�

                            // ���ݴ�ӡ��������ͼ��
                            Enumeration zoomEnumeration = alignPanel.getZoomEnumeration();
                            while (zoomEnumeration.hasMoreElements()) {
                                JRadioButton radioButton = (JRadioButton) zoomEnumeration.nextElement();
                                if (radioButton.isSelected()) {
                                    String text = radioButton.getText();// ������ŷ�ʽ

                                    if (text.equals("����")) {
                                        if (imgWidth != WIDTH) {
                                            imgBF = getScaledBF(WIDTH, imgBF);// ��������ҳ��ͬ��

                                            imgWidth = imgBF.getWidth();// ������ź�Ŀ��

                                            imgHeight = imgBF.getHeight();// ������ź�ĸ߶�

                                        }
                                    }
                                    if (text.equals("�Զ���")) {
                                        if (imgWidth < WIDTH) {// ͼƬ���С��ҳ����

                                            Enumeration minEnumeration = alignPanel.getMinEnumeration();
                                            while (minEnumeration.hasMoreElements()) {
                                                JRadioButton minRadioButton = (JRadioButton) minEnumeration.nextElement();
                                                if (minRadioButton.isSelected()) {
                                                    String minText = minRadioButton.getText();// ���СͼƬ�����ŷ�ʽ

                                                    if (minText.equals("����")) {
                                                        imgBF = getScaledBF(WIDTH, imgBF);// ��������ҳ��ͬ��

                                                        imgWidth = imgBF.getWidth();// ������ź�Ŀ��

                                                        imgHeight = imgBF.getHeight();// ������ź�ĸ߶�

                                                    }
                                                    if (minText.equals("�Զ���")) {
                                                        imgWidth = (int) (imgBF.getWidth() * alignPanel.getMinPresent());// ������

                                                        imgBF = getScaledBF(imgWidth, imgBF);// ����ͼƬ

                                                        imgHeight = imgBF.getHeight();// ��ø߶�

                                                    }
                                                    break;
                                                }
                                            }
                                        } else {
                                            Enumeration maxEnumeration = alignPanel.getMaxEnumeration();
                                            while (maxEnumeration.hasMoreElements()) {
                                                JRadioButton maxRadioButton = (JRadioButton) maxEnumeration.nextElement();
                                                if (maxRadioButton.isSelected()) {
                                                    String maxText = maxRadioButton.getText();// ������ŷ�ʽ

                                                    if (maxText.equals("����")) {
                                                        imgBF = getScaledBF(WIDTH, imgBF);// ��������ҳ��ͬ��

                                                        imgWidth = imgBF.getWidth();// ������ź�Ŀ��

                                                        imgHeight = imgBF.getHeight();// ������ź�ĸ߶�

                                                    }
                                                    if (maxText.equals("�Զ���")) {
                                                        imgWidth = (int) (imgBF.getWidth() * alignPanel.getMaxPresent());// ������

                                                        imgBF = getScaledBF(imgWidth, imgBF);// ����ͼƬ

                                                        imgHeight = imgBF.getHeight();// ��ø߶�

                                                    }
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    break;
                                }
                            }


                            if (contentMap.size() == 1 && imgHeight < HEIGHT) {// ֻ��һ��ͼƬ�����Ҹ߶�С��ҳ��߶�

                                ImageLabel imageLabel = new ImageLabel(imgBF, imgInfo[1]);// ����ͼƬ��ǩ

                                imageLabel.setBounds(X, Y, WIDTH, HEIGHT);// ����λ��

                                paper.add(imageLabel);// �����ҳ��

                            } else {
                                double page = (double) (y - Y + imgHeight) / HEIGHT;// ������Ҫ��ҳ��

                                if (page <= 1) {// ֻ��Ҫһҳ

                                    ImageLabel imageLabel = new ImageLabel(imgBF, imgInfo[1]);// ����ͼƬ��ǩ

                                    int h = (int) imageLabel.getPreferredSize().getHeight();// �����Ҫ�ĸ߶�

                                    imageLabel.setBounds(X, y, WIDTH, h);// ����λ��

                                    paper.add(imageLabel);// �����ҳ��

                                    if (page < 1) {
                                        y += h;
                                    } else {
                                        y = -1;
                                    }
                                } else {
                                    int imgY = 0;// ��ȡͼƬ������������
                                    // ��䵱ǰҳ��ʣ�ಿ��

                                    int spareHeight = HEIGHT - (y - Y);
                                    BufferedImage subimage = imgBF.getSubimage(0, imgY, imgWidth, spareHeight);
                                    ImageLabel imageLabel = new ImageLabel(subimage, null);
                                    imageLabel.setBounds(X, y, WIDTH, spareHeight);
                                    paper.add(imageLabel);
                                    imgY += spareHeight;
                                    // ��ȡ��ҳͼƬ
                                    for (int p = 2; p <= page; p++) {
                                        paper = new PaperPanel();
                                        papersPanel.add(paper);
                                        subimage = imgBF.getSubimage(0, imgY, imgWidth, HEIGHT);
                                        imageLabel = new ImageLabel(subimage, null);
                                        imageLabel.setBounds(X, Y, WIDTH, HEIGHT);
                                        paper.add(imageLabel);
                                        imgY += HEIGHT;
                                    }
                                    // ���ʣ���ͼƬ
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
                        } else {// ���

                            TablePanel tablePanel = new TablePanel(info);// ����������

                            tablePanel.continueTable(HEIGHT - (y - Y));
                            int h = tablePanel.getPreferredSize().height;// ������߶�

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
                progressBarDialog.dispose();// ���ٽ������Ի���

            }
        }.start();

        progressBarDialog.setVisible(true);// ��ʾ�������Ի���

    }

    private BufferedImage getScaledBF(int width, BufferedImage imgBF) {
        int height = imgBF.getHeight() * width / imgBF.getWidth();
        Image img = imgBF.getScaledInstance(width, height, BufferedImage.SCALE_DEFAULT);
        imgBF = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        imgBF.getGraphics().drawImage(img, 0, 0, null);
        return imgBF;
    }

    private int getInterceptHeight(BufferedImage bufferedImage, int interceptY, int interceptHeight) {
        for (; interceptHeight > 0; interceptHeight--) {// �ݼ���ȡ�߶�

            boolean isWhiteInLine = true;// Ĭ���ڸ���������û���ı�

            int y = interceptY + interceptHeight - 1;
            for (int x = 0; x < bufferedImage.getWidth(); x++) {// �����������е�����������

                if (bufferedImage.getRGB(x, y) != 0XFFFFFFFF) {// �ж�ָ�����ص��RGBֵ

                    isWhiteInLine = false;// �ڸ������������ı�

                    break;
                }
            }
            if (isWhiteInLine) {// ����ڸ���������û���ı���������ѭ��

                break;
            }
        }
        return interceptHeight;// ���ؽ�ȡ�߶�

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
        setTitle("���ܴ�ӡ");
        getContentPane().setLayout(new java.awt.BorderLayout(5, 5));

        previewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("��ӡԤ��"));
        previewPanel.setLayout(new java.awt.BorderLayout());

        papersPanel.setLayout(new java.awt.GridLayout(0, 1, 0, 10));
        viewPanel.add(papersPanel);

        previewScrollPane.setViewportView(viewPanel);

        previewPanel.add(previewScrollPane, java.awt.BorderLayout.CENTER);

        firstButton.setText("����");
        firstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstButtonActionPerformed(evt);
            }
        });
        previewButtonPanel.add(firstButton);

        previousButton.setText("����");
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });
        previewButtonPanel.add(previousButton);

        nextButton.setText("����");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        previewButtonPanel.add(nextButton);

        lastButton.setText("�׶�");
        lastButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastButtonActionPerformed(evt);
            }
        });
        previewButtonPanel.add(lastButton);

        previewPanel.add(previewButtonPanel, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(previewPanel, java.awt.BorderLayout.CENTER);

        setupPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("��ӡ����"));
        setupPanel.setLayout(new java.awt.BorderLayout());

        setupTabbedPane.setFocusable(false);
        setupTabbedPane.addTab("����", basePanel);
        setupTabbedPane.addTab("ҳ��", pagePanel);
        setupTabbedPane.addTab("���������", alignPanel);
        setupTabbedPane.addTab("ˮӡ", waterPanel);
        setupTabbedPane.addTab("����", backPanel);
        setupTabbedPane.addTab("��ӡ", printPanel);

        setupPanel.add(setupTabbedPane, java.awt.BorderLayout.EAST);

        setupButtonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        submitButton.setText("ȷ��");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });
        setupButtonPanel.add(submitButton);

        printButton.setText("��ӡ");
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
submitButtonActionPerformed(null);// ˢ�´�ӡԤ��
HashPrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();// ������ӡ���Լ�����
// ȷ����ӡֽ��
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
PageFormat pageFormat = new PageFormat();// ����ҳ���ʽ������
Paper p = new Paper();// ����ֽ�Ŷ���
if (basePanel.getVertical()) {// �����ӡ
    attributeSet.add(OrientationRequested.PORTRAIT);// �����ӡ
    pageFormat.setOrientation(PageFormat.PORTRAIT);// �����ӡ
    p.setSize(PaperPanel.Width, PaperPanel.Height);// ����ֽ�Ŵ�С
    int areaWidth = PaperPanel.Width 
            - PaperPanel.LeftSpace - PaperPanel.RightSpace + 42;// �����ӡ������
    int areaHeight = PaperPanel.Height 
            - PaperPanel.UpSpace - PaperPanel.DownSpace + 42;// �����ӡ����߶�
    p.setImageableArea(PaperPanel.LeftSpace - 20, PaperPanel.UpSpace - 20, 
            areaWidth, areaHeight);// ����ֽ�ŵĴ�ӡ����
} else {// �����ӡ
    attributeSet.add(OrientationRequested.LANDSCAPE);// �����ӡ
    pageFormat.setOrientation(PageFormat.LANDSCAPE);// �����ӡ
    p.setSize(PaperPanel.Height, PaperPanel.Width);// ����ֽ�Ŵ�С
    int areaWidth = PaperPanel.Height 
            - PaperPanel.UpSpace - PaperPanel.DownSpace + 42;// �����ӡ������
    int areaHeight = PaperPanel.Width 
            - PaperPanel.LeftSpace - PaperPanel.RightSpace + 42;// �����ӡ����߶�
    p.setImageableArea(PaperPanel.LeftSpace - 20, PaperPanel.UpSpace - 20, 
            areaWidth, areaHeight);// ����ֽ�ŵĴ�ӡ����
}
pageFormat.setPaper(p);// ��ֽ�Ŷ������õ�ҳ���ʽ������
PrinterJob printerJob = PrinterJob.getPrinterJob();// ��ô�ӡ���������
if (printerJob.printDialog(attributeSet)) {// ��ʾ��ӡ�Ի���
    Book book = new Book();// ������ӡ�ĵ�����
    Printable paper;// ������ӡҳ����
    Component[] components = papersPanel.getComponents();// ��ӡҳ����
    int pageAmount = components.length;// ��ӡҳ��
    int printShare = printPanel.getPrintShare();// ��ӡ����
    String bluePrint = printPanel.getBluePrint();// ��ô�ӡ����
    if (bluePrint.equals("ShareForehand")) {// ���˳���ӡ
        for (int share = 0; share < printShare; share++) {// ������
            for (int page = 0; page <pageAmount; page++) {// ����ҳ
                paper = (Printable) components[page];// ��ô�ӡҳ
                book.append(paper, pageFormat);// ��ӵ���ӡ�ĵ�
            }
        }
    } else if (bluePrint.equals("PageBackhander")) {// ��ҳ�����ӡ
        for (int page = pageAmount - 1; page >= 0; page--) {// ����ҳ
            paper = (Printable) components[page];// ��ô�ӡҳ
            book.append(paper, pageFormat, printShare);// ��ӵ���ӡ�ĵ�
        }
    } else if (bluePrint.equals("ShareBackhander")) {// ��������ӡ
        for (int share = 0; share < printShare; share++) {// ������
            for (int page = pageAmount-1; page>=0; page--) {// ����ҳ
                paper = (Printable) components[page];// ��ô�ӡҳ
                book.append(paper, pageFormat);// ��ӵ���ӡ�ĵ�
            }
        }
    } else {// ��ҳ˳���ӡ
        for (int page = 0; page < pageAmount; page++) {// ����ҳ
            paper = (Printable) components[page];// ��ô�ӡҳ
            book.append(paper, pageFormat, printShare);// ��ӵ���ӡ�ĵ�
        }
    }
    printerJob.setPageable(book);// ���ô�ӡ�ĵ�
    try {
        printerJob.print();// ִ�д�ӡ����
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
