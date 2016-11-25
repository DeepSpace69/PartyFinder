package main.java.Repositories;
import main.java.DAOs.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Tatka on 24.11.2016.
 */
@Repository
public interface UserRepository  extends JpaRepository<UserDAO, Long> {
    @Query(value = "SELECT * FROM users WHERE login = ?1 and password = ?2", nativeQuery = true)
    UserDAO auth(String login, String password);

}
