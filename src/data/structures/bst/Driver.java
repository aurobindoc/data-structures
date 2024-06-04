package data.structures.bst;

public class Driver {
  public static void main(String[] args) {
    BinarySearchTree bst = new BinarySearchTree();
    bst.insert(10);
    bst.insert(5);
    bst.insert(15);
    bst.insert(3);
    bst.insert(7);
    bst.insert(18);
    bst.insert(16);
    bst.insert(19);
    BinaryTreeUtils.prettyPrint(bst.getRoot());

    bst.delete(3);
    BinaryTreeUtils.prettyPrint(bst.getRoot());
    bst.delete(15);
    BinaryTreeUtils.prettyPrint(bst.getRoot());
    bst.delete(10);
    BinaryTreeUtils.prettyPrint(bst.getRoot());
  }
}
