package org.example.expection;

/**
 * Исключение, которое отвечает на соответсвие значения требованиям
 */
public class InvalidValue extends Exception{
    public  InvalidValue(String message){
        super(message);
    }
}
