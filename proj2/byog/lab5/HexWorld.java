package byog.lab5;
import javafx.geometry.Pos;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import javax.swing.text.Position;
import java.awt.*;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final Random RANDOM = new Random(19931119);

    /**
     * return the bottom-left Position of the top right neighbor
     * to pass to the generateHexagon method.
     * @return p
     */
    public static Position topRightNeighbor(Position current, int s){
        return new Position(current.xPosition + calculateMiddle(s) - s + 1, current.yPosition + s);
    }

    public static Position topLeftNeighbor(Position current, int s){
        return new Position(current.xPosition - calculateMiddle(s) + s - 1, current.yPosition + s);
    }

    public static Position topNeighbor(Position current, int s){
        return new Position(current.xPosition, current.yPosition + s * 2);
    }



//    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
//        for (int i = 0; i < s; i += 1){     //size * 2, iterates through each row
//            int counter = 0;
//            for(int j = p.xPosition - i; j < world.length & counter < s + 2 * i; j += 1) {
//                //s + 2 * i = how many tiles in each row
//                world[j][i] = t;
//                world[j][s * 2 - 1 - i] = t;
//                counter += 1;
//            }
//
//        }
//
//    }
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        int k = 0;
        for (int i = p.yPosition; i < s + p.yPosition; i += 1){     //size * 2, iterates through each row
            int counter = 0;
            for(int j = p.xPosition - k; j < world.length & counter < s + 2 * k; j += 1) {
                //s + 2 * i = how many tiles in each row
                world[j][i] = t;
                world[j][p.yPosition + s * 2 - k - 1] = t;
                counter += 1;
            }
            k += 1;
        }

    }

    private static void fillWithBlanks(TETile[][] world){
        for (int i = 0; i < world.length; i += 1) {
            for (int j = 0; j < world[0].length; j += 1) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    public static class Position {
        private int xPosition;
        private int yPosition;

        private Position(int xp, int yp) {
            xPosition = xp;
            yPosition = yp;
        }
    }

    public static void drawRandomVerticalHexes(TETile[][] world, int noh, Position p, int s, TETile t){
        addHexagon(world, p, s, t);
        Position newPosition = topNeighbor(p, s);
        for (int i = 0; i < noh - 1; i += 1) {
            addHexagon(world, newPosition, s, randomTile());
            newPosition = topNeighbor(newPosition, s);
        }
    }




    private static int calculateMiddle(int size) {
        return size + 2 * (size - 1);
    }

    public static Position calculateLowerLeft(int size) {
        return new Position(size - 1, size * 2 - 1);
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(6);
        switch (tileNum) {
            case 0: return Tileset.SAND;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.MOUNTAIN;
            case 3: return Tileset.FLOOR;
            case 4: return Tileset.GRASS;
            case 5: return Tileset.WATER;
            default: return Tileset.TREE;
        }
    }


    public static void main(String[] args) {
        int SIZE = 3;

        TERenderer ter = new TERenderer();
        ter.initialize(SIZE * 2 + calculateMiddle(SIZE) * 3, SIZE * 5 * 2);

//        TETile tile = new TETile('a', Color.white, Color.black, "tile that display a");
//        TETile [][] hexagon = new TETile[calculateMiddle(SIZE)][SIZE * 2];
//        Position p = calculateLowerLeft(SIZE);
//
//        addHexagon(hexagon, p, SIZE, Tileset.MOUNTAIN);
        /**
         * Generate world
         */
        TETile [][] world = new TETile[SIZE * 2 + calculateMiddle(SIZE) * 3][SIZE * 5 * 2];
        fillWithBlanks(world);


        Position bottomLeft = new Position(calculateMiddle(SIZE) + SIZE * 2 - 1, 0);
        drawRandomVerticalHexes(world, 5, bottomLeft, SIZE, randomTile());
        Position topLeft = topLeftNeighbor(bottomLeft, SIZE);
        drawRandomVerticalHexes(world, 4, topLeft, SIZE, randomTile());
        topLeft = topLeftNeighbor(topLeft, SIZE);
        drawRandomVerticalHexes(world, 3, topLeft, SIZE, randomTile());
        Position topRight = topRightNeighbor(bottomLeft, SIZE);
        drawRandomVerticalHexes(world, 4, topRight, SIZE, randomTile());
        topRight = topRightNeighbor(topRight, SIZE);
        drawRandomVerticalHexes(world, 3, topRight, SIZE, randomTile());


        ter.renderFrame(world);

    }


}
