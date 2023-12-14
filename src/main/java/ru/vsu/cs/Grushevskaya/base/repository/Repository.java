package ru.vsu.cs.Grushevskaya.base.repository;

import ru.vsu.cs.Grushevskaya.base.Identifiable;

import java.util.List;

public interface Repository<T extends Identifiable> {
    void add(T item);
    T getById(int id);
    List<T> getAll();
    void update(int id, T newItem) throws IllegalArgumentException;
    void delete(int id) throws IllegalArgumentException;
}