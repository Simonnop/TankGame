package group.su.view;

import group.Mybatis.pojo.User;
import group.Mybatis.util.UserMethod;
import group.su.control.GameInstance;
import group.su.control.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import static group.Application.playerName;
import static group.Mybatis.util.UserMethod.getAllUsers;
import static group.Mybatis.util.UserMethod.getAllUsersAccordingToType;

public class SelectPanel extends JPanel {
    private final MainFrame mainFrame;

    public static String difficulty;

    public static boolean allIsSelect = false;
    public boolean difficultyIsSelect = false;
    public static boolean isTheSameType=true;

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
        JLabel l1 = new JLabel("请选择挑战难度");
        l1.setFont(new Font("幼圆", Font.PLAIN, 20));
        setGridBagConstraints(l1,0,0,80,80);

        rb1 = new JRadioButton("简单模式");
        rb2 = new JRadioButton("普通模式");
        rb3 = new JRadioButton("困难模式");
        rb4 = new JRadioButton("地狱模式");
        diffcultiesSelect = new ButtonGroup();
        diffcultiesSelect.add(rb1);
        diffcultiesSelect.add(rb2);
        diffcultiesSelect.add(rb3);
        diffcultiesSelect.add(rb4);

        setGridBagConstraints(rb1,2,0,80,80);
        setGridBagConstraints(rb2,4,0,80,80);
        setGridBagConstraints(rb3,6,0,80,80);
        setGridBagConstraints(rb4,8,0,80,80);

        rb1.addActionListener(new difficultySelectHandler());
        rb2.addActionListener(new difficultySelectHandler());
        rb3.addActionListener(new difficultySelectHandler());
        rb4.addActionListener(new difficultySelectHandler());




        JLabel l2 = new JLabel("请选择挑战地图");
        l2.setFont(new Font("幼圆", Font.PLAIN, 20));
        setGridBagConstraints(l2,0,2,80,80);

        Image map1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/map1.png"));
        Image map2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/map2.png"));
        Image map3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/map3.png"));
        ImageIcon imageIcon1 = new ImageIcon(map1);
        ImageIcon imageIcon2 = new ImageIcon(map2);
        ImageIcon imageIcon3 = new ImageIcon(map3);
        mapSelect1 = new JButton(imageIcon1);
        mapSelect2 = new JButton(imageIcon2);
        mapSelect3 = new JButton(imageIcon3);
        mapSelect1.setToolTipText("地图1");
        mapSelect2.setToolTipText("地图2");
        mapSelect3.setToolTipText("地图3");

        mapSelect1.addActionListener(new mapSelectHandler());
        mapSelect2.addActionListener(new mapSelectHandler());
        mapSelect3.addActionListener(new mapSelectHandler());

        setGridBagConstraints(mapSelect1,2,2,80,80);
        setGridBagConstraints(mapSelect2,4,2,80,80);
        setGridBagConstraints(mapSelect3,6,2,80,80);


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
        gc.insets = new Insets(0, 10, 0,0);
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
                //根据选择地图来加载地图，未写
                loadMap();
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
                        case "简单模式": difficulty = "简单"; break;
                        case "普通模式": difficulty = "普通";break;
                        case "困难模式": difficulty = "困难";break;
                        case "地狱模式": difficulty = "地狱";break;
                    }
                    difficultyIsSelect = true;
                    break;
                }

            }
        }


    }

    //对选择难度后的玩家重新判断
    public void playerJudgement(){
        if(WelMenuPanel.isNew){
            UserMethod.addUser(new User(playerName,difficulty));
        }
        // 判断了是老玩家之后，还要判断他选定的难度里面是否有他
        else {
            for (User user : getAllUsersAccordingToType(difficulty)) {
                if (!user.getUsername().equals(playerName)) {
                    isTheSameType=false;
                }else {
                    isTheSameType=true;
                    break;
                }
            }
            if(!isTheSameType){
                UserMethod.addUser(new User(playerName,difficulty));
            }
        }
    }

    public void modifyAccordingTodifficulty(){
        switch (difficulty){
            case "简单":  GameInstance.timeOfGenerateTank=25;  GameInstance.timeOfRefreshBuff=15;break;
            case "普通": GameInstance.timeOfGenerateTank=20; GameInstance.timeOfRefreshBuff=15;break;
            case "困难": GameInstance.timeOfGenerateTank=15; GameInstance.timeOfRefreshBuff=10;break;
            case "地狱": GameInstance.timeOfGenerateTank=10; GameInstance.timeOfRefreshBuff=6; break;
        }
    }
    public void loadMap(){

    }


}
