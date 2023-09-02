package az.elixir.experise.service.admin;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import az.elixir.experise.dto.admin.Payload;
import az.elixir.experise.dto.admin.degree.DegreeCategoryView;
import az.elixir.experise.dto.admin.degree.DegreeUpdateRequest;
import az.elixir.experise.dto.admin.degree.DegreeView;
import az.elixir.experise.dto.admin.degree.ListOfDegree;
import az.elixir.experise.model.*;
import az.elixir.experise.repository.DegreesAndCredentialsCategoryRepository;
import az.elixir.experise.repository.DegreesAndCredentialsRepository;
import az.elixir.experise.repository.LanguageRepository;
import az.elixir.experise.utils.SaveImage;

@Service
public class AdminDegreeService {
  @Autowired private DegreesAndCredentialsRepository degreesAndCredentialsRepository;

  @Autowired
  private DegreesAndCredentialsCategoryRepository degreesAndCredentialsCategoryRepository;

  @Autowired private LanguageRepository languageRepository;
  @Autowired private SaveImage saveImage;

  public Payload degreeList() {
    Payload payload = new Payload();
    List<ListOfDegree> result = new ArrayList<>();
    List<DegreesAndCredentialsEntity> getAll = degreesAndCredentialsRepository.findAll();
    LanguageEntity getLanguageByLangCode = languageRepository.findByLangCode("EN");
    for (DegreesAndCredentialsEntity degreesAndCredentialsEntity : getAll) {
      if (degreesAndCredentialsEntity.getId() == degreesAndCredentialsEntity.getReferId()) {
        String isFooterView = "NO";
        if (degreesAndCredentialsEntity.isFooter()) {
          isFooterView = "YES";
        }

        ListOfDegree listOfDegree = new ListOfDegree();
        listOfDegree.setId(degreesAndCredentialsEntity.getId());
        listOfDegree.setTitle(degreesAndCredentialsEntity.getName());
        listOfDegree.setIsFooter(isFooterView);
        listOfDegree.setCategory(
            degreesAndCredentialsCategoryRepository
                .findByCategoryKeyAndLangId(
                    degreesAndCredentialsEntity.getCategory(), getLanguageByLangCode.getId())
                .getName());
        result.add(listOfDegree);
      }
    }
    payload.setSuccess(true);
    payload.setResult(result);
    return payload;
  }

  public Payload removeDegree(String id) {
    Payload payload = new Payload();
    List<DegreesAndCredentialsEntity> getAll =
        degreesAndCredentialsRepository.findAllByReferId(Integer.parseInt(id));
    if (getAll.size() > 0) {
      for (DegreesAndCredentialsEntity degreesAndCredentialsEntity : getAll) {
        saveImage.deleteDirectory("/degrees/" + degreesAndCredentialsEntity.getId());
        saveImage.deleteDirectory("/degrees/front" + degreesAndCredentialsEntity.getId());
        degreesAndCredentialsRepository.delete(degreesAndCredentialsEntity);
      }
    }
    payload.setSuccess(true);
    return payload;
  }

  public Payload updateViewDegree(String id, String langCode) {
    LanguageEntity getLanguageByLangCode = languageRepository.findByLangCode(langCode);
    DegreesAndCredentialsEntity degreesAndCredentialsEntity =
        degreesAndCredentialsRepository.findByLangIdAndReferId(
            getLanguageByLangCode.getId(), Integer.parseInt(id));
    Payload result = new Payload();
    DegreeView degreeView = new DegreeView();
    result.setSuccess(true);
    String isFooterValue = "false";
    if (degreesAndCredentialsEntity.isFooter()) {
      isFooterValue = "true";
    }
    if (degreesAndCredentialsEntity != null) {
      degreeView.setId(degreesAndCredentialsEntity.getId());
      degreeView.setIsFooter(isFooterValue);
      degreeView.setReferId(degreesAndCredentialsEntity.getReferId());
      degreeView.setPhoto(degreesAndCredentialsEntity.getPhotoUrl());
      degreeView.setPhotoFront(degreesAndCredentialsEntity.getFrontPhotoUrl());
      degreeView.setText(degreesAndCredentialsEntity.getText());
      degreeView.setTitle(degreesAndCredentialsEntity.getName());
      degreeView.setSeoKey(degreesAndCredentialsEntity.getSeoKey());
      degreeView.setSeoDescription(degreesAndCredentialsEntity.getSeoDescription());
      degreeView.setSeoTitle(degreesAndCredentialsEntity.getSeoTitle());
      degreeView.setCategoryKey(degreesAndCredentialsEntity.getCategory());
      result.setResult(degreeView);
    } else {
      degreeView.setId(0);
      degreeView.setReferId(0);
      degreeView.setPhoto(null);
      degreeView.setPhotoFront(null);
      degreeView.setIsFooter("");
      degreeView.setText("");
      degreeView.setTitle("");
      degreeView.setSeoKey("");
      degreeView.setSeoDescription("");
      degreeView.setSeoTitle("");
      degreeView.setCategoryKey("");
      result.setResult(degreeView);
    }
    return result;
  }

