package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int speed;

    public BufferedImage front1, front2, back1, back2, left1, left2, left3, right1, right2, right3;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNumber = 1;
}
