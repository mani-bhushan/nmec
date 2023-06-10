package com.apps.nmec.entities.generators;

import java.util.Optional;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;
// import org.springframework.security.core.Authentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Data
@Component
public class AuditorAwareGenerator implements AuditorAware<String> {

	@Value("${app.auth.user}")
	private String xAuthUser;//  = "API_USER";

	@Override
    public Optional<String> getCurrentAuditor() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null) {
			Object principal = auth.getPrincipal();
//			String user = "";
			//Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof UserDetails) {
				xAuthUser = ((UserDetails) principal).getUsername();
			} // else {
//			xAuthUser = principal.toString();
//		}
		}else{
			System.out.println("Authentication is null");
		}
        return Optional.of(xAuthUser);
    }


}