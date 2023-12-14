package ru.vsu.cs.Grushevskaya.console;

import ru.vsu.cs.Grushevskaya.base.models.Category;
import ru.vsu.cs.Grushevskaya.base.models.Disk;
import ru.vsu.cs.Grushevskaya.base.models.DiskCategoryEntity;
import ru.vsu.cs.Grushevskaya.base.models.DiskType;
import ru.vsu.cs.Grushevskaya.base.services.CategoryServiceImpl;
import ru.vsu.cs.Grushevskaya.base.services.DCEServiceImpl;
import ru.vsu.cs.Grushevskaya.base.services.DiskServiceImpl;
import ru.vsu.cs.Grushevskaya.base.services.DiskTypeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class PrintBase extends ConsoleScanner {
    public PrintBase(Scanner scanner) {
        super(scanner);
    }

    private static PrintBase example;

    public static PrintBase getExample(Scanner scanner) {
        if (example == null) {
            example = new PrintBase(scanner);
        }
        return example;
    }

    @Override
    public void process() {

        System.out.println("Disks collection:");
        for (Disk disk : disks.getAllDisks()) {
            System.out.println("id: " + disk.getID() + " content: " + disk.getName() + " year of release: " + disk.getYearOfRelease() +
                    "\ndescription: " + disk.getDescription() + " content categories: " + getCategoriesByDiskId(disk.getID(), dce, categories) +
                    "\ndisk type: " + getTypeByDiskId(disk, diskTypes) + "\n");
        }

        System.out.println("Types of disks:");
        for (DiskType type : diskTypes.getAllDiskTypes()) {
            System.out.println("id: " + type.getID() + " name: " + type.getName() + "\n");
        }

        System.out.println("Categories of content:");
        for (Category category : categories.getAllCategories()) {
            System.out.println("id: " + category.getID() + " name: " + category.getName() + "\n");
        }

    }

    public List<String> getCategoriesByDiskId(int diskId, DCEServiceImpl dceService, CategoryServiceImpl categoryService) {
        ArrayList<DiskCategoryEntity> temp = new ArrayList<>();
        ArrayList<String> categories = new ArrayList<>();
        for (DiskCategoryEntity dce : dceService.getAllDiskCategoryEntities()) {
            if (dce.getDiskId() == diskId) {
                temp.add(dce);
            }
        }
        for (Category category : categoryService.getAllCategories()) {
            for (DiskCategoryEntity dce : temp) {
                if (category.getID() == dce.getCategoryId()) {
                    categories.add(category.getName());
                }
            }
        }
        return categories;
    }

    public String getTypeByDiskId(Disk disk, DiskTypeServiceImpl diskTypeService) {
        for (DiskType type : diskTypeService.getAllDiskTypes()) {
            if (type.getID() == disk.getDiskTypeId()) {
                return type.getName();
            }
        }
        return null;
    }

    public Disk getDiskByName(String name, DiskServiceImpl diskService) {
        for (Disk disk : diskService.getAllDisks()) {
            if (Objects.equals(disk.getName(), name)) {
                return disk;
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return "Print base";
    }
}
