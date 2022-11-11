package az.elixir.experise.repository;

import az.elixir.experise.model.FAQEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FAQRepository extends JpaRepository<FAQEntity, Integer> {
}
