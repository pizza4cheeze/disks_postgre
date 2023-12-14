package ru.vsu.cs.Grushevskaya.console;

import ru.vsu.cs.Grushevskaya.base.services.CategoryServiceImpl;
import ru.vsu.cs.Grushevskaya.base.services.DCEServiceImpl;
import ru.vsu.cs.Grushevskaya.base.services.DiskServiceImpl;
import ru.vsu.cs.Grushevskaya.base.services.DiskTypeServiceImpl;
import ru.vsu.cs.Grushevskaya.generator.Generator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Database database = DatabaseMemory.getExample();

        final DiskServiceImpl diskService = DiskServiceImpl.getExample();
        final DiskTypeServiceImpl diskTypeService = DiskTypeServiceImpl.getExample();
        final CategoryServiceImpl categoryService = CategoryServiceImpl.getExample();
        final DCEServiceImpl dceService = DCEServiceImpl.getExample();
        Generator generator = new Generator(diskService, diskTypeService, categoryService, dceService);
//        generator.generate();

        Scanner scanner = new Scanner(System.in);
        ConsoleScanner[] commands = new ConsoleScanner[]{
                AddDisk.getExample(scanner),
                AddDiskType.getExample(scanner),
                AddCategory.getExample(scanner),
                DeleteDisk.getExample(scanner),
                DeleteDiskType.getExample(scanner),
                DeleteCategory.getExample(scanner),
                UpdateDisk.getExample(scanner),
                UpdateDiskType.getExample(scanner),
                UpdateCategory.getExample(scanner),
                PrintBase.getExample(scanner),
        };

        commands[commands.length-1].process();

        while (true) {
            for (int i = 0; i < commands.length; i++) {
                ConsoleScanner c = commands[i];
                System.out.print(i);
                System.out.print(") ");
                System.out.println(c.getName());
            }
            System.out.println("Choose number:");
            int num = scanner.nextInt();
            if (num < 0) break;
            if (num >= commands.length) {
                System.out.println("illegal num");
                continue;
            }

            try {
                commands[num].process();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
        }
    }
}
