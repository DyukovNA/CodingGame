package object;

import swing.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

import static model.LevelManager.gamePanel;

public class Tangerine extends Object {
    public Tangerine(int xTiles, int yTiles){
        name = "Tangerine";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/tangerine.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        x = xTiles * gamePanel.getTileSize() + gamePanel.getTileSize() / 4;
        y = yTiles * gamePanel.getTileSize() + gamePanel.getTileSize() / 4;
        solidAreaDefaultX = x;
        solidAreaDefaultY = y;
        solidArea.x = x;
        solidArea.y = y;
        solidArea.width = 60;
        solidArea.height = 60;
    }
    public void draw(Graphics2D g2, GamePanel gamePanel) {
        g2.drawImage(image, x , y, gamePanel.getTileSize() / 2, gamePanel.getTileSize() / 2, null);
    }
}
