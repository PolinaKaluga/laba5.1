package org.example.commands;

import org.example.collection.CollectionManager;
import org.example.collection.Ticket;


/**
 * Класс команды, которая выводит первый элемент коллекции.
 */
public class HeadCommand implements Command {
    private CollectionManager collectionManager;

    public HeadCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute() {
        if (collectionManager.getCollection().isEmpty()) {
            System.out.println("Коллекция пуста.");
            return false;
        }

        Ticket firstTicket = collectionManager.getCollection().peekFirst();
        System.out.println("Первый элемент коллекции: " + firstTicket);

        return true;
    }

    @Override
    public String getDescription() {
        return "команда выводит первый элемент коллекции";
    }
}

