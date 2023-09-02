package az.elixir.experise.service.admin;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.admin.Payload;
import az.elixir.experise.dto.admin.blog.BlogCategoryUpdateRequest;
import az.elixir.experise.dto.admin.blog.BlogCategoryView;
import az.elixir.experise.dto.admin.blog.ListOfBlogCategory;
import az.elixir.experise.model.BlogCategoryEntity;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.model.UserEntity;
import az.elixir.experise.repository.BlogCategoryRepository;
import az.elixir.experise.repository.BlogRepository;
import az.elixir.experise.repository.LanguageRepository;
import az.elixir.experise.utils.SaveImage;

@Service
public class AdminBlogService {

  @Autowired private BlogRepository blogRepository;
  @Autowired private BlogCategoryRepository blogCategoryRepository;
  @Autowired private LanguageRepository languageRepository;
  @Autowired private SaveImage saveImage;

  public Payload blogCategoryList() {
    Payload payload = new Payload();
    List<ListOfBlogCategory> result = new ArrayList<>();
    List<BlogCategoryEntity> getAll = blogCategoryRepository.findAll();
    for (BlogCategoryEntity blogCategoryEntity : getAll) {
      if (blogCategoryEntity.getId() == blogCategoryEntity.getReferId()) {
        ListOfBlogCategory listOfBlogCategory = new ListOfBlogCategory();
        listOfBlogCategory.setId(blogCategoryEntity.getId());
        listOfBlogCategory.setTitle(blogCategoryEntity.getCategory());
        result.add(listOfBlogCategory);
      }
    }
    payload.setSuccess(true);
    payload.setResult(result);
    return payload;
  }

  public Payload removeBlogCategory(String id) {

    Payload payload = new Payload();
    List<BlogCategoryEntity> getAll = blogCategoryRepository.findAllByReferId(Integer.parseInt(id));
    if (getAll.size() > 0) {
      for (BlogCategoryEntity blogCategoryEntity : getAll) {
        blogCategoryRepository.delete(blogCategoryEntity);
      }
    }
    payload.setSuccess(true);
    return payload;
  }

  public Payload updateViewBlogCategory(String id, String langCode) {
    LanguageEntity getLanguageByLangCode = languageRepository.findByLangCode(langCode);
    BlogCategoryEntity blogCategoryEntity =
        blogCategoryRepository.findByLangIdAndReferId(
            getLanguageByLangCode.getId(), Integer.parseInt(id));
    Payload result = new Payload();
    BlogCategoryView blogCategoryView = new BlogCategoryView();
    result.setSuccess(true);
    if (blogCategoryEntity != null) {
      blogCategoryView.setId(blogCategoryEntity.getId());
      blogCategoryView.setReferId(blogCategoryEntity.getReferId());
      blogCategoryView.setCategory(blogCategoryEntity.getCategory());
      result.setResult(blogCategoryView);
    } else {
      blogCategoryView.setId(0);
      blogCategoryView.setReferId(0);
      blogCategoryView.setCategory("");
      result.setResult(blogCategoryView);
    }
    return result;
  }

  public Payload doUpdateBlogCategory(
      BlogCategoryUpdateRequest blogCategoryUpdateRequest, UserEntity userEntity) {
    Payload payload = new Payload();
    try {
      LanguageEntity getLanguageByLangCode =
          languageRepository.findByLangCode(blogCategoryUpdateRequest.getLangCode());
      if (blogCategoryUpdateRequest.getBlogCategoryId() != 0) {
        BlogCategoryEntity blogCategoryEntity =
            blogCategoryRepository.findById(blogCategoryUpdateRequest.getBlogCategoryId()).get();
        blogCategoryEntity.setCategory(blogCategoryUpdateRequest.getCategory());
        blogCategoryEntity.setUpdatedBy(userEntity.getId());
        blogCategoryEntity.setUpdatedAt(Instant.now());
        blogCategoryRepository.save(blogCategoryEntity);
      } else {
        BlogCategoryEntity blogCategoryEntitySave = new BlogCategoryEntity();
        blogCategoryEntitySave.setCategory(blogCategoryUpdateRequest.getCategory());
        blogCategoryEntitySave.setCreatedAt(Instant.now());
        blogCategoryEntitySave.setCreatedBy(userEntity.getId());
        blogCategoryEntitySave.setUpdatedBy(0);
        blogCategoryEntitySave.setLangId(getLanguageByLangCode.getId());
        blogCategoryEntitySave.setReferId(blogCategoryUpdateRequest.getReferId());
        blogCategoryRepository.save(blogCategoryEntitySave);
      }
      payload.setSuccess(true);
      return payload;
    } catch (Exception e) {
      return payload;
    }
  }

  public Payload doAddBlogCategory(
      BlogCategoryUpdateRequest blogCategoryUpdateRequest, UserEntity userEntity) {
    Payload payload = new Payload();
    try {
      LanguageEntity getLanguageByLangCode =
          languageRepository.findByLangCode(blogCategoryUpdateRequest.getLangCode());
      BlogCategoryEntity blogCategoryEntity = new BlogCategoryEntity();
      blogCategoryEntity.setCategory(blogCategoryUpdateRequest.getCategory());
      blogCategoryEntity.setCreatedAt(Instant.now());
      blogCategoryEntity.setCreatedBy(userEntity.getId());
      blogCategoryEntity.setUpdatedBy(0);
      blogCategoryEntity.setLangId(getLanguageByLangCode.getId());
      BlogCategoryEntity saved = blogCategoryRepository.save(blogCategoryEntity);
      saved.setReferId(saved.getId());
      blogCategoryRepository.save(saved);
      payload.setSuccess(true);
      return payload;
    } catch (Exception e) {
      return payload;
    }
  }
}