  public Payload doUpdateDegree(
      DegreeUpdateRequest degreeUpdateRequest,
      MultipartFile file,
      MultipartFile fileFront,
      UserEntity userEntity) {
    Payload payload = new Payload();
    try {
      boolean isFooter = false;
      String category = "";
      LanguageEntity getLanguageByLangCode =
          languageRepository.findByLangCode(degreeUpdateRequest.getLangCode());
      if (degreeUpdateRequest.getDegreeId() != 0) {
        DegreesAndCredentialsEntity degreesAndCredentialsEntity =
            degreesAndCredentialsRepository.findById(degreeUpdateRequest.getDegreeId()).get();
        if (degreeUpdateRequest.getCategoryKey().isEmpty()) {
          category = degreesAndCredentialsEntity.getCategory();
        } else {
          category = degreeUpdateRequest.getCategoryKey();
        }
        if (degreeUpdateRequest.getIsfooter().equals("true")) {
          isFooter = true;
        } else if (degreeUpdateRequest.getIsfooter().isEmpty()) {
          isFooter = degreesAndCredentialsEntity.getIsFooter();
        }
        if (!file.isEmpty()) {
          boolean savedImage =
              saveImage.savePhoto("/degrees/" + degreesAndCredentialsEntity.getId(), file);
          if (savedImage) {
            degreesAndCredentialsEntity.setPhotoUrl(
                "/store/degrees/"
                    + degreesAndCredentialsEntity.getId()
                    + "/"
                    + file.getOriginalFilename());
          } else {
            payload.setSuccess(false);
            return payload;
          }
        }
        if (!fileFront.isEmpty()) {
          boolean savedImage =
              saveImage.savePhoto(
                  "/degrees/front" + degreesAndCredentialsEntity.getId(), fileFront);
          if (savedImage) {
            degreesAndCredentialsEntity.setFrontPhotoUrl(
                "/store/degrees/front"
                    + degreesAndCredentialsEntity.getId()
                    + "/"
                    + fileFront.getOriginalFilename());
          } else {
            payload.setSuccess(false);
            return payload;
          }
        }
        degreesAndCredentialsEntity.setCategory(category);
        degreesAndCredentialsEntity.setIsFooter(isFooter);
        degreesAndCredentialsEntity.setText(degreeUpdateRequest.getText());
        degreesAndCredentialsEntity.setName(degreeUpdateRequest.getTitle());
        degreesAndCredentialsEntity.setSeoDescription(degreeUpdateRequest.getSeoDescription());
        degreesAndCredentialsEntity.setSeoKey(degreeUpdateRequest.getSeoKey());
        degreesAndCredentialsEntity.setSeoTitle(degreeUpdateRequest.getSeoTitle());
        degreesAndCredentialsEntity.setUpdatedBy(userEntity.getId());
        degreesAndCredentialsEntity.setUpdatedAt(Instant.now());
        degreesAndCredentialsRepository.save(degreesAndCredentialsEntity);
      } else {
        DegreesAndCredentialsEntity degreesAndCredentialsEntity = new DegreesAndCredentialsEntity();
        degreesAndCredentialsEntity.setPhotoUrl(degreeUpdateRequest.getPhoto());
        degreesAndCredentialsEntity.setText(degreeUpdateRequest.getText());
        degreesAndCredentialsEntity.setFrontPhotoUrl(degreeUpdateRequest.getPhotoFront());
        degreesAndCredentialsEntity.setName(degreeUpdateRequest.getTitle());
        degreesAndCredentialsEntity.setSeoDescription(degreeUpdateRequest.getSeoDescription());
        degreesAndCredentialsEntity.setCategory(category);
        degreesAndCredentialsEntity.setIsFooter(isFooter);
        degreesAndCredentialsEntity.setSeoKey(degreeUpdateRequest.getSeoKey());
        degreesAndCredentialsEntity.setSeoTitle(degreeUpdateRequest.getSeoTitle());
        degreesAndCredentialsEntity.setCreatedAt(Instant.now());
        degreesAndCredentialsEntity.setCreatedBy(userEntity.getId());
        degreesAndCredentialsEntity.setUpdatedBy(0);
        degreesAndCredentialsEntity.setLangId(getLanguageByLangCode.getId());
        degreesAndCredentialsEntity.setReferId(degreeUpdateRequest.getReferId());
        DegreesAndCredentialsEntity saved =
            degreesAndCredentialsRepository.save(degreesAndCredentialsEntity);
        if (!file.isEmpty()) {
          boolean savedImage =
              saveImage.savePhoto("/degrees/" + degreesAndCredentialsEntity.getId(), file);
          if (savedImage) {
            saved.setPhotoUrl(
                "/store/degrees/"
                    + degreesAndCredentialsEntity.getId()
                    + "/"
                    + file.getOriginalFilename());
          } else {
            payload.setSuccess(false);
            return payload;
          }
        }
        if (!fileFront.isEmpty()) {
          boolean savedImage =
              saveImage.savePhoto(
                  "/degrees/front" + degreesAndCredentialsEntity.getId(), fileFront);
          if (savedImage) {
            saved.setFrontPhotoUrl(
                "/store/degrees/front"
                    + degreesAndCredentialsEntity.getId()
                    + "/"
                    + fileFront.getOriginalFilename());
          } else {
            payload.setSuccess(false);
            return payload;
          }
        }
        degreesAndCredentialsRepository.save(saved);
      }
      payload.setSuccess(true);
      return payload;
    } catch (Exception e) {
      return payload;
    }
  }

