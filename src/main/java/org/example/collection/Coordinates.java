package org.example.collection;

import org.example.expection.InvalidValue;

public class Coordinates {

    private Float x;
    private float y;

    public Float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(Float x) throws InvalidValue {
        if(x == null){throw new InvalidValue("x не может быть пустым");
        }
        //проверка на неравенство 0
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }
}
