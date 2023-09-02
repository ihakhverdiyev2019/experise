package az.elixir.experise.service.admin;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import az.elixir.experise.dto.admin.Payload;
import az.elixir.experise.dto.admin.course.CourseView;
import az.elixir.experise.dto.admin.course.CoursesUpdateRequest;
import az.elixir.experise.dto.admin.course.ListOfCourses;
import az.elixir.experise.model.*;
import az.elixir.experise.repository.CoursesRepository;
import az.elixir.experise.repository.LanguageRepository;
import az.elixir.experise.utils.SaveImage;

@Service
public class AdminCourseService {

  @Autowired private CoursesRepository coursesRepository;
  @Autowired private LanguageRepository languageRepository;
  @Autowired private SaveImage saveImage;

  public Payload coursesList() {

    Payload payload = new Payload();
    List<ListOfCourses> result = new ArrayList<>();
    List<CoursesEntity> getAll = coursesRepository.findAll();
    for (CoursesEntity coursesEntity : getAll) {
      if (coursesEntity.getId() == coursesEntity.getReferId()) {
        ListOfCourses listOfCourses = new ListOfCourses();
        listOfCourses.setId(coursesEntity.getId());
        listOfCourses.setTitle(coursesEntity.getTitle());
        result.add(listOfCourses);
      }
    }
    payload.setSuccess(true);
    payload.setResult(result);
    return payload;
  }

  public Payload removecourse(String id) {

    Payload payload = new Payload();
    List<CoursesEntity> getAll = coursesRepository.findAllByReferId(Integer.parseInt(id));
    if (getAll.size() > 0) {
      for (CoursesEntity coursesEntity : getAll) {
        saveImage.deleteDirectory("/courses/" + coursesEntity.getId());
        coursesRepository.delete(coursesEntity);
      }
    }
    payload.setSuccess(true);
    return payload;
  }

  public Payload updateViewCourse(String id, String langCode) {
    LanguageEntity getLanguageByLangCode = languageRepository.findByLangCode(langCode);
    CoursesEntity coursesEntity =
        coursesRepository.findByLangIdAndReferId(
            getLanguageByLangCode.getId(), Integer.parseInt(id));
    Payload result = new Payload();
    CourseView courseView = new CourseView();
    result.setSuccess(true);
    if (coursesEntity != null) {
      courseView.setId(coursesEntity.getId());
      courseView.setReferId(coursesEntity.getReferId());
      courseView.setPhoto(coursesEntity.getPhoto());
      courseView.setText(coursesEntity.getText());
      courseView.setTitle(coursesEntity.getTitle());
      courseView.setSeoKey(coursesEntity.getSeoKey());
      courseView.setSeoDescription(coursesEntity.getSeoDescription());
      courseView.setSeoTitle(coursesEntity.getSeoTitle());
      result.setResult(courseView);
    } else {
      courseView.setId(0);
      courseView.setReferId(0);
      courseView.setPhoto(null);
      courseView.setText("");
      courseView.setTitle("");
      courseView.setSeoKey("");
      courseView.setSeoDescription("");
      courseView.setSeoTitle("");
      result.setResult(courseView);
    }
    return result;
  }

