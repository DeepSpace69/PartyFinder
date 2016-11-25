package main.java.Repositories;

import main.java.DAOs.PartyDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends JpaRepository<PartyDAO, Long> {
    @Query(value = "SELECT * FROM parties WHERE name = ?1", nativeQuery = true)
    PartyDAO findNyName(String name);

}
