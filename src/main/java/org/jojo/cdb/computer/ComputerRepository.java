package org.jojo.cdb.computer;

import java.util.Collection;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ComputerRepository extends PagingAndSortingRepository<Computer, Long> {

    Collection<Computer> findAllByName(@Param("name") @NotEmpty String name);

    Collection<Computer> findByNameContaining(@Param("name") String name);

}
