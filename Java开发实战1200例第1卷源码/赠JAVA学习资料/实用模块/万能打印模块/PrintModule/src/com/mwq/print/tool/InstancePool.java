/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.print.tool;

import com.mwq.print.tabbedPane.AlignPanel;
import com.mwq.print.tabbedPane.BackPanel;
import com.mwq.print.tabbedPane.BasePanel;
import com.mwq.print.tabbedPane.PagePanel;
import com.mwq.print.tabbedPane.PrintPanel;
import com.mwq.print.tabbedPane.WaterPanel;
/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class InstancePool {

    private static BasePanel basePanel;
    private static PagePanel pagePanel;
    private static AlignPanel alignPanel;
    private static WaterPanel waterPanel;
    private static BackPanel backPanel;

    public static BasePanel getBasePanel() {
        return basePanel;
    }

    public static void setBasePanel(BasePanel basePanel) {
        InstancePool.basePanel = basePanel;
    }

    public static PagePanel getPagePanel() {
        return pagePanel;
    }

    public static void setPagePanel(PagePanel pagePanel) {
        InstancePool.pagePanel = pagePanel;
    }

    public static AlignPanel getAlignPanel() {
        return alignPanel;
    }

    public static void setAlignPanel(AlignPanel alignPanel) {
        InstancePool.alignPanel = alignPanel;
    }

    public static WaterPanel getWaterPanel() {
        return waterPanel;
    }

    public static void setWaterPanel(WaterPanel waterPanel) {
        InstancePool.waterPanel = waterPanel;
    }

    public static BackPanel getBackPanel() {
        return backPanel;
    }

    public static void setBackPanel(BackPanel backPanel) {
        InstancePool.backPanel = backPanel;
    }
}
