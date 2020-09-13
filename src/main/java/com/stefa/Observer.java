package com.stefa;

import java.io.FileNotFoundException;
import java.text.ParseException;

abstract public class Observer {
    public abstract void Update() throws FileNotFoundException, ParseException;
}
