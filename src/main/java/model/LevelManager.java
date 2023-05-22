package model;

import entity.Player;
import object.Bath;
import object.Tangerine;
import swing.GamePanel;

import javax.swing.*;
import java.awt.*;

public class LevelManager {
    public static int currentLevel = 1;
    public static int maxLevel = 3;
    public static void setGamePanel(GamePanel gamePanel) {
        LevelManager.gamePanel = gamePanel;
    }
    public static GamePanel gamePanel;
    public static void setPlayer(Player player) {
        LevelManager.player = player;
    }
    public static Player player;

    public static void setMainPanel(JPanel mainPanel) {
        LevelManager.mainPanel = mainPanel;
    }

    static JPanel mainPanel;

    public static void reset(int type) {
        placePlayer(currentLevel);
        player.setTangerines(0);
        createLevel(currentLevel);
        gamePanel.repaint();
        CodeExecutor.commands.clear();
        if (type == 1) gamePanel.codePanel.getMessageArea().setText("Collision detected. Please,try again.");
    }
    public static void goToNextLevel() {
        if (player.getTangerines() == gamePanel.getTangerinesToFinish()) {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            if (!(LevelManager.currentLevel + 1 >= LevelManager.maxLevel)) {
                gamePanel.codePanel.getTextArea().setText("");
                gamePanel.codePanel.getMessageArea().setText("");
                cl.show(mainPanel, "End");
            } else cl.show(mainPanel, "Thanks");
        }
    }
    public static void createLevel(int n) {
        switch (n) {
            case 1 -> createLevelOne();
            case 2 -> createLevelTwo();
            case 3 -> createLevelThree();
            case 4 -> {
                mainPanel = gamePanel.codePanel.getMainPanel();
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Thanks");
            }
            default -> createTestLevel();
        }
    }
    public static void createTestLevel() {
        gamePanel.getTileManager().loadMap("maps/map00.txt");
        placePlayer(0);
    }

    private static void createLevelOne() {
        gamePanel.getTileManager().loadMap("maps/map01.txt");
        placePlayer(1);

        gamePanel.objects[0] = new Tangerine(1, 4);
        gamePanel.objects[1] = new Bath(6, 1);

        gamePanel.setTangerinesToFinish(1);
    }

    private static void createLevelTwo() {
        gamePanel.getTileManager().loadMap("maps/map02.txt");

        gamePanel.objects[0] = new Tangerine(3, 2);
        gamePanel.objects[1] = new Tangerine(7, 4);
        gamePanel.objects[2] = new Bath(0, 2);

        gamePanel.setTangerinesToFinish(2);

        placePlayer(2);
    }
    private static void createLevelThree() {
        gamePanel.getTileManager().loadMap("maps/map03.txt");

        gamePanel.objects[0] = new Tangerine(4, 2);
        gamePanel.objects[1] = new Tangerine(1, 3);
        gamePanel.objects[2] = new Tangerine(0, 4);
        gamePanel.objects[3] = new Bath(0, 2);

        gamePanel.setTangerinesToFinish(3);

        placePlayer(3);
    }
    public static void placePlayer(int level) {
        player.speed = 4;
        player.shouldBeMoved = false;
        int x = 0;
        int y = 0;
        String direction = "right";
        switch (level) {
            case 0 -> {
                //direction = "right";
                x = 5 * gamePanel.getTileSize();
                y = 2 * gamePanel.getTileSize();
            }
            case 1 ->  {
                //direction = "down";
                x = gamePanel.getTileSize();
                y = gamePanel.getTileSize();
            }
            case 2 -> {
                x = 0;
                y = 0;
                direction = "right";
            }
            case 3 -> {
                x = 4 *gamePanel.getTileSize();
                y = 5 * gamePanel.getTileSize();
                direction = "up";
            }
        }
        player.x = x;
        player.y = y;
        player.direction = direction;
    }
}
