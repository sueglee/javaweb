package com.lee.service.role;

import com.lee.dao.BaseDao;
import com.lee.dao.role.RoleDao;
import com.lee.dao.role.RoleDaoImpl;
import com.lee.pojo.Role;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService{
    //引入Dao
    private RoleDao roleDao;

    public RoleServiceImpl() {
        roleDao = new RoleDaoImpl();
    }

    @Override
    public ArrayList<Role> getRoleList() {
        Connection connection = null;
        ArrayList<Role> roleList = null;

        try {
            connection = BaseDao.getConnection();
            roleList = roleDao.getRoleList(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return roleList;
    }

    @Test
    public void test() {
        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();
        for (Object obj : roleList) {
            System.out.println(((Role)obj).getRoleName());
        }
    }
}
