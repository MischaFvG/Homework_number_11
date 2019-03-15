package com.cat;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends BasicShape {
    public Square(int x, int y, GraphicsContext graphicsContext, Board board) {
        super(x, y, graphicsContext, board);
    }

    @Override
    public void draw() {
        getGraphicsContext().setFill(Color.GREEN);
        getGraphicsContext().setStroke(Color.BLACK);
        getGraphicsContext().setLineWidth(2);
        getGraphicsContext().fillRect(getX(), getY(), getDIAMETER(), getDIAMETER());
        getGraphicsContext().strokeRect(getX(), getY(), getDIAMETER(), getDIAMETER());
    }
}
