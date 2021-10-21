/*
 * StatPanel.java
 *
 * Created on 2008年7月10日, 下午1:47
 */
package com.mwq.stat;

/**
 * 版权所有：明日科技有限公司
 * @author Administrator
 */
public class StatPanel extends javax.swing.JPanel {

	/** Creates new form StatPanel */
	public StatPanel() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		tabbedPane = new javax.swing.JTabbedPane();
		tablePanel = new com.mwq.stat.StatTablePanel();
		chartPanel = new com.mwq.stat.StatChartPanel();

		setLayout(new java.awt.BorderLayout());

		tabbedPane.setFocusable(false);
		tabbedPane.addTab("表格统计", tablePanel);
		tabbedPane.addTab("图形分析", chartPanel);

		add(tabbedPane, java.awt.BorderLayout.CENTER);
	}// </editor-fold>

	// Variables declaration - do not modify
	private com.mwq.stat.StatChartPanel chartPanel;
	private javax.swing.JTabbedPane tabbedPane;
	private com.mwq.stat.StatTablePanel tablePanel;
	// End of variables declaration
}
