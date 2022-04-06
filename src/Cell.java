import java.awt.*;

/*
像素点单元格
 */
public class Cell {
    public static final int DEFAULTSIZE = 10;
    private int x;
    private int y;

    public Cell(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //这里可能存在一定的误差
    public void paintCell(Graphics g){
        g.fillRect(x * DEFAULTSIZE,y * DEFAULTSIZE,DEFAULTSIZE,DEFAULTSIZE);
    }
}
