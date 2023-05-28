package view;

import model.LevelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndOfLevelPanel extends JPanel {
    JPanel mainPanel;
    Dimension buttonSize = new Dimension(640, 140);
    JPanel buttonsPanel;

    public EndOfLevelPanel(MainWindow mainWindow) {
        mainPanel = mainWindow.mainPanel;
        this.setPreferredSize(new Dimension(1920, 1080));
        addLabel();
        createButtons();
    }

    private void addLabel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1920, 290));
        JLabel label = new JLabel("Congrats!");
        label.setFont(new Font("Arial", Font.PLAIN, 40));
        label.setPreferredSize(new Dimension(420, 280));
        panel.add(label);

        this.add(panel, JLayeredPane.DRAG_LAYER);
    }

    private void createButtons() {
        buttonsPanel = new JPanel();
        createBackButton();
        createNextButton();
        this.add(buttonsPanel);
    }

    private void createBackButton() {
        JButton back = new JButton("Back to the main menu");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Menu");
                LevelManager.currentLevel = 1;
            }
        });
        back.setPreferredSize(buttonSize);
        this.add(back);
    }

    private void createNextButton() {
        JButton next = new JButton("Next level");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LevelManager.currentLevel++;
                LevelManager.createLevel(LevelManager.currentLevel);
                LevelManager.reset(3);
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Level");

            }
        });
        next.setPreferredSize(buttonSize);
        this.add(next);
    }
}
