package org.example.commands;


/**
 * Uнтерфейс, реализация которого приведена в командах.
 */
public interface Command {
    /**
     * Метод, исполняющий команду.
     */
    void execute();

    /**
     * Метод, возвращающий описание команды.
     * @return Возвращает описание команды.
     */
    default String getDescription() {
        return "Описание работы данной команды еще не реализовано";
    }
}