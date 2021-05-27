package com.adpro.tasc.security.filter;

import com.adpro.tasc.user.db.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    private UserDAO userDB;

    @Autowired
    public void setUserDB(UserDAO userDB) {
        this.userDB = userDB;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            com.adpro.tasc.user.db.model.User user = userDB.getUser(username);

            return new User(user.getUserName(), user.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString())));
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException(username);
        }
    }
}
