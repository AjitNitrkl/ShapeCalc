package com.sample.test.model.factory;

import com.sample.test.model.Circle;
import com.sample.test.model.Rectangle;
import com.sample.test.model.Shape;
import com.sample.test.model.ShapeType;
import com.sample.test.model.function.TriFunction;

public class ShapeFactory {

    private ShapeFactory(){

    }

    public static TriFunction<Integer,Double,Double,Shape> createShape(ShapeType shapeType){
        TriFunction<Integer,Double, Double,Shape> circle = Circle::new;
        TriFunction<Integer,Double, Double, Shape> rectangle = Rectangle::new;

        switch (shapeType){
            case CIRCLE:
                return circle;
            case RECTANGLE:
                return rectangle;
            default:
                throw new IllegalStateException("Entered shapeType is not available");
        }
    }

    public static TriFunction<Double,Double,Double, Shape> createNewShape(ShapeType shapeType){
        TriFunction<Double,Double, Double,Shape> circle = Circle::new;
        TriFunction<Double,Double, Double, Shape> rectangle = Rectangle::new;

        switch (shapeType){
            case CIRCLE:
                return circle;
            case RECTANGLE:
                return rectangle;
            default:
                throw new IllegalStateException("Entered shapeType is not available");
        }
    }

}
