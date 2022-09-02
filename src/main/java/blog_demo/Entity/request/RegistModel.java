package blog_demo.Entity.request;

import lombok.Data;

@Data
public class RegistModel {
    public String username;
    public String password;
    public String IDcard;
    public String sex;
    public String nickname;
}
