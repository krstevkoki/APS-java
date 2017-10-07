package mk.ukim.finki.a1;

public class PolinomList {
    protected SLL<Monom> listaMonomi;

    public PolinomList() {
        listaMonomi = new SLL<>();
    }

    public PolinomList(SLL<Monom> listaMonomi) {

        this.listaMonomi = listaMonomi;
    }

    public SLL<Monom> getListaMonomi() {
        return listaMonomi;
    }

    public void setListaMonomi(SLL<Monom> listaMonomi) {
        this.listaMonomi = listaMonomi;
    }

    public PolinomList soberi(PolinomList other) {
        SLL<Monom> listaMonomiOther = other.getListaMonomi();
        SLLNode<Monom> nodeListaMonomi = listaMonomi.getFirst();
        SLLNode<Monom> nodeListaMonomiOther = listaMonomiOther.getFirst();
        SLL<Monom> result = new SLL<>();

        while (nodeListaMonomi != null && nodeListaMonomiOther != null) {
            if (nodeListaMonomi.element.compareTo(nodeListaMonomiOther.element) < 0) {
                result.insertLast(nodeListaMonomiOther.element);
                nodeListaMonomiOther = nodeListaMonomiOther.successor;
            } else if (nodeListaMonomi.element.compareTo(nodeListaMonomiOther.element) > 0) {
                result.insertLast(nodeListaMonomi.element);
                nodeListaMonomi = nodeListaMonomi.successor;
            } else {
                result.insertLast(nodeListaMonomi.element.soberi(nodeListaMonomiOther.element));
                nodeListaMonomi = nodeListaMonomi.successor;
                nodeListaMonomiOther = nodeListaMonomiOther.successor;
            }
        }

        while (nodeListaMonomi != null) {
            result.insertLast(nodeListaMonomi.element);
            nodeListaMonomi = nodeListaMonomi.successor;
        }

        while (nodeListaMonomiOther != null) {
            result.insertLast(nodeListaMonomiOther.element);
            nodeListaMonomiOther = nodeListaMonomiOther.successor;
        }

        return new PolinomList(result);
    }

    @Override
    public String toString() {
        return listaMonomi.toString();
    }
}
