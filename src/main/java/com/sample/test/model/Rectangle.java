package com.sample.test.model;

public class Rectangle implements Shape,Comparable<Rectangle>{

    int id;
    double height;
    double width;
    double area;

    public Rectangle(){
    }
    public Rectangle(int id, double height, double width){
        this.id = id;
        this.height = height;
        this.width = width;
    }
    public Rectangle(double height,double width){
        this.height = height;
        this.width = width;
    }
    public Rectangle(double height, double width,double area){
        this.height = height;
        this.width = width;
        this.area = area;

    }

    @Override
    public double getArea() {
        return this.height * this.width;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return width;
    }

    public void setWeight(double weight) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Rectangle [" +
                "width=" + (int)width +
                ", height=" + (int)height +
                ']';
    }

    @Override
    public int compareTo(Rectangle rectangle) {
        return rectangle.getArea() > getArea() ? 1:rectangle.getArea()<rectangle.getArea()?-1:0;
    }
}
