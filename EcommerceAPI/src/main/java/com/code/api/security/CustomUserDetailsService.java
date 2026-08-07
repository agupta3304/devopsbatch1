package com.code.api.security;

import com.code.api.models.Users;
import com.code.api.reposatories.IUsersrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsersrepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmailid(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // ASSUMPTION: your Users entity has a field containing roles. Common patterns:
        // 1) `String role` with single role like "ROLE_USER" or "USER"
        // 2) `List<String> roles` or `Set<Role> roles`
        // If your entity differs, you'll need to adapt the extraction below.

        List<GrantedAuthority> authorities;
        if (user.getRole() != null) {
            // single role string
            String role = user.getRole();
            if (!role.startsWith("ADMIN")) role =  role;
            authorities = List.of(new SimpleGrantedAuthority(role));
        } else {
            // fallback — give empty authorities
            authorities = List.of();
        }

        return new User(user.getEmailid(), user.getPassword(), authorities);
    }
}

