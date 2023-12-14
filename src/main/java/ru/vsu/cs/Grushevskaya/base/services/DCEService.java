package ru.vsu.cs.Grushevskaya.base.services;

import ru.vsu.cs.Grushevskaya.base.models.DiskCategoryEntity;

import java.util.List;

public interface DCEService {
    void createDiskCategoryEntity(DiskCategoryEntity diskCategory);
    List<DiskCategoryEntity> getAllDiskCategoryEntities();
    DiskCategoryEntity getDiskCategoryEntityById(int id);
    void updateDiskCategoryEntity(int id, DiskCategoryEntity newDiskCategoryEntity);
    void deleteDiskCategoryEntity(int id);
}
