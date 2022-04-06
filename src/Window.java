import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;

/*
界面以及游戏主进入口
 */
public class Window extends JFrame{
    private WormStage ws;
    private JLabel label;
    public static final int DEFAULT_ROW = 50;
    public static final int DEFAULT_COLUMN = 50;

    public Window(){
        ws = new WormStage();
        label = new JLabel("请按空格以开始或暂停游戏");

        //设置窗口的标题
        setTitle("非常开心的贪吃蛇游戏");
        //设置窗口的菜单栏

        /*
        setMenuBar("开始");
         */
        //设置窗口是否可以变换大小
        setResizable(true);
        //设置窗口的大小
        setSize(new Dimension(DEFAULT_ROW * Cell.DEFAULTSIZE,DEFAULT_COLUMN * Cell.DEFAULTSIZE));
        //设置窗口的居中状态
        setLocationRelativeTo(null);
        //设置布局管理器
        //必须得设置布局管理器，否则不能进行相应的窗口展示
        setLayout(new FlowLayout());
        //设置默认的关闭操作，窗口提供了4个默认的关闭操作。
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //必须要将ws，和标签对象add进入标签里面。否则是不会出现在窗口中的。
        add(ws);
        add(label);
        //监听贪吃蛇的活动
        addKeyListener(ws.getKeyListener());
        //设置窗口为可见状态
        setVisible(true);
    }

    /*
    @Test
    public void setMenuBar_beta(String str) {
        Menu menu = new Menu(str);
        System.out.println(menu.toString());
        MenuBar menubar = new MenuBar();
        menubar.add(menu);
    }

     */

    public static void main(String[] args) {
        //添加背音乐
        new Window();
    }
}
