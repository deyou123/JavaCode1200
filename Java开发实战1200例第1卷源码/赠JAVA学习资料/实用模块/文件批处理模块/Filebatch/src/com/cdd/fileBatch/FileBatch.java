package com.cdd.fileBatch;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import javax.swing.JTree;
import javax.swing.event.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.*;
import com.cdd.editItem.*;
import com.cdd.fileBatch.medol.*;
import com.cdd.fileItem.*;
import com.cdd.tool.*;
import com.cdd.uitl.*;
import com.fileBath.util.*;
import javax.swing.JMenu;
import java.awt.Rectangle;
/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class FileBatch extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;		//���ô������
	private JToolBar jJToolBarBar = null;
	private JMenuBar jmenubar = null;
	private JMenu fileMenu = null;	
	private JPanel managerjPanel = null;
	private JScrollPane fileScrollPane = null;
	private JTable fileList = null;
	private JTree jtree = null;
	private JPopupMenu filePopMenu = null;
	private JMenuItem createFileItem = null;			//�����½��ļ��˵���
	private JMenuItem createFolderItem = null;			//�����½��ļ��в˵���
	private JMenuItem copyItem = null;
	private JMenuItem deleItem = null;
	private JMenuItem removeItem = null;
	private JMenuItem findFileItem = null;
	private int indexForDel;
	private FileHeald fileHeald = new FileHeald();
	private String strFile = null;
	private List list = null;
	private JMenu editMenu = null;
	private JMenu toolMenu = null;
	private JMenuItem commItem = null;
	private JMenuItem hebingItem = null;
	private JMenuItem deletePath = null;
	private JMenuItem zipFolderPath = null;
	private JMenuItem zipOut = null;
	private JMenuItem renameBath = null;
	private JMenu removeBath = null;
	private JMenuItem removeFolder = null;
	private JMenuItem removeFile = null;
	private JMenuItem changeBach = null;
	private JMenu createFile = null;
	private JMenuItem copyFolder = null;
	private JMenuItem copyFileBath = null;
	private JMenuItem close = null;
	private JMenu copyBatch = null;
	private JButton jButtonCreate = null;
	private JButton jButtonFind = null;
	private JButton jButtonSort = null;
	private JButton jButtonClose = null;	
	/**
	 * This method initializes jJToolBarBar
	 * 
	 * @return javax.swing.JToolBar
	 */
