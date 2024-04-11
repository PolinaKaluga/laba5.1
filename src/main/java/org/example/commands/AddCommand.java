    package org.example.commands;

    import org.example.collection.*;

    import java.util.NoSuchElementException;
    import java.util.Scanner;
    import org.example.exception.InvalidValue;

    /**
     * Класс для добавления нового элемента в коллекцию.
     */

    public class AddCommand implements Command {
        private CollectionManager collectionManager;

        public AddCommand(CollectionManager collectionManager) {
            this.collectionManager = collectionManager;
        }

        @Override
        public boolean execute() throws InvalidValue {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите название: ");
            String name = scanner.nextLine();

            Coordinates coordinates = null;
            while (true) {
                try {
                    System.out.print("Введите координату x: ");
                    float x = Float.parseFloat(scanner.next());
                    System.out.print("Введите координату y: ");
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
                    System.out.print("Введите цену: ");
                    price = scanner.nextInt();
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
                    System.out.print("Введите тип билета(VIP, USUAL, BUDGETARY, CHEAP): ");
                    type = TicketType.valueOf(scanner.next());
                    break;
                } catch (IllegalArgumentException e) {
                    System.err.println("Неправильное написание типа билета.");
                }
            }

            Person person = null;
            while (true) {
                try {
                    System.out.print("Введите рост: ");
                    Long height = scanner.nextLong();

                    System.out.print("Введите вес: ");
                    Float weight = scanner.nextFloat();

                    System.out.print("Введите цвет глаз(GREEN, BLACK, WHITE): ");
                    EyeColor eyeColor = EyeColor.valueOf(scanner.next());

                    System.out.print("Введите цвет волос(RED, BLACK, YELLOW, ORANGE, WHITE): ");
                    HairColor hairColor = HairColor.valueOf(scanner.next());

                    person = new Person(height, weight, eyeColor, hairColor);
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Координаты должны состоять из нескольких значений!");
                } catch (NoSuchElementException e) {
                    System.err.println("");
                } catch (IllegalArgumentException e) {
                    System.err.println("Неправильное написание цвета глаз или цвета волос.");
                }
            }

            Ticket newTicket = new Ticket(name, coordinates, price, type, person);
            if (collectionManager.isTicketValid(newTicket)) {
                collectionManager.add(newTicket);
            } else {
                System.out.println("Ошибка: Невозможно добавить элемент в коллекцию. Проверьте условия для билета.");
            }
            return false;
        }

        @Override
        public String getDescription(){
            return "команда добавляет новый элемент в коллекцию";
        }
    }

