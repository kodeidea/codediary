package com.cd.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cd.persistence.model.User;
import com.cd.persistence.service.AccountDto;
import com.cd.persistence.service.IUserService;
import com.cd.validation.EmailExistsException;
import com.cd.web.util.GenericResponse;
import com.cd.web.util.ResponseType;

@Controller
public class UserController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserService userService;
	
	@Autowired
    private MessageSource messages;
	
	@RequestMapping(value = "/account/register", method = RequestMethod.POST)
	@ResponseBody
	public  GenericResponse create(@Valid final AccountDto accountDto, final BindingResult result, final HttpServletRequest pRequest) {
		LOGGER.debug("Registering user account with information: {}", accountDto);
        if (result.hasErrors()) {
            return new GenericResponse(result.getFieldErrors(), result.getGlobalErrors());
        }
        
        final User registered = createUserAccount(accountDto);
        if (registered == null) {
            return new GenericResponse("email", messages.getMessage("message.regError", null, pRequest.getLocale()));
        }
        
		return new GenericResponse(ResponseType.SUCCESS);
	}

	private User createUserAccount(AccountDto accountDto) {
		User registered = null;
		
		try {
			registered = userService.registerNewUserAccount(accountDto);
		} catch (final EmailExistsException e) {
			LOGGER.error(e.getMessage());
		}
		
		return registered;
	}

}
