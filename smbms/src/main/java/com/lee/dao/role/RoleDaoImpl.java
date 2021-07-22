package com.lee.dao.role;

import com.lee.dao.BaseDao;
import com.lee.pojo.Role;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoleDaoImpl implements RoleDao{
    //获取角色列表
    @Override
    public ArrayList<Role> getRoleList(Connection connection) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        ArrayList<Role> roleList = new ArrayList<>();

        if (connection != null) {
            String sql = "select * from smbms_role";
            Object[] params = {};
            resultSet = BaseDao.execute(connection, pstm, resultSet, sql, params);

            while (resultSet.next()) {
                Role role = new Role();
                role.setRoleName(resultSet.getString("roleName"));
                role.setId(resultSet.getInt("id"));
                role.setRoleCode(resultSet.getString("roleCode"));

                roleList.add(role);
            }
        }
        //没有connection要关
        BaseDao.closeResource(null, pstm, resultSet);

        return roleList;
    }

}
