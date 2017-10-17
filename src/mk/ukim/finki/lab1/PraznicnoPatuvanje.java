package mk.ukim.finki.lab1;

class PraznicnoPatuvanje extends Patuvanje {
    private int startDay;
    private int startMonth;
    private int endDay;
    private int endMonth;

    public PraznicnoPatuvanje(String agency, int price, int startDay, int startMonth, int endDay, int endMonth) {
        super(agency, price);
        try {
            if (startMonth > endMonth)
                throw new Exception("Iskluchok");
            this.startDay = startDay;
            this.startMonth = startMonth;
            this.endDay = endDay;
            this.endMonth = endMonth;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.startDay = endDay;
            this.startMonth = endMonth;
            this.endDay = startDay;
            this.endMonth = startMonth;
        }
    }

    public int getStartDay() {
        return startDay;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public int getEndDay() {
        return endDay;
    }

    public int getEndMonth() {
        return endMonth;
    }

    @Override
    int vratiVremeVoDenovi() {
        int days = 0;
        days += endDay;
        days += endMonth * 30;
        days -= startDay;
        days -= startMonth * 30;
        return days;
    }
}
