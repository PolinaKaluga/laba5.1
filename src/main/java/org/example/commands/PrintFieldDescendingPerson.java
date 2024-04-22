package org.example.commands;

import org.example.collection.CollectionManager;
import org.example.collection.Ticket;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Класс для вывода значения поля person всех элементов в порядке убывания.
 */

public class PrintFieldDescendingPerson implements Command {
    private CollectionManager collectionManager;

    public PrintFieldDescendingPerson(CollectionManager collectionManager) {
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
        ticketList.sort(Comparator.comparing(ticket -> ticket.getPerson().getHeight(), Comparator.reverseOrder()));
        tickets.clear();
        tickets.addAll(ticketList);



        System.out.println("Список билетов в порядке убывания роста персоны:");
        for (Ticket ticket : tickets) {
            System.out.println("Ticket ID: " + ticket.getId() + " Person height:" + ticket.getPerson().getHeight());
        }

        return true;
    }

    @Override
    public String getDescription() {
        return "команда выводит значения поля person всех элементов в порядке убывания";
    }
}
