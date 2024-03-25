package org.example.commands;

import org.example.collection.CollectionManager;




/**
 * Класс команды, которая выводит первый элемент коллекции.
 */
public class HeadCommand implements Command {
    private CollectionManager collectionManager;

    public HeadCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.head();
    }

    @Override
    public String getDescription() {
        return "вывести первый элемент коллекции";
    }
}

