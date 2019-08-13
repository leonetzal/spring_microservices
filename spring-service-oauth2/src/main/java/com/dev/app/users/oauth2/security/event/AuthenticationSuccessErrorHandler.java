package com.dev.app.users.oauth2.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.dev.app.users.commons.models.User;
import com.dev.app.users.oauth2.services.IUserService;

import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

	@Autowired
	private IUserService userService;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String msg = "Success Login: " + userDetails.getUsername();
		System.out.println(msg);
		log.info(msg);
		
		User user = userService.findByUsername(authentication.getName());
		if(user.getIntents() != null && user.getIntents() > 0) {
			user.setIntents(0);
			userService.update(user, user.getId());
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String msg = "Error en Login Login: " + exception.getMessage();
		System.out.println(msg);
		log.info(msg);

		try {
			User user = userService.findByUsername(authentication.getName());
			if (user.getIntents() == null) {
				user.setIntents(0);
			}
			log.info("Intentos actuales: " + user.getIntents());
			user.setIntents(user.getIntents() + 1);
			log.info("Intentos posteriores: " + user.getIntents());
			if (user.getIntents() >= 3) {
				log.error(String.format("El usuario %s deshabilitado por 3 intentos.", authentication.getName()));
				user.setEnabled(false);
			}
			userService.update(user, user.getId());
		} catch (FeignException e) {
			log.info(String.format("El usuario %s no existe en el sistema.", authentication.getName()));
		}
	}
}
