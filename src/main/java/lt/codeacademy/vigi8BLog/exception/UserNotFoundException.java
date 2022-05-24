package lt.codeacademy.vigi8BLog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND,reason = "User not found at the moment")
public class UserNotFoundException extends RuntimeException{
}
