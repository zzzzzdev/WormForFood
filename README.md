# 一、游戏简介

### 游戏名称：贪吃蛇



本游戏主要使用的是Java GUI的相关类库，包括JPanel、JFrame、JOptionPane等窗口类库、Graphics绘图类、AudioSystem语音类、键盘事件监听KeyAdapter以及定时器Timer。



本游戏的设计难点在于

1）无按键控制时蛇体自动向前爬行算法

2）通过按键控制蛇体改变方向算法

3）吃到食物之后的游戏反馈（包括新食物的生成、蛇体长度改变、音效控制等）

4）判断游戏结束的算法。

详细开发过程及相关功能如下：

# 二、游戏测试

## 游戏版本：1.1（Beta）

##### 1、食物的随机生成测试

1）偶尔会出现生成的食物无法显示在游戏界面中。

2）其他随机生成测试完全通过。



2、蛇碰壁结束游戏测试

1）右端面：并不是一碰到边界就结束，而是在往前一个坐标才结束的。

2）左端面：正常

3）下端面：并不是一碰到边界就结束，让整个蛇都消失之后才结束的。

4）上端面：正常



3、蛇碰撞自身结束游戏测试

正常

4、蛇正常爬行测试

正常

5、吃到食物之后，食物随机生成，蛇继续以更快速度爬行测试

正常

6、蛇上下左右按键控制测试，游戏开始结束空格按键测试。

正常



## 游戏版本：1.2（Beta）

#修复蛇碰壁结束游戏的判定

#修复随机生成食物不出现在界面的BUG



## 游戏版本：1.3（Beta）

#新增游戏统计分数功能。

#新增随着游戏进行蛇体颜色变化功能。

#新增多个食物共享功能。



## 游戏版本：1.4（Final）

#取消随着时间蛇体颜色变化功能。

#新增游戏背景音乐，并且随着游戏的开始与暂停，音乐同步运行。

#新增食物、蛇体图片素材。