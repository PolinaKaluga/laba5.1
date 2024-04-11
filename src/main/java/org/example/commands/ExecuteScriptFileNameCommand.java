package org.example.commands;


/**
 * Класс команды, которая считает и исполняет скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме
 */

import org.example.collection.CollectionManager;
import org.example.exception.InvalidValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InvalidPropertiesFormatException;
import java.util.Scanner;


public class ExecuteScriptFileNameCommand implements Command {
    private final CollectionManager collectionManager;
    private String fileName;

    public ExecuteScriptFileNameCommand(CollectionManager collectionManager, String fileName) {
        this.collectionManager = collectionManager;
        this.fileName = fileName;
    }

    @Override
    public boolean execute() throws InvalidValue {
        File file = new File(fileName);
        boolean result = true;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    String[] parts = line.split(" ");
                    if (parts.length > 0) {
                        String commandName = parts[0];
                        String[] commandArgs = new String[parts.length - 1];
                        System.arraycopy(parts, 1, commandArgs, 0, commandArgs.length);
                        collectionManager.execute_script(commandName, commandArgs);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public String getDescription() {
        return "команда исполняет скрипт из указанного файла";
    }
}

