package lk.fuel_app.repository;

import lk.fuel_app.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, String> {

    @Query(value = "from AppUser where (email=?1 or contactNumber=?1) and password=?2")
    Optional<AppUser> login(String email, String password);
}
