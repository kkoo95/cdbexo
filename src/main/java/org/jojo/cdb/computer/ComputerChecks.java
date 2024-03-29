package org.jojo.cdb.computer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { ComputerValidator.class })
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ComputerChecks {
    String message() default "Invalid computer entity";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

