package com.cy.store.mapper;

import com.cy.store.entity.User;

/*
用户模块的持久层接口
 */
public interface UserMapper {
    /**
     * 插入用户数据
     * @param user 用户数据
     * @return 受影响的行数 （可以根据查询语句来判断是否执行成功）
     */
    Integer insert(User user);

    /**
     *
     * @param name 用户名
     * @return 如果找到用户名，就返回该用户，没有找到就返回null
     */
    User findByUsername(String name);
}
