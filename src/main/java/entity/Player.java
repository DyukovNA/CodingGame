package entity;

import model.CodeExecutor;
import model.LevelManager;
import swing.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;

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

    int tangerines = 0;
    GamePanel gamePanel;
    public CodeExecutor codeExecutor;

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    JPanel mainPanel;

    public Player(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.codeExecutor = gamePanel.getCodeExecutor();

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

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/CapiBup.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/CapiBdown.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/CapiBleft.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/CapyB.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(String command) {
        String[] splitCommand = command.split(" ");
        switch (splitCommand[0]) {
            case "turn":
                turn(splitCommand);
            case "grab":
                grab();
            case "open": // TODO: 19.04.2023
        }
        codeExecutor.execute();
    }
    private void turn(String[] splitCommand) {
        this.direction = splitCommand[1];
        //gamePanel.codePanel.getMessageArea().setText("turning");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void move() {
        // Check tile collision
        gamePanel.collisionChecker.checkTile(this);
        // Check object collision
        indexOfObjectOnTile = gamePanel.collisionChecker.checkObject(this, true);
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
            codeExecutor.unitsToMove -= speed;
            shouldBeMoved = codeExecutor.unitsToMove > 0;
            if (codeExecutor.unitsToMove == 0) codeExecutor.execute();
        } else {
            LevelManager.reset(1);
        }
    }

    public void grab() {
        int index = indexOfObjectOnTile;
        //gamePanel.codePanel.getMessageArea().setText("grabbing");
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
                System.out.println(tangerines);
            } else gamePanel.codePanel.getMessageArea().setText("Nothing to grab");
        }
        gamePanel.repaint();
    }

    private void checkFinish() {
        int index = indexOfObjectOnTile;
        if (index != -1 && gamePanel.objects[index] != null && gamePanel.objects[index].name.equals("Bath")) {
            goToNextLevel();
        }
    }

    private void goToNextLevel() {
        if (tangerines == gamePanel.tangerinesToFinish) {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            if (!(LevelManager.currentLevel + 1 >= LevelManager.maxLevel)) {
                gamePanel.codePanel.getTextArea().setText("");
                gamePanel.codePanel.getMessageArea().setText("");
                cl.show(mainPanel, "End");
            } else cl.show(mainPanel, "Thanks");
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
