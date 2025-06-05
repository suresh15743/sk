class Node {
    int key;
    Node left, right;
    public Node(int val) {
        key = val;
        left = right = null;
    }
}
class BinaryTree {
    Node root;
    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(" " + node.key);
            inorder(node.right);
        }
    }
    void preorder(Node node) {
        if (node != null) {
            System.out.println(" " + node.key);
            preorder(node.left);
            preorder(node.right);
        }
    }
    void postorder(Node node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.println(" " + node.key);
        }
    }
    public static void main(String args[]) {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        System.out.println("Inorder Traversal:");
        tree.inorder(tree.root);
        System.out.println("Preorder Traversal:");
        tree.preorder(tree.root);
        System.out.println("Postorder Traversal:");
        tree.postorder(tree.root);
    }
}
