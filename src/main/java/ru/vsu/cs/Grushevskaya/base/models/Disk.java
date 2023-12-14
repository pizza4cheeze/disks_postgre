package ru.vsu.cs.Grushevskaya.base.models;

import ru.vsu.cs.Grushevskaya.base.Identifiable;

public class Disk implements Identifiable {
    private Integer id;
    private String name;
    private int diskTypeId;
    private int yearOfRelease;
    private String description;

    public Disk(String name, int diskTypeId, int yearOfRelease, String description) {
        this.id = null;
        this.name = name;
        this.diskTypeId = diskTypeId;
        this.yearOfRelease = yearOfRelease;
        this.description = description;
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

    public int getDiskTypeId() {
        return diskTypeId;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public String getDescription() {
        return description;
    }
}
