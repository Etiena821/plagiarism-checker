package com.plagiarism_checker.config;

import com.plagiarism_checker.model.ProfessorAssistant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private ProfessorAssistant personalAssistant;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public UserDetailsImpl(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDetailsImpl(ProfessorAssistant user) {
        this.personalAssistant = user;
    }

    public UserDetailsImpl(Long id, String username, String password,
                           Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }

    public static UserDetails buildUserDetail(ProfessorAssistant personalAssistant){
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        return new UserDetailsImpl(
                personalAssistant.getId(),
                personalAssistant.getUsername(),
                personalAssistant.getPassword(),
                grantedAuthorityList
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public ProfessorAssistant getPersonalAssistant() {
        return personalAssistant;
    }
}
