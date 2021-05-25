package com.metronicproject.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.metronicproject.Repository.UserRepository;
import com.metronicproject.dto.GoogleOAuth2UserInfo;
import com.metronicproject.model.User;
import com.metronicproject.model.UserType;

import java.util.Map;

@Service
public class CustomOidcUserService extends OidcUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        Map<String, Object> attributes = oidcUser.getAttributes();
        GoogleOAuth2UserInfo userInfo = new GoogleOAuth2UserInfo();
        userInfo.setUsername((String) attributes.get("email"));
        userInfo.setId((String) attributes.get("sub"));
       
        userInfo.setFirstName((String) attributes.get("name"));
        updateUser(userInfo);

        return oidcUser;
    }

    private void updateUser(GoogleOAuth2UserInfo userInfo) {
        User user = userRepository.findByUsername(userInfo.getUsername());
        if(user == null) {
            user = new User();
        }
        user.setUsername(userInfo.getUsername());
     
        user.setFirstName(userInfo.getFirstName());
        user.setUserType(UserType.google);
        userRepository.save(user);
    }
}