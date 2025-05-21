package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//游戏主界面
public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    int[][] data=new int[4][4];
    int x=0;
    int y=0;
    int[][] win={ {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}};
    int step=0;
    //创建选项下面的条目对象
    JMenuItem replayJMenuItem = new JMenuItem("重新游戏");
    JMenuItem reLoginJMenuItem = new JMenuItem("重新登录");
    JMenuItem closeJMenuItem = new JMenuItem("关闭游戏");
    JMenu changeJMenuItem = new JMenu("更换图片");
    JMenuItem accountJMenuItem = new JMenuItem("微信");

    JMenuItem beauty = new JMenuItem("美女");
    JMenuItem Animal = new JMenuItem("动物");
    JMenuItem Sport = new JMenuItem("运动");

    String path="image\\animal\\animal3\\";
    public GameJFrame() {
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenuBar();
        //初始化数据（打乱）
        initData();
        //初始化图片
        initImage();

        //可视化窗口
        this.setVisible(true);
    }

    private void initData() {
        int[] arr={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random r = new Random();
        for(int i=0;i<arr.length;i++){
            int index=r.nextInt(arr.length);
            int temp=arr[i];
            arr[i]=arr[index];
            arr[index]=temp;
        }

        for(int i=0;i<arr.length;i++){
            data[i/4][i%4]=arr[i];
            if(arr[i]==0){
                x=i/4;
                y=i%4;
            }
        }
//        for(int i=0;i<4;i++){
//            for(int j=0;j<4;j++){
//                data[i][j]=arr[j+i*4];
//            }
//        }
    }

    private void initImage() {
        this.getContentPane().removeAll();

        if(victory()){
            JLabel winlabel=new JLabel(new ImageIcon("image\\win.png"));
            winlabel.setBounds(203,283,197,73);
            this.getContentPane().add(winlabel);
        }
        JLabel stepCount=new JLabel("步数："+step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);
     //先加载的图片在上方，后加载的图片在下方
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //创建一个JLable的对象（管理容器）
                JLabel jLabel = new JLabel(new ImageIcon(path+data[i][j]+".jpg"));
                //指定图片位置
                jLabel.setBounds(105*j+83, 105*i+134, 105,105);
                //给图片添加边框
                 //0表示让图片凸起来，1表示让图片凹下去
                jLabel.setBorder(new BevelBorder(1));
                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);

            }

        }
        //背景图片要后添加
        ImageIcon bg=new ImageIcon("image\\background.png");
        JLabel background=new JLabel(bg);
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);
        this.getContentPane().repaint();

    }

    private void initJMenuBar() {
        //初始化菜单
        //创建整个的菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上面的两个选项的对象（功能 关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        changeJMenuItem.add(beauty);
        changeJMenuItem.add(Animal);
        changeJMenuItem.add(Sport);
        //将每一个选项的条目添加到选项中
        functionJMenu.add(changeJMenuItem);
        functionJMenu.add(replayJMenuItem);
        functionJMenu.add(reLoginJMenuItem);
        functionJMenu.add(closeJMenuItem);
        aboutJMenu.add(accountJMenuItem);



        //给条目绑定事件
        replayJMenuItem.addActionListener(this);
        reLoginJMenuItem.addActionListener(this);
        closeJMenuItem.addActionListener(this);
        accountJMenuItem.addActionListener(this);
        changeJMenuItem.addActionListener(this);

        beauty.addActionListener(this);
        Sport.addActionListener(this);
        Animal.addActionListener(this);

        //将菜单里面的两个选项添加到菜单当中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);


        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面宽高
        this.setSize(603,680);
        //设置界面的标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);//运行完之后点击其他地方不会消失
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置游戏的关闭模式（关掉界面，退出程序）
        this.setDefaultCloseOperation(3);
        //取消默认的布局方式接口
        this.setLayout(null);

        this.addKeyListener(this);



    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==65){
            this.getContentPane().removeAll();
            JLabel all=new JLabel(new ImageIcon(path+"all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
        }
        //背景图片要后添加
        ImageIcon bg=new ImageIcon("image\\background.png");
        JLabel background=new JLabel(bg);
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);
        this.getContentPane().repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //对上下左右进行判断
        //左37，右39，上38，下40
        if(victory()){
            return;
        }
        int code=e.getKeyCode();
        int temp=0;
        if(code==39){
            if(y==3){
                return;
            }
            data[x][y]=data[x][y+1];
            data[x][y+1]=0;
            y++;
            step++;
            initImage();
        }else if(code==37){
            if(y==0){
                return;
            }
            data[x][y]=data[x][y-1];
            data[x][y-1]=0;
            y--;
            step++;
            initImage();
        }else if(code==40){
            if(x==3){
                return;
            }
            data[x][y]=data[x+1][y];
            data[x+1][y]=0;
            x++;
            step++;
            initImage();
        }else if(code==38){
            if(x==0){
                return;
            }
            data[x][y]=data[x-1][y];
            data[x-1][y]=0;
            x--;
            step++;
            initImage();
        }else if(code==65){
            initImage();

        }else if(code==87){
            data=new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }

    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if(data[i][j]!=win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        Random r=new Random();
        if(source==replayJMenuItem){
            step=0;
            initData();
            initImage();

        }else if(source==reLoginJMenuItem){
            this.setVisible(false);
            new LoginJFrame();
        }else if(source==closeJMenuItem){
            System.exit(0);
        }else if(source==accountJMenuItem){
            JDialog jd=new JDialog();
            JLabel jLabel=new JLabel(new ImageIcon("image\\about.png"));
            jLabel.setBounds(0,0,258,258);
            jd.getContentPane().add(jLabel);
            jd.setSize(344,344);
            jd.setAlwaysOnTop(true);
            jd.setLocationRelativeTo(null);
            jd.setModal(true);
            jd.setVisible(true);
        }else if(source==beauty){
            step=0;
            initData();
            int i=r.nextInt(13);
            path="image\\girl\\girl"+i+"\\";
            initImage();
        }else if(source==Animal){
            step=0;
            initData();
            int i=r.nextInt(8);
            path="image\\animal\\animal"+i+"\\";
            initImage();
        }else if(source==Sport){


            step=0;
            initData();
            int i=r.nextInt(10);
            path="image\\sport\\sport"+i+"\\";
            initImage();
        }
    }
}
