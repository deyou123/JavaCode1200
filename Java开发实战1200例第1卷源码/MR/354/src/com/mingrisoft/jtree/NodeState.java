package com.mingrisoft.jtree;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class NodeState extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -8240502815018021871L;
    private JPanel contentPane;
    private JTree tree;
    private JTextArea textArea;
    private DefaultMutableTreeNode root;
    
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
                    NodeState frame = new NodeState();
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
    public NodeState() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u67E5\u770B\u6811\u8282\u70B9\u7684\u72B6\u6001");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(1, 2, 5, 5));
        
        JScrollPane scrollPane1 = new JScrollPane();
        panel.add(scrollPane1);
        
        tree = new JTree();
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                do_tree_valueChanged(e);
            }
        });
        tree.setFont(new Font("????????", Font.PLAIN, 16));
        scrollPane1.setViewportView(tree);
        
        JScrollPane scrollPane2 = new JScrollPane();
        panel.add(scrollPane2);
        
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setFont(new Font("????????", Font.PLAIN, 14));
        scrollPane2.setViewportView(textArea);
    }
    
    protected void do_this_windowActivated(WindowEvent e) {
        root = new DefaultMutableTreeNode("????????????");
        DefaultMutableTreeNode parent1 = new DefaultMutableTreeNode("????????????????");
        parent1.add(new DefaultMutableTreeNode("??Java????????????????2??????"));
        parent1.add(new DefaultMutableTreeNode("??PHP????????????????2??????"));
        parent1.add(new DefaultMutableTreeNode("??Visual Basic????????????????2??????"));
        parent1.add(new DefaultMutableTreeNode("??Visual C++????????????????2??????"));
        root.add(parent1);
        DefaultMutableTreeNode parent2 = new DefaultMutableTreeNode("????????????");
        parent2.add(new DefaultMutableTreeNode("??Java??????????"));
        parent2.add(new DefaultMutableTreeNode("??PHP??????????"));
        parent2.add(new DefaultMutableTreeNode("??Visual Basic??????????"));
        parent2.add(new DefaultMutableTreeNode("??Visual C++??????????"));
        root.add(parent2);
        DefaultTreeModel model = new DefaultTreeModel(root);
        tree.setModel(model);
    }
    
    protected void do_tree_valueChanged(TreeSelectionEvent e) {
        TreePath path = tree.getSelectionPath();
        if (path == null) {
            return;
        }
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        StringBuilder sb = new StringBuilder();
        sb.append("????????????????????" + node.getChildCount() + "\n");
        sb.append("????????????????????" + node.getLevel() + "\n");
        sb.append("????????????????????" + node.getLeafCount() + "\n");
        sb.append("????????????????????" + node.isRoot() + "\n");
        sb.append("??????????????????????" + node.isLeaf() + "\n");
        textArea.setText(sb.toString());
    }
}
