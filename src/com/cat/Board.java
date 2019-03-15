package com.cat;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private GraphicsContext graphicsContext;
    private List<Shape> shapes = new ArrayList<>();

    public Board(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        shapes.add(new Square(34, 21, graphicsContext, this));
        shapes.add(new Ball(245, 675, graphicsContext, this));
        shapes.add(new Cat(555, 667, graphicsContext, this));
    }

    public void move() {
        for (Shape shape : shapes) {
            shape.move();
        }
    }

    public void draw() {
        clean();
        for (Shape shape : shapes) {
            shape.draw();
        }
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    private void clean() {
        graphicsContext.clearRect(0, 0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());
    }
}
