import java.io.*;

public class InitialGrid {

    private String pathName;
    private int Row;
    private int Column;
    private File f;
    private FileReader fr;
    private BufferedReader br;
    private int generation;
    int[][] initialGrid;

    public InitialGrid (String name, int R, int C) throws IOException {
        pathName = name;
        Row = R;
        Column = C;
        f = new File(pathName);
        fr = new FileReader(f);
        br = new BufferedReader(fr);
        initialGrid = new int[Row][Column];
    }
    public int[][] createInitialGrid() throws IOException {

        int content = 0;
        int r = 0;
        int c = 0;

        //read character by character and convert into 0(dead cell) and 1(live cell), then add to the array
        while((content = br.read())!= -1)
        {
            char character = (char) content;

            if((c < Column) && (r < Row))
            {
                if (character == 'X')
                    initialGrid[r][c] = 1;
                else
                    initialGrid[r][c] = 0;
                c++;
            }
            else if((c == Column) && (r < Row))
            {
                c = 0;
                r++;
            }
            else
                generation = Character.getNumericValue(character); //the last line of file is the number of generations
        }
        return initialGrid;
    }

    public int getGeneration() { return generation; }

}
