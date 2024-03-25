package org.example.commands;


import org.example.collection.CollectionManager;

/**
 * Класс команды, которая выводит элементы, значение поля name которых начинается с заданной подстроки
 */
public class FilterStartsWithNameCommand implements Command {
    private CollectionManager collectionManager;
    private String filterName;

    public FilterStartsWithNameCommand(CollectionManager collectionManager, String filterName){
        this.collectionManager = collectionManager;
        this.filterName = filterName;
    }

    @Override
    public void execute() {
    }

    @Override
    public String getDescription() {
        return "вывести элементы, значение поля name которых начинается с заданной подстроки";
    }
}
