package com.cy.store.service;

import com.cy.store.entity.User;

// 用户模块业务层接口
public interface IUserService {

    /**
     * 用户注册接口，底层是持久层的插入方法
     * @param user
     * @return
     */
    void reg(User user);
}
