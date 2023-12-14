package ru.vsu.cs.Grushevskaya.base.models;

import ru.vsu.cs.Grushevskaya.base.Identifiable;

public class DiskCategoryEntity implements Identifiable {
    private Integer id;
    private int diskId;
    private int categoryId;

    public DiskCategoryEntity(int diskId, int categoryId) {
        this.id = null;
        this.diskId = diskId;
        this.categoryId = categoryId;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    public int getDiskId() {
        return diskId;
    }

    public int getCategoryId() {
        return categoryId;
    }
}
