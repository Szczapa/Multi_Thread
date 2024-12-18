package org.example;

public class Main {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread addThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sharedResource.addElement(i, Thread.currentThread().getName());
            }
        }, "addThread");

        Thread removeThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sharedResource.removeElement(i, Thread.currentThread().getName());
            }
        }, "removeThread");

        addThread.start();
        removeThread.start();

        System.out.println("État final de la liste : " + sharedResource.getSharedList());

    }
}