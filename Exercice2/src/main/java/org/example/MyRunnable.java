package org.example;

public class MyRunnable implements Runnable {
    private int number;

    public MyRunnable(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("Le carré de" + number + " est : " + Math.pow(number, 2));
    }
}
