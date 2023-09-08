package pacman.components;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import pacman.entities.Player;
import pacman.entities.Entity.Direction;
import pacman.entities.mobs.BlueGhost;
import pacman.entities.mobs.Devil;
import pacman.entities.mobs.Mob;
import pacman.entities.mobs.RedGhost;
import pacman.tiles.DirtTile;
import pacman.tiles.GrassTile;
import pacman.tiles.LavaTile;
import pacman.tiles.SandTile;
import pacman.tiles.Tile;
import pacman.tiles.WallTile;
import pacman.tiles.WaterTile;

public class GameMap {
    GamePanel panel;
    Tile mapTiles[][];
    Mob mapMobs[][];
    Player player;

    public GameMap(GamePanel panel) {
        this.panel = panel;
        mapTiles = new Tile[GamePanel.WIDTH_NUM][GamePanel.HEIGHT_NUM];
        mapMobs = new Mob[GamePanel.WIDTH_NUM][GamePanel.HEIGHT_NUM];
        loadMap();
        loadMobs();
    }

    public void addPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Tile getTile(int x, int y) {
        return mapTiles[x][y];
    }

    public void loadMap() {
        try {
            InputStream mapStream = getClass().getResourceAsStream("/maps/defaultmap.txt");
            BufferedReader mapReader = new BufferedReader(new InputStreamReader(mapStream));

            for (int row = 0; row < GamePanel.HEIGHT_NUM; row++) {
                String line = mapReader.readLine();
                String numbers[] = line.split(" ");

                for (int col = 0; col < GamePanel.WIDTH_NUM; col++) {
                    int num = Integer.parseInt(numbers[col]);   
                    switch (num) {
                        case 0:
                            mapTiles[col][row] = new DirtTile(true, col, row);
                            break;
                        case 1:
                            mapTiles[col][row] = new GrassTile(true, col, row);
                            break;
                        case 2:
                            mapTiles[col][row] = new SandTile(true, col, row);
                            break;
                        case 3:
                            mapTiles[col][row] = new WaterTile(true, col, row);
                            break;
                        case 4:
                            mapTiles[col][row] = new LavaTile(true, col, row);
                            break;
                        case 5:
                            mapTiles[col][row] = new WallTile(false, col, row);
                            break;
                    }
                }
            }
            mapReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMobs() {
        try {
            InputStream mobStream = getClass().getResourceAsStream("/maps/defaultmob.txt");
            BufferedReader mobReader = new BufferedReader(new InputStreamReader(mobStream));

            for (int row = 0; row < GamePanel.HEIGHT_NUM; row++) {
                String line = mobReader.readLine();
                String numbers[] = line.split(" ");

                for (int col = 0; col < GamePanel.WIDTH_NUM; col++) {
                    int num = Integer.parseInt(numbers[col]);   
                    switch (num) {
                        case 0:
                            break;
                        case 1:
                            BlueGhost blueGhost = new BlueGhost(col, row, BlueGhost.BLUEGHOST_SPEED, Direction.LEFT, this);
                            mapMobs[col][row] = blueGhost;
                            panel.addMob(blueGhost);
                            break;
                        case 2:
                            RedGhost redGhost = new RedGhost(col, row, RedGhost.REDGHOST_SPEED, Direction.LEFT, this);
                            mapMobs[col][row] = redGhost;
                            panel.addMob(redGhost);
                            break;
                        case 3:
                            Devil devil = new Devil(col, row, Devil.DEVIL_SPEED, Direction.LEFT, this);
                            mapMobs[col][row] = devil;
                            panel.addMob(devil);
                            break;
                    }
                }
            }
            mobReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadObjects() {
        try {
            InputStream mobStream = getClass().getResourceAsStream("/maps/defaultmob.txt");
            BufferedReader mobReader = new BufferedReader(new InputStreamReader(mobStream));

            for (int row = 0; row < GamePanel.HEIGHT_NUM; row++) {
                String line = mobReader.readLine();
                String numbers[] = line.split(" ");

                for (int col = 0; col < GamePanel.WIDTH_NUM; col++) {
                    int num = Integer.parseInt(numbers[col]);   
                    switch (num) {
                        case 0:
                            break;
                        case 1:
                            BlueGhost blueGhost = new BlueGhost(col, row, BlueGhost.BLUEGHOST_SPEED, Direction.LEFT, this);
                            mapMobs[col][row] = blueGhost;
                            panel.addMob(blueGhost);
                            break;
                        case 2:
                            RedGhost redGhost = new RedGhost(col, row, RedGhost.REDGHOST_SPEED, Direction.LEFT, this);
                            mapMobs[col][row] = redGhost;
                            panel.addMob(redGhost);
                            break;
                        case 3:
                            Devil devil = new Devil(col, row, Devil.DEVIL_SPEED, Direction.LEFT, this);
                            mapMobs[col][row] = devil;
                            panel.addMob(devil);
                            break;
                    }
                }
            }
            mobReader.close();
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
