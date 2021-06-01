package com.quintoimpacto.turismomendoza.app.oauth;

import com.quintoimpacto.turismomendoza.app.entity.Usuario;
import com.quintoimpacto.turismomendoza.app.service.UsuarioService;
import static com.quintoimpacto.turismomendoza.app.utils.Texts.SESSION_LABEL;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired 
    private HttpSession session;
	
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        CustomOauth2User oauth2User = (CustomOauth2User) authentication.getPrincipal();
        String mail = oauth2User.getEmail();
        Usuario usuario = usuarioService.findByEmail(mail);

        if (usuario == null) {
            usuario = usuarioService.save(oauth2User);
        }
        
        session.setAttribute(SESSION_LABEL, usuario);

        super.onAuthenticationSuccess(request, response, authentication); //To change body of generated methods, choose Tools | Templates.
    }

}
