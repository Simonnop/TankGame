package group.database.pojo;

import lombok.Data;

@Data
public class User {

    private String username;
    private int score;
    private String type;


    public User(String username ,String type) {
        this.username = username;
        this.type=type;
    }


    public User(String username, int score, String type) {
        this.username = username;
        this.score = score;
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", score=" + score +
                '}';
    }


}
