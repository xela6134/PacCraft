package pacman;

import javax.swing.JFrame;

import pacman.components.GamePanel;

public class App {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pacman");
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack(); // sets screen size automatically
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
