import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        int threadsBuyerCount = 3;
        int threadsSellerCount = 1;
        Thread[] threads = new Thread[threadsBuyerCount];
        Thread[] threads2 = new Thread[threadsSellerCount];
        Random random = new Random();

        ConcurrentHashMap<String, Integer> inventory = new ConcurrentHashMap<>();
        inventory.put("ProduitA", 10);
        inventory.put("ProduitB", 10);
        inventory.put("ProduitC", 10);

        Runnable buyerTask = () -> {
            String[] products = inventory.keySet().toArray(new String[0]);
            for (int i = 0; i < 10; i++) {
                String product = products[random.nextInt(products.length)];
                inventory.computeIfPresent(product, (key, quantity) -> {
                    if (quantity > 0) {
                        System.out.println(Thread.currentThread().getName() + " a acheté 1 unité de " + key);
                        return quantity - 1;
                    } else {
                        System.out.println(Thread.currentThread().getName() + " n'a pas pu acheter " + key + " (en rupture de stock)");
                        return quantity;
                    }
                });
            }
        };

        Runnable sellerTask = () -> {
            for (int i = 0; i < 10; i++) {
                for (String product : inventory.keySet()) {
                    inventory.compute(product, (key, quantity) -> {
                        System.out.println("Réapprovisionneur a réapprovisionné 10 unités de " + key);
                        return quantity + 10;
                    });
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        for (int i = 0; i < threadsBuyerCount; i++) {
            threads[i] = new Thread(buyerTask, "Acheteur-" + (i + 1));
            threads[i].start();
        }

        for (int i = 0; i < threadsSellerCount; i++) {
            threads2[i] = new Thread(sellerTask, "Réapprovisionneur-" + (i + 1));
            threads2[i].start();
        }

        for (Thread acheteur : threads) acheteur.join();
        for (Thread appro : threads2) appro.join();
        System.out.println("Inventaire final : " + inventory);
    }
}