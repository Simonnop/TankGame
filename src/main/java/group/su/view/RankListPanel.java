package group.su.view;

import group.Mybatis.pojo.User;
import group.Mybatis.util.UserMethod;
import lombok.ToString;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import static group.Application.VIEW_CONTROL;
import static group.Application.playerName;

public class RankListPanel extends JPanel {

    private final MainFrame mainFrame;
    JButton backButton;
    JTable RankListTable;
     public  RankListPanel (MainFrame mainFrame){
         this.mainFrame=mainFrame;
         backButton = new JButton("返回");
         RankListTable=getRankListTable();
         JScrollPane Scroll=new JScrollPane(RankListTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
         Scroll.getViewport().setBackground(new Color(243,247,255));
         this.add(Scroll);

         backButton.setForeground(Color.WHITE);
         backButton.setFont(new Font("幼圆", Font.PLAIN, 20));
         backButton.setBackground(new Color(1, 85, 157));
         backButton.addActionListener(new backButtonHandler());
         this.add(backButton);

     }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        //g.setColor(Color.WHITE);
       // g.fillRect(0, 0, 800, 700);

        backButton.setSize(150, 40);
        backButton.requestFocus();

        RankListTable.setRowHeight(50);							//行高
//        RankListTable.getColumnModel().getColumn(0).setPreferredWidth(200);//；列宽
//        RankListTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        RankListTable.setFont(new Font("幼圆", Font.BOLD, 25));;//字体、颜色、大小
        RankListTable.setShowHorizontalLines(true);           //显示行的分割线
        RankListTable.setShowVerticalLines(true);	  		   //显示列的分割线
        RankListTable.setIntercellSpacing(new Dimension(0,1)); //单元格的间隔
        RankListTable.setGridColor(new Color(220,220,220));	  //分割线的颜色
        RankListTable.setColumnSelectionAllowed (false);	  //是否允许选中一整列
        RankListTable.setRowSelectionAllowed (false);//是否允许选中一整行
        setTableHeader();//表头渲染
    }

    public JTable getRankListTable(){

        Vector<User> allUsers = UserMethod.getAllUsers();
        String [] title ={"用户名","分数"};
        String [][] usersInfo=new String[allUsers.size()][2];

        for(int i=0;i<allUsers.size();i++){
            usersInfo[i][0]=allUsers.get(i).getUsername();
            usersInfo[i][1]=Integer.toString(allUsers.get(i).getScore());
        }

        JTable jTable=new JTable(usersInfo,title){
            public boolean isCellEditable(int row, int column)
            {
                return false;//禁止表格行列的编辑权限
            }
        };
        return  jTable;
    }

    //表头渲染
    public void setTableHeader(){
        JTableHeader header=RankListTable.getTableHeader();
        header.setPreferredSize(new Dimension(1, 50));//表头的高
        header.setBackground(new Color(101, 152, 234));//表头的背景颜色
        //接下来是表头的渲染
        JLabel tableName1= new JLabel("用户名", JLabel.CENTER);//表格标题，
        DefaultTableCellRenderer dtcr1 = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,int row, int column)
            {
                tableName1.setFont(new Font("幼圆", Font.PLAIN, 25));
                return tableName1;//返回的是图片效果（可以干脆是组件）
            }
        };
        JLabel tableName2= new JLabel("分数", JLabel.CENTER);
        DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,int row, int column)
            {
                tableName2.setFont(new Font("幼圆", Font.PLAIN, 25));
                return tableName2;//返回的是图片效果（可以干脆是组件）
            }
        };
        RankListTable.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(dtcr1);
        RankListTable.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(dtcr2);
        RankListTable.getTableHeader().setPreferredSize(new Dimension(100,50));
    }

    class backButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            VIEW_CONTROL.overMenuShow();
        }
    }


}



