package az.elixir.experise.service.website;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.website.AllBlogsView;
import az.elixir.experise.dto.website.BlogView;
import az.elixir.experise.dto.website.SeoDto;
import az.elixir.experise.model.BlogCategoryEntity;
import az.elixir.experise.model.BlogEntity;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.repository.BlogCategoryRepository;
import az.elixir.experise.repository.BlogRepository;
import az.elixir.experise.repository.LanguageRepository;

@Service
public class BlogService {

  @Autowired private BlogRepository repository;

  @Autowired private BlogCategoryRepository categoryRepository;

  @Autowired private LanguageRepository languageRepository;

  public List<AllBlogsView> getAllBlogs(int page, String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);
    List<BlogEntity> getAllBlogs = repository.findAllByLangId(getLanguageByLangCode.getId());
    if (getAllBlogs.size() > 0) {
      getAllBlogs.sort(Comparator.comparing(BlogEntity::getId).reversed());
      int lastObject = page * 6;
      if (lastObject > getAllBlogs.size()) {
        lastObject = getAllBlogs.size();
      }
      List<AllBlogsView> result = new ArrayList<>();
      for (int i = (page * 6) - 6; i < lastObject; i++) {
        AllBlogsView allBlogsView = new AllBlogsView();
        allBlogsView.mapper(getAllBlogs.get(i));
        result.add(allBlogsView);
      }
      return result;
    }
    return new ArrayList<>();
  }

  public List<AllBlogsView> getAllBlogsByCategory(int page, int category, String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);
    List<BlogEntity> getAllBlogs =
        repository.findAllByBlogCategoryAndLangId(category, getLanguageByLangCode.getId());
    if (getAllBlogs.size() > 0) {
      getAllBlogs.sort(Comparator.comparing(BlogEntity::getId).reversed());
      int lastObject = page * 6;
      if (lastObject > getAllBlogs.size()) {
        lastObject = getAllBlogs.size();
      }
      List<AllBlogsView> result = new ArrayList<>();
      for (int i = (page * 6) - 6; i < lastObject; i++) {
        AllBlogsView allBlogsView = new AllBlogsView();
        allBlogsView.mapper(getAllBlogs.get(i));
        result.add(allBlogsView);
      }
      return result;
    }
    return new ArrayList<>();
  }

  public int numOfPage() {
    List<BlogEntity> blogEntities = repository.findAll();
    if (blogEntities.size() == 0) {
      return 0;
    }
    if (blogEntities.size() % 6 > 0) {
      return blogEntities.size() / 6 + 1;
    } else {
      return blogEntities.size() / 6;
    }
  }

  public int numOfPageByCategory(int category, String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);
    List<BlogEntity> blogEntities =
        repository.findAllByBlogCategoryAndLangId(category, getLanguageByLangCode.getId());
    if (blogEntities.size() == 0) {
      return 0;
    }
    if (blogEntities.size() % 6 > 0) {
      return blogEntities.size() / 6 + 1;
    } else {
      return blogEntities.size() / 6;
    }
  }

  public List<BlogCategoryEntity> getCategoryDetails(String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);
    List<BlogCategoryEntity> blogCategories =
        categoryRepository.findAllByLangId(getLanguageByLangCode.getId());
    if (blogCategories.size() > 0) {
      return blogCategories;
    }
    return new ArrayList<>();
  }

  public String category(int id, String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);
    return categoryRepository.findByIdAndLangId(id, getLanguageByLangCode.getId()).getCategory();
  }

  public BlogView blogDetails(int id, String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);
    BlogEntity blogEntity = repository.findByIdAndLangId(id, getLanguageByLangCode.getId());
    BlogView blogView = new BlogView();
    blogView.mapper(blogEntity);
    return blogView;
  }

  public SeoDto seoDetails(String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);
    //    FAQSeoEntity faqSeoEntity = faqSeoRepository.findByLangId(getLanguageByLangCode.getId());
    SeoDto seoDto = new SeoDto();
    if (false) {
      //      seoDto.setTitle(faqSeoEntity.getTitle());
      //      seoDto.setKey(faqSeoEntity.getKey());
      //      seoDto.setDescription(faqSeoEntity.getDescription());
    } else {
      seoDto.setTitle("Elixir LLC.");
      seoDto.setKey("Elixir LLC.");
      seoDto.setDescription("Elixir LLC.");
    }
    return seoDto;
  }
}
