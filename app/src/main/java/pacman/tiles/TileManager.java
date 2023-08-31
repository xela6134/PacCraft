package pacman.tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


import pacman.components.GamePanel;

public class TileManager {
    GamePanel panel;
    Tile mapTiles[][];

    public TileManager(GamePanel panel) {
        this.panel = panel;
        mapTiles = new Tile[GamePanel.WIDTH_NUM][GamePanel.HEIGHT_NUM];
        loadMap();
    }

    public void loadMap() {
        try {
            InputStream mapStream = getClass().getResourceAsStream("/maps/default.txt");
            BufferedReader mapReader = new BufferedReader(new InputStreamReader(mapStream));

            for (int row = 0; row < GamePanel.HEIGHT_NUM; row++) {
                String line = mapReader.readLine();
                String numbers[] = line.split(" ");

                for (int col = 0; col < GamePanel.WIDTH_NUM; col++) {
                    int num = Integer.parseInt(numbers[col]);   
                    switch (num) {
                        case 0:
                            mapTiles[col][row] = new DirtTile(true);
                            break;
                        case 1:
                            mapTiles[col][row] = new GrassTile(true);
                            break;
                        case 2:
                            mapTiles[col][row] = new SandTile(true);
                            break;
                        case 3:
                            mapTiles[col][row] = new WaterTile(true);
                            break;
                        case 4:
                            mapTiles[col][row] = new LavaTile(true);
                            break;
                        case 5:
                            mapTiles[col][row] = new WallTile(false);
                            break;
                    }
                }
            }
            mapReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        for (int row = 0; row < GamePanel.HEIGHT_NUM; row++) {
            for (int col = 0; col < GamePanel.WIDTH_NUM; col++) {
                g2.drawImage(mapTiles[col][row].getTileImage(), col * GamePanel.TILE_SIZE, row * GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
            }
        }
    }
}
