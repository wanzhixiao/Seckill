package com.xxx.seckill.validator;

import com.xxx.seckill.vo.IsMobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

//静态引用，一般引入需要使用 ClassName.method(); 的方式去调用类中的静态方法；Math.sqrt()->sqrt()
//直接拿到类中的静态成员
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })  //注解信息，表示定义的注解（IsMobile）可以用于什么地方
@Retention(RUNTIME)         //表示需要在什么级别保存该注解信息,SOURCE表示在编译时被丢弃，CLASS表示在class文件中被丢弃，RUNTIME表示VM在运行期也保留注解，可以通过反射机制读取注解信息
@Documented                 //将此注解包含在Javadoc文件中
@Constraint(validatedBy = {IsMobileValidator.class})    ////此处指定了注解的实现类为IsMobileValidator,真正的校验逻辑由该类实现

public @interface IsMobile {
    boolean required() default true;            //默认是必填

    String message() default "手机号码格式错误";

    //↓↓下面两行是做啥的？
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
