package az.elixir.experise.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SaveImage {
  public static String UPLOAD_DIRECTORY = "/opt/image/uploads";

  public boolean savePhoto(String path, MultipartFile file) {
    try {
      File theDir = new File(UPLOAD_DIRECTORY + path);
      if (!theDir.exists()) {
        theDir.mkdirs();
      }
      StringBuilder fileNames = new StringBuilder();
      Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY + path, file.getOriginalFilename());
      System.out.println(fileNameAndPath.getFileName());
      fileNames.append(file.getOriginalFilename());
      Files.write(fileNameAndPath, file.getBytes());
      return true;
    } catch (IOException e) {
      System.out.println("Message: " + e.getMessage());
      return false;
    }
  }

  public boolean deleteDirectory(String filePath) {
    try {
      FileUtils.deleteDirectory(new File(UPLOAD_DIRECTORY + filePath));
      return true;
    } catch (IOException e) {
      System.out.println("Message: " + e.getMessage());
      return false;
    }
  }
}
