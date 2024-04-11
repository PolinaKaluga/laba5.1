package org.example.commands;

import org.example.collection.CollectionManager;
import org.example.collection.Ticket;
import org.example.exception.InvalidValue;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Scanner;


/**
 * Класс для вывода элемента коллекции, которая начинается с заданной подстроки.
 */

public class FilterStartsWithName implements Command {
    private CollectionManager collectionManager;

    public FilterStartsWithName(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute() throws InvalidValue {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите подстроку, начиная с которой должно начинаться название билета: ");
        String nameSubstring = scanner.nextLine();

        ArrayDeque<Ticket> filteredTickets = collectionManager.filterStartsWithName(nameSubstring);

        if (filteredTickets.isEmpty()) {
            System.out.println("Нет билетов, названия которых начинаются с заданной подстроки.");
            return false;
        } else {
            System.out.println("Найденные билеты:");
            for (Ticket ticket : filteredTickets) {
                System.out.println(ticket);
            }
            return true;
        }
    }

    @Override
    public String getDescription() {
        return "команда выводит элементы, значение поля name которых начинается с заданной подстроки";
    }
}
