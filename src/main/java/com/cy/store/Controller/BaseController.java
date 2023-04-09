package com.cy.store.Controller;

import com.cy.store.service.exception.InsertException;
import com.cy.store.service.exception.ServiceException;
import com.cy.store.service.exception.UsernameDuplicatedException;
import com.cy.store.service.exception.UsernameIsNull;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

/** 控制层的基类：用于统一处理异常*/
public class BaseController {
    /**操作成功的状态码*/
    public static final int OK = 200;

    /**
     * 请求处理方法，这个方法的返回值就是需要传递给前段的数据
     * @return
     */
    //ServiceException.class:service层的异常的基类，这样表示只处理service层的异常
    //ExceptionHandler会自动将异常对象传递给此方法的参数列表表上，所以要有个参数来接收
    //当前项目中产生了ServiceException的异常，被统一拦截到此方法中，方法的返回值直接返回前端
    @ExceptionHandler(ServiceException.class) //用于统一处理抛出的异常，可以指定那些异常可以进行处理
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if(e instanceof UsernameIsNull){
            result.setState(4001);
            result.setMessage("用户名为空");
        }else if(e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名被占用");
        }else if(e instanceof InsertException){
            result.setState(4002);
            result.setMessage("注册时产生未知的异常");
        }
        return result;
    }
}
