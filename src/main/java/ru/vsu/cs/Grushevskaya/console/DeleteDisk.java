package ru.vsu.cs.Grushevskaya.console;

import ru.vsu.cs.Grushevskaya.base.models.Disk;
import ru.vsu.cs.Grushevskaya.base.services.DiskServiceImpl;

import java.util.List;
import java.util.Scanner;

public class DeleteDisk extends ConsoleScanner {
    public DeleteDisk(Scanner scanner) {
        super(scanner);
    }

    private static DeleteDisk example;

    public static DeleteDisk getExample(Scanner scanner) {
        if (example == null) {
            example = new DeleteDisk(scanner);
        }
        return example;
    }

    @Override
    public void process() {
        scanner.nextLine();
        System.out.println("Input ID of disk");
        int typeToDelete = scanner.nextInt();

        disks.deleteDisk(typeToDelete);

        System.out.println("success");
    }

    @Override
    public String getName() {
        return "Delete disk";
    }
}
