package blog_demo.Service;

import blog_demo.Entity.request.ChangeUserInfoModel;
import blog_demo.Entity.request.LoginModel;
import blog_demo.Entity.request.RegistModel;
import blog_demo.Entity.request.SelectModel;
import blog_demo.Entity.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    BaseResponse baseResponse = new BaseResponse();
    Integer SUCCESS_STATUS = 1;
    Integer DEFAULT_STATUS = 0;

    public BaseResponse regist(RegistModel registModel) {
        //注册
        String sql = String.format("insert into user_info (username,password,IDcard,sex,nickname) values('%s','%s','%s','%s','%s')",
                registModel.getUsername(), registModel.getPassword(), registModel.getIDcard(), registModel.getSex(), registModel.getNickname());
        try {
            jdbcTemplate.execute(sql);
            baseResponse.setMessage("注册成功");
            baseResponse.setStatus(SUCCESS_STATUS);
        } catch (Exception e) {
            System.out.println(e);
            baseResponse.setMessage("注册失败");
            baseResponse.setStatus(DEFAULT_STATUS);
        }
        return baseResponse;
    }

    public BaseResponse select(SelectModel selectModel) {
        //查看个人信息
        BaseResponse baseResponse = new BaseResponse();
        String sql = String.format("select * from user_info where username = '%s'", selectModel.getUsername());
        List<Map<String, Object>> res = jdbcTemplate.queryForList(sql);//查询结果以list类型返回，jdbcTemplate是一个工具类
        //List的增强for循环，也叫for each操作，用于遍历列表中的项
        baseResponse.setData(res);
        baseResponse.setMessage("查找成功");
        baseResponse.setStatus(SUCCESS_STATUS);
        return baseResponse;
    }

    public BaseResponse update(ChangeUserInfoModel changeUserInfoModel) {
        //修改个人信息
        BaseResponse baseResponse = new BaseResponse();
        String sql = "";
        if (changeUserInfoModel.getType().equals("password")) {
            sql += String.format("update user_info set password='%s' where username='%s'",
                    changeUserInfoModel.getParam(), changeUserInfoModel.getUsername());
        } else if (changeUserInfoModel.getType().equals("IDcard")) {
            sql += String.format("update user_info set IDcard='%s' where username='%s'",
                    changeUserInfoModel.getParam(), changeUserInfoModel.getUsername());
        } else if (changeUserInfoModel.getType().equals("nickname")) {
            sql += String.format("update user_info set nickname='%s' where username='%s'",
                    changeUserInfoModel.getParam(), changeUserInfoModel.getUsername());
        } else {
            baseResponse.setData(sql);
        }
        try {
            jdbcTemplate.execute(sql);
            String sql1 = String.format("select *from user_info where username='%s'", changeUserInfoModel.getUsername());
            List<Map<String, Object>> res = jdbcTemplate.queryForList(sql1);//查询结果以list类型返回，jdbcTemplate是一个工具类

            //List的增强for循环，也叫for each操作，用于遍历列表中的项
            System.out.println("修改后数据：");
            res.forEach((e) -> {
                //e是该List中的一个项，每次循环都对应一个项，如本次循环中，对应着List中的Map类型的项
                System.out.println(e);
            });
            baseResponse.setData(res);
            baseResponse.setMessage("修改成功");
            baseResponse.setStatus(SUCCESS_STATUS);
        } catch (Exception e) {
            baseResponse.setMessage("失败");
            baseResponse.setStatus(DEFAULT_STATUS);
        }
        return baseResponse;
    }

    public BaseResponse login(LoginModel loginModel) {
        BaseResponse baseResponse = new BaseResponse();
        String sql = String.format("select *from user_info where username = '%s' and password = '%s'",
                loginModel.getUsername(), loginModel.getPassword());
        try {
            List<Map<String, Object>> res = jdbcTemplate.queryForList(sql);
            if (res.size() > 0) {//
                res.forEach((e) -> {
                    //e是该List中的一个项，每次循环都对应一个项，如本次循环中，对应着List中的Map类型的项
                    System.out.println(e);
                });
                baseResponse.setData(res);
                baseResponse.setMessage("登录成功");
                baseResponse.setStatus(SUCCESS_STATUS);
            } else {
                baseResponse.setMessage("账号或密码错误");
                baseResponse.setStatus(DEFAULT_STATUS);
            }
        } catch (Exception e) {
            baseResponse.setMessage("数据库错误");
            baseResponse.setStatus(DEFAULT_STATUS);
        }
        return baseResponse;
    }
}


