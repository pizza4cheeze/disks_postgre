package ru.vsu.cs.Grushevskaya.console;

import ru.vsu.cs.Grushevskaya.base.models.Category;
import ru.vsu.cs.Grushevskaya.base.models.DiskCategoryEntity;
import ru.vsu.cs.Grushevskaya.base.services.DCEServiceImpl;

import java.util.Scanner;

public class DeleteCategory extends ConsoleScanner {
    public DeleteCategory(Scanner scanner) {
        super(scanner);
    }

    private static DeleteCategory example;

    public static DeleteCategory getExample(Scanner scanner) {
        if (example == null) {
            example = new DeleteCategory(scanner);
        }
        return example;
    }

    @Override
    public void process() {
        scanner.nextLine();
        System.out.println("Input ID of category");
        int categoryToDelete = scanner.nextInt();
        for (DiskCategoryEntity dces : dce.getAllDiskCategoryEntities()) {
            if (dces.getCategoryId() == categoryToDelete) {
                dce.deleteDiskCategoryEntity(dces.getID());
            }
        }
        categories.deleteCategory(categoryToDelete);
        System.out.println("success");
    }

    @Override
    public String getName() {
        return "Delete category";
    }
}
