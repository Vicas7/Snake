package logic;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.Random;

public class Grid {
    public static final int SIZE = 10;
    public static final Color COLOR = Color.BLACK;

    private final int rows;
    private final int cols;

    private Snake snake;
    private Food food;

    public Grid(final int width,final int height) {
        rows = width / SIZE;
        cols = height / SIZE;
        snake = new Snake(this, new Point2D((double)rows / 2, (double)cols / 2));
        food = generateFood();
    }

    public Point2D wrapPosition(Point2D newPosition) {
        int x = (int) newPosition.getX();
        int y = (int) newPosition.getY();
        if (x < 0) x = rows - 1;
        if (y < 0) y = cols - 1;
        if (x >= rows) x = 0;
        if (y >= cols) y = 0;
        return new Point2D(x, y);
    }

    public void update() {
        if (food.getPosition().equals(snake.getHeadPosition())) {
            snake.getsBigger();
            food = generateFood();

        } else {
            snake.move();
        }
    }

    private Food generateFood() {
        Random rand = new Random();
        int x = rand.nextInt(cols);
        int y = rand.nextInt(rows);
        return new Food(new Point2D(x, y));
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }


    public int getWidth() {
        return cols * SIZE;
    }

    public int getHeight() {
        return rows * SIZE;
    }
}
