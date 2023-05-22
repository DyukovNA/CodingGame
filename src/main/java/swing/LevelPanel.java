package swing;

import model.CodeExecutor;
import model.CollisionChecker;
import model.LevelManager;

import javax.swing.*;
import java.awt.*;

public class LevelPanel extends JPanel {
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    GamePanel gamePanel;
    JPanel endOfLevelPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    JPanel mainPanel;

    public CodePanel getCodePanel() {
        return codePanel;
    }

    CodePanel codePanel;

    public LevelPanel() {
        codePanel = new CodePanel();
        gamePanel = new GamePanel(codePanel);
        codePanel.gamePanel = gamePanel;
        gamePanel.levelPanel = this;
        CodeExecutor.setGamePanel(gamePanel);
        CodeExecutor.setPlayer(gamePanel.player);
        mainPanel = gamePanel.codePanel.mainPanel;

        this.add(gamePanel);
        this.add(codePanel);
        this.setPreferredSize(new Dimension(1920, 1080));

        LevelManager.createLevel(LevelManager.currentLevel);
    }
}
