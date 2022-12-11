package az.elixir.experise.repository;

import az.elixir.experise.model.BlogCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogCategoryRepository extends JpaRepository<BlogCategoryEntity, Integer> {
}
