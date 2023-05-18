package tile;

import swing.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public List<Tile> tileList = new ArrayList<>();
    public int[][] mapTileNum;
    int maxCol;
    int maxRow;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        maxCol = gp.maxScreenCol;
        maxRow = gp.maxScreenRow;
        mapTileNum = new int[maxCol][maxRow];
        getTileImage();
    }
    public void draw (Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < maxCol && row < maxRow) {
            int type = mapTileNum[col][row];
            g2.drawImage(tileList.get(type).image, x, y, gp.getTileSize(), gp.getTileSize(), null);
            col++;
            x += gp.getTileSize();
            if (col == maxCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.getTileSize();
            }
        }
    }

    public void loadMap(String filePath) {
        try (
                InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
        ) {
            int col = 0;
            int row = 0;

            while (row < maxRow) {
                String line = br.readLine();
                String[] numbers = line.split(" ");
                while (col < maxCol) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                row++;
                col = 0;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        public void getTileImage () {
            try {
                Tile dirt = new Tile(); //0
                dirt.image = ImageIO.read(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                                "environment/dirt.png"
                        )));
                dirt.collision = true;
                tileList.add(dirt);

                Tile roadHor = new Tile(); //1
                roadHor.image = ImageIO.read(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                                "environment/road_hor.png"
                        )));
                roadHor.collision = false;
                tileList.add(roadHor);

                Tile roadVert = new Tile(); //2
                roadVert.image = ImageIO.read(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                                "environment/road_ver.png"
                        )));
                roadVert.collision = false;
                tileList.add(roadVert);

                Tile turn1 = new Tile(); //3
                turn1.image = ImageIO.read(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                                "environment/turn_1.png"
                        )));
                turn1.collision = false;
                tileList.add(turn1);

                Tile turn2 = new Tile(); //4
                turn2.image = ImageIO.read(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                                "environment/turn_2.png"
                        )));
                turn2.collision = false;
                tileList.add(turn2);

                Tile turn3 = new Tile(); //5
                turn3.image = ImageIO.read(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                                "environment/turn_3.png"
                        )));
                turn3.collision = false;
                tileList.add(turn3);

                Tile turn4 = new Tile(); //6
                turn4.image = ImageIO.read(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                                "environment/turn_4.png"
                        )));
                turn4.collision = false;
                tileList.add(turn4);

                Tile turnT1 = new Tile(); //7
                turnT1.image = ImageIO.read(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                                "environment/turn_T_1.png"
                        )));
                turnT1.collision = false;
                tileList.add(turnT1);


                Tile turnT2 = new Tile(); //8
                turnT2.image = ImageIO.read(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                                "environment/turn_T_2.png"
                        )));
                turnT2.collision = false;
                tileList.add(turnT2);

                Tile turnT3 = new Tile(); //9
                turnT3.image = ImageIO.read(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                                "environment/turn_T_3.png"
                        )));
                turnT3.collision = false;
                tileList.add(turnT3);

                Tile turnT4 = new Tile(); //10
                turnT4.image = ImageIO.read(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                                "environment/turn_T_4.png"
                        )));
                turnT4.collision = false;
                tileList.add(turnT4);

                Tile crossing = new Tile(); //11
                crossing.image = ImageIO.read(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                                "environment/crossing.png"
                        )));
                crossing.collision = false;
                tileList.add(crossing);

                Tile deadEndL = new Tile(); //12
                deadEndL.image = ImageIO.read(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                                "environment/deadend_l.png"
                        )));
                deadEndL.collision = false;
                tileList.add(deadEndL);

                Tile deadEndR = new Tile(); //13
                deadEndR.image = ImageIO.read(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                                "environment/deadend_r.png"
                        )));
                deadEndR.collision = false;
                tileList.add(deadEndR);

                Tile deadEndU = new Tile(); //14
                deadEndU.image = ImageIO.read(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                                "environment/deadend_u.png"
                        )));
                deadEndU.collision = false;
                tileList.add(deadEndU);

                Tile deadEndD = new Tile(); //15
                deadEndD.image = ImageIO.read(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                                "environment/deadend_d.png"
                        )));
                deadEndD.collision = false;
                tileList.add(deadEndD);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
