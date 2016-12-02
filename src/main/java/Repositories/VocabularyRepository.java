package main.java.Repositories;

import main.java.DAOs.PartyDAO;
import main.java.DAOs.VocabularyDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VocabularyRepository extends JpaRepository<VocabularyDAO, Long> {
    @Query(value = "SELECT * FROM vocabulary WHERE name = ?1", nativeQuery = true)
    PartyDAO doWork(String name);

}
