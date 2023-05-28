package view;

import model.LevelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RulesPanel extends JPanel {
    JPanel mainPanel;
    Font font = new Font("Arial", Font.PLAIN, 20);
    public RulesPanel(MainWindow mainWindow) {
       mainPanel = mainWindow.mainPanel;
       this.setPreferredSize(new Dimension(1920, 1080));
       this.setLayout(new FlowLayout(FlowLayout.CENTER));
       addText();
       addBackButton();
    }

    private void addText() {
        JPanel panel = new JPanel();
        JLabel commands = new JLabel("<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Rules</title>\n" +
                "</head>\n" +
                "<body width=\"1920\">\n" +
                "<h1>\n" +
                "    Rules of the game\n" +
                "</h1>\n" +
                "<h2>\n" +
                "<p>To make Capybara's car move you can use the following commands:</p>\n" +
                "<ul>\n" +
                "    <li>\"move + number of tiles\" to move</li>\n" +
                "    <li>\"turn + left/right/up/down\" to turn</li>\n" +
                "    <li>\"grab\" to grab a tangerine</li>\n" +
                "</ul>\n" +
                "<p>After every command you must write a semicolon</p>\n" +
                "<p>To complete a level you must collect all of the tangerines</p>\n" +
                "<p>Have fun!</p>\n" +
                "</h2>\n" +
                "</body>\n" +
                "</html>");
        commands.setFont(font);
        commands.setPreferredSize(new Dimension(800,400));
        panel.add(commands);
        this.add(panel);
    }
    private void addBackButton() {
        JButton back = new JButton("Got it!");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Menu");
                LevelManager.currentLevel = 1;
            }
        });
        back.setPreferredSize(new Dimension(640,140));
        this.add(back);
    }
}
