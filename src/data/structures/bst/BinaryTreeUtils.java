package data.structures.bst;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeUtils {

  private static int indent(List<String> lines, int margin) {
    if (margin >= 0) return margin;
    String spaces = " ".repeat(-margin);
    int i = 0;
    for (var line : lines) {
      lines.set(i++, spaces + line);
    }
    return 0;
  }

  private static List<String> merge(List<String> left, List<String> right) {
    int minSize = Math.min(left.size(), right.size());
    int offset = 0;
    for (int i = 0; i < minSize; i++) {
      int padding = 5;
      offset =
          Math.max(
              offset,
              left.get(i).length() + padding - right.get(i).replaceAll("\\S.*", "").length());
    }
    indent(right, -indent(left, offset));
    for (int i = 0; i < minSize; i++) {
      left.set(i, left.get(i) + right.get(i).substring(left.get(i).length()));
    }
    if (right.size() > minSize) {
      left.addAll(right.subList(minSize, right.size()));
    }
    return left;
  }

  private static List<String> buildLines(Node node) {
    if (node == null) return new ArrayList<>();
    List<String> lines = merge(buildLines(node.getLeft()), buildLines(node.getRight()));
    int half = String.valueOf(node.getData()).length() / 2;
    int i = half;
    if (!lines.isEmpty()) {
      String line;
      i = lines.get(0).indexOf('*');
      if (node.getRight() == null) {
        line = " ".repeat(i) + "┌─┘";
        i += 2;
      } else if (node.getLeft() == null) {
        i = indent(lines, i - 2);
        line = " ".repeat(i) + "└─┐";
      } else {
        int dist = lines.get(0).length() - 1 - i;
        line =
            String.format(
                "%s┌%s┴%s┐", " ".repeat(i), "─".repeat(dist / 2 - 1), "─".repeat((dist - 1) / 2));
        i += dist / 2;
      }
      lines.set(0, line);
    }
    lines.add(0, " ".repeat(indent(lines, i - half)) + node.getData());
    lines.add(0, " ".repeat(i + Math.max(0, half - i)) + "*"); // Add a marker for caller
    return lines;
  }

  public static void prettyPrint(Node root) {
    List<String> lines = buildLines(root);
    System.out.println(String.join("\n", lines.subList(1, lines.size())));
  }

  private static int getHeight(Node node) {
    if (node == null) {
      return 0;
    }
    return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
  }

  public static Node balanceTree(Node root) {
    int balanceFactor = getHeight(root.getLeft()) - getHeight(root.getRight());

    if (balanceFactor > 1) {
      if (getHeight(root.getLeft().getLeft()) < getHeight(root.getLeft().getRight())) {
        root.setLeft(leftRotate(root.getLeft()));
      }
      return rightRotate(root);
    } else if (balanceFactor < -1) {
      if (getHeight(root.getRight().getRight()) < getHeight(root.getRight().getLeft())) {
        root.setRight(rightRotate(root.getRight()));
      }
      return leftRotate(root);
    }

    return root;
  }

  private static Node leftRotate(Node y) {
    Node x = y.getRight();
    Node t2 = x.getLeft();

    x.setLeft(y);
    y.setRight(t2);

    return x;
  }

  private static Node rightRotate(Node x) {
    Node y = x.getLeft();
    Node t2 = y.getRight();

    y.setRight(x);
    x.setLeft(t2);

    return y;
  }
}
