package az.elixir.experise.repository;

import az.elixir.experise.model.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {
}
