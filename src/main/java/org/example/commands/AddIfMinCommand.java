package org.example.commands;

import org.example.collection.CollectionManager;
import org.example.collection.Ticket;

/**
 * Класс команды, который добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
 */

public class AddIfMinCommand implements Command {
    private CollectionManager collectionManager;
    private Ticket newTicket;

    public AddIfMinCommand(CollectionManager collectionManager, Ticket newTicket){
        this.collectionManager = collectionManager;
        this.newTicket = newTicket;
    }

    @Override
    public void execute() {
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }
}
