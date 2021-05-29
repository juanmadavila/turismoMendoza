package com.quintoimpacto.turismomendoza.app.oauth;

import java.util.Collection;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOauth2User implements OAuth2User {

    private final OAuth2User oauth2User;

    public CustomOauth2User(OAuth2User oauth2User) {
        this.oauth2User = oauth2User;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oauth2User.getAttribute("name");
    }
    
    public String getNameOnly() {
        return oauth2User.getAttribute("given_name");
    }
    
    public String getLastName() {
        return oauth2User.getAttribute("family_name");
    }
    
    public String getFullName() {
        return oauth2User.getAttribute("name");
    }
    
    public String getEmail() {
        return oauth2User.getAttribute("email");
    }
    
    public String getPhoto() {
        String photo = oauth2User.getAttribute("picture");
        if (photo == null || photo.isEmpty()) {
            //El usuario es de facebook
            String id = oauth2User.getAttribute("id");
            photo = "https://graph.facebook.com/"+id+"/picture?type=small";
        }
        return photo;
    }

}
