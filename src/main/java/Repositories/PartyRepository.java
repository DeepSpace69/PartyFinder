package main.java.Repositories;

import main.java.DAOs.PartyDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyRepository extends JpaRepository<PartyDAO, Long> {
    @Query(value = "SELECT * FROM parties WHERE ?1", nativeQuery = true)
    List<PartyDAO> getPartiesByFilters(String clause);


}
