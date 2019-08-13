package com.dev.app.users.oauth2.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev.app.users.commons.models.User;
import com.dev.app.users.oauth2.clients.UserClientFeign;

import feign.FeignException;

@Service
public class UserService implements IUserService, UserDetailsService {

	private Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserClientFeign client;

	@Override
	public User findByUsername(String username) {
		return client.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user = client.findByUsername(username);
			List<GrantedAuthority> authorities = user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getName()))
					.peek(authority -> log.info("Role: " + authority.getAuthority())).collect(Collectors.toList());
			log.info("Usuario autenticado: " + username);
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					user.getEnabled(), true, true, true, authorities);
		} catch (FeignException e) {
			log.info("Error en el login; no existe '" + username + "'en la Base de Datos!");
			throw new UsernameNotFoundException("Error en el login; no existe '" + username + "'en la Base de Datos!");
		}
	}

	@Override
	public User update(User user, Long id) {
		return client.update(user, id);
	}
}
