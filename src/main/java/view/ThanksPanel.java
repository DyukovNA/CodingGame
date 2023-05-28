package view;

import model.LevelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThanksPanel extends JPanel {
    JPanel mainPanel;
    public ThanksPanel(MainWindow mainWindow) {
        mainPanel = mainWindow.mainPanel;
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        addLabel();
        createBackButton();
    }
    private void addLabel(){
        JLabel label = new JLabel("Thank you for playing!");
        label.setFont(new Font("Arial", Font.PLAIN, 40));
        label.setPreferredSize(new Dimension(420, 280));
        this.add(label);

    }
    private void createBackButton() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1920, 400));
        JButton back = new JButton("Back to the main menu");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Menu");
                LevelManager.currentLevel = 1;
            }
        });
        back.setPreferredSize(new Dimension(640, 140));
        panel.add(back);
        this.add(panel);
    }
}
