package lt.codeacademy.vigi8BLog.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND,reason = "Post not found at the moment")
public class UserNotFoundException extends RuntimeException{
}
