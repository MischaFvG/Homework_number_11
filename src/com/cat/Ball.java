package com.cat;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends BasicShape {

    public Ball(int x, int y, GraphicsContext graphicsContext, Board board) {
        super(x, y, graphicsContext, board);
    }

    @Override
    public void draw() {
        getGraphicsContext().setFill(Color.RED);
        getGraphicsContext().setStroke(Color.BLACK);
        getGraphicsContext().setLineWidth(2);
        getGraphicsContext().fillOval(getX(), getY(), getDIAMETER(), getDIAMETER());
        getGraphicsContext().strokeOval(getX(), getY(), getDIAMETER(), getDIAMETER());
    }
}
