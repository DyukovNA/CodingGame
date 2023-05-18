package swing;

import model.LevelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseLevelPanel extends JPanel {
    JPanel mainPanel;
    Dimension buttonSize = new Dimension(420, 180);

    public ChooseLevelPanel(MainWindow mainWindow) {
        mainPanel = mainWindow.mainPanel;
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        addLabel();
        addButtons();
    }

    private void addLabel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1920, 200));
        JLabel label = new JLabel("The choice is yours");
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setPreferredSize(new Dimension(420, 200));
        panel.add(label);

        this.add(panel, JLayeredPane.DRAG_LAYER);
    }

    private void addButtons() {
        for (int i = 1; i <= LevelManager.maxLevel; i++) createButton(i);
        createBackButton();
    }

    private void createButton(int i) {
        JButton back = new JButton("Level " + i);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LevelManager.createLevel(i);
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Level");
            }
        });
        back.setPreferredSize(buttonSize);
        this.add(back);
    }

    private void createBackButton() {
        JButton back = new JButton("Back to the main menu");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Menu");
                LevelManager.currentLevel = 0;
            }
        });
        back.setPreferredSize(buttonSize);
        this.add(back);
    }
}
