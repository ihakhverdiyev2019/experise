package az.elixir.experise.repository;

import az.elixir.experise.model.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {
List<BlogEntity> findAllByBlogCategory(int blogCategory);
}
