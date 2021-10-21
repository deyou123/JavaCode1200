package com.fileBath.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.cdd.fileBatch.FileBatch;
import com.cdd.uitl.*;
/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class FileTree extends JFrame implements Runnable{
	private JLabel path_ = new JLabel("   ");
	private JTree tree;

	private static String filepath;
	private long sizenew =0;
	private int si = 0;
	private long currentValue;
	private Thread thread = new Thread(this);
	private FileHeald fileheald = new FileHeald();
	private JProgressBar aJProgressBar = null;	
	private JPanel buttonPanel = null;
	public FileTree() {
		this.setVisible(true);
		init();
	}
	private void init() {
		JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelPanel.setSize(300, 40);
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel message = new JLabel("您选择的路径为：");
		labelPanel.setLayout(new BorderLayout());
		labelPanel.add(message, BorderLayout.NORTH);
		labelPanel.add(path_, BorderLayout.SOUTH);
		File[] roots = (new  FileSystem()).getRoots();
		FileNode nod = new FileNode(roots[0]);
		nod.explore();
		tree = new JTree(new DefaultTreeModel(nod));
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		JScrollPane sp = new JScrollPane(tree);
		sp.setBorder(BorderFactory.createEtchedBorder(Color.white, new Color(
				148, 145, 140)));
		labelPanel.setBorder(BorderFactory.createEmptyBorder(0, 19, 0, 0));		
		JButton buttonOK = new JButton("确定");
		buttonOK.setPreferredSize(new Dimension(70, 25));
		JButton buttonCanel = new JButton("退出");
		buttonCanel.setPreferredSize(new Dimension(70, 25));
		buttonPanel.add(buttonOK);
		buttonPanel.add(buttonCanel);	
		buttonCanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});		
		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
			
			/*	thread.start();*/
				aJProgressBar = new JProgressBar(0, 100);
				aJProgressBar.setIndeterminate(true);			
				buttonPanel.add(aJProgressBar);	
				validate();
				File file = new File("filelist.txt");		
				if(!file.exists()){
					return;
				}
				
				try {
					FileReader fr = new FileReader(file);			
					BufferedReader bufr = new BufferedReader(fr);					
					String s = null;							
					int i = 0;								
					while ((s = bufr.readLine()) != null) {		
						i++;								
						int news = s.lastIndexOf("\\");						
						String newstr = s.substring(news, s.length());				
						fileheald.copyFile(s,filepath+newstr);
					}	
					thread.start();					
					bufr.close();							
					fr.close();	
					/*thread.stop();*/
			/*		file.delete();*/
				} catch (Exception ee) {						
					ee.printStackTrace();
				}
			}
		});
		tree.setShowsRootHandles(true);
		tree.addTreeExpansionListener(new TreeExpansionListener() {
			public void treeCollapsed(TreeExpansionEvent e) {

			}

			public void treeExpanded(TreeExpansionEvent e) {
				TreePath path = e.getPath();
				FileNode node = (FileNode) path.getLastPathComponent();
				if (!node.isExplored()) {
					DefaultTreeModel model = ((DefaultTreeModel) tree.getModel());
					node.explore();
					model.nodeStructureChanged(node);
				}
			}
		});
		tree.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JTree tree = (JTree) e.getSource();
				int row = tree.getRowForLocation(e.getX(), e.getY());
				if (row == -1) {
					return;
				}
				TreePath path = tree.getPathForRow(row);
				if (path == null) {
					return;
				}
				FileNode node = (FileNode) path.getLastPathComponent();
				if (node == null) {
					return;
				}
				filepath = node.getString();			
				path_.setText(filepath);
			}
		});
		getContentPane().add(sp, BorderLayout.CENTER);
		getContentPane().add(labelPanel, BorderLayout.NORTH);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		this.setBounds(200, 100, 350, 400);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setTitle("浏览器");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {						
				disable();
			}
		});		
	}
	class FileNode extends DefaultMutableTreeNode {
		private boolean explored_ = false;
		public FileNode(File file) {
			setUserObject(file);
		}
		public boolean getAllowChildren() {
			return isDirectory();
		}
		public boolean isLeaf() {
			return !isDirectory();
		}
		public File getFile() {
			return (File) getUserObject();
		}

		public boolean isExplored() {
			return explored_;
		}

		public boolean isDirectory() {
			File file = getFile();
			return file.isDirectory();
		}

		public String toString() {
			File file = getFile();
			String filename = file.toString();
			int index = filename.lastIndexOf("\\");
			return (index != -1 && index != filename.length() - 1) ? filename
					.substring(index + 1) : filename;
		}

		public String getString() {
			File file = getFile();
			String filename = file.getAbsolutePath();
			return filename;
		}

		public void explore() {
			if (!isDirectory()) {
				return;
			}
			if (!isExplored()) {
				File file = getFile();
				File[] children = file.listFiles();
				for (int i = 0; i < children.length; ++i) {
					if (children[i].isDirectory()) {
						add(new FileNode(children[i]));
					}
				}
				explored_ = true;
			}
		}
	}

	class FileSystem extends FileSystemView {
		public File createNewFolder(File containingDir) throws IOException {
			return null;
		}
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		FileTree fileTree = new FileTree();
	}
	public void run() {
		buttonPanel.remove(aJProgressBar);
		validate();
		JOptionPane.showMessageDialog(this, "文件复制成功", "信息对话框",JOptionPane.WARNING_MESSAGE);
	
		
	}
}
