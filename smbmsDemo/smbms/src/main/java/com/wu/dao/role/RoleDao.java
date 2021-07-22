package com.wu.dao.role;


import com.wu.pojo.Role;

import java.sql.Connection;
import java.util.List;

public interface RoleDao {

    /**
     * 获取角色列表
     *
     * @param connection
     * @return
     * @throws Exception
     */
    List<Role> getRoleList(Connection connection) throws Exception;

}
