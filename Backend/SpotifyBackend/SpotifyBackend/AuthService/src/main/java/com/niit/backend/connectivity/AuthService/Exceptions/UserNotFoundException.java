package com.niit.backend.connectivity.AuthService.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "This User does Not Exists...!!!")
public class UserNotFoundException extends Exception{
}
