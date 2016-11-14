import java.io.*;
import java.util.*;

class Main
{
    int rotation; //0 - Right; 1 - Up; 2 - Left; 3 - Down;
    int maze[][];

    int robotX;
    int robotY;

    int mazeValue[][];
    int countDuplicateValues[];

    String ReadLn ()  // utility function to read from stdin
    {
        byte buff[] = new byte[255];
        int i = 0, a = -1;

        try
        {
            while (i < 255)
            {
                a = System.in.read();
                if ((a < 0) || (a == '\n')) break;
                buff[i++] += a;
            }
        }
        catch (IOException e)
        {
            return (null);
        }

        if ((a < 0) && (i == 0)) return (null);  // eof
        return (new String (buff, 0, i));
    }

    public static void main (String args[])  // entry point from OS
    {
        Main myWork = new Main();  // create a dinamic instance
        myWork.Begin();            // the true entry point
    }

    void Begin() {
        while (true) {
            String input = ReadLn();
            StringTokenizer idata = new StringTokenizer(input);
            int a = Integer.parseInt(idata.nextToken());
            int b = Integer.parseInt(idata.nextToken());
            if(a == 0 && b == 0) return;
            mazeSolution(a, b);
        }
    }

    void mazeSolution(int height, int width)
    {
        rotation = 0; //0 - Right; 1 - Up; 2 - Left; 3 - Down;
        countDuplicateValues = new int[5];

        String input = "";
        int mazeHeight = height + 2;
        int mazeWidth = width + 2;

        maze = new int[mazeHeight][mazeWidth];

        for(int i = 0; i < mazeHeight; i++) {
            if(i != 0 && i != mazeHeight - 1) input = ReadLn();
            maze[i][0] = 1;
            for(int j = 1; j < mazeWidth - 1; j++) {
                if(i == 0 || i == mazeHeight - 1) {
                    maze[i][j] = 1;
                }
                else maze[i][j] = Character.getNumericValue(input.charAt(j-1));
            }
            maze[i][mazeWidth - 1] = 1;
        }

        robotX = 1;
        robotY = mazeHeight - 2;

        mazeValue = new int[mazeHeight-2][mazeWidth-2];

        do {
            doStep();
            mazeValue[robotY-1][robotX-1]++;
        } while(robotX != 1 || robotY != mazeHeight - 2);

        for(int i = 0; i < mazeHeight - 2; i++)
            for (int j = 0; j < mazeWidth - 2; j++)
                if(maze[i+1][j+1] != 1)
                    if(mazeValue[i][j] < 5)
                        countDuplicateValues[mazeValue[i][j]]++;

        for (int i = 0; i < 5; i++) {

            System.out.printf("%3d",countDuplicateValues[i]);
        }
        System.out.println();
    }

    void doStep()
    {
        if(rotation == 0) {     //Right rotation
            if(maze[robotY+1][robotX] == 0) {           //Step Down
                rotation = 3;
                robotY++;
            }
            else if(maze[robotY][robotX+1] == 0) {      //Step Right
                robotX++;
            }
            else if(maze[robotY-1][robotX] == 0) {      //Step Up
                rotation = 1;
                robotY--;
            }
            else {                                      //Step Left
                rotation = 2;
                robotX--;
            }
        }

        else if(rotation == 1) {     //Up rotation
            if(maze[robotY][robotX+1] == 0) {           //Step Right
                rotation = 0;
                robotX++;
            }
            else if(maze[robotY-1][robotX] == 0) {      //Step Up
                robotY--;
            }
            else if(maze[robotY][robotX-1] == 0) {      //Step Left
                rotation = 2;
                robotX--;
            }
            else {                                      //Step Down
                rotation = 3;
                robotY++;
            }
        }

        else if(rotation == 2) {     //Left rotation
            if(maze[robotY-1][robotX] == 0) {           //Step Up
                rotation = 1;
                robotY--;
            }
            else if(maze[robotY][robotX-1] == 0) {      //Step Left
                robotX--;
            }
            else if(maze[robotY+1][robotX] == 0) {      //Step Down
                rotation = 3;
                robotY++;
            }
            else {                                      //Step Right
                rotation = 0;
                robotX++;
            }
        }

        else if(rotation == 3) {     //Down rotation
            if(maze[robotY][robotX-1] == 0) {           //Step Left
                rotation = 2;
                robotX--;
            }
            else if(maze[robotY+1][robotX] == 0) {      //Step Down
                robotY++;
            }
            else if(maze[robotY][robotX+1] == 0) {      //Step Right
                rotation = 0;
                robotX++;
            }
            else {                                      //Step Up
                rotation = 1;
                robotY--;
            }
        }
    }
}
