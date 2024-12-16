package org.example;

public class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <11 ; i++) {
            System.out.println(getName()+" - Compteur : "+ i);
        }
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
