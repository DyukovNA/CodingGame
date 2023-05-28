package view;

import model.CodeExecutor;
import model.LevelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CodePanel extends JPanel {
    public JTextArea getTextArea() {
        return textArea;
    }

    JTextArea textArea;

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    JPanel mainPanel;

    public JTextField getMessageArea() {
        return messageArea;
    }

    JTextField messageArea;
    JButton tryButton;
    JButton resetButton;
    JButton backButton;
    GamePanel gamePanel;
    int screenHeight = 1080;
    int screenWidth = 470;

    public CodePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        addCodeArea();
        addMessageArea();
        addButtons();
    }

    private void addCodeArea() {
        textArea = new JTextArea();
        textArea.setBounds(120 * 8 + 10, 0,
                screenWidth, screenHeight - 100);
        textArea.setRows(39);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(Color.LIGHT_GRAY);
        this.add(textArea);
    }

    private void addMessageArea() {
        JPanel messagePanel = new JPanel();
        messageArea = new JTextField();
        messageArea.setBounds(1240, 1080, screenWidth, 50);
        messageArea.setEditable(false);
        messageArea.setColumns(10);
        messageArea.setBackground(Color.LIGHT_GRAY);
        messagePanel.add(messageArea);
        this.add(messagePanel);
    }

    private void addButtons() {
        JPanel buttonsPanel = new JPanel();

        createTryButton(buttonsPanel);
        createResetButton(buttonsPanel);
        createBackButton(buttonsPanel);

        this.add(buttonsPanel);
    }

    private void createTryButton(JPanel panel) {
        tryButton = new JButton("Try");
        tryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                try {
                    CodeExecutor.parse(text);
                } catch (IllegalArgumentException exception) {
                    exception.printStackTrace();
                    textArea.setText("Unknown command");
                }
                CodeExecutor.execute();
                System.out.println(CodeExecutor.commands);
            }
        });
        tryButton.setBounds(1080 / 2, 1080, 400, 100);
        panel.add(tryButton);
    }

    private void createResetButton(JPanel panel) {
        resetButton = new JButton("Reset Level");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LevelManager.reset(0);
            }
        });
        tryButton.setBounds(1080 / 2, 1080, 400, 100);
        panel.add(resetButton);
    }

    private void createBackButton(JPanel panel) {
        backButton = new JButton("Back to the main menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Menu");
                LevelManager.currentLevel = 1;
            }
        });
        panel.add(backButton);
    }
}
