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
import pacman.objects.Object;

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

    public static final int FPS = 30;
    public static final int nanosInSecond = 1000000000;

    public static final int GOLD_GOAL = 5;

    private List<Mob> mobList = new ArrayList<Mob>();
    private List<Object> objectList = new ArrayList<Object>();

    Thread gameThread;
    GameMap map = new GameMap(this);
    KeyHandler handler = new KeyHandler();
    UI ui = new UI(this);

    private Player player = new Player(0, 1, Player.DEFAULT_PLAYER_SPEED, Direction.LEFT, map);
    private boolean gameWon = false;
    private boolean gameLost = false;

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

        int result = 0;
        while (gameThread != null) {
            update();
            repaint(); // this calls paintComponent()
            result = checkGameStatus();
            
            if (result != 0) break;

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

        if (result == 1) {
            gameLost = true;
            repaint();
        } else if (result == 2) {
            gameWon = true;
            repaint();
        } else {
            throw new UnsupportedOperationException("Weird Error");
        }
    }

    public void update() {
        mobList.stream().forEach(x -> x.update());
        player.update(handler);
        mobList.stream().forEach(x -> x.onInteract(player));
        objectList.stream().forEach(x -> x.onInteract(player));
        player.battle(mobList);

        System.out.println("GreenOrb Activation: " + player.getGreenOrbInEffect());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        map.draw(g2);
        objectList.stream().forEach(x -> x.draw(g2));
        mobList.stream().forEach(x -> x.draw(g2));
        
        if (!gameWon && !gameLost) {
            player.draw(g2);
            ui.draw(g2);
        } else if (gameWon) {
            g2.setFont(ui.arial_40);
            g2.setColor(Color.BLACK);
            g2.fillRect(400, 268, 160, 40);
            g2.setColor(Color.WHITE);
            g2.drawString("Game Won :D", 420, 292);
        } else if (gameLost) {
            g2.setFont(ui.arial_40);
            g2.setColor(Color.BLACK);
            g2.fillRect(400, 268, 160, 40);
            g2.setColor(Color.WHITE);
            g2.drawString("Game Lost :(", 420, 292);
        }

        g2.dispose();
    }

    public int checkGameStatus() {
        if (!player.getAlive()) {
            return 1;
        }

        if (player.getGold() == GOLD_GOAL) {
            return 2;
        }

        return 0;
    }

    public void addMob(Mob mob) {
        mobList.add(mob);
    }

    public void addObject(Object object) {
        objectList.add(object);
    }

    public Player getPlayer() {
        return player;
    }
}
