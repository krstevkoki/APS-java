package mk.ukim.finki.lab2;

class Vraboten {
    private int ID;
    private int salary;

    public Vraboten(int ID, int salary) {
        this.ID = ID;
        this.salary = salary;
    }

    public int getID() {
        return ID;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return ID + " " + salary;
    }
}
