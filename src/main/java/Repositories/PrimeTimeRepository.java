package main.java.Repositories;

import main.java.DAOs.PartyDAO;
import main.java.DAOs.PrimeTimeDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrimeTimeRepository extends JpaRepository<PrimeTimeDAO, Long> {
    @Query(value = "SELECT * FROM prime_times WHERE fk_party in :ids", nativeQuery = true)
    List<PrimeTimeDAO> getPrimeTimesByPartyIds(@Param("ids") List<Long> ids);

}
