package az.elixir.experise.service.admin;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import az.elixir.experise.dto.admin.Payload;
import az.elixir.experise.dto.admin.writing.ListOfWritings;
import az.elixir.experise.dto.admin.writing.WritingUpdateRequest;
import az.elixir.experise.dto.admin.writing.WritingView;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.model.ResearchAndWritingsEntity;
import az.elixir.experise.model.UserEntity;
import az.elixir.experise.repository.LanguageRepository;
import az.elixir.experise.repository.ResearchAndWritingsRepository;
import az.elixir.experise.utils.SaveImage;

@Service
public class AdminWritingService {

  @Autowired private ResearchAndWritingsRepository repository;
  @Autowired private LanguageRepository languageRepository;
  @Autowired private SaveImage saveImage;

  public Payload writingList() {

    Payload payload = new Payload();
    List<ListOfWritings> result = new ArrayList<>();
    List<ResearchAndWritingsEntity> getAll = repository.findAll();
    for (ResearchAndWritingsEntity researchAndWritingsEntity : getAll) {
      if (researchAndWritingsEntity.getId() == researchAndWritingsEntity.getReferId()) {
        ListOfWritings listOfWritings = new ListOfWritings();
        listOfWritings.setId(researchAndWritingsEntity.getId());
        listOfWritings.setTitle(researchAndWritingsEntity.getName());
        result.add(listOfWritings);
      }
    }
    payload.setSuccess(true);
    payload.setResult(result);
    return payload;
  }

  public Payload removeWriting(String id) {

    Payload payload = new Payload();
    List<ResearchAndWritingsEntity> getAll = repository.findAllByReferId(Integer.parseInt(id));
    if (getAll.size() > 0) {
      for (ResearchAndWritingsEntity researchAndWritingsEntity : getAll) {
        saveImage.deleteDirectory("/writing/" + researchAndWritingsEntity.getId());
        repository.delete(researchAndWritingsEntity);
      }
    }
    payload.setSuccess(true);
    return payload;
  }

  public Payload updateViewWriting(String id, String langCode) {
    LanguageEntity getLanguageByLangCode = languageRepository.findByLangCode(langCode);
    ResearchAndWritingsEntity researchAndWritingsEntity =
        repository.findByLangIdAndReferId(getLanguageByLangCode.getId(), Integer.parseInt(id));
    Payload result = new Payload();
    WritingView view = new WritingView();
    result.setSuccess(true);
    if (researchAndWritingsEntity != null) {
      view.setId(researchAndWritingsEntity.getId());
      view.setReferId(researchAndWritingsEntity.getReferId());
      view.setPhoto(researchAndWritingsEntity.getPhotoUrl());
      view.setText(researchAndWritingsEntity.getText());
      view.setTitle(researchAndWritingsEntity.getName());
      view.setSeoKey(researchAndWritingsEntity.getSeoKey());
      view.setSeoDescription(researchAndWritingsEntity.getSeoDescription());
      view.setSeoTitle(researchAndWritingsEntity.getSeoTitle());
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

  public Payload doUpdateWriting(
      WritingUpdateRequest writingUpdateRequest, MultipartFile file, UserEntity userEntity) {
    Payload payload = new Payload();
    try {
      LanguageEntity getLanguageByLangCode =
          languageRepository.findByLangCode(writingUpdateRequest.getLangCode());
      if (writingUpdateRequest.getId() != 0) {
        ResearchAndWritingsEntity entity = repository.findById(writingUpdateRequest.getId()).get();
        if (!file.isEmpty()) {
          boolean savedImage = saveImage.savePhoto("/writing/" + entity.getId(), file);
          if (savedImage) {
            entity.setPhotoUrl(
                "/store/writing/" + entity.getId() + "/" + file.getOriginalFilename());
          } else {
            payload.setSuccess(false);
            return payload;
          }
        }
        entity.setText(writingUpdateRequest.getText());
        entity.setName(writingUpdateRequest.getTitle());
        entity.setSeoDescription(writingUpdateRequest.getSeoDescription());
        entity.setSeoKey(writingUpdateRequest.getSeoKey());
        entity.setSeoTitle(writingUpdateRequest.getSeoTitle());
        entity.setUpdatedBy(userEntity.getId());
        entity.setUpdatedAt(Instant.now());
        repository.save(entity);
      } else {
        ResearchAndWritingsEntity entityNew = new ResearchAndWritingsEntity();
        entityNew.setPhotoUrl(writingUpdateRequest.getPhoto());
        entityNew.setText(writingUpdateRequest.getText());
        entityNew.setName(writingUpdateRequest.getTitle());
        entityNew.setSeoDescription(writingUpdateRequest.getSeoDescription());
        entityNew.setSeoKey(writingUpdateRequest.getSeoKey());
        entityNew.setSeoTitle(writingUpdateRequest.getSeoTitle());
        entityNew.setCreatedAt(Instant.now());
        entityNew.setCreatedBy(userEntity.getId());
        entityNew.setUpdatedBy(0);
        entityNew.setLangId(getLanguageByLangCode.getId());
        entityNew.setReferId(writingUpdateRequest.getReferId());
        ResearchAndWritingsEntity saved = repository.save(entityNew);
        if (!file.isEmpty()) {
          boolean savedImage = saveImage.savePhoto("/courses/" + saved.getId(), file);
          if (savedImage) {
            saved.setPhotoUrl("/store/courses/" + saved.getId() + "/" + file.getOriginalFilename());
          } else {
            payload.setSuccess(false);
            return payload;
          }
        }
        repository.save(saved);
      }
      payload.setSuccess(true);
      return payload;
    } catch (Exception e) {
      return payload;
    }
  }

  public Payload doAddWriting(
      WritingUpdateRequest writingUpdateRequest, MultipartFile file, UserEntity userEntity) {
    Payload payload = new Payload();
    try {
      LanguageEntity getLanguageByLangCode =
          languageRepository.findByLangCode(writingUpdateRequest.getLangCode());
      ResearchAndWritingsEntity entity = new ResearchAndWritingsEntity();
      entity.setPhotoUrl(writingUpdateRequest.getPhoto());
      entity.setText(writingUpdateRequest.getText());
      entity.setName(writingUpdateRequest.getTitle());
      entity.setSeoDescription(writingUpdateRequest.getSeoDescription());
      entity.setSeoKey(writingUpdateRequest.getSeoKey());
      entity.setSeoTitle(writingUpdateRequest.getSeoTitle());
      entity.setCreatedAt(Instant.now());
      entity.setCreatedBy(userEntity.getId());
      entity.setUpdatedBy(0);
      entity.setLangId(getLanguageByLangCode.getId());
      ResearchAndWritingsEntity saved = repository.save(entity);
      if (!file.isEmpty()) {
        boolean savedImage = saveImage.savePhoto("/courses/" + saved.getId(), file);
        if (savedImage) {
          saved.setPhotoUrl("/store/courses/" + saved.getId() + "/" + file.getOriginalFilename());
        } else {
          payload.setSuccess(false);
          return payload;
        }
      }
      saved.setReferId(saved.getId());
      repository.save(saved);
      payload.setSuccess(true);
      return payload;
    } catch (Exception e) {
      return payload;
    }
  }
}
