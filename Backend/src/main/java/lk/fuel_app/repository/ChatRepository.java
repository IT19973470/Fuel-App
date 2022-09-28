package lk.fuel_app.repository;
import lk.fuel_app.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ChatRepository extends JpaRepository<Chat, String> {
    List<Chat> findAllByChaterName(String name);

}
