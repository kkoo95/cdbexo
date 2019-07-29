package org.jojo.cdb.computer;

import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.jojo.cdb.computer.ComputerChecks;

public class ComputerValidator implements ConstraintValidator<ComputerChecks, Computer> {

    @Override
    public void initialize(ComputerChecks constraint) {
    }

    @Override
    public boolean isValid(Computer computer, ConstraintValidatorContext cxt) {
        LocalDate introduced = computer.getIntroduced();
        LocalDate discontinued = computer.getDiscontinued();

        boolean valid =((introduced == null && discontinued == null)
          || (introduced != null && discontinued == null)
          || (introduced != null && (discontinued.isAfter(introduced) || discontinued.isEqual(introduced))));

        return valid;
    }

}
