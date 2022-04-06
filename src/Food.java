//食物类

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Food {
    private int x;
    private int y;
    public Food[] foods;
    public static int score = 0;
    public static final int FOODNUMBER = 4;


    public  Food(int x,int y){
        this.x = x;
        this.y = y;
    }
    public Food(){
        this.x = Window.DEFAULT_COLUMN / 2;
        this.y = Window.DEFAULT_ROW / 2;

        //必须得先声明再进行赋值
        foods = new Food[FOODNUMBER];
        for (int i= 0;i < FOODNUMBER ;i++){
            foods[i] = new Food(i * 10 + 10,i * 10 + 10);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    //随机生成一堆食物。
    public Food[] randomFoods(int foodNumber){
        Random random = new Random();
        Food[] foods = new Food[foodNumber];
        for (int i = 0;i < foods.length ; i++){
            int new_x = random.nextInt(Window.DEFAULT_COLUMN);
            int new_y =  random.nextInt(Window.DEFAULT_ROW);
            foods[i] = new Food(new_x,new_y);
        }
        return foods;
    }

    public void paintFoods(Graphics g){
        for (int i = 0 ;i < foods.length ;i++){
            Image image = new ImageIcon("picture/food.png").getImage();
            g.drawImage(image,foods[i].getX() * Cell.DEFAULTSIZE,foods[i].getY() * Cell.DEFAULTSIZE,null);
            // g.fillRect(foods[i].getX() * Cell.DEFAULTSIZE,foods[i].getY() * Cell.DEFAULTSIZE,Cell.DEFAULTSIZE,Cell.DEFAULTSIZE);
        }
    }
}
