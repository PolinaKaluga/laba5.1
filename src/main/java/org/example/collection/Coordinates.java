package org.example.collection;

import org.example.exception.InvalidValue;


/**
 * Класс - координаты объекта класса Ticket.
 */
public class Coordinates {

    private Float x;
    private float y;

    public Float getX() {
        return x;
    }

    public Coordinates(Float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public void setX(Float x) throws InvalidValue {
        if(x == null){throw new InvalidValue("x не может быть пустым");
        }
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }
}

