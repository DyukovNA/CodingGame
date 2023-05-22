package entity;

import model.CodeExecutor;
import model.CollisionChecker;
import model.LevelManager;
import swing.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    public boolean isShouldBeMoved() {
        return shouldBeMoved;
    }

    public void setShouldBeMoved(boolean shouldBeMoved) {
        this.shouldBeMoved = shouldBeMoved;
    }

    public boolean shouldBeMoved = false;
    int indexOfObjectOnTile = -1;

    public void setTangerines(int tangerines) {
        this.tangerines = tangerines;
    }

    public int getTangerines() {
        return tangerines;
    }

    int tangerines = 0;
    GamePanel gamePanel;

    public Player(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        solidArea = new Rectangle();
        solidArea.x = this.x + 10;
        solidArea.y = this.y + 10;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.height = 100;
        solidArea.width = 100;

        getPlayerImage();

        LevelManager.setPlayer(this);
    }

    private void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/CapiBup.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/CapiBdown.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/CapiBleft.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/CapyB.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turn(String[] splitCommand) {
        this.direction = splitCommand[1];
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void move() {
        // Check tile collision
        collisionOn = CollisionChecker.checkTile(x, y, speed, direction);
        // Check object collision
        indexOfObjectOnTile = CollisionChecker.checkObject(speed, solidArea, direction, true);
        // Check if we are on a finish tile
        checkFinish();
        if (!collisionOn) {
            switch (direction) {
                case "up" -> y -= speed;
                case "down" -> y += speed;
                case "left" -> x -= speed;
                case "right" -> x += speed;
            }
            solidArea.x = x;
            solidArea.y = y;
            CodeExecutor.unitsToMove -= speed;
            shouldBeMoved = CodeExecutor.unitsToMove > 0;
            if (CodeExecutor.unitsToMove == 0) CodeExecutor.execute();
        } else {
            LevelManager.reset(1);
        }
    }

    public void grab() {
        int index = indexOfObjectOnTile;
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (index != -1 && gamePanel.objects[index] != null) {
            String name = gamePanel.objects[index].name;
            if (name.equals("Tangerine")) {
                tangerines++;
                gamePanel.objects[index] = null;
            } else gamePanel.codePanel.getMessageArea().setText("Nothing to grab");
        }
        gamePanel.repaint();
    }

    private void checkFinish() {
        int index = indexOfObjectOnTile;
        if (index != -1 && gamePanel.objects[index] != null && gamePanel.objects[index].name.equals("Bath")) {
            LevelManager.goToNextLevel();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = switch (direction) {
            case "up" -> up1;
            case "down" -> down1;
            case "left" -> left1;
            case "right" -> right1;
            default -> down1;
        };

        g2.drawImage(image, x, y, 120, 100, null);
    }
}