private JToolBar getJToolBar() {
	if (jJToolBarBar == null) {
		jJToolBarBar = new JToolBar();
		jJToolBarBar.setRollover(true);
		URL url = getClass().getResource("/image/create.png");
		ImageIcon icon = new ImageIcon(url);
		jButtonCreate = new JButton(icon);
		jButtonCreate.setFocusable(false);
		jButtonCreate
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButtonCreate
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jButtonCreate.setToolTipText("�½�");
		jButtonCreate.addActionListener(new ToolButton());
		jJToolBarBar.add(jButtonCreate);
		URL url2 = getClass().getResource("/image/find.png");
		ImageIcon icon2 = new ImageIcon(url2);
		jButtonFind = new JButton(icon2);
		jButtonFind.setFocusable(false);
		jButtonFind
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButtonFind
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jButtonFind.setToolTipText("����");
		jButtonFind.addActionListener(new ToolButton());
		jJToolBarBar.add(jButtonFind);
		URL url3 = getClass().getResource("/image/choice.png");
		ImageIcon icon3 = new ImageIcon(url3);
		jButtonSort = new JButton(icon3);
		jButtonSort.setFocusable(false);
		jButtonSort
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButtonSort
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jButtonSort.setToolTipText("����");
		jButtonSort.addActionListener(new ToolButton());
		jJToolBarBar.add(jButtonSort);
		URL url5 = getClass().getResource("/image/close.png");
		ImageIcon icon5 = new ImageIcon(url5);
		jButtonClose = new JButton(icon5);
		jButtonClose.setFocusable(false);
		jButtonClose
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButtonClose
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jButtonClose.setToolTipText("�˳�");
		jButtonClose.addActionListener(new ToolButton());
		jJToolBarBar.add(jButtonClose);
		jJToolBarBar.setBounds(new Rectangle(0, 10, 644, 45));
	}
	return jJToolBarBar;
}
	// ���������еİ�ť�¼���
	class ToolButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//�������½�����ť�����¼�
			if(e.getSource() == jButtonCreate){
				try {
					UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
					
				} catch (UnsupportedLookAndFeelException ee) {					
					ee.printStackTrace();
				}
				CreateFile createFile = new CreateFile();
				createFile.setVisible(true);
				createFile.setBounds(200, 150, 450, 400);
				createFile.setTitle("�½��ļ�ϵ��");
			}
			//��������������ť�����¼�
			if(e.getSource() == jButtonFind){
				try {
					UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
					
				} catch (UnsupportedLookAndFeelException ee) {					
					ee.printStackTrace();
				}
				SearchFrame searchFrame = new SearchFrame();
				searchFrame.setVisible(true);
				searchFrame.setBounds(150, 200, 550, 350);
				searchFrame.setTitle("�����ļ�����");					
			}
			//���������ࡱ��ť�����¼�
			if(e.getSource() == jButtonSort){
				try {
					UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
					
				} catch (UnsupportedLookAndFeelException ee) {					
					ee.printStackTrace();
				}
				SortFrame sortFrame = new SortFrame();
				sortFrame.setVisible(true);
				sortFrame.setBounds(200, 200, 430,150);
				sortFrame.setTitle("�ļ����ಿ��");
			}
			//�������˳�����ť����ʱ��
			if(e.getSource() == jButtonClose){
				FileBatch fileb = new FileBatch();
				int n = JOptionPane.showConfirmDialog(fileb, "ȷ��Ҫ�˳���", "ȷ�϶Ի���",JOptionPane.YES_NO_OPTION);
				if(n == JOptionPane.YES_OPTION){
				System.exit(0);
				}
			}			
		}		
	}
	/**
	 * This method initializes managerjPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getManagerjPanel() {
		if (managerjPanel == null) {
			try {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			managerjPanel = new JPanel();
			managerjPanel.setLayout(new BorderLayout());
			JTree jtree = getTree();
			JScrollPane sp = new JScrollPane(jtree);
			sp.setBorder(BorderFactory.createEtchedBorder(Color.white,
					new Color(148, 145, 140)));
			managerjPanel.add(sp);
		}
		managerjPanel.setBounds(0, 68, 300, 382);
		return managerjPanel;
	}
	/**
	 * This method initializes fileScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getFileScrollPane() {
		if (fileScrollPane == null) {
			fileScrollPane = new JScrollPane();
			fileScrollPane.setBounds(new Rectangle(301, 67, 345, 382));
			fileScrollPane.setViewportView(getFileList());
		}
		return fileScrollPane;
	}
	/**
	 * This method initializes fileList
	 * 
	 * @return javax.swing.JList
	 */
