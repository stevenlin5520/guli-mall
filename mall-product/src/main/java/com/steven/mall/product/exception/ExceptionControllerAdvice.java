package com.steven.mall.product.exception;

import com.steven.common.utils.R;
import com.steven.common.valid.SysExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局的异常处理机制
 * @author steven
 * @desc
 * @date 2021/2/7 16:33
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 参数异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R preHandle(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        Map<String,String> map = new HashMap<>(16);
        bindingResult.getFieldErrors().forEach(error -> {
            map.put(error.getField(),error.getDefaultMessage());
        });
        log.error("系统异常，异常类型{}，异常内容{}",e.getClass(),e.getMessage());
        return R.error(SysExceptionEnum.VALID_EXCEPTION.getCode(),SysExceptionEnum.VALID_EXCEPTION.getMsg()).put("data",map);
    }

    /**
     * 所有其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public R preHandle(Throwable e){
        log.error("系统未知异常，异常类型{}，异常内容{}",e.getClass(),e.getMessage());
        return R.error(SysExceptionEnum.UNKNOWN_EXCEPTION.getCode(),SysExceptionEnum.UNKNOWN_EXCEPTION.getMsg());
    }
}
