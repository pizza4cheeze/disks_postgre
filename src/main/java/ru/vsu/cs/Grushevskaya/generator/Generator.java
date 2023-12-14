package ru.vsu.cs.Grushevskaya.generator;

import ru.vsu.cs.Grushevskaya.base.models.Category;
import ru.vsu.cs.Grushevskaya.base.models.Disk;
import ru.vsu.cs.Grushevskaya.base.models.DiskCategoryEntity;
import ru.vsu.cs.Grushevskaya.base.models.DiskType;
import ru.vsu.cs.Grushevskaya.base.services.*;

import java.util.List;
import java.util.Random;

public class Generator {
    //protected final Database db;
    protected final DiskServiceImpl diskService;
    protected final DiskTypeServiceImpl diskTypeService;
    protected final CategoryServiceImpl categoryService;
    protected final DCEServiceImpl dceService;
    protected final Random random;

    public Generator(DiskServiceImpl diskService, DiskTypeServiceImpl diskTypeService, CategoryServiceImpl categoryService, DCEServiceImpl dceService){
        this.diskService = diskService;
        this.categoryService = categoryService;
        this.diskTypeService = diskTypeService;
        this.dceService = dceService;
        random = new Random();
    }

    public void generate() {
        // categories 4
        String[] categories = {"games", "music", "films", "personal archive"};
        for (String str : categories) {
            categoryService.createCategory(new Category(str));
        }
        // diskType 3
        String[] diskTypes = {"CD-R", "DVD", "mini-disc"};
        for (String str : diskTypes) {
            diskTypeService.createDiskType(new DiskType(str));
        }
        // disks 240
        String[] names = {"Форсаж", "Семейные записи", "Illusion of skam", "Some game", "Анапа отдых лето", "Titanic", "Some film", "Выпускной 1999", "dirty Dance", "Кухня", "Winx", "Чародейки"};
        for (String str : names) {
            for (int i = 0; i < 20; i++) {
                String name = str + " " + i;
                int diskType = random.nextInt(diskTypes.length);
                int yearOfRelease = random.nextInt(50) + 1973;
                String description = "Some description for film " + name;
                diskService.createDisk(new Disk(name, diskType, yearOfRelease, description));
            }
        }
        // disk_categories_entity 240-720
        List<Category> categories_new = categoryService.getAllCategories();
        for (Disk disks : diskService.getAllDisks()){
            for (int i = 0; i < random.nextInt(3) + 1; i++) {
                dceService.createDiskCategoryEntity(new DiskCategoryEntity(disks.getID(), random.nextInt(categories_new.size())));
            }
        }
    }
}
