
package mx.unison.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Ellipse2D;

public class Board extends JComponent {

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

        configureKeyBindings();

        gameTimer = new Timer(16, e -> {
            if (!isPaused) {
                ballRunner.update();
            }
            repaint();
        });

        gameTimer.start();
    }

    private void configureKeyBindings() {

        InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "pause");
        inputMap.put(KeyStroke.getKeyStroke("R"), "resume");

        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPaused) {
                    paddle.moveLeft();
                    repaint();
                }
            }
        });

        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPaused) {
                    paddle.moveRight();
                    repaint();
                }
            }
        });

        actionMap.put("pause", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPaused = true;
                repaint();
            }
        });

        actionMap.put("resume", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPaused = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics graphicsContext) {
        super.paintComponent(graphicsContext);

        Graphics2D g = (Graphics2D) graphicsContext;

        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

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
}
