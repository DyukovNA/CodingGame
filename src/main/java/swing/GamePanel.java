package swing;

import entity.Player;
import model.CodeExecutor;
import model.CollisionChecker;
import model.LevelManager;
import object.Object;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    int tileSize = 120;
    public int maxScreenCol = 8;
    public int maxScreenRow = 6;
    int screenWidth = tileSize * maxScreenCol;
    int screenHeight = 1080;

    Thread gameThread;
    public CodePanel codePanel;

    public int getTileSize() {
        return tileSize;
    }

    CodeExecutor codeExecutor;

    public CodeExecutor getCodeExecutor() {
        return codeExecutor;
    }

    public TileManager tileManager = new TileManager(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    Player player = new Player(this);
    public int tangerinesToFinish;
    public Object[] objects = new Object[10];
    LevelPanel levelPanel;

    public GamePanel(CodePanel codePanel) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        //this.setBackground(Color.BLACK);
        this.codePanel = codePanel;
        this.codeExecutor = codePanel.codeExecutor;
        //this.mainPanel = codePanel.mainPanel;
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.startGameThread();
        LevelManager.setGamePanel(this);
    }

    @Override
    public void run() {
        while (gameThread != null) {
            while (player.isShouldBeMoved()) {
                player.move();
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //Tile
        tileManager.draw(g2);
        //Objects
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] != null) objects[i].draw(g2, this);
        }
        //Player
        player.draw(g2);

        g2.dispose();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
}
