package mk.ukim.finki.exercises;

class SLLNode {
    protected int id;
    protected int plata;
    protected SLLNode succ;

    public SLLNode(int id, int plata, SLLNode succ) {
        this.id = id;
        this.plata = plata;
        this.succ = succ;
    }
}
