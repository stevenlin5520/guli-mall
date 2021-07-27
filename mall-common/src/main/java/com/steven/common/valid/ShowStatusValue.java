package com.steven.common.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author steven
 * @desc 自定义校验器的注解，validatedBy要指定对应的校验器
 * @date 2021/2/8 10:42
 */
@Documented
@Constraint(validatedBy = { ShowStatusValidator.class})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface ShowStatusValue {
    String message() default "{com.steven.common.valid.ShowStatusValidator}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    int[] values() default { };
}
