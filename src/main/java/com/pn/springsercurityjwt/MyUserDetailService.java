package com.pn.springsercurityjwt;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * created by : pnema
 * on 6/30/2021
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Any strategy you use for fetching user. you can use JPA Repository, you can use
        //as of now for JWT I am using ddefault security user.
        return new User("admin", "admin", new ArrayList<>());
    }


}
