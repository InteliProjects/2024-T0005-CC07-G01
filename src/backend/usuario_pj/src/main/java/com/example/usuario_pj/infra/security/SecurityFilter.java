package com.example.usuario_pj.infra.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import com.example.usuario_pj.model.entity.UsuarioPJEntity;
import com.example.usuario_pj.repository.UsuarioPJRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Custom security filter responsible for handling JWT authentication.
 */
@Component
public class SecurityFilter extends OncePerRequestFilter{
    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioPJRepository usuario_pjRepository;

    /**
     * Performs the JWT authentication process for each incoming HTTP request.
     *
     * @param request     The HTTP request.
     * @param response    The HTTP response.
     * @param filterChain The filter chain to continue processing the request.
     * @throws ServletException If there is a servlet-related exception.
     * @throws IOException      If there is an I/O-related exception.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                var token= this.recoverToken(request);

                if(token != null){
                    var login = tokenService.validateToken(token);
                    var claims= tokenService.verify(token);

                    System.out.println("claims "+ claims);
                    Optional<UsuarioPJEntity> user=  usuario_pjRepository.findByLogin(login);

                    if(user.isPresent()){
                        var userDetails= user.get();
                        var auth= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }

                filterChain.doFilter(request, response);
    }

    /**
     * Retrieves the JWT token from the HTTP request's authorization header.
     *
     * @param request The HTTP request.
     * @return The JWT token as a string or null if it is not found.
     */
    private String recoverToken(HttpServletRequest request){
        var authHeader= request.getHeader("Authorization");
        if(authHeader == null){
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}