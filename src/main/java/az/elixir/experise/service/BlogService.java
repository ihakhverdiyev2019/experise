package az.elixir.experise.service;

import az.elixir.experise.dto.AllBlogsView;
import az.elixir.experise.dto.BlogView;
import az.elixir.experise.model.BlogCategoryEntity;
import az.elixir.experise.model.BlogEntity;
import az.elixir.experise.repository.BlogCategoryRepository;
import az.elixir.experise.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository repository;

    @Autowired
    private BlogCategoryRepository categoryRepository;


    public List<AllBlogsView> getAllBlogs(int page){
        List<BlogEntity> getAllBlogs = repository.findAll();
        if (getAllBlogs.size()>0) {
            getAllBlogs.sort(Comparator.comparing(BlogEntity::getId).reversed());
            int lastObject = page*6;
            if (lastObject>getAllBlogs.size()){
                lastObject = getAllBlogs.size();
            }
            List<AllBlogsView> result = new ArrayList<>();
            for (int i = (page*6) - 6; i < lastObject ;i++){
                AllBlogsView allBlogsView = new AllBlogsView();
                allBlogsView.mapper(getAllBlogs.get(i));
                result.add(allBlogsView);
            }
            return result;
        }
        return new ArrayList<>();
    }

    public List<AllBlogsView> getAllBlogsByCategory(int page, int category){
        List<BlogEntity> getAllBlogs = repository.findAllByBlogCategory(category);
        if (getAllBlogs.size()>0) {
            getAllBlogs.sort(Comparator.comparing(BlogEntity::getId).reversed());
            int lastObject = page*6;
            if (lastObject>getAllBlogs.size()){
                lastObject = getAllBlogs.size();
            }
            List<AllBlogsView> result = new ArrayList<>();
            for (int i = (page*6) - 6; i < lastObject ;i++){
                AllBlogsView allBlogsView = new AllBlogsView();
                allBlogsView.mapper(getAllBlogs.get(i));
                result.add(allBlogsView);
            }
            return result;
        }
        return new ArrayList<>();
    }


    public int numOfPage(){
        List<BlogEntity> blogEntities = repository.findAll();
        if (blogEntities.size()==0){
            return 0;
        }
        if (blogEntities.size()%6>0){
            return blogEntities.size()/6+1;
        }else {
            return blogEntities.size()/6;
        }
    }

    public int numOfPageByCategory(int category){
        List<BlogEntity> blogEntities = repository.findAllByBlogCategory(category);
        if (blogEntities.size()==0){
            return 0;
        }
        if (blogEntities.size()%6>0){
            return blogEntities.size()/6+1;
        }else {
            return blogEntities.size()/6;
        }
    }


    public List<BlogCategoryEntity> getCategoryDetails(){
        List<BlogCategoryEntity> blogCategories = categoryRepository.findAll();
        if (blogCategories.size()>0){
            return  blogCategories;
        }
        return new ArrayList<>();
    }


    public String category(int id){
        return categoryRepository.findById(id).get().getCategory();
    }

    public BlogView blogDetails(int id){
        BlogEntity blogEntity = repository.findById(id).get();
        BlogView blogView = new BlogView();
        blogView.mapper(blogEntity);
        return blogView;
    }

    
}
