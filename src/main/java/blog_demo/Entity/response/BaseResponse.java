package blog_demo.Entity.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data//@data注解的作用:代替setter、getter方法，配置好以后就可反复使用
public class BaseResponse {
    public String message;
    public Integer status;
    public Object data;
}
