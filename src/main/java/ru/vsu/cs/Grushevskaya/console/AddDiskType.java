package ru.vsu.cs.Grushevskaya.console;

import ru.vsu.cs.Grushevskaya.base.models.DiskType;

import java.util.Scanner;

public class AddDiskType extends ConsoleScanner {

    public AddDiskType(Scanner scanner) {
        super(scanner);
    }

    private static AddDiskType example;

    public static AddDiskType getExample(Scanner scanner) {
        if (example == null) {
            example = new AddDiskType(scanner);
        }
        return example;
    }

    @Override
    public void process() {
        scanner.nextLine();
        System.out.println("Input new type");
        diskTypes.createDiskType(new DiskType(scanner.nextLine()));
        System.out.println("success");

    }

    @Override
    public String getName() {
        return "Add new disk type";
    }
}
