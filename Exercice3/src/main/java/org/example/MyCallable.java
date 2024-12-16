package org.example;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    private int nombre;

    public MyCallable(int i) {
        this.nombre = i;
    }

    // La méthode call qui retourne le résultat
    @Override
    public String call() {
        int cube = nombre * nombre * nombre;
        return "Le cube de " + nombre + " est " + cube;
    }

}
