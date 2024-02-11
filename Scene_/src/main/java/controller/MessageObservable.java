package controller;

import controller.Observable;
import controller.Observer;

import java.util.ArrayList;
import java.util.List;

public class MessageObservable implements Observable {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        // Notifica tutti gli observer registrati
        for (Observer observer : observers) {
            observer.notify(message);
        }
    }
}
