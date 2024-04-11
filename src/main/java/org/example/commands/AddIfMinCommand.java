package org.example.commands;

import org.example.collection.CollectionManager;
import org.example.collection.Coordinates;
import org.example.collection.Person;
import org.example.collection.Ticket;
import org.example.exception.InvalidValue;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Класс команды, который добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
 */

public class AddIfMinCommand implements Command {
    private final CollectionManager collectionManager;
    private Ticket ticketToAdd;

    public AddIfMinCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setTicketToAdd(Ticket ticketToAdd) {
        this.ticketToAdd = ticketToAdd;
    }

    @Override
    public boolean execute() throws InvalidValue {
        if (ticketToAdd == null) {
            throw new InvalidValue("No ticket to add provided.");
        }

        Ticket minTicket = collectionManager.getMinPriceTicket();

        if (minTicket == null || ticketToAdd.getPrice() < minTicket.getPrice()) {
            collectionManager.add(ticketToAdd);
            return true;
        } else {
            System.out.println("The price of the new ticket is not less than the price of the minimum price ticket in the collection.");
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "команда добавления нового элемента в коллекцию, если его значение цены меньше, чем у наименьшего элемента этой коллекции";
    }
}




