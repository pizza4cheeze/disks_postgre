package ru.vsu.cs.Grushevskaya.console;

import ru.vsu.cs.Grushevskaya.base.models.Category;
import ru.vsu.cs.Grushevskaya.base.models.Disk;
import ru.vsu.cs.Grushevskaya.base.models.DiskCategoryEntity;
import ru.vsu.cs.Grushevskaya.base.models.DiskType;

import java.awt.*;
import java.util.Objects;
import java.util.Scanner;

public class UpdateDisk extends ConsoleScanner {
    public UpdateDisk(Scanner scanner) {
        super(scanner);
    }

    private static UpdateDisk example;

    public static UpdateDisk getExample(Scanner scanner) {
        if (example == null) {
            example = new UpdateDisk(scanner);
        }
        return example;
    }


    //todo обновление диска целиком
    @Override
    public void process() {
        scanner.nextLine();
        System.out.println("Input id of updating disk");
        int idTemp = scanner.nextInt();
        Disk diskTemp = disks.getDiskById(idTemp);
        System.out.println("Select the field to update\n 1) name\n " +
                "2) disk type\n 3) year of release \n 4) description\n 5) categories");
        int inputCase = scanner.nextInt();
        switch (inputCase) {
            case 1:
                System.out.println("input new name");
                scanner.nextLine();
                String newName = scanner.nextLine();
                disks.updateDisk(idTemp, new Disk(newName, diskTemp.getDiskTypeId(), diskTemp.getYearOfRelease(), diskTemp.getDescription()));
                System.out.println("success");
                break;
            case 2:
                System.out.println("input id of new type");
                System.out.println("Types of disks:");
                for (DiskType type : diskTypes.getAllDiskTypes()) {
                    System.out.println("id: " + type.getID() + " name: " + type.getName() + "\n");
                }
                int newType = scanner.nextInt();
                if (newType < 0 || newType > diskTypes.getAllDiskTypes().size()) {
                    System.out.println("Input real id of disk type");
                } else {
                    disks.updateDisk(idTemp, new Disk(diskTemp.getName(), newType, diskTemp.getYearOfRelease(), diskTemp.getDescription()));
                    System.out.println("success");
                }
                break;
            case 3:
                System.out.println("input new year of release");
                scanner.nextLine();
                int newYear = scanner.nextInt();
                disks.updateDisk(idTemp, new Disk(diskTemp.getName(), diskTemp.getDiskTypeId(), newYear, diskTemp.getDescription()));
                System.out.println("success");
                break;
            case 4:
                System.out.println("input new description");
                scanner.nextLine();
                String newDesc = scanner.nextLine();
                disks.updateDisk(idTemp, new Disk(diskTemp.getName(), diskTemp.getDiskTypeId(), diskTemp.getYearOfRelease(), newDesc));
                System.out.println("success");
                break;
            case 5:
                System.out.println("Input new amount of categories");
                int count = scanner.nextInt();
                deleteOldDCEs(diskTemp.getID());
                for (Category category : categories.getAllCategories()) {
                    System.out.println("id: " + category.getID() + " name: " + category.getName() + "\n");
                }
                for (int i = 0; i < count; i++) {
                    System.out.println("input " + (i + 1) + " category's ID");
                    int id = scanner.nextInt();
                    dce.createDiskCategoryEntity(new DiskCategoryEntity(diskTemp.getID(), id));
                }
                System.out.println("success");
                break;
            default:
                System.out.println("Incorrect number of field");
                break;
        }

    }

    private void deleteOldDCEs (int idOfDisk) {
        for (DiskCategoryEntity dceTemp : dce.getAllDiskCategoryEntities()) {
            if (idOfDisk == dceTemp.getDiskId()) {
                dce.deleteDiskCategoryEntity(dceTemp.getID());
            }
        }
    }

    @Override
    public String getName() {
        return "update disk";
    }
}
