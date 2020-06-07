package com.sample.test.model.function;

import com.sample.test.model.Circle;
import com.sample.test.model.Rectangle;
import com.sample.test.model.Shape;

public class TriFunctionDoubImpl implements TriFunction<Double,Double,Double, Shape>  {

    Shape shape;

    @Override
    public Shape apply(Double d1, Double d2, Double area) {
        if(shape instanceof Circle)
            return new Circle(d1,d2,area);
        else
            return new Rectangle(d1,d2,area);
    }
}
