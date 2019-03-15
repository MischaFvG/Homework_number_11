package com.cat;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Cat implements Shape {
    private static final int DIAMETER1 = 200;
    private static final int DIAMETER2 = 100;
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    private GraphicsContext graphicsContext;
    private Board board;

    public Cat(int x, int y, GraphicsContext graphicsContext, Board board) {
        this.x = x;
        this.y = y;
        this.graphicsContext = graphicsContext;
        this.board = board;
        Random r = new Random();
        xSpeed = 2 + r.nextInt(5);
        ySpeed = 2 + r.nextInt(5);
    }

    @Override
    public void move() {
        x += xSpeed;
        y += ySpeed;
        if (handleСollision()) {
            return;
        }
        if (x + DIAMETER1 > graphicsContext.getCanvas().getWidth()) {
            setDirectionLeft();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    murMeow();
                }
            });
            thread.start();
        }
        if (y + DIAMETER2 > graphicsContext.getCanvas().getHeight()) {
            setDirectionTop();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    murMeow();
                }
            });
            thread.start();
        }
        if (x < 0) {
            setDirectionRight();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    murMeow();
                }
            });
            thread.start();
        }
        if (y < 0) {
            setDirectionBottom();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    murMeow();
                }
            });
            thread.start();
        }
    }

    @Override
    public void draw() {
        graphicsContext.setFill(Color.GREEN);
        graphicsContext.setStroke(Color.BLUE);
        graphicsContext.setLineWidth(5);
        graphicsContext.fillOval(x, y, DIAMETER1, DIAMETER2);
        graphicsContext.strokeOval(x, y, DIAMETER1, DIAMETER2);
        graphicsContext.setFill(Color.RED);
        graphicsContext.setStroke(Color.GREEN);
        graphicsContext.setLineWidth(3);
        graphicsContext.fillOval(x + 30, y + 30, 30, 30);
        graphicsContext.fillOval(x + 140, y + 30, 30, 30);
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(3);
        graphicsContext.fillOval(x + 30 + 10, y + 30 + 10, 10, 10);
        graphicsContext.fillOval(x + 140 + 10, y + 30 + 10, 10, 10);
        graphicsContext.fillOval(x + 95, y + 48, 10, 10);
        graphicsContext.strokeLine(x + 100, y + 53, x + 20, y + 100);
        graphicsContext.strokeLine(x + 100, y + 53, x + 30, y + 100);
        graphicsContext.strokeLine(x + 100, y + 53, x + 40, y + 100);
        graphicsContext.strokeLine(x + 100, y + 53, x + 50, y + 100);
        graphicsContext.strokeLine(x + 100, y + 53, x + 60, y + 100);
        graphicsContext.strokeLine(x + 100, y + 53, x + 70, y + 100);
        graphicsContext.strokeLine(x + 100, y + 53, x + 40, y + 100);
        graphicsContext.strokeLine(x + 100, y + 53, x + 80, y + 100);
        graphicsContext.strokeLine(x + 100, y + 53, x + 90, y + 100);
        graphicsContext.strokeLine(x + 100, y + 53, x + 100, y + 100);
        graphicsContext.strokeLine(x + 100, y + 53, x + 110, y + 100);
        graphicsContext.strokeLine(x + 100, y + 53, x + 120, y + 100);
        graphicsContext.strokeLine(x + 100, y + 53, x + 130, y + 100);
        graphicsContext.strokeLine(x + 100, y + 53, x + 140, y + 100);
        graphicsContext.strokeLine(x + 100, y + 53, x + 150, y + 100);
        graphicsContext.strokeLine(x + 100, y + 53, x + 160, y + 100);
        graphicsContext.strokeLine(x + 100, y + 53, x + 170, y + 100);
        graphicsContext.strokeLine(x + 100, y + 53, x + 180, y + 100);
        graphicsContext.strokeLine(x + 100, y + 53, x + 190, y + 100);
        graphicsContext.setFill(Color.GREEN);
        graphicsContext.setStroke(Color.BLUE);
        graphicsContext.setLineWidth(5);
        graphicsContext.strokeLine(x + 80, y, x - 10, y - 10);
        graphicsContext.strokeLine(x - 10, y - 10, x + 10, y + 25);
        graphicsContext.strokeLine(x + 120, y, x + 210, y - 10);
        graphicsContext.strokeLine(x + 210, y - 10, x + 188, y + 25);
    }

    private boolean handleСollision() {
        for (Shape shape : board.getShapes()) {
            if (shape == this) {
                continue;
            }
            if (shape.getDistance(x, y) < DIAMETER1) {
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

    private void murMeow() {
        try {
            FileInputStream fileInputStream = new FileInputStream("murMeow.mp3");
            try {
                Player player = new Player(fileInputStream);
                player.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
