package org.example;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        demoCreationThread();

        for (int i = 0;  i<5 ; i++){
            new Thread(new SingleTask("Robert"+i)).start();
        }
        System.out.println("Done");
    }

    public static void demoCreationThread() throws ExecutionException, InterruptedException {

        // Avec classs Thread
        MyThread thread1 = new MyThread();
        thread1.start();

        // Avec interface Runnable
       Thread thread2 = new Thread(new MyRunnable());
       thread2.start();

       // Runable avec Lambda
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " Lambda : " + i);
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread3.start();

        // Thread avec ExecutorService
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> callableTask = ()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+ " Exercutor Service: " + i);
                Thread.sleep(300);
            }
            return "Callable Terminé";
        };
        Future<String> futureResult = executorService.submit(callableTask);
        System.out.println("Resultat du callable :"+futureResult.get());
        executorService.shutdown();
    }
}