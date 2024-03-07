package org.example.collection;

import org.example.expection.InvalidValue;

public class Person {
    private Long height; //Поле может быть null, Значение поля должно быть больше 0

    private Float weight; //Поле может быть null, Значение поля должно быть больше 0
    private EyeColor eyeColor; //Поле не может быть null
    private HairColor hairColor; //Поле не может быть null


    public Person(Long height,Float weight, EyeColor eyeColor,HairColor hairColor ){
        this.height = height;
        this.weight = weight;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
    }


    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) throws InvalidValue {
        if(weight == null){throw new InvalidValue("Вес не может быть пустым");}
        if(weight <= 0){throw new InvalidValue("Вес должен быть больше нуля");}
        this.weight = weight;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) throws InvalidValue{
        if(height == null){throw new InvalidValue("Рост не может быть пустым");}
        if(height <= 0){throw new InvalidValue("Рост должен быть больше нуля");}
        this.height = height;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(EyeColor eyeColor) throws InvalidValue {
        if(eyeColor == null){throw new InvalidValue("Цвет глаз не может быть пустым");}

        this.eyeColor = eyeColor;
    }


    public HairColor  getHairColor() {
        return hairColor;
    }

    public void setHairColor(HairColor hairColor)throws InvalidValue  {
        if(hairColor == null){throw new InvalidValue("Цвет волос не может быть пустым");}
        this.hairColor = hairColor;
    }
}
