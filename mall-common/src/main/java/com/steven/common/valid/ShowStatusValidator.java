package com.steven.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @author steven
 * @desc 自定义校验器的处理方法
 * @date 2021/2/8 10:51
 */
public class ShowStatusValidator implements ConstraintValidator<ShowStatusValue,Integer> {

    private Set<Integer> values = new HashSet<>();

    @Override
    public void initialize(ShowStatusValue constraintAnnotation) {
        System.out.println("ShowStatusValidator.initialize");
        int[] valuesInt = constraintAnnotation.values();
        for (int value : valuesInt) {
            values.add(value);
        }
    }

    /**
     * 校验传入的参数
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return values.contains(value);
    }
}
