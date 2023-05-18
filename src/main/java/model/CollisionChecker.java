package model;

import entity.Entity;
import swing.GamePanel;

public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        int column = entity.x / gamePanel.getTileSize();
        int row = entity.y / gamePanel.getTileSize();
        int nextColumn = column;
        int nextRow = row;
        int tileNum;
        try {
            switch (entity.direction) {
                case "up" -> {
                    if (entity.y - entity.speed < 0) {
                        entity.collisionOn = true;
                    } else {
                        nextRow = (entity.y - entity.speed) / gamePanel.getTileSize();
                        tileNum = gamePanel.tileManager.mapTileNum[nextColumn][nextRow];
                        entity.collisionOn = gamePanel.tileManager.tileList.get(tileNum).collision;
                    }
                }
                case "down" -> {
                    //nextRow = (entity.y + entity.speed) / gp.tileSize;
                    tileNum = gamePanel.tileManager.mapTileNum[nextColumn][nextRow + 1];
                    entity.collisionOn = gamePanel.tileManager.tileList.get(tileNum).collision;
                }
                case "left" -> {
                    if (entity.x - entity.speed < 0) {
                        entity.collisionOn = true;
                    } else {
                        nextColumn = (entity.x - entity.speed) / gamePanel.getTileSize();
                        tileNum = gamePanel.tileManager.mapTileNum[nextColumn][nextRow];
                        entity.collisionOn = gamePanel.tileManager.tileList.get(tileNum).collision;
                    }
                }
                case "right" -> {
                    //nextColumn = (entity.x + entity.speed) / gp.tileSize;
                    tileNum = gamePanel.tileManager.mapTileNum[nextColumn + 1][nextRow];
                    entity.collisionOn = gamePanel.tileManager.tileList.get(tileNum).collision;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            entity.collisionOn = true;
        }
    }

    public int checkObject(Entity entity, boolean isPlayer) {
        int index = -1;
        for (int i = 0; i < gamePanel.objects.length; i++) {
            if (gamePanel.objects[i] != null) {
                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gamePanel.objects[i].solidArea)) {
                            if (gamePanel.objects[i].collision) entity.collisionOn = true;
                            if (isPlayer) return i;
                            System.out.println("up collision");
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gamePanel.objects[i].solidArea)) {
                            if (gamePanel.objects[i].collision) entity.collisionOn = true;
                            if (isPlayer) return i;
                            System.out.println("down collision");
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gamePanel.objects[i].solidArea)) {
                            if (gamePanel.objects[i].collision) entity.collisionOn = true;
                            if (isPlayer) return i;
                            System.out.println("left collision");
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gamePanel.objects[i].solidArea)) {
                            if (gamePanel.objects[i].collision) entity.collisionOn = true;
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
