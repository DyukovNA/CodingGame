package object;

import swing.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

import static model.LevelManager.gamePanel;

public class Bath extends Object{
    public Bath(int xTiles, int yTiles){
        name = "Bath";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/bath.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        x = xTiles * gamePanel.getTileSize();
        y = yTiles * gamePanel.getTileSize();
        solidAreaDefaultX = x;
        solidAreaDefaultY = y;
        solidArea.x = x;
        solidArea.y = y;
        solidArea.height = gamePanel.getTileSize() / 2;
    }
    public void draw(Graphics2D g2, GamePanel gamePanel) {
        g2.drawImage(image, x + 10 , y + 20, 100, 80, null);
    }
}
