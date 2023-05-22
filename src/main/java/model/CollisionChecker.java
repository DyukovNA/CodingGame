package model;

import swing.GamePanel;

import java.awt.*;

public class CollisionChecker {
    static GamePanel gamePanel = CodeExecutor.gamePanel;

    public static boolean checkTile(int x, int y, int speed, String direction) {
        int column = x / gamePanel.getTileSize();
        int row = y / gamePanel.getTileSize();
        int nextColumn = column;
        int nextRow = row;
        int tileNum;
        try {
            switch (direction) {
                case "up" -> {
                    if (y - speed < 0) {
                        return true;
                    } else {
                        nextRow = (y - speed) / gamePanel.getTileSize();
                        tileNum = gamePanel.getTileManager().mapTileNum[nextColumn][nextRow];
                        return gamePanel.getTileManager().tileList.get(tileNum).collision;
                    }
                }
                case "down" -> {
                    tileNum = gamePanel.getTileManager().mapTileNum[nextColumn][nextRow + 1];
                    return gamePanel.getTileManager().tileList.get(tileNum).collision;
                }
                case "left" -> {
                    if (x - speed < 0) {
                        return true;
                    } else {
                        nextColumn = (x - speed) / gamePanel.getTileSize();
                        tileNum = gamePanel.getTileManager().mapTileNum[nextColumn][nextRow];
                        return gamePanel.getTileManager().tileList.get(tileNum).collision;
                    }
                }
                case "right" -> {
                    tileNum = gamePanel.getTileManager().mapTileNum[nextColumn + 1][nextRow];
                    return gamePanel.getTileManager().tileList.get(tileNum).collision;
                }
                default -> {
                    return false;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    public static int checkObject(int speed, Rectangle solidArea, String direction, boolean isPlayer) {
        int index = -1;
        for (int i = 0; i < gamePanel.objects.length; i++) {
            if (gamePanel.objects[i] != null) {
                switch (direction) {
                    case "up":
                        solidArea.y -= speed;
                        if (solidArea.intersects(gamePanel.objects[i].solidArea)) {
                            if (isPlayer) return i;
                            System.out.println("up collision");
                        }
                        break;
                    case "down":
                        solidArea.y += speed;
                        if (solidArea.intersects(gamePanel.objects[i].solidArea)) {
                            if (isPlayer) return i;
                            System.out.println("down collision");
                        }
                        break;
                    case "left":
                        solidArea.x -= speed;
                        if (solidArea.intersects(gamePanel.objects[i].solidArea)) {
                            if (isPlayer) return i;
                            System.out.println("left collision");
                        }
                        break;
                    case "right":
                        solidArea.x += speed;
                        if (solidArea.intersects(gamePanel.objects[i].solidArea)) {
                            if (isPlayer) return i;
                            System.out.println("right collision");
                        }
                        break;
                }
            }
        }
        return index;
    }
}
