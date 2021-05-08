package logic;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Food {
    public static final Color COLOR  = Color.TOMATO;

    private Point2D position;

    Food (Point2D position){
        this.position = position;
        System.out.println("Food position: " + position.getX() + ", " + position.getY());
    }

    public Point2D getPosition() {
        return position;
    }

}
