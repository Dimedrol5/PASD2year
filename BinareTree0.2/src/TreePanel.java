
//TreePanel  v2
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TreePanel extends JPanel {
    private BinaryTree tree;
    private int nodeSize = 30;
    private int verticalSpacing = 50;
    private int horizontalSpacing = 10;
    private int animationDelay = 500; // затримка між кроками анімації
    private BinaryTree.Node animationNode; // вузол, який відображається під час анімації

    public TreePanel(BinaryTree tree) {
        this.tree = tree;
    }

    public void setTree(BinaryTree tree) {
        this.tree = tree;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tree.getRoot() != null) {
            drawTree(g, tree.getRoot(), getWidth() / 2, 30, getWidth() / 4);
        }
    }

    private void drawTree(Graphics g, BinaryTree.Node node, int x, int y, int xOffset) {
        if (node != null) {
            g.setColor(node == tree.getCurrentNode() ? Color.RED : Color.GRAY);
            g.fillOval(x - nodeSize / 2, y - nodeSize / 2, nodeSize, nodeSize);
            g.setColor(node == animationNode ? Color.WHITE : Color.BLACK);
            g.setFont(node == animationNode ? new Font("Arial", Font.BOLD, 14) : new Font("Arial", Font.PLAIN, 12));
            g.drawString(Integer.toString(node.value), x - nodeSize / 4, y + nodeSize / 4);
            g.drawString(Integer.toString(node.value), x - nodeSize / 4, y + nodeSize / 4);

            if (node.left != null) {
                drawLine(g, x - xOffset, y + verticalSpacing, x, y);
                drawTree(g, node.left, x - xOffset, y + verticalSpacing, xOffset / 2);
            }
            if (node.right != null) {
                drawLine(g, x + xOffset, y + verticalSpacing, x, y);
                drawTree(g, node.right, x + xOffset, y + verticalSpacing, xOffset / 2);
            }
        }
    }
    private void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
    }

    public void animateTraversal(ArrayList<BinaryTree.Node> nodes) {
        for (BinaryTree.Node node : nodes) {
            animationNode = node;
            repaint();
            try {
                Thread.sleep(animationDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        animationNode = null;
        repaint();
    }
}