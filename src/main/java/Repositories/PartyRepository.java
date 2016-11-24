package main.java.Repositories;

import main.java.DAOs.PartyDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends JpaRepository<PartyDAO, Long> {
}
