package mk.ukim.finki.lab4;

class Gragjanin {
    private String name;
    private int identityCard;
    private int passport;
    private int drivingLicense;

    public Gragjanin(String name, int identityCard, int passport, int drivingLicense) {
        this.name = name;
        this.identityCard = identityCard;
        this.passport = passport;
        this.drivingLicense = drivingLicense;
    }

    public String getName() {
        return name;
    }

    public int getIdentityCard() {
        return identityCard;
    }

    public int getPassport() {
        return passport;
    }

    public int getDrivingLicense() {
        return drivingLicense;
    }

    @Override
    public String toString() {
        return name;
    }
}
