package com.miracle.UserActivity.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.miracle.UserActivity.dao.EmployeeInfoDao;
import com.miracle.UserActivity.entities.UserInfo;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    EmployeeInfoDao userDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserInfo user=userDao.findByName(userName);

        if(user==null) throw new UsernameNotFoundException("User Not Found");
        System.out.println(new MyUserDetails(user));
        System.out.println(user);
        return new MyUserDetails(user);
    }
}
