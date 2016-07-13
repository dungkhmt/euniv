/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 

package vn.webapp.security;

import java.util.Collection;
import java.util.UUID;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import vn.webapp.model.User;
import vn.webapp.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {       
        
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        User user = new User();        
        if ("".equals(username)) {
            throw new BadCredentialsException("Username not be empty");
        } else {
            user = userService.loadUserByUsername(username);
            if (user == null) {
                throw new BadCredentialsException("Invalid Username");
            }
            if ("".equals(password)) {
                throw new BadCredentialsException("Password not be empty");
            }
            if (!DigestUtils.md5Hex(password + user.getSalt()).equals(user.getPassword())) {
                throw new BadCredentialsException("Password wrong");
            }
        }
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}
*/