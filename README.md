
# 🧩 Java Swing拼图小游戏：可切换主题的单机休闲益智项目  

## 项目全景概览  
本项目是基于 **Java Swing** 的经典单机拼图游戏，深度融合 GUI 开发、事件驱动编程与基础算法逻辑。游戏以 15 宫格拼图为核心玩法，拓展了多主题切换、用户系统（预留）、步数统计等功能，代码结构清晰易读，适合新手学习 Java 桌面应用开发。  


### 🎮 核心功能详解  
#### 一、多模态交互界面  
1. **登录与注册模块**  
   - **登录界面（`LoginJFrame`）**：  
     - 包含用户名、密码、验证码输入框，支持键盘快捷操作与鼠标点击交互。  
     - 验证码功能由 `CodeUtil` 生成随机字符+数字组合，点击验证码可刷新🔄。  
     - 集成弹框提示（`showJDialog`），实时反馈输入错误（如密码为空、验证码错误）。  
   - **注册界面（`RegisteJFrame`）**：  
     - 预留用户注册功能，可扩展数据库存储（当前仅界面原型）。  

2. **游戏主界面（`GameJFrame`）**  
   - **动态拼图区域**：  
     - 4x4 网格布局，每个拼图块对应独立图片（存储于 `image/` 目录），支持浮雕边框（`BevelBorder`）增强立体感。  
     - 背景图 `background.png` 与胜利提示图 `win.png` 分离渲染，确保视觉层次清晰。  
   - **交互控件**：  
     - **菜单栏**：  
       - **功能**：重新游戏（重置步数+随机打乱）、重新登录（返回登录界面）、关闭游戏。  
       - **主题切换**：支持「美女」「动物」「运动」三大主题，每个主题含多个子分类（如 `animal1`-`animal8`），点击菜单随机加载对应目录图片。  
     - **键盘操作**：  
       - `←↑→↓` 键控制拼图块移动，空白块（数值 `0`）为基准点，移动时自动更新步数统计。
       - `W`实现一键通关，长按`A`显示最终拼图效果。


#### 二、核心算法与数据逻辑  
1. **拼图初始化与打乱**  
   - **随机排列算法**：  
     ```java  
     private void initData() {  
         int[] arr = Arrays.stream(new int[16]).boxed().toArray(Integer[]::new);  
         new Random().shuffle(Arrays.asList(arr)); // 高效洗牌算法  
         for (int i = 0; i < 16; i++) {  
             data[i/4][i%4] = arr[i];  
             if (arr[i] == 0) x = i/4; y = i/4; // 记录空白块坐标  
         }  
     }  
     ```  
     - 基于 `Random.shuffle` 实现高效随机化，确保每次开局状态唯一。  

2. **胜利判定与界面渲染**  
   - **状态检查**：  
     ```java
     public boolean victory(){
       for (int i = 0; i < data.length; i++) {
               for (int j = 0; j < data.length; j++) {
                   if(data[i][j]!=win[i][j]){ //二位数组比较
                       return false;
                   }
               }
        }
        return true;
     }
     ```  
   - **增量渲染优化**：  
     ```java  
     private void initImage() {  
         getContentPane().removeAll();  
         add(stepCount); // 先绘制步数，避免覆盖  
         // 拼图块按坐标布局，确保移动时仅重绘相关区域  
         for (int i=0; i<4; i++) {  
             for (int j=0; j<4; j++) {  
                 JLabel imgLabel = new JLabel(new ImageIcon(path + data[i][j] + ".jpg"));  
                 imgLabel.setBounds(105*j+83, 105*i+134, 105, 105);  
                 add(imgLabel);  
             }  
         }  
         add(background); // 背景图最后绘制，作为底层  
         repaint();  
     }  
     ```  


#### 三、主题切换与资源管理  
- **动态资源加载**：  
  ```java  
  // 主题切换逻辑（以动物主题为例）  
  if (source == Animal) {  
      step = 0;  
      int i = new Random().nextInt(8); // 随机加载 animal1-animal8 子目录  
      path = "image\\animal\\animal" + (i+1) + "\\";  
      initData();  
      initImage();  
  }  
  ```  
  - 支持自定义主题：在 `image/` 下新建目录（如 `nature/`），按 `0.jpg`-`15.jpg` 命名图片（`0.jpg` 为空白块背景），修改 `path` 路径即可扩展。  


### 📁 代码架构与最佳实践  
```
src/  
├─ APP.java                # 程序入口，可切换启动界面（登录/游戏）  
├─ domain/  
│  ├─ CodeUtil.java        # 验证码生成工具（随机字符+数字混合）  
│  └─ User.java            # 用户实体类（存储用户名/密码）  
└─ ui/  
   ├─ GameJFrame.java       # 游戏核心逻辑（拼图算法/界面渲染）  
   ├─ LoginJFrame.java      # 登录界面（含验证码交互）  
   ├─ RegisteJFrame.java    # 注册界面（预留扩展）  
   └─ MyActionListener.java # 事件监听器模板（可扩展按钮行为）  
image/  
├─ animal/                 # 动物主题图片（8个子主题）  
├─ beauty/                 # 美女主题图片（13个子主题）  
├─ sport/                  # 运动主题图片（10个子主题）  
├─ background.png          # 界面背景图（508x560像素）  
└─ win.png                 # 胜利提示图（197x73像素）  
```  

**设计亮点**：  
- **MVC雏形**：界面逻辑（`ui/`）与数据逻辑（`domain/`）分离，便于维护。  
- **事件驱动架构**：通过 `MouseListener` 与 `KeyListener` 解耦用户输入与业务逻辑。  
- **资源热更新**：主题图片可动态替换，无需修改代码即可扩展内容。  


### 🚀 运行与扩展指南  
#### 一、环境配置  
- **依赖要求**：  
  - JDK 8+（推荐 JDK 11+，需与编译版本一致）  
  - IDE：IDEA/Eclipse（推荐 IDEA，支持 Swing 可视化布局）  
- **启动步骤**：  
  1. 克隆项目：  
     ```bash  
     git clone https://github.com/your-username/jigsaw-game-swing.git  
     ```  
  2. 导入 IDE，确保 `image/` 目录结构完整。  
  3. 运行 `APP.java`，默认启动示登录界面。  

#### 二、自定义与优化  
1. **图片扩展**：  
   - 在 `image/` 下创建新主题目录（如 `cars/`），按规则命名图片。  
   - 修改 `GameJFrame` 中 `path` 变量，新增主题菜单项（参考 `beauty` 逻辑）。  

2. **功能增强建议**：  
   - **用户系统**：  
     - 使用文件/数据库存储用户数据（如 SQLite）。  
   - **进阶玩法**：  
     - 增加计时功能（`Timer` 类），记录最短完成时间。  
     - 支持 `3x3`/`5x5` 难度选择，动态调整网格尺寸与图片数量。  
   - **性能优化**：  
     - 使用 `BufferedImage` 优化图片加载性能。  
     - 引入 `SwingWorker` 实现异步打乱拼图，避免界面卡顿。  

## 📌 项目价值与学习路径  
- **新手入门**：适合学习 Swing 基础组件（`JFrame`/`JLabel`/`JMenu`）、事件监听机制（`ActionListener`/`KeyListener`）。  
- **算法实践**：理解二维数组操作、随机化算法（洗牌排序）、状态比较逻辑。  
- **工程思维**：掌握资源管理（图片路径规划）、界面布局（绝对定位 vs 布局管理器）、异常处理（空指针/输入校验）。  

欢迎 Star ⭐ 本项目，共同打造更完善的 Java Swing 学习案例！ 🎉
