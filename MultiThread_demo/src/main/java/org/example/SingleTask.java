package org.example;

public class SingleTask implements Runnable {
    private final String threadName;

    public SingleTask(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(threadName + " SingleTask : Etape : " +i);
        }
        System.out.println(threadName + " SingleTask : Done");
    }
}
