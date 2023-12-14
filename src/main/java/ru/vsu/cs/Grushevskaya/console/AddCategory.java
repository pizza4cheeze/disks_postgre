package ru.vsu.cs.Grushevskaya.console;

import ru.vsu.cs.Grushevskaya.base.models.Category;

import java.util.Scanner;

public class AddCategory extends ConsoleScanner {
    public AddCategory(Scanner scanner) {
        super(scanner);
    }

    private static AddCategory example;

    public static AddCategory getExample(Scanner scanner) {
        if (example == null) {
            example = new AddCategory(scanner);
        }
        return example;
    }

    @Override
    public void process() {
        scanner.nextLine();
        System.out.println("Input name of category");
        categories.createCategory(new Category(scanner.nextLine()));
        System.out.println("success");
    }

    @Override
    public String getName() {
        return "Add new category";
    }
}
