package az.elixir.experise.service;

import az.elixir.experise.dto.AllCoursesView;
import az.elixir.experise.dto.AllScholarshipView;
import az.elixir.experise.dto.CoursesView;
import az.elixir.experise.dto.ScholarshipView;
import az.elixir.experise.model.CoursesEntity;
import az.elixir.experise.model.ScholarshipEntity;
import az.elixir.experise.repository.CoursesRepository;
import az.elixir.experise.repository.ScholarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoursesService {

    @Autowired
    private CoursesRepository repository;

    public List<AllCoursesView> findAll() {
        List<CoursesEntity> getAllDetails = repository.findAll();
        List<AllCoursesView> result = new ArrayList<>();
        for (CoursesEntity entity :
                getAllDetails) {
            AllCoursesView allCoursesView = new AllCoursesView();
            allCoursesView.mapper(entity);
            result.add(allCoursesView);
        }
        return result;
    }

    public CoursesView findById(int id) {
        CoursesEntity getAllDetails = repository.findById(id).get();
        CoursesView coursesView = new CoursesView();
        coursesView.mapper(getAllDetails);
        return coursesView;
    }
}
