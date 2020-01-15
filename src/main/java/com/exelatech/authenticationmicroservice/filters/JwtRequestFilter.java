package com.exelatech.authenticationmicroservice.filters;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exelatech.authenticationmicroservice.errors.FilterAuthenticationException;
import com.exelatech.authenticationmicroservice.model.SimpleAuthority;
import com.exelatech.authenticationmicroservice.service.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String jws = null;
        String username = null;
        ArrayList<SimpleAuthority> authorities = null;
        UsernamePasswordAuthenticationToken token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jws = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractSubject(jws);
                authorities = jwtUtil.extractAuthorities(jws);
            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
                    | IllegalArgumentException ex) {
                throw new FilterAuthenticationException(ex.getMessage(), ex);
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            token = new UsernamePasswordAuthenticationToken(username, null, authorities);
            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
