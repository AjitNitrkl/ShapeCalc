package com.sample.test.model;

public class Circle implements Shape, Comparable<Circle> {

    int id;
    double radius;
    double area;

    public Circle(){
    }
    public Circle(double radius){
        this.radius = radius;
    }
    public Circle(int id, double radius){
        this.id = id;
        this.radius = radius;
    }
    /*Below 2 constructor are required for TriFunction impl */
    public Circle(Double radius,Double radius1, Double area){
        this.radius = radius;
        this.area = area;
    }
    public Circle(Integer id, Double radius,Double radius1){
        this(id,radius);
    }

    @Override
    public double getArea() {
        return (22*this.radius*this.radius)/7;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }


    @Override
    public String toString() {
        return "Circle [" +
                "radius=" + radius +
                ']';
    }

    @Override
    public int compareTo(Circle circle) {
        return circle.getArea() > getArea() ? 1:circle.getArea()<circle.getArea()?-1:0;
    }
}
