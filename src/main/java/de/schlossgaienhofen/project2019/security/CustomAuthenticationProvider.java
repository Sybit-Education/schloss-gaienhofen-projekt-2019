
package de.schlossgaienhofen.project2019.security;

import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.service.UserService;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
@Component
public class CustomAuthenticationProvider  implements AuthenticationProvider {
    
    @Autowired
    UserService userService;
    
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
  
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        // use the credentials
        // and authenticate against the third-party system
        User existingUser = userService.findUserByEmail(email);
        UsernamePasswordAuthenticationToken result = null;
        
        try {
            if(existingUser != null && existingUser.getPassword().equals(userService.getSHA(password))) {
                result = new UsernamePasswordAuthenticationToken(
                        email, password, new ArrayList<>());
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CustomAuthenticationProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
          UsernamePasswordAuthenticationToken.class);
    }
}
