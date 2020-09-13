package com.stefa.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public interface IRepository<T> {
    public void add(T t) throws IOException, ParseException;
    public void remove(T t) throws IOException, ParseException;
    public void update(T t) throws IOException, ParseException;
    public ArrayList<T> getAll() throws FileNotFoundException, ParseException;
}
