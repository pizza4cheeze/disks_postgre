package ru.vsu.cs.Grushevskaya.console;

import ru.vsu.cs.Grushevskaya.base.models.Category;

import java.util.Scanner;

public class UpdateCategory extends ConsoleScanner {
    public UpdateCategory(Scanner scanner) {
        super(scanner);
    }

    private static UpdateCategory example;

    public static UpdateCategory getExample(Scanner scanner) {
        if (example == null) {
            example = new UpdateCategory(scanner);
        }
        return example;
    }

    @Override
    public void process() {
        scanner.nextLine();
        System.out.println("Input id of updating category");
        int idTemp = scanner.nextInt();
        System.out.println("Input new name of updating category");
        scanner.nextLine();
        String newName = scanner.nextLine();
        categories.updateCategory(idTemp, new Category(newName));
        System.out.println("success");
    }

    @Override
    public String getName() {
        return "Update category";
    }
}
