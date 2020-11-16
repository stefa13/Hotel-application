package com.stefa.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface IRepository<T> {
    public void add(T t) throws IOException, ParseException, SQLException;

    public void remove(T t) throws IOException, ParseException, SQLException;

    public void update(T t) throws IOException, ParseException, SQLException;

    public ArrayList<T> getAll() throws FileNotFoundException, ParseException, SQLException;
}
