
package mx.unison.pong;

import java.awt.geom.Ellipse2D;

public class BallRunner implements Runnable {

    public static final int MAX_X = 640;
    public static final int MAX_Y = 480;
    public static final int SIGN = -1;

    public static final int DX = 20;
    public static final int DY = 20;

    private final Ellipse2D.Double ball;
    private final Paddle paddle;

    private int ballX;
    private int ballY;

    private boolean isPaused;
    private volatile boolean stop = false;

    private final Object pauseLock = new Object();

    public BallRunner(Ellipse2D.Double shape, Paddle p) {
        ball = shape;
        paddle = p;

        ballX = 320;
        ballY = 240;

        ball.x = ballX;
        ball.y = ballY;

        isPaused = false;
    }

    public void pause() {
        synchronized (pauseLock) {
            isPaused = true;
        }
    }

    public void resume() {
        synchronized (pauseLock) {
            isPaused = false;
            pauseLock.notifyAll();
        }
    }

    @Override
    public void run() {

        int directionY = 1;
        int directionX = 1;

        while (!stop) {

            synchronized (pauseLock) {
                while (isPaused && !stop) {
                    try {
                        pauseLock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        stop = true;
                    }
                }
            }

            if (stop) {
                break;
            }

            if (paddle.intersects(ball)) {
                directionY = directionY * SIGN;
            }

            if (ball.getMinY() < 0) {
                directionY = directionY * SIGN;
            }

            if (ball.getMinX() < 0) {
                directionX = directionX * SIGN;
            }

            if (ball.getMaxY() > MAX_Y) {
                directionY = directionY * SIGN;
            }

            if (ball.getMaxX() > MAX_X) {
                directionX = directionX * SIGN;
            }

            ballX = ballX + (DX * directionX);
            ballY = ballY + (DY * directionY);

            ball.x = ballX;
            ball.y = ballY;

            try {
                Thread.sleep(150L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                stop = true;
            }
        }
    }

    public void stop() {
        synchronized (pauseLock) {
            stop = true;
            pauseLock.notifyAll();
        }
    }
}
