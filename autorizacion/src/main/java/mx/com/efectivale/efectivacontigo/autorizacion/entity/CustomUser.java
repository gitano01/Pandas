package mx.com.efectivale.efectivacontigo.autorizacion.entity;

import java.util.Collection;
import java.util.logging.Logger;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;



public class CustomUser extends User {
	private static final long serialVersionUID = 1L;
	
	public CustomUser(UserEntity user, Collection<GrantedAuthority> grantedAuthoritiesList) {
		super(user.getUsername(), user.getPassword(), grantedAuthoritiesList);
	}
}
