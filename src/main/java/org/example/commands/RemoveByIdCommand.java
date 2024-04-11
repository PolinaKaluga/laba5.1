package org.example.commands;


import org.example.collection.CollectionManager;

import java.util.Scanner;

/**
 * Класс команды, которая удаляет элемент из коллекции по его id.
 */
public class RemoveByIdCommand implements Command {
    private CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите id элемента, который нужно удалить: ");
        int id = scanner.nextInt();

        if (collectionManager.removeById(id)) {
            System.out.println("Элемент с id " + id + " успешно удален.");
            return true;
        } else {
            System.out.println("Ошибка: Элемент с id " + id + " не найден в коллекции.");
            return false;
        }
    }

    @Override
    public String getDescription() {

        return "команда удаляет элемент по заданному id";
    }
}
