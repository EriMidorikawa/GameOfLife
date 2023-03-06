import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        int Row = 20, Column = 20;
        int[][] grid;
        int numGeneration;

        //open file and create initial grid
        //please set a path of your file on "pathname"
        String pathname = "/Users/eri/GameOfLife/start";
        InitialGrid ig = new InitialGrid(pathname, Row, Column);
        grid = ig.createInitialGrid();
        numGeneration = ig.getGeneration(); //for recursion

        //display the original generation for testing
        /*
        System.out.println("Original Generation");
        printArray(grid, Row, Column);
        System.out.println();
        */
        nextGen(grid, Row, Column, numGeneration, numGeneration);
    }

    // Find out next generation recursively
    public static void nextGen(int[][] grid, int Row, int Column, int k, int n) throws InterruptedException {
        if (k > 0)  //recursion
        {
            ArrayList<LifeGenerator> life = new ArrayList<>(); //the list of threads
            int[][] future = new int[Row][Column]; //the array for the next generation

            //add all threads into the list
            for(int r = 0; r < Row; r++)
                for (int c = 0; c < Column; c++)
                    life.add(new LifeGenerator(grid, Row, Column, r, c));

            //start all the threads
            for(int i = 0; i < Row*Column; i++)
                life.get(i).start();
            //join all the threads
            for(int i = 0; i < Row*Column; i++)
                life.get(i).t.join();
            //get result from each thread
            for(int i = 0; i < Row*Column; i++)
            {
                if (life.get(i).liveOrDead)
                    future[life.get(i).r][life.get(i).c] = 1;   //make the cell live
                else
                    future[life.get(i).r][life.get(i).c] = 0;   //make the cell dead
            }

            nextGen(future, Row, Column, k - 1, n);

        }
        else //base case
        {
            //display the result
            printArray(grid, Row, Column);
            System.out.println(n);
        }
    }

    //print the array
    public static void printArray(int[][] grid, int R, int C)
    {
        for (int i = 0; i < R; i++)
        {
            for (int j = 0; j < C; j++)
            {
                if (grid[i][j] == 0)
                    System.out.print(".");
                else
                    System.out.print("X");
            }
            System.out.println();
        }
    }
}
