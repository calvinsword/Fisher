package Main;

import object.OBJ_Chair;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){

        gp.obj[0] = new OBJ_Chair();
        gp.obj[0].worldX = 31 * gp.tileSize;
        gp.obj[0].worldY = 37 * gp.tileSize;
    }
}
