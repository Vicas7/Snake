package gui;

import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import logic.GameLoop;
import logic.Grid;
import logic.Snake;


public class Main extends Application {
    private final int width = 200;
    private final int height = 200;
    private GameLoop loop;
    private Grid grid;
    private GraphicsContext gc;

    @Override
    public void start(Stage stage) {
        VBox vBox = new VBox();
        Canvas canvas = new Canvas(width,height);
        gc = canvas.getGraphicsContext2D();

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(key -> {
            Snake snake = grid.getSnake();
            if (loop.isKeyPressed()){
                return;
            }
            loop.setKeyPressed(true);
            switch (key.getCode()){
                case UP:
                case W:
                    snake.setUp();
                    break;
                case DOWN:
                case S:
                    snake.setDown();
                    break;
                case LEFT:
                case A:
                    snake.setLeft();
                    break;
                case RIGHT:
                case D:
                    snake.setRight();
                    break;
                case ESCAPE:
                    System.out.println("ESCAPE");
                    if(!loop.isPaused() && loop.isRunning()) {
                        loop.pause();
                        gc.setFill(Color.WHITE);
                        gc.setTextAlign(TextAlignment.CENTER);
                        gc.setTextBaseline(VPos.CENTER);
                        gc.fillText("Click ESCAPE to resume or R to restart", grid.getWidth()/ 2f, grid.getHeight()/2f);
                        gc.fillText("Or R to restart", grid.getWidth()/ 2f, grid.getHeight()/2f + 15);
                    } else if (loop.isRunning()) {
                        loop.resume();
                        (new Thread(loop)).start();
                    }
                    loop.setKeyPressed(false);
                    break;
                case R:
                    if (loop.isPaused() || !loop.isRunning()) {
                        restart();
                        (new Thread(loop)).start();
                    }
                    loop.setKeyPressed(false);
            }
        });
        restart();
        vBox.getChildren().add(canvas);
        Scene scene = new Scene(vBox);

        stage.setResizable(false);
        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.show();
        (new Thread(loop)).start();
    }

    private void restart() {
        grid = new Grid(width,height);
        loop = new GameLoop(grid,gc);
    }


    public static void main(String[] args) {
        launch();
    }
}


