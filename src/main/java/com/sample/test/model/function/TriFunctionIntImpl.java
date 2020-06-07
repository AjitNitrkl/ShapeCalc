package com.sample.test.model.function;

import com.sample.test.model.Circle;
import com.sample.test.model.Rectangle;
import com.sample.test.model.Shape;

public class TriFunctionIntImpl implements TriFunction<Integer,Double,Double, Shape>  {

    Shape shape;

    @Override
    public Shape apply(Integer id, Double d1, Double d2) {
        if(shape instanceof Circle)
            return new Circle(id,d1);
        else
            return new Rectangle(id,d1,d2);
    }
}
