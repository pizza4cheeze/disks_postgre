package ru.vsu.cs.Grushevskaya.console;

import ru.vsu.cs.Grushevskaya.base.models.Category;
import ru.vsu.cs.Grushevskaya.base.models.Disk;
import ru.vsu.cs.Grushevskaya.base.models.DiskCategoryEntity;
import ru.vsu.cs.Grushevskaya.base.models.DiskType;

import java.util.ArrayList;
import java.util.Scanner;

public class AddDisk extends  ConsoleScanner {
    public AddDisk(Scanner scanner) {
        super(scanner);
    }

    private static AddDisk example;

    public static AddDisk getExample(Scanner scanner) {
        if (example == null) {
            example = new AddDisk(scanner);
        }
        return example;
    }

    @Override
    public void process() {
        scanner.nextLine();
        System.out.println("input disk name");
        String name = scanner.nextLine();
        System.out.println("input disk type id");
        System.out.println("Types of disks:");
        for (DiskType type : diskTypes.getAllDiskTypes()) {
            System.out.println("id: " + type.getID() + " name: " + type.getName() + "\n");
        }
        int diskTypeId = scanner.nextInt();
        System.out.println("input year of release");
        int year = scanner.nextInt();
        System.out.println("input description");
        scanner.nextLine();
        String desc = scanner.nextLine();
        Disk result = new Disk(name, diskTypeId, year, desc);
        disks.createDisk(result);
        System.out.println("Input amount of disk's categories");
        int count = scanner.nextInt();
        for (Category category : categories.getAllCategories()) {
            System.out.println("id: " + category.getID() + " name: " + category.getName() + "\n");
        }
        for (int i = 0; i < count; i++) {
            System.out.println("input " + (i + 1) + " category's ID");
            int id = scanner.nextInt();
            dce.createDiskCategoryEntity(new DiskCategoryEntity(result.getID(), id));
        }
        System.out.println("success");
    }

    @Override
    public String getName() {
        return "Add new disk to collection";
    }
}
