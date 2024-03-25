package org.example.commands;

import org.example.collection.CollectionManager;
import org.example.collection.Ticket;


/**
 * Класс команды, которая обновляет значение элемента коллекции, id которого равен заданному.
 */
public class UpdateIdCommand implements Command {
    private CollectionManager collectionManager;
    private int id;
    private Ticket updatedTicket;

    public UpdateIdCommand(CollectionManager collectionManager, int id, Ticket updatedTicket){
        this.collectionManager = collectionManager;
        this.id = id;
        this.updatedTicket = updatedTicket;
    }

    @Override
    public void execute() {
//        collectionManager.updateById(id, updatedTicket);
    }

    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции по заданному id";
    }
}




