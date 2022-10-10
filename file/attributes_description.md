## 全局变量

boolean gameRun 
-- 游戏进行的布尔值

MyTank myTank 
-- 玩家坦克对象

Vector<EnemyTank> enemyTanksList
-- 敌方坦克对象列表

Map<Obstacle.ObstacleKind, Vector<Obstacle>> obstacleMap
-- 障碍物图

GamePanel gamePanel
-- 游戏主面板

MainFrame mainFrame
-- 游戏主面板框及主程序


## 常量说明

final int REFRESH_TIME 
-- 统一线程sleep的时间为25毫秒 (40FPS)

final int OBJECT_SIZE
-- 统一物品尺寸为40px

final int WINDOW_LENGTH
-- 统一窗口长度为1080px

final int WINDOW_WIDTH
-- 统一窗口宽度为720px