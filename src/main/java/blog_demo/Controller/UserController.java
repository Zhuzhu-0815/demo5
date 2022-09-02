package blog_demo.Controller;

import blog_demo.Entity.request.ChangeUserInfoModel;
import blog_demo.Entity.request.LoginModel;
import blog_demo.Entity.request.SelectModel;
import blog_demo.Service.UserService;
import blog_demo.Entity.request.RegistModel;
import blog_demo.Entity.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("111")
public class UserController {
    @Autowired//当Spring发现@Autowired注解时，将自动在代码上下文中找到和其匹配(默认是类型匹配)的Bean，并自动注入到相应的地方去。
    UserService userService;

    @RequestMapping("/222")//注册
    public BaseResponse Regist(@RequestBody RegistModel registModel) {
        return userService.regist(registModel);
    }

    @RequestMapping("333")//登录
    public BaseResponse Login(@RequestBody LoginModel loginModel) {
        return userService.login(loginModel);
    }

    @RequestMapping("/444")//查看
    public BaseResponse Select(@RequestBody SelectModel selectModel) {
        return userService.select(selectModel);
    }

    @RequestMapping("/555")//修改信息
    public BaseResponse Update(@RequestBody ChangeUserInfoModel changeUserInfoModel) {//@RequestBody主要用来接收前端传递给后端的json字符串中的数据(请求体中的数据的)，所以只能发送POST请求。
        return userService.update(changeUserInfoModel);
    }
}
