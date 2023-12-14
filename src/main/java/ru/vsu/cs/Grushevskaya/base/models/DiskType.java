package ru.vsu.cs.Grushevskaya.base.models;

import ru.vsu.cs.Grushevskaya.base.Identifiable;

public class DiskType implements Identifiable {
    private Integer id;
    private String name;

    public DiskType(String name) {
        this.id = null;
        this.name = name;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
