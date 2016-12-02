package main.java.Repositories;


import main.java.DAOs.SlotDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<SlotDAO, Long> {
    @Query(value = "SELECT * FROM slots WHERE fk_party in :ids", nativeQuery = true)
    List<SlotDAO> getSlotsByPartyIds(@Param ("ids") List<Long> ids);


}
