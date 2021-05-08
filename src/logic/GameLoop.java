package logic;

import gui.Painter;
import javafx.scene.canvas.GraphicsContext;

public class GameLoop implements Runnable{
    private final Grid grid;
    private final GraphicsContext gc;

    private float fps;
    private float interval;
    private boolean running;
    private boolean paused;
    private boolean keyPressed;


    public GameLoop(final Grid grid, final GraphicsContext gc){
        this.grid = grid;
        this.gc = gc;
        fps = 20;
        interval = 1000f / fps;
        running = true;
        paused = false;
    }

    @Override
    public void run() {
        while(running && !paused){
            long time = System.currentTimeMillis();

            grid.update();
            Painter.paint(grid, gc);
            keyPressed = false;

            if(!grid.getSnake().isAlive()){
                stop();
                Painter.paintGameOverMessage(grid,gc);
                keyPressed = false;
                break;
            }
            time = System.currentTimeMillis() - time;

            if (time < interval){
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isPaused() {
        return paused;
    }

    public boolean isRunning() {
        return running;
    }

    public void pause() {
        paused = true;
    }
    public void resume() {
        paused = false;
    }

    public void stop() {
        running = false;
    }

    public boolean isKeyPressed() {
        return keyPressed;
    }

    public void setKeyPressed(boolean b) {
        keyPressed = b;
    }
}
