
package mx.unison.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

public class Board extends JComponent implements KeyListener {

    private Dimension preferredSize = null;
    private Ellipse2D.Double ball;
    private Paddle paddle;
    private BallRunner ballRunner;

    private boolean isPaused = false;

    private final Font theFont = new Font("SansSerif", Font.BOLD, 24);

    private final Timer gameTimer;

    public Board() {
        setOpaque(true);
        setBorder(BorderFactory.createMatteBorder(
                5, 5, 5, 5, Color.BLACK));

        ball = new Ellipse2D.Double(20, 320, 20, 20);
        paddle = new Paddle();
        ballRunner = new BallRunner(ball, paddle);

        gameTimer = new Timer(16, e -> {
            if (!isPaused) {
                ballRunner.update();
            }
            repaint();
        });

        gameTimer.start();
    }

    @Override
    protected void paintComponent(Graphics graphicsContext) {
        super.paintComponent(graphicsContext);

        Graphics2D g = (Graphics2D) graphicsContext;

        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(getForeground());
        }

        if (isPaused) {
            g.setFont(theFont);
            g.setColor(Color.BLUE);
            g.drawString("Paused", 20, 30);
        }

        g.setStroke(new BasicStroke(5.0f));

        g.setColor(Color.RED);
        g.fill(ball);

        g.setColor(Color.BLACK);
        g.fill(paddle.getPaddle());
    }

    @Override
    public Dimension getPreferredSize() {
        if (preferredSize == null) {
            return new Dimension(640, 480);
        }
        return preferredSize;
    }

    @Override
    public void setPreferredSize(Dimension newPrefSize) {
        preferredSize = newPrefSize;
        super.setPreferredSize(newPrefSize);
    }

    public void stopGame() {
        gameTimer.stop();
        ballRunner.stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (!isPaused) {
                    paddle.moveLeft();
                }
                break;

            case KeyEvent.VK_RIGHT:
                if (!isPaused) {
                    paddle.moveRight();
                }
                break;

            case KeyEvent.VK_SPACE:
                isPaused = true;
                break;

            case KeyEvent.VK_R:
                isPaused = false;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
