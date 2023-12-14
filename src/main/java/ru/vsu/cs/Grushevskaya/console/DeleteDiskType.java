package ru.vsu.cs.Grushevskaya.console;

import ru.vsu.cs.Grushevskaya.base.models.Category;
import ru.vsu.cs.Grushevskaya.base.models.Disk;
import ru.vsu.cs.Grushevskaya.base.models.DiskCategoryEntity;

import java.util.Scanner;

public class DeleteDiskType extends ConsoleScanner {
    public DeleteDiskType(Scanner scanner) {
        super(scanner);
    }

    private static DeleteDiskType example;

    public static DeleteDiskType getExample(Scanner scanner) {
        if (example == null) {
            example = new DeleteDiskType(scanner);
        }
        return example;
    }

    @Override
    public void process() {
        scanner.nextLine();
        System.out.println("Input ID of type");
        int typeToDelete = scanner.nextInt();
        for (Disk disk : disks.getAllDisks()) {
            if (disk.getDiskTypeId() == typeToDelete) {
                disks.deleteDisk(disk.getID());
            }
        }
        diskTypes.deleteDiskType(typeToDelete);
        System.out.println("success");
    }

    @Override
    public String getName() {
        return "Delete type of disk";
    }
}
