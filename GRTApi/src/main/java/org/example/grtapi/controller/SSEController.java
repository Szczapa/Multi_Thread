package org.example.grtapi.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


@RestController
public class SSEController {
    @GetMapping("/connect")
    public Map<String, String> connect() {
        // Création d'une réponse sous forme de map
        Map<String, String> response = new HashMap<>();
        response.put("status", "Connected");
        response.put("message", "You are successfully connected to the server.");
        return response;
    }


    @GetMapping(value = "/connectsse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map<String, String>> connectSse() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("status", "Connected");
                    response.put("message", "Toujours connecté.");
                    return response;
                })
                .doOnCancel(() -> System.out.println("Le client a fermé la connexion."))
                .doFinally(signalType -> System.out.println("Fin du flux : " + signalType));
    }
}
