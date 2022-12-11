package az.elixir.experise.repository;

import az.elixir.experise.model.AboutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutRepository extends JpaRepository<AboutEntity, Integer> {
}
