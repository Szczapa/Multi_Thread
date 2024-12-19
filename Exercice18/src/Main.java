import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[2];
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        Runnable AddToList = () -> {
            for (int i = 0; i <= 9; i++) {
                list.add(String.valueOf(Thread.currentThread().getName()+"-"+"produit"+i));
            }
        };

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(AddToList,"Thread"+i);
            threads[i].start();
        }

        for (Thread thread : threads) thread.join();

        System.out.println("List Final de produit" + list);
    }
}