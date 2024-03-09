package org.example.exception;

/**
 * Исключение, которое отвечает на соответствие значения требованиям
 */
public class InvalidValue extends Exception{
    public  InvalidValue(String message){
        super(message);
    }
}
