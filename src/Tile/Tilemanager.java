package Tile;

import Main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Tilemanager {
    GamePanel gp;
    Tile[] tile;

    public Tilemanager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage(){
        try{
            //TILES
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("tiles/grass_1.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("tiles/water_1.png"));



        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {

        g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize,null);
    }
}
