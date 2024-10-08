package view;

import model.LevelManager;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    static JFrame frame;
    JPanel mainPanel;
    MenuPanel menuPanel;
    final static String BUTTONPANEL = "Menu";
    final static String LEVELPANEL = "Level";
    final static String END = "End";
    final static String THANKS = "Thanks";
    final static String CHOOSE = "Choose";
    final static String RULES = "Rules";

    public void addComponentToPane(Container pane) {
        mainPanel = new JPanel(new CardLayout());

        menuPanel = new MenuPanel(this);

        LevelPanel levelPanel = new LevelPanel();
        EndOfLevelPanel endOfLevelPanel = new EndOfLevelPanel(this);
        ThanksPanel thanksPanel = new ThanksPanel(this);
        ChooseLevelPanel chooseLevelPanel = new ChooseLevelPanel(this);
        RulesPanel rulesPanel = new RulesPanel(this);

        //adding cards
        mainPanel.add(rulesPanel, RULES);
        mainPanel.add(menuPanel, BUTTONPANEL);
        mainPanel.add(endOfLevelPanel, END);
        mainPanel.add(thanksPanel, THANKS);
        mainPanel.add(chooseLevelPanel, CHOOSE);
        mainPanel.add(levelPanel, LEVELPANEL);


        levelPanel.getCodePanel().setMainPanel(mainPanel);
        LevelManager.setMainPanel(mainPanel);

        pane.add(mainPanel, BorderLayout.PAGE_START);
    }

    public static void createAndShowGUI() {
        frame = new JFrame("CapyRun");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainWindow mainWindow = new MainWindow();
        mainWindow.addComponentToPane(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }
}
