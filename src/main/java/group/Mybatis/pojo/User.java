package group.Mybatis.pojo;


import lombok.Data;

@Data
public class User {

    private String username;
    private int score;


    public User(String username ) {
        this.username = username;
        this.score = 0;
    }
    public User(String username,int score) {
        this.username = username;
        this.score = score;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", score=" + score +
                '}';
    }
}
