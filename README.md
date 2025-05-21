
# 🧩 Java Swing拼图小游戏：可切换主题的单机休闲益智项目  


## 项目简介  
本项目是一个基于 **Java Swing** 的单机拼图小游戏，实现了经典的15宫格拼图逻辑，支持多界面交互、主题切换、步数统计及可视化反馈。代码结构清晰，适合新手学习 GUI 开发、事件驱动编程及基础算法逻辑。


### 🌟 核心功能  
1. **多场景界面系统**  
   - **登录界面**（`LoginJFrame`）：基础的用户入口界面，支持界面居中、置顶显示，可扩展账号密码功能（当前暂未实现具体验证逻辑）。  
   - **注册界面**（`RegisteJFrame`）：预留用户注册功能界面，可后续扩展用户系统。  
   - **游戏主界面**（`GameJFrame`）：核心交互界面，包含拼图逻辑、菜单功能及胜利判定。  

2. **沉浸式游戏体验**  
   - **随机打乱算法**：通过 `Random` 生成随机排列，确保每次开局拼图顺序不同，增加可玩性。  
   - **键盘操作**：支持 **←↑→↓** 键移动拼图块，空白块（数值0）为移动基准，符合直觉。  
   - **实时步数统计**：界面顶部显示移动步数，记录玩家操作效率。  
   - **胜利判定**：当拼图顺序与目标数组 `win` 完全一致时，显示胜利图片（`win.png`），并终止操作响应。  

3. **丰富的交互功能**  
   - **菜单系统**：  
     - **功能菜单**：包含 **重新游戏**（重置步数+打乱拼图）、**重新登录**（返回登录界面）、**关闭游戏**（退出程序）。  
     - **更换图片**：支持 **美女**、**动物**、**运动** 三大主题，每个主题包含多个子分类（如 `animal1`-`animal8`），随机切换图片资源路径。  
   - **图片可视化**：  
     - 每个拼图块对应一张独立图片（存储于 `image/` 目录），支持自定义图片资源（需按命名规则添加）。  
     - 背景图片固定为 `background.png`，拼图块带浮雕边框（`BevelBorder`）提升立体感。  


### 📁 代码结构与技术细节  
```plaintext
ui/
├─ GameJFrame.java       # 游戏主界面，核心逻辑实现
├─ LoginJFrame.java      # 登录界面
├─ RegisteJFrame.java    # 注册界面
├─ MyActionListener.java # 事件监听器（示例空实现，可扩展）
└─ APP.java              # 程序入口
image/                   # 资源目录
├─ animal/               # 动物主题图片
├─ beauty/               # 美女主题图片
├─ sport/                # 运动主题图片
├─ background.png        # 背景图
└─ win.png               # 胜利提示图
```  

#### 关键实现逻辑  
1. **拼图数据初始化**  
   ```java
   private void initData() {
       int[] arr = {0, 1, 2, ..., 15}; // 0代表空白块
       Random r = new Random();
       // 洗牌算法打乱数组
       for (int i = 0; i < arr.length; i++) {
           int index = r.nextInt(arr.length);
           swap(arr, i, index);
       }
       // 填充二维数组并记录空白块位置
       for (int i = 0; i < arr.length; i++) {
           data[i/4][i%4] = arr[i];
           if (arr[i] == 0) x = i/4; y = i%4; // 空白块坐标
       }
   }
   ```  

2. **移动逻辑与胜利判定**  
   - **键盘事件处理**（`keyReleased`）：监听方向键，通过二维数组交换实现拼图块移动，并更新界面。  
   - **胜利判定**（`victory()`）：遍历数组对比当前状态与目标数组 `win` 是否一致。  

3. **界面渲染优化**  
   ```java
   private void initImage() {
       getContentPane().removeAll(); // 清空容器
       // 先绘制步数、拼图块，再绘制背景图（确保背景在底层）
       add(stepCount);
       for (int i=0; i<4; i++) {
           for (int j=0; j<4; j++) {
               JLabel imgLabel = new JLabel(new ImageIcon(path + data[i][j] + ".jpg"));
               imgLabel.setBounds(105*j+83, 105*i+134, 105, 105);
               imgLabel.setBorder(new BevelBorder(1)); // 浮雕边框
               add(imgLabel);
           }
       }
       add(background); // 背景图
       repaint(); // 重绘界面
   }
   ```  


### 🛠️ 环境要求与运行指南  
- **依赖环境**：  
  - JDK 8+（需配置 `JAVA_HOME`）  
  - 开发工具：IDEA/Eclipse 或命令行编译  

- **运行步骤**：  
  1. 克隆项目：  
     ```bash
     git clone https://github.com/your-username/jigsaw-game-swing.git
     ```  
  2. 导入项目到 IDE，确保 `image/` 目录资源完整。  
  3. 运行 `APP.java`，默认启动游戏主界面（可修改 `main` 方法切换启动界面）。  

- **自定义图片**：  
  - 在 `image/` 下新建主题目录（如 `nature/`），按 `0.jpg`、`1.jpg`、...、`15.jpg` 命名图片（`0.jpg` 为空白块背景图）。  
  - 修改 `GameJFrame` 中 `path` 变量指向新主题路径，即可扩展图片资源。  


### 🚀 扩展与优化建议  
1. **功能增强方向**  
   - 添加用户系统：在 `LoginJFrame` 和 `RegisteJFrame` 中实现账号密码存储（如使用文件或数据库）。  
   - 计时功能：增加倒计时，记录玩家完成时间。  
   - 难度选择：支持 3x3、5x5 等更多拼图尺寸。  

2. **代码优化点**  
   - 将图片路径配置外置（如使用 `properties` 文件），避免硬编码。  
   - 提取公共组件（如带边框的 JLabel）为工具类，减少代码冗余。  
   - 使用 MVC 模式重构逻辑层与视图层，提升可维护性。  

3. **技术学习点**  
   - Swing 界面布局（`null` 布局 vs `LayoutManager`）  
   - 事件监听机制（`KeyListener`、`ActionListener`）  
   - 二维数组操作与算法基础（洗牌算法、状态判定）  


### 📜 开源协议  
本项目采用 **MIT 开源协议**，允许自由修改、分发，但需保留原作者声明。欢迎 star、fork 及提交 PR 贡献代码！  

如果遇到问题或有新功能建议，可通过 Issues 反馈，期待与你共同完善这个有趣的拼图小游戏！ 😊
