package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chair extends SuperObject{

    public OBJ_Chair() {
        name = "Chair";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/Chair.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
