package data.structures.bst;

public class BinarySearchTree {
  private Node root;

  public BinarySearchTree() {
    this.root = null;
  }

  public Node getRoot() {
    return root;
  }

  public void insert(int data) {
    this.root = insertNode(this.root, data);
  }

  public void delete(int data) {
    System.out.println("Deleting " + data + " from the tree.");
    this.root = deleteNode(this.root, data);
  }

  public void inorder() {
    System.out.print("Inorder traversal: ");
    inorderTraversal(this.root);
    System.out.println();
  }

  private void inorderTraversal(Node root) {
    if (root == null) {
      return;
    }
    inorderTraversal(root.getLeft());
    System.out.print(root.getData() + ", ");
    inorderTraversal(root.getRight());
  }

  private Node insertNode(Node root, int data) {
    if (root == null) {
      root = new Node(data);
      return root;
    }
    if (data <= root.getData()) {
      root.setLeft(insertNode(root.getLeft(), data));
    } else {
      root.setRight(insertNode(root.getRight(), data));
    }
    return root;
  }

  private Node deleteNode(Node root, int data) {
    if (data == root.getData()) {
      /* No Child */
      if (root.getLeft() == null && root.getRight() == null) {
        return null;
      }

      /* One Child */
      if (root.getLeft() == null) {
        return root.getRight();
      } else if (root.getRight() == null) {
        return root.getLeft();
      }

      /* Two Children */
      Node min = findMin(root.getRight());
      root.setData(min.getData());
      root.setRight(deleteNode(root.getRight(), root.getData()));
    }
    if (data < root.getData() && root.getLeft() != null) {
      root.setLeft(deleteNode(root.getLeft(), data));
    } else if (root.getRight() != null) {
      root.setRight(deleteNode(root.getRight(), data));
    }
    return root;
  }

  private Node findMin(Node root) {
    while (root.getLeft() != null) {
      root = root.getLeft();
    }
    return root;
  }
}
