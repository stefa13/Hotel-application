package com.stefa;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;

abstract public class Observer {
    public abstract void Update() throws FileNotFoundException, ParseException, SQLException;
}
