package ui;

import javax.swing.*;

public class RegisteJFrame extends JFrame {
    public RegisteJFrame() {
        this.setSize(488,500);
        //设置界面的标题
        this.setTitle("拼图注册界面");
        //设置界面置顶
        this.setAlwaysOnTop(true);//运行完之后点击其他地方不会消失
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置游戏的关闭模式（关掉界面，退出程序）
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }
}
