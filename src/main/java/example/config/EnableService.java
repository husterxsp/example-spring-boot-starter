package example.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author xushaopeng
 * @date 2018/12/25
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ExampleAutoConfigure.class)
@Documented
public @interface EnableService {

}
