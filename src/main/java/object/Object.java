package object;

import view.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object {
    public BufferedImage image;
    public String name;
    public Rectangle solidArea = new Rectangle(0,0,120,120);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public Boolean collision = false;
    public int x, y;
    public void draw(Graphics2D g2, GamePanel gamePanel) {
        g2.drawImage(image, x, y, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }

}
