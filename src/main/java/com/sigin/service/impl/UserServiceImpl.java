package com.sigin.service.impl;

import com.sigin.dao.UserDAO;
import com.sigin.domain.SignRecord;
import com.sigin.domain.User;
import com.sigin.service.UserService;
import com.sigin.util.UUIDGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dam on 14-5-20.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User saveUserNameAndPhone(User user) {
        user.setUserId(UUIDGen.genShortPK());
        return userDAO.saveUserNameAndPhone(user);
    }

    @Override
    public User updateUser(User user) {
        return userDAO.updateUser(user);
    }

    @Override
    public User findUserById(String userId) {
        return userDAO.findUserById(userId);
    }

    @Override
    public void saveSignRecord(SignRecord record) {
        userDAO.saveSignRecord(record);
    }
}
