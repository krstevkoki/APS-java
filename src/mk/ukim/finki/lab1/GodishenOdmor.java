package mk.ukim.finki.lab1;

class GodishenOdmor extends Patuvanje {
    private static final int popustOdDrzava = 1000;
    private int duration;

    public GodishenOdmor(String agency, int price, int duration) {
        super(agency, price - popustOdDrzava);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    int vratiVremeVoDenovi() {
        return duration;
    }
}
