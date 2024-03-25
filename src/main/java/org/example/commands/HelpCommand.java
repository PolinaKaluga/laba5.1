package org.example.commands;

import org.example.collection.CollectionManager;
/**
 * Класс команды, которая выводит справку по доступным командам
 */
public class HelpCommand implements Command {
    private CollectionManager collectionManager;

    public HelpCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.help();
    }

    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }
}
