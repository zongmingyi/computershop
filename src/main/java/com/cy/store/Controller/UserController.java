package com.cy.store.Controller;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.exception.InsertException;
import com.cy.store.service.exception.UsernameDuplicatedException;
import com.cy.store.service.exception.UsernameIsNull;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController//@Controller + @ResponseBody,不用很多方法都加@ResponseBody
//请求映射路径
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    private IUserService iUserService;
    @RequestMapping("reg")
    //@ResponseBody // 方法响应结果Json的格式进行返回到前端
    public JsonResult<Void> reg(User user) {
        iUserService.reg(user);
        //不考虑异常了，有异常直接被拦截到BaseController进行处理了
        //只考虑成功的之后，返回一个JsonResult对象即可
        return new JsonResult<>(OK);
    }

//    @RequestMapping("reg")
//    //@ResponseBody // 方法响应结果Json的格式进行返回到前端
//    public JsonResult<Void> reg(User user){
//        //创建响应结果对象
//        JsonResult<Void> result =new JsonResult<>();
//        try {
//            iUserService.reg(user);
//            result.setState(200);
//            result.setMessage("用户注册成功");
//        } catch (UsernameDuplicatedException e) {
//            //用户名被占用的状态码
//            result.setState(4000);
//            result.setMessage("用户名被占用");
//        }catch (UsernameIsNull e){
//            result.setState(4001);
//            result.setMessage("用户名为空");
//        }catch (InsertException e){
//            result.setState(4002);
//            result.setMessage("注册时产生未知的异常");
//        }
//
//        return result;
//    }
}
