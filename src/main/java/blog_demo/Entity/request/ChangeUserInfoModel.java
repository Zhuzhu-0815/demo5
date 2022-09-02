package blog_demo.Entity.request;

import lombok.Data;

@Data
public class ChangeUserInfoModel {
    private String type;
    private String username;
    private String param;
}
