package ru.vsu.cs.Grushevskaya.console;

import ru.vsu.cs.Grushevskaya.base.services.CategoryServiceImpl;
import ru.vsu.cs.Grushevskaya.base.services.DCEServiceImpl;
import ru.vsu.cs.Grushevskaya.base.services.DiskServiceImpl;
import ru.vsu.cs.Grushevskaya.base.services.DiskTypeServiceImpl;

import java.util.Scanner;

public abstract class ConsoleScanner {
    public final Scanner scanner;

    protected final DiskServiceImpl disks = DiskServiceImpl.getExample();
    protected final CategoryServiceImpl categories = CategoryServiceImpl.getExample();
    protected final DiskTypeServiceImpl diskTypes = DiskTypeServiceImpl.getExample();
    protected final DCEServiceImpl dce = DCEServiceImpl.getExample();

    public ConsoleScanner(Scanner scanner){
        this.scanner = scanner;
    }

    public abstract void process();

    public abstract String getName();
}
