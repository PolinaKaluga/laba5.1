package org.example.commands;


import org.example.exception.InvalidValue;

/**
 * Uнтерфейс, реализация которого приведена в командах.
 */
public interface Command {
    /**
     * Метод, исполняющий команду.
     *
     * @return
     */
    boolean execute() throws InvalidValue;

    /**
     * Метод, возвращающий описание команды.
     * @return Возвращает описание команды.
     */
    default String getDescription() {

        return "Описание работы данной команды еще не реализовано";
    }

}