package model;

import entity.Player;
import swing.GamePanel;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CodeExecutor {
    public int tilesToMove;
    public int unitsToMove;
    public Queue<String> commands;

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    GamePanel gamePanel;

    public void setPlayer(Player player) {
        this.player = player;
    }

    Player player;
    public CodeExecutor() {
        commands = new LinkedList<>();
    }
    public void execute() {
        if (!player.codeExecutor.commands.isEmpty()) {
            String command = commands.poll();
            gamePanel.codePanel.getMessageArea().setText(command);
            System.out.println(commands);
            String[] splitCommand = command.split(" ");
            if (splitCommand[0].equals("move")) {
                //gamePanel.codePanel.getMessageArea().setText(command);
                tilesToMove = Integer.parseInt(splitCommand[1]);
                unitsToMove = tilesToMove * gamePanel.getTileSize();
                player.setShouldBeMoved(true);
            }
            if (!player.isShouldBeMoved()) player.update(command);
        }
    }
    public void parse(String code) throws IllegalArgumentException {
        List<String> lines = List.of(code.split(";"));

        for (String line : lines) {
            line = line.strip();
            String[] splitLine = line.split(" ");

            if (!line.equals("")) {
                if (splitLine.length == 2) {
                    String command = splitLine[0];
                    String argument = splitLine[1];
                    if (command.equals("move") && argument.matches("\\d+")) {
                        commands.add(line);
                    } else if ((command.equals("turn") && (argument.equals("up") || argument.equals("down") ||
                            argument.equals("left") || argument.equals("right")))) {
                        commands.add(line);
                    } else throw new IllegalArgumentException();
                } else if (splitLine.length == 1) {
                    if (splitLine[0].equals("grab") || splitLine[0].equals("open")) {
                        commands.add(line);
                    } else throw new IllegalArgumentException();
                }
            }
        }
    }
}
