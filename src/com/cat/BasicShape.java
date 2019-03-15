package com.cat;

import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public abstract class BasicShape implements Shape {
    private static int DIAMETER = 30;
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    private GraphicsContext graphicsContext;
    private Board board;

    public BasicShape(int x, int y, GraphicsContext graphicsContext, Board board) {
        this.x = x;
        this.y = y;
        this.graphicsContext = graphicsContext;
        this.board = board;
        Random r = new Random();
        xSpeed = 2 + r.nextInt(5);
        ySpeed = 2 + r.nextInt(5);
    }

    public static int getDIAMETER() {
        return DIAMETER;
    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    @Override
    public void move() {
        x += xSpeed;
        y += ySpeed;
        if (handleСollision()) {
            return;
        }
        if (x + DIAMETER > graphicsContext.getCanvas().getWidth()) {
            setDirectionLeft();
        }
        if (y + DIAMETER > graphicsContext.getCanvas().getHeight()) {
            setDirectionTop();
        }
        if (x < 0) {
            setDirectionRight();
        }
        if (y < 0) {
            setDirectionBottom();
        }
    }

    @Override
    public double getDistance(double x, double y) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    private boolean handleСollision() {
        for (Shape shape : board.getShapes()) {
            if (shape == this) {
                continue;
            }
            if (shape.getDistance(x, y) < DIAMETER) {
                if (x < shape.getX()) {
                    setDirectionLeft();
                } else {
                    setDirectionRight();
                }
                if (y < shape.getY()) {
                    setDirectionTop();
                } else {
                    setDirectionBottom();
                }
                return true;
            }
        }
        return false;
    }

    private void setDirectionLeft() {
        xSpeed = -Math.abs(xSpeed);
    }

    private void setDirectionRight() {
        xSpeed = Math.abs(xSpeed);
    }

    private void setDirectionTop() {
        ySpeed = -Math.abs(ySpeed);
    }

    private void setDirectionBottom() {
        ySpeed = Math.abs(ySpeed);
    }
}
