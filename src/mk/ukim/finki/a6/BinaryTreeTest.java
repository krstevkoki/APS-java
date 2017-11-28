package mk.ukim.finki.a6;

public class BinaryTreeTest {
    public static void main(String[] args) {
        BinaryTree<Character>.BinaryTreeNode<Character> a, b, c;
        BinaryTree<Character> tree = new BinaryTree<>('F');
        a = tree.addChild(tree.getRoot(), "LeFt", 'D');
        b = tree.addChild(a, " LeFt  ", 'B');
        c = tree.addChild(b, "    LEFt  ", 'A');
        c = tree.addChild(b, "RIGht", 'C');
        c = tree.addChild(a, "rigHt  ", 'E');
        a = tree.addChild(tree.getRoot(), "  RIgHt      ", 'G');
        b = tree.addChild(a, "RIGHT", 'I');
        c = tree.addChild(b, "LEFT", 'H');
        c = tree.addChild(b, "right", 'J');

        tree.printTree("inOrdeR");
        tree.printTree("  PREorder  ");
        tree.printTree(" PoStOrDeR ");

        tree.printInOrderNonRecursive();
        tree.printPreOrderNonRecursive();
        tree.printPostOrderNonRecursive();

        System.out.println("Brojot na vnatresni jazli vo drvoto e: " + tree.countInsideNodes());
        System.out.println("Brojot na listovi vo drvoto e: " + tree.countLeaves());
        System.out.println("Dlabocinata na drvoto e: " + tree.depth());

        tree.mirror();
        tree.printTree("INORDER");
    }
}
