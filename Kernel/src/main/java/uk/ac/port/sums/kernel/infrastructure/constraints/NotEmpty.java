package uk.ac.port.SUMS.kernel.infrastructure.constraints;
import java.lang.annotation.*;
import javax.validation.*;
import javax.validation.constraints.*;

@NotNull
@Size(min=1)
@ReportAsSingleViolation

@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotEmpty{
 String message() default "Supply a non-empty value";
 Class<?>[] groups() default {};
 Class<? extends Payload>[] payload() default {};
}