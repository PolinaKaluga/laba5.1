package org.example.commands;

public interface CommandWithArguments extends Command{
    /**
     * Метод, получающий аргументы команды.
     *
     * @param commandArguments Аргументы команды.
     */
    void getCommandArguments(String[] commandArguments);
}
