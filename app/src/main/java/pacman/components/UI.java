package pacman.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UI {
    public Font arial_40 = new Font("Arial", Font.PLAIN, 20);

    private BufferedImage health;
    private BufferedImage gold;
    private GamePanel panel;

    public UI(GamePanel panel) {
        this.panel = panel;
        setUIImage();
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.RED);
        g2.drawImage(health, 870, 500, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawString("x " + panel.getPlayer().getHealth(), 910, 520);
        
        g2.setColor(Color.YELLOW);
        g2.drawImage(gold, 870, 540, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawString("x " + panel.getPlayer().getGold(), 910, 560);

        if (panel.getPlayer().getBlueOrbInEffect()) {
            g2.setColor(Color.BLUE);
            g2.drawString("Speed Up!", 840, 450);
        }

        if (panel.getPlayer().getGreenOrbInEffect()) {
            g2.setColor(Color.GREEN);
            g2.drawString("Invulnerable!", 840, 480);
        }
    }

    public void setUIImage() {
        try {
            health = ImageIO.read(getClass().getResourceAsStream("/objects/health.png"));
            gold = ImageIO.read(getClass().getResourceAsStream("/objects/gold.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
