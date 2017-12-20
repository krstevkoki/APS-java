package mk.ukim.finki.exercises.kolokvium2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BNodeN<E> {
    static int LEFT = 1;
    static int RIGHT = 2;

    E info;
    BNodeN<E> left;
    BNodeN<E> right;
    char leftTag;
    char rightTag;

    public BNodeN(E info) {
        this.info = info;
        left = null;
        right = null;
        leftTag = '-';
        rightTag = '-';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BNodeN<?> bNodeN = (BNodeN<?>) o;
        if (leftTag != bNodeN.leftTag) return false;
        if (rightTag != bNodeN.rightTag) return false;
        if (info != null ? !info.equals(bNodeN.info) : bNodeN.info != null) return false;
        if (left != null ? !left.equals(bNodeN.left) : bNodeN.left != null) return false;
        return right != null ? right.equals(bNodeN.right) : bNodeN.right == null;
    }

    @Override
    public int hashCode() {
        int result = info != null ? info.hashCode() : 0;
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        result = 31 * result + (int) leftTag;
        result = 31 * result + (int) rightTag;
        return result;
    }
}

class BTreeN<E> {
    BNodeN<E> head;

    public BTreeN() {
        head = new BNodeN<E>(null);
        // po definicija ako nema koren, t.e. ako stebloto e prazno
        head.left = head;
        head.leftTag = '-';
        // kaj vodacot sekogas desnata vrska pokazuva kon samiot sebe
        head.right = head;
        head.rightTag = '+';
    }

    public BNodeN<E> makeRoot(E elem) {
        BNodeN<E> tmp = new BNodeN<E>(elem);
        return makeRootNode(tmp);
    }

    public BNodeN<E> makeRootNode(BNodeN<E> tmp) {
        head.left = tmp;
        head.leftTag = '+';

        tmp.left = head;
        tmp.leftTag = '-';
        tmp.right = head;
        tmp.rightTag = '-';

        return tmp;
    }

    public BNodeN<E> addChild(BNodeN<E> node, int where, E elem) {
        BNodeN<E> tmp = new BNodeN<E>(elem);
        return addChildNode(node, where, tmp);
    }

    public BNodeN<E> addChildNode(BNodeN<E> node, int where, BNodeN<E> tmp) {
        if (where == BNodeN.LEFT) {
            if (node.leftTag == '+')  // veke postoi element
                return null;

            tmp.left = node.left;
            tmp.leftTag = '-';

            tmp.right = node;
            tmp.rightTag = '-';

            node.left = tmp;
            node.leftTag = '+';
        } else {
            if (node.rightTag == '+')  // veke postoi element
                return null;

            tmp.right = node.right;
            tmp.rightTag = '-';

            tmp.left = node;
            tmp.leftTag = '-';

            node.right = tmp;
            node.rightTag = '+';
        }
        return tmp;
    }

    public BNodeN<E> successorPreorder(BNodeN<E> node) {
        if (node.leftTag == '+')
            return node.left;
        if (node.leftTag == '-' && node.rightTag == '+')
            return node.right;

        BNodeN<E> temp = node;

        while (temp.rightTag == '-')
            temp = temp.right;

        return temp.right;
    }

    public int getNumberOfRelations() {
        // vasiot kod ovde
        if (head.leftTag == '-')
            return 0;
        BNodeN<E> tmp = head.left;
        int counter = 0;
        while (!tmp.equals(head)) {
            if (tmp.leftTag == '+') {
                if (tmp.left.leftTag == '+')
                    ++counter;
                if (tmp.left.rightTag == '+')
                    ++counter;
            }
            if (tmp.rightTag == '+') {
                if (tmp.right.leftTag == '+')
                    ++counter;
                if (tmp.right.rightTag == '+')
                    ++counter;
            }
            tmp = successorPreorder(tmp);
        }
        return counter;
    }
}

public class Relations {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st;
            int N = Integer.parseInt(br.readLine());
            BNodeN<Integer> nodes[] = (BNodeN<Integer>[]) new BNodeN[N];
            BTreeN<Integer> tree = new BTreeN<Integer>();

            for (int i = 0; i < N; i++)
                nodes[i] = null;

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                st = new StringTokenizer(line);
                int index = Integer.parseInt(st.nextToken());
                nodes[index] = new BNodeN<Integer>(Integer.parseInt(st.nextToken()));
                String action = st.nextToken();
                if (action.equals("LEFT")) {
                    tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNodeN.LEFT, nodes[index]);
                } else if (action.equals("RIGHT")) {
                    tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNodeN.RIGHT, nodes[index]);
                } else {
                    // this node is the root
                    tree.makeRootNode(nodes[index]);
                }
            }
            // vasiot kod ovde
            System.out.println(tree.getNumberOfRelations());
        }
    }
}

