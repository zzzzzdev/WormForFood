import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/*
蛇的类
 */
public class Worm {
    public static final int UP = -1;
    public static final int DOWN = 1;
    public static final int LEFT = -2;
    public static final int RIGHT = 2;
    public static final int DEFAULT_LENGTH = 2;
    public static final int DEFAULT_SPEED = 100;
    public static final int DEFAULT_DIRECTION = RIGHT;

    private Cell[] wormBody;
    private int currentLength;
    private int currentDirection;
    private boolean isEat = false;

    public Worm(){
        this.wormBody = new Cell[DEFAULT_LENGTH];
        this.currentDirection = DEFAULT_DIRECTION;
        this.currentLength = DEFAULT_LENGTH;

        for(int i=0;i < DEFAULT_LENGTH ;i ++){
            wormBody[i] = new Cell(DEFAULT_LENGTH - i - 1,0);
        }
    }

    public int getCurrentLength() {
        return currentLength;
    }

    public int getCurrentDirection() {
        return currentDirection;
    }

    public boolean isEat() {
        return isEat;
    }

    public void setCurrentLength(int currentLength) {
        this.currentLength = currentLength;
    }

    public void changeDiretion(int newDirection){
        if(newDirection + currentDirection != 0){
            currentDirection = newDirection;
        }
    }

    public void setEat(boolean eat) {
        isEat = eat;
    }

    //根据当前的前进方向生成新的头
    public Cell newHead(int currentDirection){
        Cell newhead = null;
        switch (currentDirection){
            case UP :
                newhead = new Cell(wormBody[0].getX() ,wormBody[0].getY() - 1 );
                break;
            case DOWN :
                newhead = new Cell(wormBody[0].getX() ,wormBody[0].getY() + 1 );
                break;
            case LEFT :
                newhead = new Cell(wormBody[0].getX() - 1,wormBody[0].getY());
                break;
            case RIGHT :
                newhead = new Cell(wormBody[0].getX() + 1,wormBody[0].getY() );
                break;
            default:
                //如果没有改变方向则继续维持现有的蛇头
                newhead = wormBody[0];
        }
        return newhead;
    }

    public boolean isEatYourself(Cell nextHead){
        boolean flag = false;
        for (int i = 0;i < currentLength;i ++){
            if (wormBody[i].getX() == nextHead.getX() && wormBody[i].getY() == nextHead.getY()){
                flag = true;
            }
        }
        return flag;
    }


    //用于判断是否是由于蛇的原因而导致游戏结束
    public boolean isOver(int currentDirection ){
        Cell newhead = newHead(currentDirection);
        //如果吃到了自己，或者是到达了边界，那么游戏状态为终止
        if(isEatYourself(newhead) == true || newhead.getX() < 0 ||
                newhead.getX() >= Window.DEFAULT_ROW || newhead.getY() < 0 ||
                newhead.getY() >= Window.DEFAULT_COLUMN){
            return true;
        }
        return false;
    }


    //这里是蛇单次爬行的算法，要么吃到食物，要么没有吃到
    public boolean creep(int currentDirection,Food food){
        isEat = false;
        Cell newhead = newHead(currentDirection);

        //在这里随即对生成的任意一个食物进行判断
        for(int i = 0;i < food.foods.length ;i ++){
            if (newhead.getX() == food.foods[i].getX() && newhead.getY() == food.foods[i].getY()){
                isEat = true;
            }
        }
        if (isEat){
            wormBody = Arrays.copyOf(wormBody,wormBody.length + 1);
            currentLength ++;
        }

        //注意这个地方的索引应该是长度-1
        for (int i = wormBody.length - 1;i > 0 ;i -- ){
            wormBody[i]= wormBody[i - 1];
        }
        wormBody[0] = newhead;

        return isEat;
    }

    public void paint(Graphics g){
        Image image;
        for (int i = 0;i <wormBody.length; i++){
            //绘制图案
            if (i == 0){
                image = new ImageIcon("picture/head.png").getImage();
            }else{
                image = new ImageIcon("picture/body.png").getImage();
            }
            g.drawImage(image,wormBody[i].getX() * Cell.DEFAULTSIZE,wormBody[i].getY()* Cell.DEFAULTSIZE,null);
        }
    }
}
