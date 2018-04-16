package students.presence.list.config.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface UserDetails {

    Collection<? extends GrantedAuthority> getAuthorities();

    String getPassword();

    String getUsername();

}
