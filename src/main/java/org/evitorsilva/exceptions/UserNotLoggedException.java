package org.evitorsilva.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotLoggedException extends UsernameNotFoundException {
    public UserNotLoggedException(String message){
        super(message);
    }
}
