package az.elixir.experise.service.admin;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import az.elixir.experise.dto.admin.Payload;
import az.elixir.experise.dto.admin.scholarship.ListOfScholarShips;
import az.elixir.experise.dto.admin.scholarship.ScholarShipUpdateRequest;
import az.elixir.experise.dto.admin.scholarship.ScholarShipView;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.model.ScholarshipEntity;
import az.elixir.experise.model.UserEntity;
import az.elixir.experise.repository.LanguageRepository;
import az.elixir.experise.repository.ScholarshipRepository;
import az.elixir.experise.utils.SaveImage;

@Service
public class AdminScholarshipService {

  @Autowired private ScholarshipRepository scholarshipRepository;
  @Autowired private LanguageRepository languageRepository;
  @Autowired private SaveImage saveImage;

  public Payload shcolarshipList() {

    Payload payload = new Payload();
    List<ListOfScholarShips> result = new ArrayList<>();
    List<ScholarshipEntity> getAll = scholarshipRepository.findAll();
    for (ScholarshipEntity scholarshipEntity : getAll) {
      if (scholarshipEntity.getId() == scholarshipEntity.getReferId()) {
        ListOfScholarShips listOfScholarShips = new ListOfScholarShips();
        listOfScholarShips.setId(scholarshipEntity.getId());
        listOfScholarShips.setTitle(scholarshipEntity.getTitle());
        result.add(listOfScholarShips);
      }
    }
    payload.setSuccess(true);
    payload.setResult(result);
    return payload;
  }

  public Payload removeScholarship(String id) {

    Payload payload = new Payload();
    List<ScholarshipEntity> getAll = scholarshipRepository.findAllByReferId(Integer.parseInt(id));
    if (getAll.size() > 0) {
      for (ScholarshipEntity scholarshipEntity : getAll) {
        saveImage.deleteDirectory("/scholarship/" + scholarshipEntity.getId());
        scholarshipRepository.delete(scholarshipEntity);
      }
    }
    payload.setSuccess(true);
    return payload;
  }

  public Payload updateViewScholarship(String id, String langCode) {
    LanguageEntity getLanguageByLangCode = languageRepository.findByLangCode(langCode);
    ScholarshipEntity scholarshipEntity =
        scholarshipRepository.findByLangIdAndReferId(
            getLanguageByLangCode.getId(), Integer.parseInt(id));
    Payload result = new Payload();
    ScholarShipView view = new ScholarShipView();
    result.setSuccess(true);
    if (scholarshipEntity != null) {
      view.setId(scholarshipEntity.getId());
      view.setReferId(scholarshipEntity.getReferId());
      view.setPhoto(scholarshipEntity.getPhoto());
      view.setText(scholarshipEntity.getText());
      view.setTitle(scholarshipEntity.getTitle());
      view.setSeoKey(scholarshipEntity.getSeoKey());
      view.setSeoDescription(scholarshipEntity.getSeoDescription());
      view.setSeoTitle(scholarshipEntity.getSeoTitle());
      result.setResult(view);
    } else {
      view.setId(0);
      view.setReferId(0);
      view.setPhoto(null);
      view.setText("");
      view.setTitle("");
      view.setSeoKey("");
      view.setSeoDescription("");
      view.setSeoTitle("");
      result.setResult(view);
    }
    return result;
  }

  public Payload doUpdateScholarship(
      ScholarShipUpdateRequest scholarShipUpdateRequest,
      MultipartFile file,
      UserEntity userEntity) {
    Payload payload = new Payload();
    try {
      LanguageEntity getLanguageByLangCode =
          languageRepository.findByLangCode(scholarShipUpdateRequest.getLangCode());
      if (scholarShipUpdateRequest.getId() != 0) {
        ScholarshipEntity entity =
            scholarshipRepository.findById(scholarShipUpdateRequest.getId()).get();
        if (!file.isEmpty()) {
          boolean savedImage = saveImage.savePhoto("/scholarship/" + entity.getId(), file);
          if (savedImage) {
            entity.setPhoto(
                "/store/scholarship/" + entity.getId() + "/" + file.getOriginalFilename());
          } else {
            payload.setSuccess(false);
            return payload;
          }
        }
        entity.setText(scholarShipUpdateRequest.getText());
        entity.setTitle(scholarShipUpdateRequest.getTitle());
        entity.setSeoDescription(scholarShipUpdateRequest.getSeoDescription());
        entity.setSeoKey(scholarShipUpdateRequest.getSeoKey());
        entity.setSeoTitle(scholarShipUpdateRequest.getSeoTitle());
        entity.setUpdatedBy(userEntity.getId());
        entity.setUpdatedAt(Instant.now());
        scholarshipRepository.save(entity);
      } else {
        ScholarshipEntity entityNew = new ScholarshipEntity();
        entityNew.setPhoto(scholarShipUpdateRequest.getPhoto());
        entityNew.setText(scholarShipUpdateRequest.getText());
        entityNew.setTitle(scholarShipUpdateRequest.getTitle());
        entityNew.setSeoDescription(scholarShipUpdateRequest.getSeoDescription());
        entityNew.setSeoKey(scholarShipUpdateRequest.getSeoKey());
        entityNew.setSeoTitle(scholarShipUpdateRequest.getSeoTitle());
        entityNew.setCreatedAt(Instant.now());
        entityNew.setCreatedBy(userEntity.getId());
        entityNew.setUpdatedBy(0);
        entityNew.setLangId(getLanguageByLangCode.getId());
        entityNew.setReferId(scholarShipUpdateRequest.getReferId());
        ScholarshipEntity saved = scholarshipRepository.save(entityNew);
        if (!file.isEmpty()) {
          boolean savedImage = saveImage.savePhoto("/courses/" + saved.getId(), file);
          if (savedImage) {
            saved.setPhoto("/store/courses/" + saved.getId() + "/" + file.getOriginalFilename());
          } else {
            payload.setSuccess(false);
            return payload;
          }
        }
        scholarshipRepository.save(saved);
      }
      payload.setSuccess(true);
      return payload;
    } catch (Exception e) {
      return payload;
    }
  }

  public Payload doAddScholarship(
      ScholarShipUpdateRequest scholarShipUpdateRequest,
      MultipartFile file,
      UserEntity userEntity) {
    Payload payload = new Payload();
    try {
      LanguageEntity getLanguageByLangCode =
          languageRepository.findByLangCode(scholarShipUpdateRequest.getLangCode());
      ScholarshipEntity entity = new ScholarshipEntity();
      entity.setPhoto(scholarShipUpdateRequest.getPhoto());
      entity.setText(scholarShipUpdateRequest.getText());
      entity.setTitle(scholarShipUpdateRequest.getTitle());
      entity.setSeoDescription(scholarShipUpdateRequest.getSeoDescription());
      entity.setSeoKey(scholarShipUpdateRequest.getSeoKey());
      entity.setSeoTitle(scholarShipUpdateRequest.getSeoTitle());
      entity.setCreatedAt(Instant.now());
      entity.setCreatedBy(userEntity.getId());
      entity.setUpdatedBy(0);
      entity.setLangId(getLanguageByLangCode.getId());
      ScholarshipEntity saved = scholarshipRepository.save(entity);
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
      scholarshipRepository.save(saved);
      payload.setSuccess(true);
      return payload;
    } catch (Exception e) {
      return payload;
    }
  }
}
