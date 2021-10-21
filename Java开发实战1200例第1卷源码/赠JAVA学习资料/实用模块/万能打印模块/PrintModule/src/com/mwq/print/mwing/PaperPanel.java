/*
 * PagePanel.java
 *
 * Created on 2008��7��29��, ����2:38
 */
package com.mwq.print.mwing;

import com.mwq.print.tabbedPane.BackPanel;
import com.mwq.print.tabbedPane.BasePanel;
import com.mwq.print.tabbedPane.PagePanel;
import com.mwq.print.tabbedPane.WaterPanel;
import com.mwq.print.tool.InstancePool;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class PaperPanel extends JPanel implements Printable {

    private static int Page = 1;
    private static int[] PaperWidths = {420, 297, 210, 148, 364, 257, 182, 0};
    private static int[] PaperHeights = {594, 420, 297, 210, 514, 364, 257, 0};
    public static int Width,  Height;
    public static int UpSpace,  DownSpace,  LeftSpace,  RightSpace;
    public static int UpBorder,  DownBorder,  LeftBorder,  RightBorder;
    

    static {
        for (int i = 0; i < PaperWidths.length - 1; i++) {
            PaperWidths[i] = (int) (PaperWidths[i] * 2.8);
            PaperHeights[i] = (int) (PaperHeights[i] * 2.8);
        }
    }

    public static void setPage(int aPage) {
        Page = aPage;
    }

    public static void setPaperSize(int width, int height) {
        int index = PaperWidths.length - 1;
        PaperWidths[index] = (int) (width * 2.8);
        PaperHeights[index] = (int) (height * 2.8);
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        printAll(graphics);
        return Printable.PAGE_EXISTS;
    }

    /** Creates new form PagePanel */
    public PaperPanel() {
        initComponents();
        //����ҳ��
        baseSetup();
        pageSetup();
        watermarkSetup();
        backgroundSetup();
    }

    //����
    private static ImageIcon background;

    public void initBackground() {
        if (InstancePool.getBackPanel().isAddBackground()) {
            BackPanel backPanel = InstancePool.getBackPanel();
            String photoPath = backPanel.getPhotoPath();
            if (photoPath.length() > 0) {
                BufferedImage bufferedImage = null;
                try {
                    bufferedImage = ImageIO.read(new File(photoPath));
                } catch (IOException ex) {
                    Logger.getLogger(PaperPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                int w = Width - LeftSpace - RightSpace;
                int h = Height - UpSpace - DownSpace;
                if (backPanel.isDefault()) {
                    w = Math.min(w, bufferedImage.getWidth());
                    h = Math.min(h, bufferedImage.getHeight());
                    background = new ImageIcon(bufferedImage.getSubimage(0, 0, w, h));
                } else {
                    background = new ImageIcon(bufferedImage.getScaledInstance(w, h, BufferedImage.SCALE_DEFAULT));
                }
            }
        }
    }

    public void backgroundSetup() {
        if (InstancePool.getBackPanel().isAddBackground() && background != null) {
            BackgroundLabel backgroundLabel = new BackgroundLabel(background);
            backgroundLabel.setBounds(LeftSpace + 1, UpSpace + 1,
                    background.getIconWidth(), background.getIconHeight());
            add(backgroundLabel);
        }
    }

    //ˮӡ
    private void watermarkSetup() {
        WaterPanel waterPanel = InstancePool.getWaterPanel();
        if (waterPanel.isYesRadioButtonSelected()) {// �ж��Ƿ����ˮӡ

            int areaWidth = Width - LeftSpace - LeftBorder - RightBorder - RightSpace;
            int areaHeight = Height - UpSpace - UpBorder - DownBorder - DownSpace;
            WatermarkLabel watermarkLabel = new WatermarkLabel(areaWidth);
            int watermarkWidth = watermarkLabel.getPreferredSize().width;
            int watermarkHeight = watermarkLabel.getPreferredSize().height;
            int x = LeftSpace + LeftBorder;
            int y = UpSpace + UpBorder;

            Enumeration areaEnumeration = waterPanel.getAreaEnumeration();
            while (areaEnumeration.hasMoreElements()) {// �ж�ˮӡ��ӵ�λ��

                JRadioButton radioButton = (JRadioButton) areaEnumeration.nextElement();
                if (radioButton.isSelected()) {
                    int i = Integer.valueOf(radioButton.getName());
                    switch (i) {// ����ˮӡ����ʼ����λ��

                        case 1:// �����Ͻ���ʾ

                            break;
                        case 2:// ���Ϸ�������ʾ

                            x += (areaWidth - watermarkWidth) / 2;
                            break;
                        case 3:// �����Ͻ���ʾ

                            x += areaWidth - watermarkWidth;
                            break;
                        case 4:// ����������ʾ

                            y += (areaHeight - watermarkHeight) / 2;
                            break;
                        case 5:// ������ʾ

                            x += (areaWidth - watermarkWidth) / 2;
                            y += (areaHeight - watermarkHeight) / 2;
                            break;
                        case 6:// ���Ҳ������ʾ

                            x += areaWidth - watermarkWidth;
                            y += (areaHeight - watermarkHeight) / 2;
                            break;
                        case 7:// �����½���ʾ

                            y += areaHeight - watermarkHeight;
                            break;
                        case 8:// ���·�������ʾ

                            x += (areaWidth - watermarkWidth) / 2;
                            y += areaHeight - watermarkHeight;
                            break;
                        default:// �����½���ʾ

                            x += areaWidth - watermarkWidth;
                            y += areaHeight - watermarkHeight;
                    }
                    break;
                }
            }
            watermarkLabel.setBounds(x, y, watermarkWidth, watermarkWidth);
            add(watermarkLabel);
        }
    }

    //ҳ��
    private void pageSetup() {
        PagePanel pagePanel = InstancePool.getPagePanel();
        if (pagePanel.isAddPage()) {
            JLabel pageLabel = null;// ����ҳ���ǩ

            String format = pagePanel.getBeforeText() + (Page++) + pagePanel.getAfterText();// ����ҳ���ı�

            Enumeration<AbstractButton> areaElements = pagePanel.getAreaButtonGroup().getElements();
            while (areaElements.hasMoreElements()) {
                JRadioButton areaRadioButton = (JRadioButton) areaElements.nextElement();
                if (areaRadioButton.isSelected()) {
                    final String area = areaRadioButton.getText();
                    if (area.equals("ҳü") || area.equals("ҳ��")) {// ��ʾ��ҳü��ҳ��

                        int h = 4;
                        Enumeration<AbstractButton> alignElements = pagePanel.getHAlignButtonGroup().getElements();
                        while (alignElements.hasMoreElements()) {
                            JRadioButton alignRadioButton = (JRadioButton) alignElements.nextElement();
                            if (alignRadioButton.isSelected()) {
                                h = Integer.valueOf(alignRadioButton.getName());
                                break;
                            }
                        }
                        pageLabel = new JLabel(format, h);
                        if (area.equals("ҳü")) {// ��ʾ��ҳü

                            pageLabel.setBounds(LeftSpace, UpSpace - 20, Width - (LeftSpace + RightSpace), 20);
                        } else {// ��ʾ��ҳ��

                            pageLabel.setBounds(LeftSpace, Height - DownSpace, Width - (LeftSpace + RightSpace), 20);
                        }
                    } else {// ��ʾ�������Ҳ�

                        if (format.length() > 1) {
                            char[] formatCs = format.toCharArray();
                            StringBuffer sb = new StringBuffer("<html>");
                            sb.append(formatCs[0]);
                            for (int i = 1; i < formatCs.length; i++) {
                                sb.append("<tr align='center'><td>");
                                sb.append(formatCs[i]);
                                sb.append("</td></tr>");
                            }
                            sb.append("</html>");
                            format = sb.toString();
                        }
                        int v = 3;
                        Enumeration<AbstractButton> alignElements = pagePanel.getVAlignButtonGroup().getElements();
                        while (alignElements.hasMoreElements()) {
                            JRadioButton alignRadioButton = (JRadioButton) alignElements.nextElement();
                            if (alignRadioButton.isSelected()) {
                                v = Integer.valueOf(alignRadioButton.getName());
                                break;
                            }
                        }
                        pageLabel = new JLabel(format, JLabel.CENTER);
                        pageLabel.setVerticalAlignment(v);
                        if (area.equals("���")) {// ��ʾ�����

                            pageLabel.setBounds(LeftSpace - 20, UpSpace, 20, Height - (UpSpace + DownSpace));
                        } else {// ��ʾ���Ҳ�

                            pageLabel.setBounds(Width - LeftSpace, UpSpace, 20, Height - (UpSpace + DownSpace));
                        }
                    }
                    break;
                }
            }
            add(pageLabel);
        }
    }

    //��������
    private void baseSetup() {
        BasePanel basePanel = InstancePool.getBasePanel();
        //����ҳ���С
        int paperIndex = basePanel.getPaperIndex();// ���ѡ��ֽ�ŵ�����ֵ

        if (basePanel.getVertical()) {// �������

            Width = PaperWidths[paperIndex];
            Height = PaperHeights[paperIndex];
        } else {// �������

            Width = PaperHeights[paperIndex];
            Height = PaperWidths[paperIndex];
        }
        Dimension paperSize = new Dimension(Width, Height);// ����ֽ�ųߴ����
// ������ֵ����Ϊ�óߴ�

        setSize(paperSize);
        setMaximumSize(paperSize);
        setMinimumSize(paperSize);
        setPreferredSize(paperSize);
        //����ҳ�߾�
        if (basePanel.isSpaceAll()) {// ͳһ����

            UpSpace = DownSpace = LeftSpace = RightSpace = (int) (basePanel.getSpaceAll() * 2.8);
        } else {// ��������

            UpSpace = (int) (basePanel.getSpaceUp() * 2.8);//����ҳü�߾�

            DownSpace = (int) (basePanel.getSpaceDown() * 2.8);//����ҳ�ű߾�

            LeftSpace = (int) (basePanel.getSpaceLeft() * 2.8);//�������߾�

            RightSpace = (int) (basePanel.getSpaceRight() * 2.8);//�����Ҳ�߾�

        }
        //����ҳ�߿�
        if (basePanel.isBorderAll()) {// ͳһ����

            if (basePanel.isBorderAllYes()) {// ��ӱ߿�

                UpBorder = DownBorder = LeftBorder = RightBorder = 10;
                add(new BorderLine(BorderLine.PAPER_UP,
                        Width, Height, UpSpace, DownSpace, LeftSpace, RightSpace));
                add(new BorderLine(BorderLine.PAPER_DOWN,
                        Width, Height, UpSpace, DownSpace, LeftSpace, RightSpace));
                add(new BorderLine(BorderLine.PAPER_LEFT,
                        Width, Height, UpSpace, DownSpace, LeftSpace, RightSpace));
                add(new BorderLine(BorderLine.PAPER_RIGHT,
                        Width, Height, UpSpace, DownSpace, LeftSpace, RightSpace));
            }
        } else {// ��������

            if (basePanel.isBorderUpYes()) {// ����ϱ߿�

                UpBorder = 10;
                add(new BorderLine(BorderLine.PAPER_UP,
                        Width, Height, UpSpace, DownSpace, LeftSpace, RightSpace));
            }
            if (basePanel.isBorderDownYes()) {// ����±߿�

                DownBorder = 10;
                add(new BorderLine(BorderLine.PAPER_DOWN,
                        Width, Height, UpSpace, DownSpace, LeftSpace, RightSpace));
            }
            if (basePanel.isBorderLeftYes()) {// �����߿�

                LeftBorder = 10;
                add(new BorderLine(BorderLine.PAPER_LEFT,
                        Width, Height, UpSpace, DownSpace, LeftSpace, RightSpace));
            }
            if (basePanel.isBorderRightYes()) {// ����ұ߿�

                RightBorder = 10;
                add(new BorderLine(BorderLine.PAPER_RIGHT,
                        Width, Height, UpSpace, DownSpace, LeftSpace, RightSpace));
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

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);
    }// </editor-fold>                        
    // Variables declaration - do not modify                     
    // End of variables declaration                   
}
