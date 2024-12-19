import java.util.concurrent.ConcurrentLinkedQueue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {

        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

        Runnable listAdd = () -> {
            for (int i = 0; i < 10; i++) {
                String element = "Producer-Element-" + i;
                queue.add(element);
                System.out.println(Thread.currentThread().getName() + " a ajouté : " + element);

            }
        };

        Runnable listRemove = () -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < 10; i++) {
                String element = queue.poll();
                if (element != null) {
                    System.out.println(Thread.currentThread().getName() + " a retiré : " + element);
                } else {
                    System.out.println(Thread.currentThread().getName() + " n'a trouvé aucun élément à retirer.");
                }
            }
        };

        Thread producer = new Thread(listAdd);
        producer.setName("producer");
        Thread consumer = new Thread(listRemove);
        consumer.setName("consumer");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("État final de la liste : "+queue);
    }
}