package mk.ukim.finki.exercises;

import java.util.NoSuchElementException;

class SLL {
    private SLLNode first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;
    }

    public void insertFirst(int id, int plata) {
        first = new SLLNode(id, plata, first);
    }

    public void insertLast(int id, int plata) {
        if (first != null) {
            SLLNode tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = new SLLNode(id, plata, null);
        } else
            insertFirst(id, plata);
    }

    public SLLNode getFirst() {
        return first;
    }

    public void deleteFirst() {
        if (first != null) {
            first = first.succ;
        } else throw new NoSuchElementException();
    }

    public void delete(SLLNode node) {
        if (first != null) {
            if (first.equals(node))
                deleteFirst();
            else {
                SLLNode temp = first;
                while (temp.succ.succ != null && !(temp.succ.equals(node)))
                    temp = temp.succ;
                if (temp.succ.equals(node))
                    temp.succ = node.succ;
                else throw new NoSuchElementException();
            }
        } else throw new NoSuchElementException();
    }

    public SLL brisi_pomali_od(int iznos) throws Exception {
        // Vasiot kod tuka
        if (first != null) {
            SLLNode temp = first;
            while (temp != null) {
                if (temp.plata < iznos) {
                    delete(temp);
                }
                temp = temp.succ;
            }
            if (!(this.first == null)) return this;
            else throw new Exception("nema");
        } else throw new NoSuchElementException();
    }

    public SLL sortiraj_opagacki() {
        // Vasiot kod tuka
        if (first != null) {
            SLLNode tempFirst = this.first;
            while (tempFirst.succ != null) {
                SLLNode tempSuccessor = this.first;
                while (tempSuccessor.succ != null) {
                    if (tempSuccessor.id < tempSuccessor.succ.id) {
                        int tempId = tempSuccessor.succ.id;  // int temp = a[j + 1]
                        int tempPlata = tempSuccessor.succ.plata;
                        tempSuccessor.succ.id = tempSuccessor.id;  // a[j + 1] = a[j]
                        tempSuccessor.succ.plata = tempSuccessor.plata;
                        tempSuccessor.id = tempId;  // a[j] = temp
                        tempSuccessor.plata = tempPlata;
                    }
                    tempSuccessor = tempSuccessor.succ;
                }
                tempFirst = tempFirst.succ;
            }
            return this;
        } else throw new NoSuchElementException();
    }

    public void pecati(SLL lista) {
        SLLNode p = lista.first;
        while (p != null) {
            System.out.println(p.id + " " + p.plata);
            p = p.succ;
        }
    }
}
