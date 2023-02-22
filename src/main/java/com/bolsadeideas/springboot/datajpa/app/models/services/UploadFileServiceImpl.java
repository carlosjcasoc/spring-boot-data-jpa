package com.bolsadeideas.springboot.datajpa.app.models.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

  private static final String UPLOADS_FOLDER = "uploads";
  private final Logger logger = LoggerFactory.getLogger(UploadFileServiceImpl.class);

  @Override
  public Resource load(String file) throws MalformedURLException {
    Path pathFoto = getPath(file);
    logger.info("PathFoto: " + pathFoto);
    Resource recurso = null;

    recurso = new UrlResource(pathFoto.toUri());
    if (!recurso.exists() || !recurso.isReadable()) {
      throw new RuntimeException("Error: no se puede cargar la imagen: " + pathFoto);
    }

    return recurso;
  }

  @Override
  public String copy(MultipartFile file) throws IOException {
    String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
    Path rootPath = getPath(uniqueFileName);

    logger.info("rootPath: " + rootPath);
    Files.copy(file.getInputStream(), rootPath);
    return uniqueFileName;
  }

  @Override
  public boolean delete(String file) {
    Path rootPath = getPath(file);
      File archivo = rootPath.toFile();
      if (archivo.exists() && archivo.canRead()) {
          if (archivo.delete()) {
              return true;
          }
      }
    return false;
  }

  private Path getPath(String filename) {
    return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
  }

  @Override
  public void init() throws Exception {
    Files.createDirectory(Paths.get(UPLOADS_FOLDER));
  }
}
