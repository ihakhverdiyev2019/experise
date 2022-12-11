package az.elixir.experise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.elixir.experise.model.BlogEntity;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {
  List<BlogEntity> findAllByBlogCategoryAndLangId(int blogCategory, int langId);

  BlogEntity findByIdAndLangId(int id, int langId);

  List<BlogEntity> findAllByLangId(int langId);
}
