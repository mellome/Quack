package de.quackr.auth;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.realm.AuthenticatingRealm;

public class DefaultRealm extends AuthenticatingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof QuackRJWToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        final QuackRJWToken jwt = (QuackRJWToken) token;

        if (!jwt.verify()) {
            throw new AuthenticationException();
        }

        System.out.println("JWT verified.");

        return new SimpleAccount(jwt.getPrincipal(), jwt.getCredentials(), this.getName());
    }
}
