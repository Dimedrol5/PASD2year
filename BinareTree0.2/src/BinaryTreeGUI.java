//BinaryTreeGUI Варіант 34
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BinaryTreeGUI extends JFrame {
    private BinaryTree tree;
    private JTextField inputField;
    private TreePanel treePanel;

    public BinaryTreeGUI() {
        tree = new BinaryTree();
        setTitle("Animated Binary Tree");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        inputField = new JTextField(10);
        JButton enterButton = new JButton("Вести");
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(inputField.getText());
                tree.insert(value);
                inputField.setText("");
                treePanel.repaint();
            }
        });

        JButton deleteButton = new JButton("Видалити");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tree = new BinaryTree();
                treePanel.setTree(tree);
                treePanel.repaint();
            }
        });

        treePanel = new TreePanel(tree);

        JButton inorderButton = new JButton("Inorder");
        inorderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tree.inorderTraversalAnimated(treePanel);
            }
        });

        JButton preorderButton = new JButton("Preorder");
        preorderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tree.preorderTraversalAnimated(treePanel);
            }
        });

        JButton postorderButton = new JButton("Postorder");
        postorderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tree.postorderTraversalAnimated(treePanel);
            }
        });

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(inputField);
        inputPanel.add(enterButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(inputPanel);
        buttonPanel.add(deleteButton);
        buttonPanel.add(inorderButton);
        buttonPanel.add(preorderButton);
        buttonPanel.add(postorderButton);

        add(treePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BinaryTreeGUI gui = new BinaryTreeGUI();
                gui.setVisible(true);
            }
        });
    }
}