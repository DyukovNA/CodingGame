package model;

import entity.Player;
import view.GamePanel;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CodeExecutor {
    public static int tilesToMove;
    public static int unitsToMove;
    public static Queue<String> commands = new LinkedList<>();

    public static void setGamePanel(GamePanel gamePanel) {
        CodeExecutor.gamePanel = gamePanel;
    }

    static GamePanel gamePanel;

    public static void setPlayer(Player player) {
        CodeExecutor.player = player;
    }

    static Player player;

    public static void execute() {
        if (!commands.isEmpty()) {
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
            if (!player.isShouldBeMoved()) update(command);
        }
    }

    public static void update(String command) {
        String[] splitCommand = command.split(" ");
        switch (splitCommand[0]) {
            case "turn":
                player.turn(splitCommand);
            case "grab":
                player.grab();
        }
        execute();
    }

    public static void parse(String code) throws IllegalArgumentException {
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
                    if (splitLine[0].equals("grab")) {
                        commands.add(line);
                    } else throw new IllegalArgumentException();
                }
            }
        }
    }
}
