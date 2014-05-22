package com.sigin.service;

import com.sigin.domain.SignRecord;
import com.sigin.domain.User;

/**
 * Created by dam on 14-5-20.
 */
public interface UserService {

    User saveUserNameAndPhone(User user);

    User updateUser(final User user);

    User findUserById(String userId);

    void saveSignRecord(SignRecord record);

    int findTodaySignCount(String userId);
}
