package com.example.Beta.Project.Services;

import com.example.Beta.Project.Interfaces.iPhoto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class PhotoImp implements iPhoto {
    private final static String ruta = "Files/Images/Products";
    private final Logger log = LoggerFactory.getLogger(PhotoImp.class);

    @Override
    public Resource viewPhoto(String nombreFoto) throws MalformedURLException {
        Path rutaArchivo = getPath(nombreFoto);
        log.info(rutaArchivo.toString());
        Resource recurso = new UrlResource(rutaArchivo.toUri());

        if (!recurso.exists() && !recurso.isReadable()){
            rutaArchivo = Paths.get("Files/Images/temp").resolve("no-image.jpg").toAbsolutePath();
            recurso = new UrlResource(rutaArchivo.toUri());
            log.error("Error al cargar la imagen" + nombreFoto);
        }
        return recurso;
    }

    @Override
    public String uplooad(MultipartFile archivo) throws IOException {
        String file = UUID.randomUUID().toString().substring(12) + " : " + archivo.getOriginalFilename().replace(" ", "");
        Path rutaArchivo = getPath(file);
        log.info(rutaArchivo.toString());

        Files.copy(archivo.getInputStream(), rutaArchivo);

        return file;
    }

    @Override
    public boolean removePic(String nombreFoto) {
        if(nombreFoto != null && nombreFoto.length() < 0){
            Path anterior = Paths.get("Files/Images/Products").resolve(nombreFoto).toAbsolutePath();
            File fotoAnterior = anterior.toFile();
            if (fotoAnterior.exists() && fotoAnterior.canRead()){
                fotoAnterior.delete();
                return true;
            }
        }
        return false;
    }

    @Override
    public Path getPath(String nombreFoto) {
        return Paths.get(ruta).resolve(nombreFoto).toAbsolutePath();
    }
}
