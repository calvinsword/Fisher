package entity;

import Main.GamePanel;
import Main.KeyHandler;
import object.OBJ_ButtonIndicator_1;
import object.OBJ_ButtonIndicator_2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    boolean sprint, buttonAnimator,action,fishing;
    int fishGetter,fish;
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        //COLLISION
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefualtX = solidArea.x;
        solidAreaDefualtY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        // Spawn point
        worldX = gp.tileSize * 26;
        worldY = gp.tileSize * 35;
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
        System.out.println(fish);
        //OBJECT ANIMATOR
        objectAnimations++;
        if (objectAnimations > 30) {
            if (objectAnimator == 1){
                objectAnimator = 2;
            }
            else if (objectAnimator == 2){
                objectAnimator = 1;
            }
            objectAnimations = 0;
        }
        //BUTTON ANIMATOR
        if (buttonAnimator == true) {
            if (action == false){
                if (objectAnimator == 1) {
                    gp.obj[1] = new OBJ_ButtonIndicator_1();
                    gp.obj[1].worldX = 31 * gp.tileSize;
                    gp.obj[1].worldY = 36 * gp.tileSize;
                    gp.obj[2] = null;
                }
                else if (objectAnimator == 2) {
                    gp.obj[2] = new OBJ_ButtonIndicator_2();
                    gp.obj[2].worldX = 31 * gp.tileSize;
                    gp.obj[2].worldY = 36 * gp.tileSize;
                    gp.obj[1] = null;
                }
            }
        }
        //FISH GIVER
        if(fishing == true){
            gp.obj[1] = null;
            gp.obj[2] = null;
            if (objectAnimations > 29){
                fishGetter++;
            }
            if (fishGetter > 10){
                fish++;
                fishGetter = 0;
            }
            //EXIT FISHING
            if (objectAnimations == 25 && keyH.actionPressed == true){
                if (keyH.leftPressed){
                    worldX = 30 * gp.tileSize;
                }
                else if(keyH.rightPressed || keyH.downPressed){
                    worldX = 32 * gp.tileSize;
                }
                else if (keyH.upPressed) {
                    worldY = 37 * gp.tileSize;
                }
                else worldX = 32 * gp.tileSize;
                fishing = false;
                action = false;
            }
        }
        //MOVEMENT
        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true){
            // SPRINT
            if (keyH.shiftPressed == true) {
                sprint = true;
                spriteCounter++;
            } else sprint = false;
            //ACTION KEY
            if (keyH.actionPressed == true) {
                action = true;
            } else action = false;

            // MOVE KEYS
            if (keyH.upPressed == true) {
                direction = "back";

            }
            else if(keyH.downPressed == true){
                direction = "front";

            }
            else if(keyH.leftPressed == true){
                direction = "left";

            }
            else if(keyH.rightPressed == true){
                direction = "right";

            }
            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            Actions(objIndex);

            //SPRINT MECANIC
            if (sprint == true){
                speed = 8;
            }
            else speed = 4;

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false) {

                switch (direction) {
                    case "front": worldY += speed; break;
                    case "back":  worldY -= speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }

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

    //PLAYER ACTIONS
    public void Actions(int i) {
        if(i != 999){
            System.out.println("test");
            String objectName = gp.obj[i].name;

            //FISHING
            switch(objectName) {
                case "Chair":
                    buttonAnimator = true;
                    // IF E IS PRESSED IT WILL DELETE THE BUTTONINDICATOR
                    if(action == true) {
                        worldX = 31 * gp.tileSize;
                        worldY = 37 * gp.tileSize;
                        fishing = true;
                    }
                    break;
            }
        }
        else if (i == 999){
            //RESETS THE BUTTON ANIMATION
            buttonAnimator = false;
            gp.obj[1] = null;
            gp.obj[2] = null;
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
