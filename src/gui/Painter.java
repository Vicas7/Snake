package gui;

import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import logic.Food;
import logic.Grid;
import logic.Snake;

public class Painter {

    public static void paint (Grid grid, GraphicsContext gc){
        // Paint the grid
        gc.setFill(Grid.COLOR);
        gc.fillRect(0,0,grid.getWidth(),grid.getHeight());

        // Paint the snake
        gc.setFill(Snake.ALIVE);
        grid.getSnake().getPoints().forEach(p -> paintPoint(p, gc));
        if (!grid.getSnake().isAlive()){
            gc.setFill(Snake.DEAD);
            paintPoint(grid.getSnake().getHeadPosition(),gc);
        }

        // Paint the food
        gc.setFill(Food.COLOR);
        paintPoint(grid.getFood().getPosition(), gc);

        //Paint the score
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText("Score : " + (grid.getSnake().getPoints().size()-1) *  100, 10, grid.getHeight()-10);

    }

    private static void paintPoint(Point2D p, GraphicsContext gc) {
        int x = (int) p.getX();
        int y = (int) p.getY();
        gc.fillRect(x * Grid.SIZE,y * Grid.SIZE,Grid.SIZE,Grid.SIZE);
    }

    public static void paintGameOverMessage(Grid grid, GraphicsContext gc){
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText("GAME OVER!", grid.getWidth()/2f,grid.getHeight()/2f);
        gc.fillText("Click R to restart", grid.getWidth()/2f,grid.getHeight()/2f + 15);
    }
}
