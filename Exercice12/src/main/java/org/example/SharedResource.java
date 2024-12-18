package org.example;

import java.util.ArrayList;
import java.util.List;

public class SharedResource {
    private final List<Integer> sharedList = new ArrayList<>();

    public void addElement(int element, String threadName) {
        synchronized (sharedList) {
            sharedList.add(element);
            System.out.println(threadName + " added to shared list " + element);
        }
    }

    public void removeElement(int element, String threadName) {
        synchronized (sharedList) {

            if(sharedList.isEmpty()){
                System.out.println(threadName + " is empty");
                return;
            }
            int value = sharedList.remove(0);
            System.out.println(threadName + " removed from shared list " + value);
        }
    }

    public List<Integer> getSharedList() {
        return sharedList;
    }

}
