package com.moon.service;

import com.moon.mapper.CloudUserMapper;
import com.moon.model.CloudUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author macbookpro
 */
@Service
public class UserService {

    @Resource
    private CloudUserMapper cloudUserMapper;

    public CloudUser getUser(int userId){
        return cloudUserMapper.selectByPrimaryKey(userId);
    }
}
