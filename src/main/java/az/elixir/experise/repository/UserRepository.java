package az.elixir.experise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.elixir.experise.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

  UserEntity findByUsernameAndPassword(String username, String password);
}
