package swing;

import model.LevelManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenuPanel extends JLayeredPane {
    JButton start;
    JButton levels;
    JButton exit;
    BufferedImage bufferedImage;
    MainWindow mainWindow;
    JPanel mainPanel;
    Dimension buttonSize = new Dimension(640, 140);
    public MenuPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.mainPanel = mainWindow.mainPanel;
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setBackground(Color.WHITE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        addPicture();
        addButtons();
    }

    private void addPicture() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1920, 290));
        try {
             bufferedImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/title.PNG")
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image image = bufferedImage.getScaledInstance(420, 280, 1);
        JLabel label = new JLabel(new ImageIcon(image));
        label.setPreferredSize(new Dimension(420, 280));
        panel.add(label);

        this.add(panel, JLayeredPane.DRAG_LAYER);
    }

    private void addButtons() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(640, 540));

        createStartButton(buttonsPanel);
        createLevelsButton(buttonsPanel);
        createExitButton(buttonsPanel);

        this.add(buttonsPanel, JLayeredPane.DRAG_LAYER);
    }

    private void createStartButton(JPanel panel) {
        start = new JButton("Start from the beginning");
        start.setPreferredSize(buttonSize);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, MainWindow.LEVELPANEL);
                LevelManager.currentLevel = 1;
            }
        });
        panel.add(start);
    }

    private void createLevelsButton(JPanel panel) {
        levels = new JButton("Select level");
        levels.setPreferredSize(buttonSize);
        levels.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, MainWindow.CHOOSE);
            }
        });
        panel.add(levels);
    }

    private void createExitButton(JPanel panel) {
        exit = new JButton("Exit");
        exit.setPreferredSize(buttonSize);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(exit);
    }
}
