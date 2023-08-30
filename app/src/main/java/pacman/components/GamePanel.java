package pacman.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import pacman.Player;

/**
 * Works as a game screen
 */
public class GamePanel extends JPanel implements Runnable {
    
    // Screen Settings
    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int SCALE = 3;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48 x 48

    public static final int WIDTH_NUM = 16;
    public static final int HEIGHT_NUM = 12;
    public static final int SCREEN_WIDTH = TILE_SIZE * WIDTH_NUM;
    public static final int SCREEN_HEIGHT = TILE_SIZE * HEIGHT_NUM;

    public static final int FPS = 60;
    public static final int nanosInSecond = 1000000000;

    Thread gameThread;
    KeyHandler handler = new KeyHandler();
    Player player = new Player(100, 100, 4);

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // enhances performance
        this.addKeyListener(handler);
        this.setFocusable(true); // comes with addKeyListener
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); // automatically calls run method
    }

    /**
     * Running Game Loop
     */
    @Override
    public void run() {
        double drawInterval = nanosInSecond / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }

    public void update() {
        player.update(handler);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        g2.dispose();
    }
}
