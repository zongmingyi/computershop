package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.exception.InsertException;
import com.cy.store.service.exception.UsernameDuplicatedException;
import com.cy.store.service.exception.UsernameIsNull;
import org.apache.tomcat.util.digester.Digester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

//Dao层（mapper层）没有这个注解，因为UserMapper只是个接口，没有实现类
@Service // @Service注解：将当前类的对象交给Spring来管理，自动创建对象以及对象的维护
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        String username = user.getUsername();
        if(null == username){
            throw new UsernameIsNull("用户名为空");
        }
        //调用findByUsername查找用户名是否被占用
        User result = userMapper.findByUsername(username);
        //进行判断
        if(null != result) {
            throw new UsernameDuplicatedException("用户名被占用");
        }


        /**
         * 密码加密处理
         * 串+password+串 ------》md5加密算法
         */
        String oldPassword = user.getPassword();
        //获得随机串
        String randomUUID = UUID.randomUUID().toString().toUpperCase();
        //加密处理
        String MD5Password = getMD5Password(oldPassword,randomUUID);

        //设置密码
        user.setPassword(MD5Password);

        //补全数据，记录随机串，以便于比较，登录时将你输入的密码进行三次MD5加密，与创建的加密密码进行比较
        user.setSalt(randomUUID);
        /**
         * 因为注册传入的只是user的一些信息，但数据库的一些字段还没有
         * 补全，需要补全
         */
        //补全数据：is_delete设置为0
        user.setIsDelete(0);
        //补全数据：日志字段信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);


        //执行注册功能的实现
        Integer rows = userMapper.insert(user);
        //插入成功的话返回结果为1
        if(rows != 1){
            throw new InsertException("在用户插入过程中产生了未知异常");
        }
    }

    /**
     * md5加密算法的加密处理
     * @param password
     * @param randomUUid
     * @return
     */
    private String getMD5Password(String password, String randomUUid){
        //三次加密
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((randomUUid+password+randomUUid).getBytes()).toUpperCase();
        }
        return password;
    }
}
