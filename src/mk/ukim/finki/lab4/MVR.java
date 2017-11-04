package mk.ukim.finki.lab4;

import mk.ukim.finki.a3.LinkedQueue;

import java.util.Scanner;

public class MVR {
    public static void main(String[] args) {
        Scanner br = new Scanner(System.in);
        LinkedQueue<Gragjanin> identityCards = new LinkedQueue<>();
        LinkedQueue<Gragjanin> passports = new LinkedQueue<>();
        LinkedQueue<Gragjanin> drivingLicenses = new LinkedQueue<>();
        SLL<Gragjanin> citizens = new SLL<>();
        int N = Integer.parseInt(br.nextLine());

        for (int i = 1; i <= N; i++) {
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            Gragjanin covek = new Gragjanin(imePrezime, lKarta, pasos, vozacka);
            citizens.insertLast(covek);
        }

        SLLNode<Gragjanin> citizen = citizens.getFirst();
        while (citizen != null) {  // 1 0 0; 0 1 0; 0 0 1
            if (citizen.element.getIdentityCard() == 1 && citizen.element.getPassport() == 0 && citizen.element.getDrivingLicense() == 0)
                identityCards.enqueue(citizen.element);
            else if (citizen.element.getIdentityCard() == 0 && citizen.element.getPassport() == 1 && citizen.element.getDrivingLicense() == 0)
                passports.enqueue(citizen.element);
            else if (citizen.element.getIdentityCard() == 0 && citizen.element.getPassport() == 0 && citizen.element.getDrivingLicense() == 1)
                drivingLicenses.enqueue(citizen.element);
            citizen = citizen.next;
        }
        citizen = citizens.getFirst();
        while (citizen != null) {  // 1 1 0
            if (citizen.element.getIdentityCard() == 1 && citizen.element.getPassport() == 1 && citizen.element.getDrivingLicense() == 0)
                passports.enqueue(citizen.element);
            citizen = citizen.next;
        }
        citizen = citizens.getFirst();
        while (citizen != null) {  // 1 0 1
            if (citizen.element.getIdentityCard() == 1 && citizen.element.getPassport() == 0 && citizen.element.getDrivingLicense() == 1)
                drivingLicenses.enqueue(citizen.element);
            citizen = citizen.next;
        }
        citizen = citizens.getFirst();
        while (citizen != null) {  // 0 1 1
            if (citizen.element.getIdentityCard() == 0 && citizen.element.getPassport() == 1 && citizen.element.getDrivingLicense() == 1)
                drivingLicenses.enqueue(citizen.element);
            citizen = citizen.next;
        }
        citizen = citizens.getFirst();  // 1 1 1
        while (citizen != null) {
            if (citizen.element.getIdentityCard() == 1 && citizen.element.getPassport() == 1 && citizen.element.getDrivingLicense() == 1)
                drivingLicenses.enqueue(citizen.element);
            citizen = citizen.next;
        }

        while (!(identityCards.isEmpty()))
            System.out.println(identityCards.dequeue());
        while (!(passports.isEmpty()))
            System.out.println(passports.dequeue());
        while (!(drivingLicenses.isEmpty()))
            System.out.println(drivingLicenses.dequeue());
    }
}
