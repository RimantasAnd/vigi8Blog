//package lt.codeacademy.vigi8BLog.Controller;
//
//import lombok.extern.slf4j.Slf4j;
//import lt.codeacademy.vigi8BLog.Exception.PostNotFoundException;
//import lt.codeacademy.vigi8BLog.Exception.UserNotFoundException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//@Slf4j
//@ControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler({ PostNotFoundException.class, UserNotFoundException.class })
//    public  String handleException(Exception ex, WebRequest request) {
//        if (ex instanceof UserNotFoundException) {
//            return "UserNotFound";
//        } else if (ex instanceof PostNotFoundException) {
//            return "PostNotAllowed";
//        } else {
//            return "error";
//        }
//    }
//}
