package org.example.commands;

import org.example.collection.*;
import org.example.exception.InvalidValue;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс команды, которая обноваляет элемент класса Ticket по его id.
 */
public class UpdateIdCommand implements Command {
    private CollectionManager collectionManager;

    public UpdateIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute() throws InvalidValue {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите id элемента, который нужно обновить: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Считываем оставшийся символ перевода строки

        Ticket updatedTicket = createUpdatedTicket(scanner);

        if (collectionManager.updateById(id, updatedTicket)) {
            System.out.println("Элемент с id " + id + " успешно обновлен.");
            return true;
        } else {
            System.out.println("Ошибка: Элемент с id " + id + " не найден в коллекции.");
            return false;
        }
    }

    private Ticket createUpdatedTicket(Scanner scanner) {

        System.out.print("Введите новое название: ");
        String name = scanner.nextLine();

        Coordinates coordinates = null;
        while (true) {
            try {
                System.out.print("Введите новую координату x: ");
                float x = Float.parseFloat(scanner.next());
                System.out.print("Введите новую координату y: ");
                float y = Float.parseFloat(scanner.next());
                coordinates = new Coordinates(x, y);
                break;
            } catch (NumberFormatException e) {
                System.err.println("Проблемы с написанием координаты");
            }
        }

        int price = 0;
        while (true) {
            try {
                System.out.print("Введите новую цену: ");
                price = scanner.nextInt();
                scanner.nextLine(); // Считываем оставшийся символ перевода строки
                break;
            } catch (NumberFormatException e) {
                System.err.println("Проблемы с написанием цены");
            } catch (NoSuchElementException e) {
                System.err.println(".");
            }
        }

        TicketType type = null;
        while (true) {
            try {
                System.out.print("Введите новый тип билета: ");
                type = TicketType.valueOf(scanner.next());
                break;
            } catch (IllegalArgumentException e) {
                System.err.println("Неправильное написание типа билета.");
            }
        }

        Person person = null;
        while (true) {
            try {
                System.out.print("Введите новый рост: ");
                Long height = scanner.nextLong();

                System.out.print("Введите новый вес: ");
                Float weight = scanner.nextFloat();

                System.out.print("Введите новый цвет глаз(GREEN, BLACK, WHITE): ");
                EyeColor eyeColor = EyeColor.valueOf(scanner.next());

                System.out.print("Введите новый цвет волос(RED, BLACK, YELLOW, ORANGE, WHITE): ");
                HairColor hairColor = HairColor.valueOf(scanner.next());

                person = new Person(height, weight, eyeColor, hairColor);
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Координаты должны состоять из нескольких значений!");
            } catch (NoSuchElementException e) {
                System.err.println(".");
            } catch (IllegalArgumentException e) {
                System.err.println("Неправильное написание цвета глаз или цвета волос.");
            }
        }

        return new Ticket(name, coordinates, price, type, person);
    }

    @Override
    public String getDescription(){
        return "команда обновляет эелемнт коллекции с заданным id";
    }
}


