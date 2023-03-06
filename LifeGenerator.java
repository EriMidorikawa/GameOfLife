public class LifeGenerator implements Runnable
{
    private int[][] grid;
    private int Row;
    private int Column;
    public int r;
    public int c;
    private int livingCells;
    public boolean liveOrDead;
    public Thread t;

    public LifeGenerator(int[][] array, int R, int C, int n, int m)
    {
        this.grid = array;
        this.Row = R;
        this.Column = C;
        this.r = n;
        this.c = m;
    }

    public void start() {
        t = new Thread(this);t.start();
    }

    //count the number of living cells
    public void countLivingCells(int r, int c)
    {
        livingCells = 0;
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                if ((r + i >= 0 && r + i < Row) && (c + j >= 0 && c + j < Column))
                    livingCells += grid[r + i][c + j];  //add 0(dead cell) or add 1(live cell)
        livingCells -= grid[r][c]; //subtract own from the number of living cells
    }


    //determine future cell will be alive or dead
    public void lifeRule()
    {
        this.countLivingCells(r, c);

        //under population
        if ((grid[r][c] == 1) && (livingCells < 2))
            liveOrDead = false;
        //live next generation
        else if ((grid[r][c] == 1) && ((livingCells == 2) || (livingCells == 3)))
            liveOrDead = true;
        //over population
        else if ((grid[r][c] == 1) && (livingCells > 3))
            liveOrDead = false;
        //reproduction
        else if ((grid[r][c] == 0) && (livingCells == 3))
            liveOrDead = true;
        //Remains dead
        else
            liveOrDead = false;

    }

    @Override
    public void run() {
       this.lifeRule();
    }
}

