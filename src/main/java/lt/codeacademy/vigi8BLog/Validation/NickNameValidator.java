//package lt.codeacademy.vigi8BLog.Validation;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//import java.lang.annotation.Annotation;
//import java.util.regex.Pattern;
//
//public class NickNameValidator implements ConstraintValidator <NickName>,String{
//    @Override
//    public boolean isValid(String value) {
//        if (value.length() == 6) {
//            return  Pattern.matches("[a-zA-Z]", value);
//        }
//
//        return false;
//    }
//
//
//    @Override
//    public void initialize(Annotation constraintAnnotation) {
//        ConstraintValidator.super.initialize((NickName) constraintAnnotation);
//    }
//}

