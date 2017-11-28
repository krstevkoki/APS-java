package mk.ukim.finki.a6;

import java.util.Iterator;

public class SLLTreeTest {
    public static void main(String[] args) {
        Tree.Node<String> a, b, c, d;
        SLLTree<String> t = new SLLTree<>();

        t.makeRoot("C:");
        a = t.addChild(t.root(), "Program files");
        b = t.addChild(a, "CodeBlocks");
        c = t.addChild(b, "codeblocks.dll");
        c = t.addChild(b, "codeblocks.exe");
        b = t.addChild(a, "Nodepad++");
        c = t.addChild(b, "langs.xml");
        d = c;
        c = t.addChild(b, "readme.txt");
        c = t.addChild(b, "notepad++.exe");
        a = t.addChild(t.root(), "Users");
        b = t.addChild(a, "Darko");
        c = t.addChild(b, "Desktop");
        c = t.addChild(b, "Downloads");
        c = t.addChild(b, "My Documents");
        c = t.addChild(b, "My Pictures");
        b = t.addChild(a, "Public");
        a = t.addChild(t.root(), "Windows");
        b = t.addChild(a, "Media");

        t.printTree();

        t.remove(d);
        t.printTree();

        Iterator<String> childrenIterator = t.children(t.root());

        System.out.println("The root of the tree is: " + "[" + t.root() + "]");
        System.out.println("The maximum number of children in the tree is: " + t.countMaxChildren());
        System.out.println("The parent of " + "[" + b + "] is: " + "[" + t.parent(b) + "]");
        System.out.print("Childrens of " + "[" + t.root() + "] are: ");
        while (childrenIterator.hasNext())
            System.out.print("[" + childrenIterator.next() + "]" + " ");
        System.out.println();

        t.remove(t.root());
        System.out.println("The maximum number of children in the tree is: " + t.countMaxChildren());
    }
}
