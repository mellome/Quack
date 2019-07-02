package de.quackr.auth;

import com.nimbusds.jose.JWSObject;
import net.minidev.json.JSONObject;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

public class QuackRJWTFilter extends AuthenticatingFilter {


    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        final HttpServletRequest httpRequest = WebUtils.toHttp(servletRequest);
        final String authorizationHeader = httpRequest.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new UsernamePasswordToken();
        }

        String token = authorizationHeader.split(" ")[1];

        try {
            final JWSObject jwsObject = JWSObject.parse(token);
            final JSONObject payload = jwsObject.getPayload().toJSONObject();

            return new QuackRJWToken(payload.getAsString("sub"), token);

        } catch (ParseException ex) {
            throw new AuthenticationException(ex);
        }

    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        final boolean loggedIn = executeLogin(servletRequest, servletResponse);

        if (!loggedIn) {
            HttpServletResponse httpResponse = WebUtils.toHttp(servletResponse);
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            return false;
        }

        return true;
    }
}
