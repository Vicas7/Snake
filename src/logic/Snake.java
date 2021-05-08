package logic;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    public static final Color ALIVE = Color.SEAGREEN;
    public static final Color DEAD = Color.RED;

    private int lenght;
    private boolean isAlive;
    private Point2D head;
    private List<Point2D> points;
    private Point2D direction;
    private Grid grid;

    Snake(Grid grid, Point2D startPosition) {
        lenght = 1;
        head = startPosition;
        points = new LinkedList<>();
        points.add(startPosition);
        isAlive = true;
        direction = new Point2D(0, 0);
        this.grid = grid;
    }

    public void move() {
        if (isMoving()) {
            wrapAndAdd(head.add(direction));
            points.remove(0);
        }
    }

    public void getsBigger() {
        lenght++;
        wrapAndAdd(head.add(direction));
    }

    public void wrapAndAdd(Point2D newPosition) {
        newPosition = grid.wrapPosition(newPosition);
        isAlive = !points.contains(newPosition);
        head = newPosition;
        points.add(newPosition);
    }

    private boolean isMoving() {
        return !direction.equals(new Point2D(0, 0));
    }

    public void setUp() {
        if (lenght > 1 && direction.equals(new Point2D(0, 1)))
            return;
        direction = new Point2D(0, -1);
    }

    public void setDown() {
        if (lenght > 1 && direction.equals(new Point2D(0, -1)))
            return;
        direction = new Point2D(0, 1);
    }

    public void setLeft() {
        if (lenght > 1 && direction.equals(new Point2D(1, 0)))
            return;
        direction = new Point2D(-1, 0);
    }

    public void setRight() {
        if (lenght > 1 && direction.equals(new Point2D(-1, 0)))
            return;
        direction = new Point2D(1, 0);
    }

    public Point2D getHeadPosition() {
        return head;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public List<Point2D> getPoints() {
        return points;
    }
}
