//package lt.codeacademy.vigi8BLog.Validation;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//public class NickName {
//    @Target(ElementType.FIELD)
//    @Retention(RetentionPolicy.RUNTIME)
//    @Constraint(validatedBy = NickNameValidator.class)
//    public @interface NickName(){
//        String message() default "{nickName.invalid}";
//        Class<?>[] groups() default { };
//        Class<? extends Payload>[] payload() default { };
//
//    }
//}
//
