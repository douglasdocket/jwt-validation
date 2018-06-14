package com.github.douglasdocket.jwtvalidation.filter;

import com.github.douglasdocket.jwtvalidation.entity.ApplicationBot;
import com.github.douglasdocket.jwtvalidation.service.BotService;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static com.github.douglasdocket.jwtvalidation.hardcode.SecurityConstants.HEADER_STRING;
import static com.github.douglasdocket.jwtvalidation.hardcode.SecurityConstants.SECRET;
import static com.github.douglasdocket.jwtvalidation.hardcode.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private UserDetailsService userDetailsService;
    private BotService botService;

    public JWTAuthorizationFilter(AuthenticationManager authManager, UserDetailsService userDetailsService, BotService botService) {
        super(authManager);
        this.userDetailsService = userDetailsService;
        this.botService = botService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        Authentication authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token == null) {
            return null;
        }

        token = token.replace(TOKEN_PREFIX, "");

        if (token.startsWith("web-")) {
            token = token.replace("web-", "");

            // parse the token.
            String subject = Jwts.parser()
                    .setSigningKey(SECRET.getBytes())
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            if (subject != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(subject);

                return new UsernamePasswordAuthenticationToken(subject, null, userDetails.getAuthorities());
            }

        }

        if (token.startsWith("bot-")) {
            token = token.replace("bot-", "");

            // parse the token.
            String subject = Jwts.parser()
                    .setSigningKey(SECRET.getBytes())
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            if (subject != null) {
                ApplicationBot applicationBot = botService.loadBotById(subject);

                return new PreAuthenticatedAuthenticationToken(applicationBot.getId(), null, Collections.emptyList());
            }

        }

        return null;
    }
}
