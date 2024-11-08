package com.sda;

public class CarRace {
    public static void main(String[] args) {
        Thread car1 = new Thread(new Car("🚗 Makina e Kuqe"));
        Thread car2 = new Thread(new Car("🚙 Makina Blu"));

        System.out.println("Gara fillon!");
        System.out.println("-------------------------------------");

        car1.start();
        car2.start();

        try {
            car1.join();
            car2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("-------------------------------------");
        System.out.println("Gara përfundoi!");
    }
}

class Car implements Runnable {
    private String name;
    private int distance = 0;
    private static final int RACE_LENGTH = 20;

    public Car(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (distance < RACE_LENGTH) {
            try {
                Thread.sleep((long) (Math.random() * 500)); // Vonesë e rastësishme 0-500ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            distance++;
            printProgress();
        }
        System.out.println(name + " arriti në finish!");
    }

    private void printProgress() {
        StringBuilder progress = new StringBuilder("|");
        for (int i = 0; i < RACE_LENGTH; i++) {
            if (i < distance) {
                progress.append("=");
            } else if (i == distance) {
                progress.append(name);
            } else {
                progress.append(" ");
            }
        }
        progress.append("|");
        System.out.println(progress);
    }
}