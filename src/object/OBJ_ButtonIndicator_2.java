package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_ButtonIndicator_2 extends SuperObject{
    public OBJ_ButtonIndicator_2() {
        name = "Button";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/Button_indicator_2.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}