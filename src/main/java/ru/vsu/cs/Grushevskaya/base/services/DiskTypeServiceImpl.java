package ru.vsu.cs.Grushevskaya.base.services;

import ru.vsu.cs.Grushevskaya.base.models.DiskType;
import ru.vsu.cs.Grushevskaya.base.repository.DiskRepositoryMemory;
import ru.vsu.cs.Grushevskaya.base.repository.DiskTypeRepositoryMemory;

import java.util.List;

public class DiskTypeServiceImpl implements DiskTypeService {
    private final DiskTypeRepositoryMemory diskTypes;
    private static DiskTypeServiceImpl example;

    private DiskTypeServiceImpl() {
        diskTypes = DiskTypeRepositoryMemory.getInstance();
    }

    public static DiskTypeServiceImpl getExample() {
        if (example == null) {
            example = new DiskTypeServiceImpl();
        }
        return example;
    }

    @Override
    public void createDiskType(DiskType diskType) {
        diskTypes.add(diskType);
    }

    @Override
    public List<DiskType> getAllDiskTypes() {
        return diskTypes.getAll();
    }

    @Override
    public DiskType getDiskTypeById(int id) {
        return diskTypes.getById(id);
    }

    @Override
    public void updateDiskType(int id, DiskType newDiskType) {
        diskTypes.update(id, newDiskType);
    }

    @Override
    public void deleteDiskType(int id) {
        diskTypes.delete(id);
    }
}
