package com.stefa;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;

public class Subject {
    private ArrayList<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void Notify() throws FileNotFoundException, ParseException {
        for (Observer observer : observers) {
            observer.Update();
        }
    }
}
