
package mx.unison.pong;

import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class Paddle {

    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    private static final int BOARD_WIDTH = 640;

    private final Rectangle2D.Double paddle;

    public Paddle() {
        paddle = new Rectangle2D.Double(
                (BOARD_WIDTH - WIDTH) / 2,
                450,
                WIDTH,
                HEIGHT
        );
    }

    public void moveRight() {
        if (paddle.x + WIDTH < BOARD_WIDTH) {
            paddle.x += WIDTH;
        }
    }

    public void moveLeft() {
        if (paddle.x > 0) {
            paddle.x -= WIDTH;
        }
    }

    public Rectangle2D.Double getPaddle() {
        return paddle;
    }

    public boolean check(int x, int y) {
        return paddle.contains(x, y);
    }

    public boolean intersects(RectangularShape shape) {
        return paddle.intersects(shape.getBounds2D());
    }
}
