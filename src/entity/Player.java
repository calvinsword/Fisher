package entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        // Spawn point
        worldX = gp.tileSize * 16;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "front";
    }

    public void getPlayerImage(){
        try{
            front1 = ImageIO.read(getClass().getResourceAsStream("/player/barry_front_1.png"));
            front2 = ImageIO.read(getClass().getResourceAsStream("/player/barry_front_2.png"));
            back1 = ImageIO.read(getClass().getResourceAsStream("/player/barry_back_1.png"));
            back2 = ImageIO.read(getClass().getResourceAsStream("/player/barry_back_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/barry_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/barry_left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/barry_left_3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/barry_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/barry_right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/barry_right_3.png"));


        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true){
            // MOVE KEYS
            if (keyH.upPressed == true) {
                direction = "back";
                worldY -= speed;
            }
            else if(keyH.downPressed == true){
                direction = "front";
                worldY += speed;
            }
            else if(keyH.leftPressed == true){
                direction = "left";
                worldX -= speed;
            }
            else if(keyH.rightPressed == true){
                direction = "right";
                worldX += speed;
            }
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                }
                else if(spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }

    }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        //DECIDES WHAT IMAGE NEEDS TO BE USED
        //ANIMATOR

        switch (direction){
            case "front":
                if(spriteNumber == 1) {
                    image = front1;
                }
                if(spriteNumber == 2) {
                    image = front2;
                }
                break;
            case "back":

                if(spriteNumber == 1) {
                    image = back1;
                }
                if(spriteNumber == 2) {
                    image = back2;
                }
                break;
            case "left":
                if(spriteNumber == 1) {
                    image = left2;
                }
                if(spriteNumber == 2) {
                    image = left3;
                }
                break;
            case "right":
                if(spriteNumber == 1) {
                    image = right2;
                }
                if(spriteNumber == 2) {
                    image = right3;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