private JTable getFileList() {
	if (fileList == null) {
		fileList = new JTable();
	}
	fileList.setModel(new LocalTableModel());
	return fileList;
}
private JTree getTree() {
	File[] root = (new FileSystem()).getRoots();
	MyNode filenod = new MyNode(root[0]);
	filenod.explore();
	jtree = new JTree(new DefaultTreeModel(filenod));
	jtree.getSelectionModel().setSelectionMode(
			TreeSelectionModel.SINGLE_TREE_SELECTION);		//��������ѡ��ģ��Ϊһ��ֻѡ��һ��·��
	JScrollPane sp = new JScrollPane(jtree);
	sp.setBorder(BorderFactory.createEtchedBorder(Color.white, new Color(
			148, 145, 140)));
	jtree.setShowsRootHandles(true);					//�����������߲���ʾ���
	jtree.addTreeExpansionListener(new TreeExpansionListener() {
		public void treeCollapsed(TreeExpansionEvent e) {	//������ĳһ��۵�ʱ����
		}	
		public void treeExpanded(TreeExpansionEvent e) {	//������ĳһ�չ��ʱ����
			TreePath treepath = e.getPath();				//��ʾ�ڵ��·��
			MyNode node = (MyNode) treepath.getLastPathComponent();	//��ȡ·�������һ�����
			if (!node.isExplored()) {
				DefaultTreeModel model = ((DefaultTreeModel) jtree
						.getModel());
				node.explore();
				model.nodeStructureChanged(node);			//���ĸ��ڵ���õķ���
			}
		}
	});
jtree.addMouseListener(new MouseAdapter() {
	public void mousePressed(MouseEvent e) {
		JTree tree = (JTree) e.getSource();
		int row = tree.getRowForLocation(e.getX(), e.getY());
		if (row == -1) {
			return;
		}
		TreePath path = tree.getPathForRow(row);		//��ȡָ����·��
		if (path == null) {
			return;
		}
		MyNode node = (MyNode) path.getLastPathComponent();
		if (node == null) {
			return;
		}
		String filepath = node.getString();
		String newPath = filepath.replace("\\", "//");				
		DefaultTableModel model = (DefaultTableModel) fileList
				.getModel();
		if (fileList.getRowCount() != 0) {				
			((DefaultTableModel) fileList.getModel()).setRowCount(0);					
		}
		list = fileHeald.getFileList(newPath);
		for (int i = 0; i < list.size(); i++) {
			MyFile myFile = new MyFile(list.get(i).toString());
			File file = (File) list.get(i);
			String length = file.length() + "B ";
			if (file.length() > 1000 * 1000 * 1000) {
				length = file.length() / 1000000000 + "G ";
			}
			if (file.length() > 1000 * 1000) {
				length = file.length() / 1000000 + "M ";
			}
			if (file.length() > 1000) {
				length = file.length() / 1000 + "K ";
			}
			String modifDate = new Date(file.lastModified())
					.toLocaleString();
			if (!file.canRead()) {
				length = "δ֪";
				modifDate = "δ֪";
			}
			model.addRow(new Object[] {
			myFile.toString((File) list.get(i)), length,
			modifDate });
		}
	}	
});
	return jtree;
}
class MyNode extends DefaultMutableTreeNode {
	private boolean explored = false;

	public MyNode(File file) {
		setUserObject(file); // ���˽ڵ���û���������Ϊfile
	}

	public boolean getAllowChildren() { // �������˽ڵ�ӵ���ӽڵ㣬�򷵻� true��
		return isDirectory();
	}

	public boolean isLeaf() { // ����˽ڵ�û���ӽڵ㣬�򷵻� true��
		return !isDirectory();
	}

	public File getFile() { // ��ȡ�ýڵ���û�����
		return (File) getUserObject();
	}

