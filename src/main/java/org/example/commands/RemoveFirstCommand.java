package org.example.commands;

import org.example.collection.CollectionManager;

/**
 * Класс команды, которая удаляет первый элемент из коллекции.
 */

public class RemoveFirstCommand implements Command {
    private CollectionManager collectionManager;

    public RemoveFirstCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.removeFirst();
    }

    @Override
    public String getDescription() {
        return "удалить первый элемент из коллекции";
    }
}
