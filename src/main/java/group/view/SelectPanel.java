package group.view;

import group.database.pojo.User;
import group.database.util.UserMethod;
import group.map.MapData;
import group.database.control.GameInstance;
import group.map.Obstacle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;

import static group.Application.playerName;
import static group.database.util.UserMethod.getAllUsersAccordingToType;

public class SelectPanel extends JPanel {
    private final MainFrame mainFrame;

    public static boolean allIsSelect = false;
    public boolean difficultyIsSelect = false;
    public static boolean isTheSameType=true;
    public static String mapSelectName;

    JRadioButton rb1;
    JRadioButton rb2;
    JRadioButton rb3;
    JRadioButton rb4;

    ButtonGroup diffcultiesSelect;
    JButton mapSelect1;
    JButton mapSelect2;
    JButton mapSelect3;

    public SelectPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(new GridBagLayout());
        JLabel l1 = new JLabel("请选择难度");
        l1.setFont(new Font("幼圆", Font.PLAIN, 20));
        setGridBagConstraints(l1,0,0,70,80);

        rb1 = new JRadioButton("简单模式");
        rb2 = new JRadioButton("普通模式");
        rb3 = new JRadioButton("困难模式");
        rb4 = new JRadioButton("地狱模式");
        diffcultiesSelect = new ButtonGroup();
        diffcultiesSelect.add(rb1);
        diffcultiesSelect.add(rb2);
        diffcultiesSelect.add(rb3);
        diffcultiesSelect.add(rb4);

        setGridBagConstraints(rb1,2,0,70,80);
        setGridBagConstraints(rb2,4,0,70,80);
        setGridBagConstraints(rb3,6,0,70,80);
        setGridBagConstraints(rb4,8,0,70,80);

        rb1.addActionListener(new difficultySelectHandler());
        rb2.addActionListener(new difficultySelectHandler());
        rb3.addActionListener(new difficultySelectHandler());
        rb4.addActionListener(new difficultySelectHandler());




        JLabel l2 = new JLabel("请选择地图");
        l2.setFont(new Font("幼圆", Font.PLAIN, 20));
        setGridBagConstraints(l2,0,2,70,80);

        Image map1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/map1.png"));
        Image map2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/map2.png"));
        Image map3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/map3.png"));
        ImageIcon imageIcon1 = new ImageIcon(map1);
        ImageIcon imageIcon2 = new ImageIcon(map2);
        ImageIcon imageIcon3 = new ImageIcon(map3);
        mapSelect1 = new JButton("map_1",imageIcon1);
        mapSelect2 = new JButton("map_2",imageIcon2);
        mapSelect3 = new JButton("map_3",imageIcon3);
        mapSelect1.setToolTipText("地图1");
        mapSelect2.setToolTipText("地图2");
        mapSelect3.setToolTipText("地图3");

        mapSelect1.addActionListener(new mapSelectHandler());
        mapSelect2.addActionListener(new mapSelectHandler());
        mapSelect3.addActionListener(new mapSelectHandler());

        setGridBagConstraints(mapSelect1,2,2,70,80);
        setGridBagConstraints(mapSelect2,4,2,70,80);
        setGridBagConstraints(mapSelect3,6,2,70,80);
    }


    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
    }

    public void setGridBagConstraints(Component component,int Gx,int Gy,int weightX,int weightY ){
        GridBagConstraints gc=new GridBagConstraints();
        gc.gridx=Gx;
        gc.gridy=Gy;
        gc.weightx=weightX;
        gc.weighty=weightY;
        gc.gridheight=2;
        gc.insets = new Insets(0, 5, 0,0);
        gc.fill=GridBagConstraints.HORIZONTAL;
        this.add(component,gc);
    }

    class mapSelectHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {


            if (difficultyIsSelect) {
                //再次判断玩家类型，是否add
                playerJudgement();
                //根据难度修改
                modifyAccordingTodifficulty();
                mapSelectName=actionEvent.getActionCommand();
                allIsSelect = true;
                diffcultiesSelect.clearSelection();
            }
        }
    }

    class difficultySelectHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Enumeration<AbstractButton> radioBtns = diffcultiesSelect.getElements();
            while (radioBtns.hasMoreElements()) {
                AbstractButton btn = radioBtns.nextElement();
                if (btn.isSelected()) {
                    switch (btn.getText()){
                        case "简单模式": GameInstance.difficulty = "简单"; break;
                        case "普通模式": GameInstance.difficulty = "普通";break;
                        case "困难模式": GameInstance.difficulty = "困难";break;
                        case "地狱模式": GameInstance.difficulty = "地狱";break;
                    }
                    difficultyIsSelect = true;
                    break;
                }

            }
        }


    }

    //对选择难度后的玩家重新判断
    public void playerJudgement() {
        if (!WelMenuPanel.isLocal) {
            if (WelMenuPanel.isNew) {
                UserMethod.addUser(new User(playerName, GameInstance.difficulty));
            }
            // 判断了是老玩家之后，还要判断他选定的难度里面是否有他
            else {
                for (User user : getAllUsersAccordingToType(GameInstance.difficulty)) {
                    if (!user.getUsername().equals(playerName)) {
                        isTheSameType = false;
                    } else {
                        isTheSameType = true;
                        break;
                    }
                }
                if (!isTheSameType) {
                    UserMethod.addUser(new User(playerName, GameInstance.difficulty));
                }
            }
        }
    }

    public void modifyAccordingTodifficulty(){
        switch (GameInstance.difficulty){
            case "简单":  GameInstance.timeOfGenerateTank=25;  GameInstance.timeOfRefreshBuff=15;break;
            case "普通": GameInstance.timeOfGenerateTank=20; GameInstance.timeOfRefreshBuff=15;break;
            case "困难": GameInstance.timeOfGenerateTank=15; GameInstance.timeOfRefreshBuff=10;break;
            case "地狱": GameInstance.timeOfGenerateTank=10; GameInstance.timeOfRefreshBuff=6; break;
        }
    }

    public static Map<Obstacle.ObstacleKind, ArrayList<int[]>> returnSelectedMap(){

        // Application 从这里拿地图
        switch (mapSelectName){
            case "map_1":  return MapData.map_1;
            case "map_2":  return MapData.map_2;
            case "map_3":  return MapData.map_3;
            default:
                System.out.println("return Map error!");
        }
        return MapData.map_1;
    }


}