	public boolean isExplored() {
		return explored;
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
					add(new MyNode(children[i]));
				}
			}
			explored = true;
		}
	}
}
	// �����ڲ���̳�FileSystemView�ļ�ϵͳ����
	class FileSystem extends FileSystemView {
		public File createNewFolder(File containingDir) throws IOException {
			return null;
		}
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
					
				} catch (UnsupportedLookAndFeelException ee) {					
					ee.printStackTrace();
				}
				FileBatch thisClass = new FileBatch();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}
	/**
	 * This is the default constructor
	 */
	public FileBatch() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setBounds(100, 100, 660, 484);
		this.setContentPane(getJContentPane());
		this.setTitle("�ļ���������");
		jmenubar = new JMenuBar();
		this.setJMenuBar(jmenubar);
		filePopMenu = new JPopupMenu();
		createFile = new JMenu("�½�");
		findFileItem = new JMenuItem("����");
		findFileItem.addActionListener(new FileMenu());
		close = new JMenuItem("�ر�");
		close.addActionListener(new FileMenu());
		createFileItem = new JMenuItem("�½��ļ�");
		createFolderItem = new JMenuItem("�½��ļ���");		
		copyFolder = new JMenuItem("���������ļ���");
		copyFileBath = new JMenuItem("����ָ���ļ�");
		copyItem = new JMenuItem("���Ƶ�");
		deleItem = new JMenuItem("ɾ��");
		removeItem = new JMenuItem("�ƶ���");
		fileMenu = new JMenu("�ļ�");
		fileMenu.add(createFile);	
		fileMenu.add(findFileItem);
		fileMenu.add(close);
		createFile.add(createFileItem);
		createFile.add(createFolderItem);
		createFileItem.addActionListener(new FileMenu());
		createFolderItem.addActionListener(new FileMenu());
		editMenu = new JMenu("�༭");
		copyBatch = new JMenu("��������");
		renameBath = new JMenuItem("����������");
		removeBath = new JMenu("�����ƶ�");
		removeFolder = new JMenuItem("�ƶ��ļ���");
		removeFile = new JMenuItem("�ƶ��ļ�");
		deletePath = new JMenuItem("����ɾ��");
		changeBach = new JMenuItem("�����޸��ļ�����");
		renameBath.addActionListener(new Rename());
		changeBach.addActionListener(new Rename());
		editMenu.add(copyBatch);
		editMenu.add(removeBath);
		removeBath.add(removeFolder);
		removeBath.add(removeFile);
		editMenu.add(renameBath);
		editMenu.add(deletePath);
		editMenu.add(changeBach);
		copyBatch.add(copyFolder);
		copyBatch.add(copyFileBath);
		removeFolder.addActionListener(new Rename());
		removeFile.addActionListener(new Rename());
		copyFolder.addActionListener(new Rename());
		copyFileBath.addActionListener(new Rename());
		toolMenu = new JMenu("����");
		commItem = new JMenuItem("�ָ�/�ϲ�");
		hebingItem = new JMenuItem("�ϲ�");
		zipFolderPath  = new JMenuItem("ѹ���ļ���");
		zipOut = new JMenuItem("��ѹ�ļ�");	
		jmenubar.add(fileMenu);
		jmenubar.add(editMenu);
		jmenubar.add(toolMenu);
		toolMenu.add(commItem);
		toolMenu.add(zipFolderPath);
		toolMenu.add(zipOut);
		createFile.addActionListener(new FileMenu());
		zipFolderPath.addActionListener(new Detele());
		zipOut.addActionListener(new Detele());
		copyItem.addActionListener(new CopyItem());
		filePopMenu.add(copyItem);
		filePopMenu.add(deleItem);
		filePopMenu.add(removeItem);
		deletePath.addActionListener(new Detele());
		deleItem.addActionListener(new CopyItem());
		removeItem.addActionListener(new CopyItem());
		commItem.addActionListener(new CopyItem());
		hebingItem.addActionListener(new CopyItem());
		fileList.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				checkMenu(e);
			}
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) 
				{
					checkMenu(e);
				}
			}
		});
	}
	class FileMenu implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == createFileItem){
				try {
					UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
					
				} catch (UnsupportedLookAndFeelException ee) {					
					ee.printStackTrace();
				}
				CreateFile createFile = new CreateFile();
				createFile.setVisible(true);
				createFile.setBounds(200, 150, 400,200);
				createFile.setTitle("�½��ļ�ϵ��");
			}
			if(e.getSource() == createFolderItem){
				try {
					UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
					
				} catch (UnsupportedLookAndFeelException ee) {					
					ee.printStackTrace();
				}
				CreateFolderFrame folderFrame = new CreateFolderFrame();
				folderFrame.setVisible(true);
				folderFrame.setBounds(200, 150, 420, 170);
			}
			if(e.getSource() == findFileItem){
				try {
					UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
					
				} catch (UnsupportedLookAndFeelException ee) {					
					ee.printStackTrace();
				}
				SearchFrame searchFrame = new SearchFrame();
				searchFrame.setVisible(true);
				searchFrame.setBounds(150, 200, 550, 350);
				searchFrame.setTitle("�����ļ�����");
			}
			if(e.getSource() == close){
				FileBatch fileb = new FileBatch();
				int n = JOptionPane.showConfirmDialog(fileb, "ȷ��Ҫ�˳�ϵͳ��", "ȷ�϶Ի���", JOptionPane.YES_NO_OPTION);
				if(n == JOptionPane.YES_OPTION){
				System.exit(0);
				}
			}
			
		}
		
	}
	class Rename implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
				
			} catch (UnsupportedLookAndFeelException ee) {					
				ee.printStackTrace();
			}
			if(e.getSource() == copyFolder ){
				
				CopyFolder copy = new CopyFolder();
				copy.setVisible(true);
				copy.setBounds(200, 300, 400, 220);
				copy.setTitle("�����ļ��в���");
			}
			if(e.getSource() ==  removeFile){
				FileNameremoveBatch fileNcb = new FileNameremoveBatch();
				fileNcb.setVisible(true);
				fileNcb.setBounds(230, 250, 400, 200);
				fileNcb.setTitle("�ƶ�ָ���ļ�");
			}
			if(e.getSource() == removeFolder){
				RemoveFolder removeFolder = new RemoveFolder();
				removeFolder.setVisible(true);
				removeFolder.setBounds(200, 250, 400, 220);
				removeFolder.setTitle("�ƶ��ļ��в���");
			}
			if(e.getSource() == copyFileBath){
				FileNameCopyBatch fileName = new FileNameCopyBatch();
				fileName.setVisible(true);
				fileName.setBounds(230, 250, 400,200);
				fileName.setTitle("�����ļ�����");
			}
			if(e.getSource() == renameBath){			
				RenameBatch renameB = new RenameBatch();
				renameB.setBounds(100, 100, 460, 500);
				renameB.setVisible(true);
				renameB.setTitle("����������ģ��");
			}
			if(e.getSource() == changeBach){
				MyCoding myCoding = new MyCoding();
				myCoding.setVisible(true);
				myCoding.setBounds(200, 200, 400, 300);
				myCoding.setTitle("�����޸��ļ�����ģ��");
					
			}
		}		
	}
	class Detele implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
				
			} catch (UnsupportedLookAndFeelException ee) {					
				ee.printStackTrace();
			}
			//��������ɾ����ť�����¼�
			if(e.getSource() == deletePath){				
				DeleteBatch delebatch = new DeleteBatch();
				delebatch.setBounds(150, 100, 500, 500);
				delebatch.setVisible(true);
				delebatch.setTitle("����ɾ��ģ��");
			}
			//����ѹ���ļ���ť��������
			if(e.getSource() == zipFolderPath){
				try {
					UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
					
				} catch (UnsupportedLookAndFeelException ee) {					
					ee.printStackTrace();
				}
				ZipFrame zipframe = new ZipFrame();
				zipframe.setVisible(true);
				zipframe.setBounds(200, 150, 400, 220);
				zipframe.setTitle("ѹ���ļ�ģ��");
			}
			if(e.getSource() == zipOut){
				try {
					UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());					
				} catch (UnsupportedLookAndFeelException ee) {					
					ee.printStackTrace();
				}
				ZipOutFrame zipoutFrame = new ZipOutFrame();
				zipoutFrame.setVisible(true);
				zipoutFrame.setBounds(300, 150, 400, 220);
				zipoutFrame.setTitle("��ѹ�ļ�ģ��");
			}
		}		
	}
	// �ڲ��࣬�������¼�
	class CopyItem implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			File file = new File("filelist.txt");			
			if (e.getSource() == copyItem) {
				FileTree fileTree = new FileTree();
			}
			if(e.getSource() == deleItem){
				int ensuce = JOptionPane.showConfirmDialog(new FileBatch(),
						"ȷ��ɾ����ѡ�ļ���?", "ȷ�϶Ի���",
						JOptionPane.YES_NO_OPTION);
				if(ensuce == JOptionPane.NO_OPTION){
					return ;
				}
				if(ensuce == JOptionPane.YES_OPTION){			
				try {
					FileReader fr = new FileReader(file);		//����FileReader�����
					BufferedReader bufr = new BufferedReader(fr);//����BufferedReader�����
					String s = null;							//�����ַ�������
					int i = 0;									//����int�ͱ���
					while ((s = bufr.readLine()) != null) {		//����ļ����ı�������Ϊnull,�����ѭ��
						i++;									//����������������
						File f = new File(s);
						f.delete();
					}
					bufr.close();							//��FileReader���ر�
					fr.close();								//��FileReader���ر�
					fileList.revalidate();
				} catch (Exception ee) {						//�����쳣
					ee.printStackTrace();
				}
			}
			}
			if(e.getSource() == removeItem){
				FileTreeRemove fileTree = new FileTreeRemove();
				fileTree.setVisible(true);
				fileTree.setBounds(200, 200, 400, 400);
				fileTree.setTitle("��ʾ����");
			}
			//�������ָ�ϲ����˵�����¼�
			if(e.getSource() == commItem){
			try {
					UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
					
				} catch (UnsupportedLookAndFeelException ee) {					
					ee.printStackTrace();
				}
				com.cdd.tool.CommAndUntie commandUntie = new com.cdd.tool.CommAndUntie();
			}			
		}
	}

	// �Զ��巽��ָ����굥���¼�
	private void checkMenu(MouseEvent e) {
		if (e.isPopupTrigger()) {
			String tempString = "";
			int[] rows = fileList.getSelectedRows();
			int[] columns = fileList.getSelectedColumns();
			File file = new File("filelist.txt");
			try {
				file.createNewFile();
				FileWriter fw = new FileWriter(file); // ����FileWriter�����
				BufferedWriter bufw = new BufferedWriter(fw);
				String ss = "sd";
				for (int i = 0; i < rows.length; i++) {
					tempString = (String) fileList.getValueAt(rows[i],
							columns[0]);
					int index = list.get(0).toString().lastIndexOf(".");
					String s = list.get(0).toString().substring(index, list.get(0).toString().length());
					if(list.get(i).toString().endsWith(ss)){						
					}
					ss = s;
					for (int k = 0; k < list.size(); k++) {
						if (list.get(k).toString().endsWith(tempString)) {
							// ����BufferedWriter�����
							bufw.write(list.get(k).toString()); // ���ַ���������Ԫ��д�뵽�����ļ���
							bufw.newLine(); // �������еĵ���Ԫ���Ե��е���ʽд���ļ�
							// ��FileWriter���ر�
						}
					}
				}
				bufw.close(); // ��BufferedWriter���ر�
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			filePopMenu.show(fileList, e.getX(), e.getY());
		}
	}
//	 �Զ��巽��ָ����굥���¼�
	private void checktree(MouseEvent e) {
		
		if (e.isPopupTrigger()) {
			
		}
	}

	public String getStrFile() {
		return strFile;
	}

	public void setStrFile(String strFile) {
		this.strFile = strFile;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
/*			jContentPane.add(getFileMenu(), null);*/
			jContentPane.add(getManagerjPanel(), null);
			jContentPane.add(getFileScrollPane(), null);
		/*	jContentPane.add(getEditMenu(), null);
			jContentPane.add(getToolMenu(), null);*/
		}
		return jContentPane;
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
}
