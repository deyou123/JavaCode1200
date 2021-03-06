package com.mingrisoft.jtree;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class ToolTipNdeTest extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -587741746033407725L;
    private JPanel contentPane;
    private JTree tree;
    
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
                    ToolTipNdeTest frame = new ToolTipNdeTest();
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
    public ToolTipNdeTest() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u4E3A\u6811\u8282\u70B9\u589E\u52A0\u63D0\u793A\u4FE1\u606F");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        tree = new JTree();
        tree.setFont(new Font("????????", Font.PLAIN, 16));
        scrollPane.setViewportView(tree);
    }
    
    protected void do_this_windowActivated(WindowEvent e) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("????????????");
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
        ToolTipManager.sharedInstance().registerComponent(tree);
        Map<DefaultMutableTreeNode, String> map = new HashMap<DefaultMutableTreeNode, String>();
        map.put(root, "????????");
        map.put(parent1, "????????");
        map.put(parent2, "????????");
        tree.setCellRenderer(new ToolTipNode(map));
    }
}
