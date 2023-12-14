package ru.vsu.cs.Grushevskaya.console;

import ru.vsu.cs.Grushevskaya.base.models.DiskType;

import java.util.Scanner;

public class UpdateDiskType extends ConsoleScanner {
    public UpdateDiskType(Scanner scanner) {
        super(scanner);
    }

    private static UpdateDiskType example;

    public static UpdateDiskType getExample(Scanner scanner) {
        if (example == null) {
            example = new UpdateDiskType(scanner);
        }
        return example;
    }

    @Override
    public void process() {
        scanner.nextLine();
        System.out.println("Input Id of updating type");
        int idTemp = scanner.nextInt();
        System.out.println("Input new name of type");
        scanner.nextLine();
        String newName = scanner.nextLine();
        diskTypes.updateDiskType(idTemp, new DiskType(newName));
        System.out.println("success");
    }

    @Override
    public String getName() {
        return "update disk type";
    }
}
