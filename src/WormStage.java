import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Timer;
import java.util.TimerTask;

//用于流程控制蛇以及食物的生成与运动
public class WormStage extends JPanel {
    private Worm worm;
    private Food foodSeed;//控制生成一堆食物的种子
    private Music backgroudmusic;
    private Music gameovermusic;
    private Music eatdmusic;
    private Timer timer;
    private  KeyControl KeyListener;
    private boolean gameStatus = false;


    public WormStage(){
        setPreferredSize(new Dimension(Window.DEFAULT_ROW * Cell.DEFAULTSIZE, Window.DEFAULT_COLUMN * Cell.DEFAULTSIZE));
        this.worm = new Worm();
        this.foodSeed = new Food();
        this.backgroudmusic = new Music("backgroud");
        this.gameovermusic = new Music("gameover");
        this.eatdmusic = new Music("eat");
        KeyListener = new KeyControl();
    }

    public KeyControl getKeyListener() {
        return KeyListener;
    }

    //设置两个事件之间的时间差距
    public long interval(int currentLength){
        return (long) (Worm.DEFAULT_SPEED * Math.pow(1.1,(-(double)currentLength)));
    }

    private  class Move extends TimerTask {
        //在运行的的过程中关注两点，1）是否over 2）是否正常爬行状态
        @Override
        public void run() {
            if (worm.isOver(worm.getCurrentDirection())){
                //游戏结束之后暂停音乐播放
                gameovermusic.playMusic();
                backgroudmusic.pauseMusic();
                JOptionPane.showMessageDialog(null, "游戏结束！" + "你的分数为：" + (worm.getCurrentLength() - Worm.DEFAULT_LENGTH) + "分");

                //取消timer计时器
                timer.cancel();
                gameStatus = false;
                worm = new Worm();
                foodSeed = new Food();
            } else if(worm.creep(worm.getCurrentDirection(),foodSeed)){
                //吃到食物之后，新建一个食物
                eatdmusic.playMusic();
                foodSeed.foods = foodSeed.randomFoods(Food.FOODNUMBER);
                //foodSeed= foodSeed.randomFood();
                //吃到食物之后删除之前的timer，同时新增下一阶段的timer
                timer.cancel();
                timer = new Timer();
                timer.scheduleAtFixedRate(new Move(),0,(long)interval(worm.getCurrentLength()));
                //吃到食物之后还得重新生成新的食物。

            }
            repaint();
        }
    }

    private class KeyControl extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_UP :
                    worm.changeDiretion(Worm.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    worm.changeDiretion(Worm.DOWN);
                    break;
                case KeyEvent.VK_LEFT :
                    worm.changeDiretion(Worm.LEFT);
                    break;
                case KeyEvent.VK_RIGHT :
                    worm.changeDiretion(Worm.RIGHT);
                    break;
                case KeyEvent.VK_SPACE:
                    if(gameStatus){
                        timer.cancel();
                        gameStatus = false;

                        //暂停音乐
                        backgroudmusic.pauseMusic();
                        break;
                    }
                    else{
                        //开始播放音乐
                        backgroudmusic.playMusic();
                        gameStatus = true;
                        timer = new Timer();
                        timer.scheduleAtFixedRate(new Move(),0,(long)interval(worm.getCurrentLength()));
                        break;
                    }
            }
        }
    }
    @Override
    public void paint(Graphics g){

        //绘画蛇
        if(worm.getCurrentLength() - Worm.DEFAULT_LENGTH <= 2) {
            g.setColor(Color.BLUE);
        }else if(worm.getCurrentLength() - Worm.DEFAULT_LENGTH <= 4){
            g.setColor(Color.GREEN);
        }
        else{
            g.setColor(Color.RED);
        }
        worm.paint(g);

        //绘画食物
        g.setColor(Color.RED);
        //在绘画的时候需要绘画食物种子以及产生的其他食物
        //食物的绘画过程中不能存在循环结构。必须得在自己的类中定义好相应的结构。
        //必须得使用foodSeed来构造foods的时候，将其初始化放在空参构造器中。
        //使用食物种子来调用绘画他
        foodSeed.paintFoods(g);
        /*
        for (int i = 0 ;i < foods.length ;i++){
            foods[i].paint(g);
        }
         */
        //尝试添加背景图片
        //Image image = new ImageIcon("picture/backgroud.png").getImage();
        //g.drawImage(image,0,0,null);

        /*
        //绘画整个版面
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Window.DEFAULT_ROW * Cell.DEFAULTSIZE,Window.DEFAULT_COLUMN * Cell.DEFAULTSIZE);

         */
    }
}
