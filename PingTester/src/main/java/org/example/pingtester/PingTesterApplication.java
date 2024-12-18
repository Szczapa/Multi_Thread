package org.example.pingtester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PingTesterApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(PingTesterApplication.class, args);
        PingController pingController = new PingController();
        pingController.start();
    }

}
