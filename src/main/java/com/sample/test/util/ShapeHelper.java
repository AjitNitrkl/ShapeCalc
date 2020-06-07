package com.sample.test.util;

import com.sample.test.model.Circle;
import com.sample.test.model.Rectangle;
import com.sample.test.model.Shape;
import com.sample.test.model.ShapeType;
import com.sample.test.model.factory.ShapeFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShapeHelper {
    private static final String COMMA_DELIMITER = ",";
    private static Function<String, Optional<Shape>> mapToShapeFun= (line) ->{
        try {
            String[] values = line.split(COMMA_DELIMITER);
            if (values.length < 3 ||
                    Stream.of(ShapeType.values()).anyMatch(k -> k.equals(values[1]))){
                System.out.println("Skipped line "+line+" invalid entry");
                return Optional.empty();
            }

            Shape shape;
            if (ShapeType.CIRCLE.name().equals(values[1])) {
                shape = ShapeFactory.createShape(ShapeType.CIRCLE)
                        .apply(Integer.parseInt(values[0]), Double.valueOf(values[2]), null);
            } else
                shape = ShapeFactory.createShape(ShapeType.RECTANGLE)
                        .apply(Integer.parseInt(values[0]), Double.valueOf(values[3]), Double.valueOf(values[2]));
            return Optional.of(shape);
        }catch (Exception e){
            e.printStackTrace(); //should not use printstack trace rather logger(log4j/logback) should be used
            throw new IllegalStateException("Error occurred while creating instance from Factory method");
        }
    };

    public static Function<List<Shape>,List<Shape>> calcAreaAndSortFun = (shapes) ->{
        List<Shape> sortedShapes = new ArrayList<Shape>();
        shapes.stream().forEach(shape -> {
            if (shape instanceof Circle) {
                sortedShapes.add(ShapeFactory.createNewShape(ShapeType.CIRCLE)
                        .apply(((Circle) shape).getRadius(), null, shape.getArea()));
            } else {
                sortedShapes.add(ShapeFactory.createNewShape(ShapeType.RECTANGLE)
                        .apply(((Rectangle) shape).getHeight(), ((Rectangle) shape).getWeight(), shape.getArea()));
            }
        });
        Collections.sort(sortedShapes, (s1, s2) -> s1.getArea() > s2.getArea() ? 1 :
                s1.getArea() < s2.getArea() ? -1 : 0);
        return sortedShapes;
    };

    public static Consumer<List<Shape>> consumerFun = shapes ->{
        StringBuffer sb = new StringBuffer();
        shapes.stream().forEach(shape ->{
            sb.append(shape.toString()+ ", ");
        });
        sb.setLength(sb.length()-2);
        System.out.println(sb);
    };

    public static Supplier<List<Shape>> supplierFun = () ->{
        List<Shape> shapes = new ArrayList<Shape>();
        try (InputStream inputStream = ShapeHelper.class.getResourceAsStream("/data.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            shapes= getListofShapes(reader);
        }catch(IOException e){
            e.printStackTrace(); //should not use printstack trace rather logger(log4j/logback) should be used
            throw new IllegalStateException("File not present");
        }
        return shapes;
    };

    public static Function<String, List<Shape>> readFileFun = (file) ->{
        List<Shape> shapes = new ArrayList<Shape>();
        try (InputStream inputStream = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
             shapes = getListofShapes(reader);
        }catch(Exception e){
            e.printStackTrace(); //should not use printstack trace rather logger(log4j/logback) should be used
            throw new IllegalStateException("File not present");
        }
        return shapes;
    };

    private static List<Shape> getListofShapes(BufferedReader reader) {
        return reader.lines().skip(1)
                .map(mapToShapeFun)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

}
