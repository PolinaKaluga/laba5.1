package org.example.commands;

import org.example.collection.CollectionManager;
import org.example.collection.Ticket;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Класс для вывода значения поля price всех элементов в порядке убывания.
 */

public class PrintFieldDescendingPrice implements Command {
    private CollectionManager collectionManager;

    public PrintFieldDescendingPrice(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute() {
        ArrayDeque<Ticket> tickets = collectionManager.getCollection();

        if (tickets.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return false;
        }

        List<Ticket> ticketList = new ArrayList<>(tickets);
        ticketList.sort(Comparator.comparingInt(Ticket::getPrice).reversed());
        tickets.clear();
        tickets.addAll(ticketList);

        System.out.println("Список билетов в порядке убывания цены:");
        for (Ticket ticket : tickets) {
            System.out.println("Ticket ID: " + ticket.getId() + " Ticket price: " + ticket.getPrice());
        }

        return true;
    }

    @Override
    public String getDescription() {
        return "команда выводит значения поля price всех элементов в порядке убывания";
    }
}
