package main.java.Repositories;
import main.java.DAOs.CharacterDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tatka on 24.11.2016.
 */
@Repository
public interface CharacterRepository extends JpaRepository<CharacterDAO, Long> {
    @Query(value = "SELECT * FROM characters ", nativeQuery = true)
    List<CharacterDAO> getCharacters(String owner);

}
