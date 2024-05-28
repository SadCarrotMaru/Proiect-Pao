package Models;
import java.sql.Timestamp;

public class User
{
    protected int userId;
    protected String username;
    protected String password;
    protected String email;
    protected Timestamp createdAt;
    protected Timestamp updatedAt;

    public User(int userId, String username, String password, String email, Timestamp createdAt, Timestamp updatedAt) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getUserId() {return userId;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getEmail() {return email;}
    public Timestamp getCreatedAt() {return createdAt;}
    public Timestamp getUpdatedAt() {return updatedAt;}
    public void setUserId(int userId) {this.userId = userId;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setEmail(String email) {this.email = email;}
    public void setCreatedAt(Timestamp createdAt) {this.createdAt = createdAt;}
    public void setUpdatedAt(Timestamp updatedAt) {this.updatedAt = updatedAt;}

    @Override
    public String toString()
    {
        return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]\n";
    }
}
