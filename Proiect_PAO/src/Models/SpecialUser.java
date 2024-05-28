package Models;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SpecialUser extends User {
    private String specialTitle;

    public SpecialUser(int userId, String username, String password, String email, Timestamp createdAt, Timestamp updatedAt, String specialTitle) {
        super(userId, username, password, email, createdAt, updatedAt);
        this.specialTitle = specialTitle;
    }

    public String getSpecialTitle() {
        return specialTitle;
    }

    public void setSpecialTitle(String specialTitle) {
        this.specialTitle = specialTitle;
    }


    @Override
    public String toString() {
        return "SpecialUser [" +
                "userId=" + getUserId() +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                ", specialTitle='" + specialTitle + '\'' +
                "]\n";
    }
}