  public Payload doUpdateCourses(
      CoursesUpdateRequest coursesUpdateRequest, MultipartFile file, UserEntity userEntity) {
    Payload payload = new Payload();
    try {
      LanguageEntity getLanguageByLangCode =
          languageRepository.findByLangCode(coursesUpdateRequest.getLangCode());
      if (coursesUpdateRequest.getCourseId() != 0) {
        CoursesEntity coursesEntity =
            coursesRepository.findById(coursesUpdateRequest.getCourseId()).get();
        if (!file.isEmpty()) {
          boolean savedImage = saveImage.savePhoto("/courses/" + coursesEntity.getId(), file);
          if (savedImage) {
            coursesEntity.setPhoto(
                "/store/courses/" + coursesEntity.getId() + "/" + file.getOriginalFilename());
          } else {
            payload.setSuccess(false);
            return payload;
          }
        }
        coursesEntity.setText(coursesUpdateRequest.getText());
        coursesEntity.setTitle(coursesUpdateRequest.getTitle());
        coursesEntity.setSeoDescription(coursesUpdateRequest.getSeoDescription());
        coursesEntity.setSeoKey(coursesUpdateRequest.getSeoKey());
        coursesEntity.setSeoTitle(coursesUpdateRequest.getSeoTitle());
        coursesEntity.setUpdatedBy(userEntity.getId());
        coursesEntity.setUpdatedAt(Instant.now());
        coursesRepository.save(coursesEntity);
      } else {
        CoursesEntity coursesEntityNew = new CoursesEntity();
        coursesEntityNew.setPhoto(coursesUpdateRequest.getPhoto());
        coursesEntityNew.setText(coursesUpdateRequest.getText());
        coursesEntityNew.setTitle(coursesUpdateRequest.getTitle());
        coursesEntityNew.setSeoDescription(coursesUpdateRequest.getSeoDescription());
        coursesEntityNew.setSeoKey(coursesUpdateRequest.getSeoKey());
        coursesEntityNew.setSeoTitle(coursesUpdateRequest.getSeoTitle());
        coursesEntityNew.setCreatedAt(Instant.now());
        coursesEntityNew.setCreatedBy(userEntity.getId());
        coursesEntityNew.setUpdatedBy(0);
        coursesEntityNew.setLangId(getLanguageByLangCode.getId());
        coursesEntityNew.setReferId(coursesUpdateRequest.getReferId());
        CoursesEntity saved = coursesRepository.save(coursesEntityNew);
        if (!file.isEmpty()) {
          boolean savedImage = saveImage.savePhoto("/courses/" + saved.getId(), file);
          if (savedImage) {
            saved.setPhoto("/store/courses/" + saved.getId() + "/" + file.getOriginalFilename());
          } else {
            payload.setSuccess(false);
            return payload;
          }
        }
        coursesRepository.save(saved);
      }
      payload.setSuccess(true);
      return payload;
    } catch (Exception e) {
      return payload;
    }
  }

  public Payload doAddCourses(
      CoursesUpdateRequest coursesUpdateRequest, MultipartFile file, UserEntity userEntity) {
    Payload payload = new Payload();
    try {
      LanguageEntity getLanguageByLangCode =
          languageRepository.findByLangCode(coursesUpdateRequest.getLangCode());
      CoursesEntity coursesEntityNew = new CoursesEntity();
      coursesEntityNew.setPhoto(coursesUpdateRequest.getPhoto());
      coursesEntityNew.setText(coursesUpdateRequest.getText());
      coursesEntityNew.setTitle(coursesUpdateRequest.getTitle());
      coursesEntityNew.setSeoDescription(coursesUpdateRequest.getSeoDescription());
      coursesEntityNew.setSeoKey(coursesUpdateRequest.getSeoKey());
      coursesEntityNew.setSeoTitle(coursesUpdateRequest.getSeoTitle());
      coursesEntityNew.setCreatedAt(Instant.now());
      coursesEntityNew.setCreatedBy(userEntity.getId());
      coursesEntityNew.setUpdatedBy(0);
      coursesEntityNew.setLangId(getLanguageByLangCode.getId());
      CoursesEntity saved = coursesRepository.save(coursesEntityNew);
      if (!file.isEmpty()) {
        boolean savedImage = saveImage.savePhoto("/courses/" + saved.getId(), file);
        if (savedImage) {
          saved.setPhoto("/store/courses/" + saved.getId() + "/" + file.getOriginalFilename());
        } else {
          payload.setSuccess(false);
          return payload;
        }
      }
      saved.setReferId(saved.getId());
      coursesRepository.save(saved);
      payload.setSuccess(true);
      return payload;
    } catch (Exception e) {
      return payload;
    }
  }
}
