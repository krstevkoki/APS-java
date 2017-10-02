package mk.ukim.finki.a0;

import java.util.Scanner;

public class Kompanija {
    private Vraboten[] employees;

    public Kompanija() {
        this.employees = new Vraboten[10];
    }

    public Kompanija(Vraboten[] employees) {
        this.employees = employees;
    }

    public Vraboten najangaziran() {
        Vraboten max = employees[0];
        for (int i = 0; i < employees.length; ++i) {
            if (max.vkupnoCasovi() < employees[i].vkupnoCasovi())
                max = employees[i];
        }
        return max;
    }

    public void pecatiPoUspesnost() {
        for (int i = 0; i < employees.length - 1; ++i) {
            for (int j = 0; j < employees.length - i - 1; ++j) {
                if (employees[j].procentZavrseni() < employees[j + 1].procentZavrseni()) {
                    Vraboten pom = employees[j];
                    employees[j] = employees[j + 1];
                    employees[j + 1] = pom;
                }
            }
        }
        for (int i = 0; i < employees.length; ++i) {
            System.out.printf("Vraboten: %s %s Usepesnost: %.2f\n", employees[i].getName(), employees[i].getSurname(),
                    employees[i].procentZavrseni());
        }
    }

    public void pecati() {
        for (Vraboten employee : employees) {
            System.out.println(employee.toString());
        }
    }

    public static void main(String[] args) {
        Scanner intScanner = new Scanner(System.in);
        Scanner stringScanner = new Scanner(System.in);
        Scanner booleanScanner = new Scanner(System.in);
        int n = intScanner.nextInt();
        Vraboten[] pom = new Vraboten[n];
        for (int i = 0; i < n; i++) {
            Vraboten v = new Vraboten();
            v.setName(stringScanner.nextLine());
            v.setSurname(stringScanner.nextLine());
            v.setWorkingExperience(intScanner.nextInt());
            v.setNumPoints(intScanner.nextInt());
            pom[i] = v;
            int p = intScanner.nextInt();
            for (int j = 0; j < p; j++) {
                Zadaca z = new Zadaca();
                z.setNumHours(intScanner.nextInt());
                z.setDescription(stringScanner.nextLine());
                z.setStatus(booleanScanner.nextBoolean());
                v.dodadiZadaca(z);
            }
        }
        Kompanija k = new Kompanija(pom);
        k.pecati();
        k.pecatiPoUspesnost();
        System.out.println("Najangaziran vraboten e: " + k.najangaziran().toString());
    }
}

class Zadaca {
    private String description;
    private int numHours;
    private boolean status;

    public Zadaca() {
        this.numHours = 0;
        this.status = false;
        this.description = "";
    }

    public Zadaca(String description) {
        this.description = description;
        this.numHours = 0;
        this.status = false;
    }

    public Zadaca(String description, int numHours) {
        this.description = description;
        this.numHours = numHours;
        this.status = false;
    }

    public Zadaca(String description, int numHours, boolean status) {
        this.description = description;
        this.numHours = numHours;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumHours() {
        return numHours;
    }

    public void setNumHours(int numHours) {
        this.numHours = numHours;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Opis: " + description + "\nCasovi: " + numHours + "\nStatus: " + (status ? "aktivna" : "zavrsena");
    }
}

class Vraboten {
    private String name;
    private String surname;
    private static double POINT = 50.0;
    private double salary;
    private int workingExperience;
    private int numPoints;
    private Zadaca[] tasks;
    private int numTasks;

    public Vraboten() {
        this.name = "";
        this.surname = "";
        this.salary = 0.0;
        this.workingExperience = 0;
        this.numPoints = 0;
        this.tasks = new Zadaca[10];
        this.numTasks = 0;
    }

    public Vraboten(String name, String surname, int workingExperience, int numPoints) {
        this.name = name;
        this.surname = surname;
        this.workingExperience = workingExperience;
        this.numPoints = numPoints;
        this.salary = numPoints * POINT;
        this.tasks = new Zadaca[10];
        this.numTasks = 0;
    }

    public void dodadiZadaca(Zadaca z) {
        if (numTasks == 10)
            System.out.println("Ne moze da se dodade zadacata!");
        else
            tasks[numTasks++] = z;
    }

    public int vkupnoCasovi() {
        int sum = 0;
        for (int i = 0; i < numTasks; ++i) {
            sum += tasks[i].getNumHours();
        }
        return sum;
    }

    public double procentZavrseni() {
        int completedTasks = 0;
        for (int i = 0; i < numTasks; ++i) {
            if (tasks[i].isStatus())
                ++completedTasks;
        }
        return (double) completedTasks / numTasks;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public static double getPOINT() {
        return POINT;
    }

    public static void setPOINT(double POINT) {
        Vraboten.POINT = POINT;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getWorkingExperience() {
        return workingExperience;
    }

    public void setWorkingExperience(int workingExperience) {
        this.workingExperience = workingExperience;
    }

    public int getNumPoints() {
        return numPoints;
    }

    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
    }

    public Zadaca[] getTasks() {
        return tasks;
    }

    public void setTasks(Zadaca[] tasks) {
        this.tasks = tasks;
    }

    public int getNumTasks() {
        return numTasks;
    }

    public void setNumTasks(int numTasks) {
        this.numTasks = numTasks;
    }
}
