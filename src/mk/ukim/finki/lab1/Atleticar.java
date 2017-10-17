package mk.ukim.finki.lab1;

class Atleticar {
    private String name;
    private String gender;
    private int age;
    private double time;
    private String country;

    public Atleticar() {
    }

    public Atleticar(String name, String gender, int age, double time, String country) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.time = time;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("\n");
        sb.append(age);
        sb.append("\n");
        sb.append(country);
        sb.append("\n");
        sb.append(time);
        sb.append("\n");
        return sb.toString();
    }
}
