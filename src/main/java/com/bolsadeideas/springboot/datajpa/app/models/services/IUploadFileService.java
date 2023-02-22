package com.bolsadeideas.springboot.datajpa.app.models.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface IUploadFileService {

    public Resource load(String file) throws MalformedURLException;

    public String copy(MultipartFile file) throws IOException;

    public boolean delete(String file);

    public void deleteAll();

    public void init() throws Exception;

}
