package ru.vsu.cs.Grushevskaya.base.services;

import ru.vsu.cs.Grushevskaya.base.models.DiskType;

import java.util.List;

public interface DiskTypeService {
    void createDiskType(DiskType diskType);
    List<DiskType> getAllDiskTypes();
    DiskType getDiskTypeById(int id);
    void updateDiskType(int id, DiskType newDiskType);
    void deleteDiskType(int id);
}
