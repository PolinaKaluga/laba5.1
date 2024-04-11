package org.example.commands;

import org.example.collection.CollectionManager;
import org.example.collection.Ticket;
import org.example.exception.InvalidValue;

/**
 * Класс команды, которая удаляет первый элемент из коллекции.
 */

import java.util.*;

import java.util.Scanner;


/**
 * Класс команды, которая удаляет первый элемент.
 */

public class RemoveFirstCommand implements Command {
    private CollectionManager collectionManager;

    public RemoveFirstCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute() {
        ArrayDeque<Ticket> collection = collectionManager.getCollection();
        if (collection.isEmpty()) {
            System.out.println("Коллекция пуста");
            return false;
        }
        Ticket firstElement = collection.pollFirst();
        collectionManager.saveCollection();
        System.out.println("Первый элемент удален из коллекции");
        return true;
    }


    @Override
    public String getDescription() {
        return "команда удаляет первый элемент из коллекции";
    }

}
