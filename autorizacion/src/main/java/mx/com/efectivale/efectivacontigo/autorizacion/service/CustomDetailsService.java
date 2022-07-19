package mx.com.efectivale.efectivacontigo.autorizacion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import java.util.logging.Logger;
import mx.com.efectivale.efectivacontigo.autorizacion.entity.CustomUser;
import mx.com.efectivale.efectivacontigo.autorizacion.entity.UserEntity;
import mx.com.efectivale.efectivacontigo.autorizacion.repository.UserEntityRepository;

@Service
public class CustomDetailsService implements UserDetailsService {
	@Autowired
	private UserEntityRepository userEntityRepository;

	private static final Logger LOG = Logger.getLogger(CustomDetailsService.class.getName());
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		
		UserEntity user = null;
		try {
			user = this.userEntityRepository.findByUsername(username);
			CustomUser customUser = new CustomUser(user, this.getAuthorities());
			return (UserDetails) customUser;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
	}

	private List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		auths.add(new SimpleGrantedAuthority("ROLE_LIKE"));
		LOG.info("Flesh "+new Gson().toJson(auths));
		return auths;
	}
}
