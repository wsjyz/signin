package com.sigin.dao.impl;

import com.sigin.dao.BaseDAO;
import com.sigin.dao.UserDAO;
import com.sigin.domain.SignRecord;
import com.sigin.domain.User;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dam on 14-5-20.
 */
@Repository("UserDAO")
public class UserDAOImpl extends BaseDAO implements UserDAO {
    @Override
    public User saveUserNameAndPhone(final User user) {
        StringBuilder sql = new StringBuilder("INSERT INTO user_info (user_id,user_name,phone_num) values (?,?,?)");
        getJdbcTemplate().update(sql.toString(),new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,user.getUserId());
                ps.setString(2,user.getUserName());
                ps.setString(3,user.getPhoneNum());
            }
        });
        return user;
    }
    public User updateUser(final User user) {
        StringBuilder sql = new StringBuilder("update user_info  set user_name=?,phone_num=?");
        sql.append(",corp_name=?").append(",position=?").append(",email=?,weixin=?,address=? ");
        sql.append(" where  user_id =?");
        getJdbcTemplate().update(sql.toString(),new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,user.getUserName());
                ps.setString(2,user.getPhoneNum());
                ps.setString(3,user.getCorpName());
                ps.setString(4,user.getPosition());
                ps.setString(5,user.getEmail());
                ps.setString(6,user.getWeixin());
                ps.setString(7,user.getAddress());
                ps.setString(8,user.getUserId());
            }
        });
        return user;
    }

    @Override
    public User findUserById(String userId) {
        StringBuilder sql = new StringBuilder("select * from ")
                .append("user_info").append(" where user_id=?");
        List<User> userList = getJdbcTemplate().query(sql.toString(),new String[]{userId},new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setAddress(rs.getString("address"));
                user.setCorpName(rs.getString("corp_name"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNum(rs.getString("phone_num"));
                user.setPosition(rs.getString("position"));
                user.setUserName(rs.getString("user_name"));
                user.setWeixin(rs.getString("weixin"));
                return user;
            }
        });
        User user = new User();
        if(userList != null && !userList.isEmpty()){
            user = userList.get(0);
        }
        return user;
    }

    @Override
    public void saveSignRecord(final SignRecord record) {
        StringBuilder sql = new StringBuilder("INSERT INTO sign_record (record_id,user_id,opt_time,opt_date) values (?,?,?,?)");
        getJdbcTemplate().update(sql.toString(),new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,record.getRecordId());
                ps.setString(2,record.getUserId());
                ps.setString(3,record.getOptTime());
                ps.setString(4,record.getOptDate());
            }
        });
    }
    public int findTodaySignCount(){
        StringBuilder sql = new StringBuilder("select count(user_id) from ")
                .append("sign_record").append(" group by opt_date");
        List<Integer> list = getJdbcTemplate().queryForList(sql.toString(),Integer.class);
        Integer count = 0;
        if(list != null && !list.isEmpty()){
            count = list.get(0);
        }
        return count;
    }
}
