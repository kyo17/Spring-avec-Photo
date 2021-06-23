package com.example.Beta.Project.Interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public interface iPhoto {
    public Resource viewPhoto(String nombreFoto) throws MalformedURLException;
    public String uplooad(MultipartFile archivo) throws IOException;
    public boolean removePic(String nombreFoto);
    public Path getPath(String nombreFoto);
}
