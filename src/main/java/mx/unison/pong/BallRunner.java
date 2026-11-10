
package mx.unison.pong;

import java.awt.geom.Ellipse2D;

public class BallRunner {

    private static final int MAX_X = 640;
    private static final int MAX_Y = 480;

    private static final int DX = 5;
    private static final int DY = 5;

    private Ellipse2D.Double ball;
    private Paddle paddle;

    private int directionX = DX;
    private int directionY = DY;

    private boolean stopped = false;

    public BallRunner(Ellipse2D.Double ball, Paddle paddle) {
        this.ball = ball;
        this.paddle = paddle;
    }

    public void update() {

        if (stopped) {
            return;
        }

        ball.x += directionX;
        ball.y += directionY;

        // Colisión con los lados
        if (ball.x <= 0 || ball.x + ball.width >= MAX_X) {
            directionX = -directionX;
        }

        // Colisión con la parte superior
        if (ball.y <= 0) {
            directionY = -directionY;
        }

        // Colisión con la paleta
        if (paddle.intersects(ball)) {
            directionY = -Math.abs(directionY);
        }

        // Si la pelota llega al fondo
        if (ball.y + ball.height >= MAX_Y) {
            ball.x = 20;
            ball.y = 320;
            directionX = DX;
            directionY = DY;
        }
    }

    public void stop() {
        stopped = true;
    }
}
