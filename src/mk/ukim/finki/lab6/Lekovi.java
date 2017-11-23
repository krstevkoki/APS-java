package mk.ukim.finki.lab6;

class Lekovi implements Comparable<Lekovi> {
    private String name;
    private int isOnPositiveList;
    private int price;
    private int inStock;

    public Lekovi(String name, int isOnPositiveList, int price, int inStock) {
        this.name = name.toUpperCase();
        this.isOnPositiveList = isOnPositiveList;
        this.price = price;
        this.inStock = inStock;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lekovi lekovi = (Lekovi) o;
        return name != null ? name.equals(lekovi.name) : lekovi.name == null;
    }

    @Override
    public int hashCode() {
        return (29 * (29 * (29 * 0 + name.charAt(0)) + name.charAt(1)) % 102780 + name.charAt(2));
    }

    @Override
    public int compareTo(Lekovi o) {
        return Integer.compare(this.price, o.price);
    }

    @Override
    public String toString() {

        return isOnPositiveList == 0 ? String.format("%s\nNEG\n%d\n%d", name, price, inStock)
                : String.format("%s\nPOZ\n%d\n%d", name, price, inStock);
    }
}
