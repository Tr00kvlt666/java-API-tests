package api.quality;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface QualityGate {
    double minSuccessRate() default 0.95;
    long maxResponseTime() default 2000;
    boolean critical() default false;
}