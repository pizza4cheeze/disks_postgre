package ru.vsu.cs.Grushevskaya.base.services;

import ru.vsu.cs.Grushevskaya.base.models.Disk;

import java.util.List;

public interface DiskService {
    void createDisk(Disk disk);
    List<Disk> getAllDisks();
    Disk getDiskById(int id);
    void updateDisk(int id, Disk newDisk);
    void deleteDisk(int id);
}
