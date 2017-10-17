package mk.ukim.finki.lab1;

abstract class Patuvanje {
    private String agency;
    int price;

    public Patuvanje(String agency, int price) {
        this.agency = agency;
        this.price = price;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static int vratiMinCena(Patuvanje[] niza, int n, Patuvanje zaSporedba) {
        int minPrice = 0;
        boolean flag = true;  // if there is not any journey longer than zaSporedba
        for (Patuvanje patuvanje : niza) {
            if (patuvanje.vratiVremeVoDenovi() > zaSporedba.vratiVremeVoDenovi()) {
                if (flag) {
                    minPrice = patuvanje.getPrice();
                    flag = false;
                    continue;
                }
                if (minPrice > patuvanje.getPrice())
                    minPrice = patuvanje.getPrice();
            }
        }
        return flag ? 0 : minPrice;
    }

    abstract int vratiVremeVoDenovi();

    @Override
    public String toString() {
        return agency;
    }
}
