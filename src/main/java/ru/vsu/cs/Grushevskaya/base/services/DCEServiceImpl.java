package ru.vsu.cs.Grushevskaya.base.services;

import ru.vsu.cs.Grushevskaya.base.models.DiskCategoryEntity;
import ru.vsu.cs.Grushevskaya.base.repository.CategoryRepositoryMemory;
import ru.vsu.cs.Grushevskaya.base.repository.DCERepositoryMemory;

import java.util.List;

public class DCEServiceImpl implements DCEService {
    private final DCERepositoryMemory dce;
    private static DCEServiceImpl example;

    private DCEServiceImpl() {
        dce = DCERepositoryMemory.getInstance();
    }

    public static DCEServiceImpl getExample() {
        if (example == null) {
            example = new DCEServiceImpl();
        }
        return example;
    }

    public void createDiskCategoryEntity(DiskCategoryEntity diskCategoryEntity) {
        // checkDiskId(diskCategoryEntity);
        // checkDiskCategory(diskCategoryEntity);
        dce.add(diskCategoryEntity);
    }

    @Override
    public List<DiskCategoryEntity> getAllDiskCategoryEntities() {
        return dce.getAll();
    }

    @Override
    public DiskCategoryEntity getDiskCategoryEntityById(int id) {
        return dce.getById(id);
    }

    @Override
    public void updateDiskCategoryEntity(int id, DiskCategoryEntity newDiskCategoryEntity) {
        dce.update(id, newDiskCategoryEntity);
    }

    @Override
    public void deleteDiskCategoryEntity(int id) {
        dce.delete(id);
    }
}
