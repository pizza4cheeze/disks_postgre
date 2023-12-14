package ru.vsu.cs.Grushevskaya.base.services;

import ru.vsu.cs.Grushevskaya.base.models.Disk;
import ru.vsu.cs.Grushevskaya.base.repository.DiskRepositoryMemory;

import java.util.List;

public class DiskServiceImpl implements DiskService {
    private final DiskRepositoryMemory disks;
    private static DiskServiceImpl example;

    private DiskServiceImpl() {
        disks = DiskRepositoryMemory.getInstance();
    }

    public static DiskServiceImpl getExample() {
        if (example == null) {
            example = new DiskServiceImpl();
        }
        return example;
    }

    @Override
    public void createDisk(Disk disk) {
        // checkDiskType(disk);
        disks.add(disk);
    }

    @Override
    public List<Disk> getAllDisks() {
        return disks.getAll();
    }

    @Override
    public Disk getDiskById(int id) {
        return disks.getById(id);
    }

    @Override
    public void updateDisk(int id, Disk newDisk) {
        disks.update(id, newDisk);
    }

    @Override
    public void deleteDisk(int id) {
        disks.delete(id);
    }
}
