package com.sample.test;

import com.sample.test.model.Circle;
import com.sample.test.model.Rectangle;
import com.sample.test.model.Shape;
import com.sample.test.util.ShapeHelper;

import java.util.List;

public class MainApp {

    public static void main(String[] args) throws Exception {
            if(!(args.length == 1 && args[0].equals("testdata.csv")))
                throw new IllegalStateException("Application need an argument only 1 as " +
                        " testdata.csv as the arg");

            List<Shape> shapes = ShapeHelper.supplierFun.get();
            findShapeIndex(null, shapes);
            printShapes(shapes);
            sortByArea(shapes);
    }

    private static void findShapeIndex(Shape instance, List<Shape> shapes){
        System.out.println("Reading Shapes.....");
        try {
            shapes.stream().forEach(line -> {
                System.out.println();
                if (line instanceof Circle)
                    System.out.println("Read Circle with id " + ((Circle) line).getId());
                else
                    System.out.println("Read Rectangle with id " + ((Rectangle) line).getId());
            });
        }catch(Exception e){
            e.printStackTrace();
            throw new IllegalStateException("Error Occured in finding index of shape");

        }
    }

    private static void sortByArea(List<Shape> shapes){
        System.out.println();
        try {
            List<Shape> sortedShapes = ShapeHelper.calcAreaAndSortFun.apply(shapes);
            System.out.println("Printing all shapes sorted by area:");
            System.out.println();
            ShapeHelper.consumerFun.accept(sortedShapes);
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalStateException("Error Occured in sorting shapes with area");
        }
    }

    private static void printShapes(List<Shape> shapes){
        System.out.println();
        System.out.println("Printing all shapes (unsorted):");
        System.out.println();
        ShapeHelper.consumerFun.accept(shapes);
        System.out.println();
    }
}
