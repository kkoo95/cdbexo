package org.jojo.cdb;

import java.time.LocalDate;
import java.util.Collection;
import javax.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.jojo.cdb.computer.Computer;
import org.jojo.cdb.computer.ComputerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class ComputerRepositoryTest {

    @Autowired
    ComputerRepository computerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void should_accept_undiscontinued_computer() {
        Computer computer = new Computer("CompA");
        computer.setIntroduced(LocalDate.now());
        computerRepository.save(computer);
        entityManager.flush();

        computer = new Computer("CompB");
        computerRepository.save(computer);
        entityManager.flush();
    }

    @Test
    public void should_fail_insert_computer() {
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class).as("discontinuation constraint").isThrownBy(() -> {
            Computer computer = new Computer("CompA");
            computer.setIntroduced(LocalDate.now());
            computer.setDiscontinued(LocalDate.now().minusDays(1));
            computerRepository.save(computer);
            entityManager.flush();
        });
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class).as("discontinuation constraint").isThrownBy(() -> {
            Computer computer = new Computer("CompA");
            computer.setDiscontinued(LocalDate.now());
            computerRepository.save(computer);
            entityManager.flush();
        });
    }

    @Test
    public void should_find_name_containing_term() {
        computerRepository.save(new Computer("Lol Apple"));
        computerRepository.save(new Computer("Lol Apple2"));
        computerRepository.save(new Computer("Appel"));
        Assertions.assertThat(computerRepository.findByNameContaining("Apple")).hasSize(2);
    }

}