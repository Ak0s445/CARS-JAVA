package com.example;

public class Car {
    Integer id;
    String plate;
    Integer year;
    String brand;
    Integer price;
    
    public Car() {
    }

    public Car(String plate, Integer year, String brand, Integer price) {
        this.plate = plate;
        this.year = year;
        this.brand = brand;
        this.price = price;
    }

    public Car(Integer id, String plate, Integer year, String brand, Integer price) {
        this.id = id;
        this.plate = plate;
        this.year = year;
        this.brand = brand;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

        

}