  public Payload doAddDegree(
      DegreeUpdateRequest degreeUpdateRequest,
      MultipartFile file,
      MultipartFile fileFront,
      UserEntity userEntity) {
    Payload payload = new Payload();
    try {
      boolean isFooter = false;
      if (degreeUpdateRequest.getIsfooter().equals("true")) {
        isFooter = true;
      }
      LanguageEntity getLanguageByLangCode =
          languageRepository.findByLangCode(degreeUpdateRequest.getLangCode());
      DegreesAndCredentialsEntity degreesAndCredentialsEntity = new DegreesAndCredentialsEntity();
      degreesAndCredentialsEntity.setPhotoUrl(degreeUpdateRequest.getPhoto());
      degreesAndCredentialsEntity.setText(degreeUpdateRequest.getText());
      degreesAndCredentialsEntity.setFrontPhotoUrl(degreeUpdateRequest.getPhotoFront());
      degreesAndCredentialsEntity.setName(degreeUpdateRequest.getTitle());
      degreesAndCredentialsEntity.setSeoDescription(degreeUpdateRequest.getSeoDescription());
      degreesAndCredentialsEntity.setSeoKey(degreeUpdateRequest.getSeoKey());
      degreesAndCredentialsEntity.setCategory(degreeUpdateRequest.getCategoryKey());
      degreesAndCredentialsEntity.setIsFooter(isFooter);
      degreesAndCredentialsEntity.setSeoTitle(degreeUpdateRequest.getSeoTitle());
      degreesAndCredentialsEntity.setCreatedAt(Instant.now());
      degreesAndCredentialsEntity.setCreatedBy(userEntity.getId());
      degreesAndCredentialsEntity.setUpdatedBy(0);
      degreesAndCredentialsEntity.setLangId(getLanguageByLangCode.getId());
      DegreesAndCredentialsEntity saved =
          degreesAndCredentialsRepository.save(degreesAndCredentialsEntity);
      if (!file.isEmpty()) {
        boolean savedImage =
            saveImage.savePhoto("/degrees/" + degreesAndCredentialsEntity.getId(), file);
        if (savedImage) {
          saved.setPhotoUrl(
              "/store/degrees/"
                  + degreesAndCredentialsEntity.getId()
                  + "/"
                  + file.getOriginalFilename());
        } else {
          payload.setSuccess(false);
          return payload;
        }
      }
      if (!fileFront.isEmpty()) {
        boolean savedImage =
            saveImage.savePhoto("/degrees/front" + degreesAndCredentialsEntity.getId(), fileFront);
        if (savedImage) {
          saved.setFrontPhotoUrl(
              "/store/degrees/front"
                  + degreesAndCredentialsEntity.getId()
                  + "/"
                  + fileFront.getOriginalFilename());
        } else {
          payload.setSuccess(false);
          return payload;
        }
      }
      saved.setReferId(saved.getId());
      degreesAndCredentialsRepository.save(saved);
      payload.setSuccess(true);
      return payload;
    } catch (Exception e) {
      return payload;
    }
  }

  public List<DegreeCategoryView> getCategoryByLang(String langCode) {
    List<DegreeCategoryView> degreeCategoryViews = new ArrayList<>();
    try {
      LanguageEntity getLanguageByLangCode = languageRepository.findByLangCode(langCode);
      List<DegreesAndCredentialsCategoryEntity> degreesAndCredentialsCategoryEntityList =
          degreesAndCredentialsCategoryRepository.findAllByLangId(getLanguageByLangCode.getId());
      for (DegreesAndCredentialsCategoryEntity degreesAndCredentialsCategoryEntity :
          degreesAndCredentialsCategoryEntityList) {
        DegreeCategoryView degreeCategoryView = new DegreeCategoryView();
        degreeCategoryView.setCategory(degreesAndCredentialsCategoryEntity.getName());
        degreeCategoryView.setCategoryKey(degreesAndCredentialsCategoryEntity.getCategoryKey());
        degreeCategoryViews.add(degreeCategoryView);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return degreeCategoryViews;
  }
}
