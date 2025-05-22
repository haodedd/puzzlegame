package ui;

import domain.CodeUtil;
import domain.User;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegisteJFrame extends JFrame implements MouseListener {
    JButton reset = new JButton();
    JButton register = new JButton();

    JTextField username = new JTextField();

    JPasswordField password1 = new JPasswordField();
    JPasswordField password2 = new JPasswordField();
    public RegisteJFrame() {
        initJFrame();;
        initView();
    }
    public void initView() {
        //1. 添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("image\\register\\注册用户名.png"));
        usernameText.setBounds(100, 135, 79, 17);
        this.getContentPane().add(usernameText);

        //2.添加用户名输入框

        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        //3.添加密码文字
        JLabel passwordText1 = new JLabel(new ImageIcon("image\\register\\注册密码.png"));
        passwordText1.setBounds(100, 195, 64, 16);
        this.getContentPane().add(passwordText1);

        //4.密码输入框
        password1.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password1);

        //5.添加再次输入密码文字
        JLabel passwordText2 = new JLabel(new ImageIcon("image\\register\\注册密码.png"));
        passwordText2.setBounds(100, 256, 64, 16);
        this.getContentPane().add(passwordText2);

        //4.再次输入密码输入框
        password2.setBounds(195, 256, 200, 30);
        this.getContentPane().add(password2);



        //5.添加注册按钮
        register.setBounds(123, 310, 128, 47);
        register.setIcon(new ImageIcon("image\\register\\注册按钮.png"));
        //去除按钮的边框
        register.setBorderPainted(false);
        //去除按钮的背景
        register.setContentAreaFilled(false);
        //给登录按钮绑定鼠标事件
        register.addMouseListener(this);
        this.getContentPane().add(register);

        //6.添加重置按钮
        reset.setBounds(256, 310, 128, 47);
        reset.setIcon(new ImageIcon("image\\register\\重置按钮.png"));
        //去除按钮的边框
        reset.setBorderPainted(false);
        //去除按钮的背景
        reset.setContentAreaFilled(false);
        //给注册按钮绑定鼠标事件
        reset.addMouseListener(this);
        this.getContentPane().add(reset);


        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
        background.setBounds(0, 0, 470, 490);
        this.getContentPane().add(background);

    }
    private void initJFrame() {
        this.setSize(500,450);
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


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == register) {
            String name = username.getText();
            String pd1=password1.getText();
            String pwd2=password2.getText();
            if(name.length()==0||pd1.length()==0||pwd2.length()==0) {
                System.out.println("用户名或密码为空");
                showJDialog("用户名或密码为空");
            }else{
                if(!pd1.equals(pwd2)) {
                    System.out.println("前后密码不一致");
                    showJDialog("前后密码不一致");
                }else{
                    User user = new User(name,pd1);
                    LoginJFrame.allUsers.add(user);
                    System.out.println("注册成功");
                    this.setVisible(false);
                    new LoginJFrame();
                }
            }
        }else if(e.getSource() == reset) {
            username.setText("");
            password1.setText("");
            password2.setText("");
        }

    }
    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }
    //按下不松
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image\\register\\注册按下.png"));
        } else if (e.getSource() == reset) {
            reset.setIcon(new ImageIcon("image\\register\\重置按下.png"));
        }
    }


    //松开按钮
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == reset) {
            reset.setIcon(new ImageIcon("image\\register\\重置按钮.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image\\register\\注册按钮.png"));
        }
    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
