package com.cy.store.service;

import com.cy.store.entity.User;

import com.cy.store.mapper.UserMapper;
import com.cy.store.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//SpringBootTest表示当前的类是一个测试1类，不会随同项目一同打包
@SpringBootTest
//RunWith:表示启动这个单元测试类（单元测试类是不能够运行的），需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService iUserService;
    /*
    单元测试方法：
    1.必须被@Test注解1修饰
    2.返回值类型必须是void
    3.方法的参数列表不指定任何类型
    4.方法的访问修饰符必须是public
     */
    @Test
    public void red(){
        try {
            User user = new User();
            user.setUsername("liku");
            user.setPassword("123456");
            iUserService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

}
