package mk.ukim.finki.a1;

class JoinSortedLists<E extends Comparable<E>> {
    private SLL<E> result;

    public JoinSortedLists() {
        result = new SLL<>();
    }

    public SLL<E> getResult() {
        return result;
    }

    public void join(SLL<E> list1, SLL<E> list2) {
        SLLNode<E> nodeList1 = list1.getFirst();
        SLLNode<E> nodeList2 = list2.getFirst();

        while (nodeList1 != null && nodeList2 != null) {
            if (nodeList1.element.compareTo(nodeList2.element) < 0) {
                result.insertLast(nodeList1.element);
                nodeList1 = nodeList1.successor;
            } else {
                result.insertLast(nodeList2.element);
                nodeList2 = nodeList2.successor;
            }
        }

        while (nodeList1 != null) {
            result.insertLast(nodeList1.element);
            nodeList1 = nodeList1.successor;
        }

        while (nodeList2 != null) {
            result.insertLast(nodeList2.element);
            nodeList2 = nodeList2.successor;
        }
    }

    @Override
    public String toString() {
        return result.toString();
    }
}
