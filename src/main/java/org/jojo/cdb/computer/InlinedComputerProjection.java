package org.jojo.cdb.computer;

import java.time.LocalDate;
import org.jojo.cdb.company.Company;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlinedComputer", types = { Computer.class })
public interface InlinedComputerProjection {
    public Long getId();

    public String getName();

    public Company getCompany();

    public LocalDate getIntroduced();

    public LocalDate getDiscontinued();
}
