package pacman.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import pacman.entities.Player;
import pacman.entities.Entity.Direction;
import pacman.entities.mobs.Mob;

/**
 * Works as a game screen
 */
public class GamePanel extends JPanel implements Runnable {

    // Screen Settings
    public static final int PIXEL_NUM = 16;
    public static final int SCALE = 2;
    public static final int TILE_SIZE = PIXEL_NUM * SCALE; // 32 x 32

    public static final int WIDTH_NUM = 30;
    public static final int HEIGHT_NUM = 18;
    public static final int SCREEN_WIDTH = TILE_SIZE * WIDTH_NUM;
    public static final int SCREEN_HEIGHT = TILE_SIZE * HEIGHT_NUM;

    public static final int FPS = 60;
    public static final int nanosInSecond = 1000000000;

    List<Mob> mobList = new ArrayList<Mob>();

    Thread gameThread;
    GameMap map = new GameMap(this);
    KeyHandler handler = new KeyHandler();
    Player player = new Player(0, 0, Player.DEFAULT_PLAYER_SPEED, Direction.LEFT, map);

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
            repaint(); // this calls paintComponent()

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
        mobList.stream().forEach(x -> x.update());
        player.update(handler);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        map.draw(g2);
        mobList.stream().forEach(x -> x.draw(g2));
        player.draw(g2);
        g2.dispose();
    }

    public void addMob(Mob mob) {
        mobList.add(mob);
    }
}
