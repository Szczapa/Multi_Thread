import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int threadPoolSize = 2;
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        Runnable addToList = () -> {
            for (int i = 0; i <= 9; i++) {
                list.add(String.valueOf(Thread.currentThread().getName()+"-"+"produit"+i));
            }
        };

        for (int i = 0; i < threadPoolSize; i++) {
            executor.submit(addToList);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.sleep(100);
        }

        System.out.println("List Final de produit" + list);
    }
}