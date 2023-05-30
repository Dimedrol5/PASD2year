//BinaryTree
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class BinaryTree {
    private Node root;
    private Node currentNode;

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    public BinaryTree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = insertRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = insertRecursive(current.right, value);
        }

        return current;
    }

    public void inorderTraversalAnimated(TreePanel treePanel) {
        Stack<Node> stack = new Stack<>();
        final Node[] node = {root};
        Timer timer = new Timer(500, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (node[0] != null) {
                    stack.push(node[0]);
                    node[0] = node[0].left;
                }

                if (!stack.isEmpty()) {
                    node[0] = stack.pop();
                    currentNode = node[0];
                    System.out.print(node[0].value + " ");
                    node[0] = node[0].right;
                } else {
                    timer.stop();
                    currentNode = null;
                }
                treePanel.repaint();
            }
        });
        timer.start();
    }

    public void preorderTraversalAnimated(TreePanel treePanel) {
        Stack<Node> stack = new Stack<>();
        final Node[] node = {root};
        Timer timer = new Timer(500, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (node[0] != null) {
                    currentNode = node[0];
                    System.out.print(node[0].value + " ");
                    stack.push(node[0].right);
                    node[0] = node[0].left;
                } else if (!stack.isEmpty()) {
                    node[0] = stack.pop();
                } else {
                    timer.stop();
                    currentNode = null;
                }
                treePanel.repaint();
            }
        });
        timer.start();
    }

    public void postorderTraversalAnimated(TreePanel treePanel) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        Timer timer = new Timer(500, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!stack1.isEmpty()) {
                    Node node = stack1.pop();
                    stack2.push(node);

                    if (node.left != null) {
                        stack1.push(node.left);
                    }
                    if (node.right != null) {
                        stack1.push(node.right);
                    }
                } else if (!stack2.isEmpty()) {
                    currentNode = stack2.pop();
                    System.out.print(currentNode.value + " ");
                } else {
                    timer.stop();
                    currentNode = null;
                }
                treePanel.repaint();
            }
        });
        timer.start();
    }
}