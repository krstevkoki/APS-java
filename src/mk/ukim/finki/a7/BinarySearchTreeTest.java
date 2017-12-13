package mk.ukim.finki.a7;

import java.util.Random;

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        Random r = new Random();
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        for (int i = 0; i < 10; i++)
            bst.insert(r.nextInt(200));

        System.out.println("Tree is empty? " + bst.isEmpty());
        bst.printTree();

        Integer max = bst.findMax();
        Integer min = bst.findMin();
        bst.remove(bst.findMax());
        bst.remove(bst.findMin());
        System.out.println();
        bst.printTree();

        bst.insert(max);
        bst.insert(min);
        System.out.println();
        bst.printTree();

        bst.makeEmpty();
        System.out.println();
        bst.printTree();
    }
}
