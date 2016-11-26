package main.java.Repositories;


import main.java.DAOs.SlotDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends JpaRepository<SlotDAO, Long> {
    @Query(value = "SELECT * FROM slots WHERE name = ?1", nativeQuery = true)
    SlotDAO doWork(String name);

}
