package mk.ukim.finki.lab1;

class Maraton implements IMaraton {
    private String city;
    private int year;
    private Atleticar[] atleticari;

    public Maraton() {
    }

    public Maraton(String city, int year, Atleticar[] atleticari) {
        this.city = city;
        this.year = year;
        this.atleticari = atleticari;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Atleticar[] getAtleticari() {
        return atleticari;
    }

    public void setAtleticari(Atleticar[] atleticari) {
        this.atleticari = atleticari;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(city);
        sb.append("\n");
        sb.append(year);
        sb.append("\n");
        for (Atleticar atleticar : atleticari)
            sb.append(atleticar);
        return sb.toString();
    }

    @Override
    public Atleticar najdobroVreme() {
        Atleticar min = atleticari[0];
        for (Atleticar atleticar : atleticari)
            if (atleticar.getTime() < min.getTime())
                min = atleticar;
        return min;
    }

    @Override
    public int atleticariOd(String s) {
        int counter = 0;
        for (Atleticar atleticar : atleticari)
            if (s.equals(atleticar.getCountry()))
                ++counter;
        return counter;
    }
}
