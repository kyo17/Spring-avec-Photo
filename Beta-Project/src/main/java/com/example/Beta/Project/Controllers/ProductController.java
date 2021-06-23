package com.example.Beta.Project.Controllers;

import com.example.Beta.Project.Interfaces.IProduct;
import com.example.Beta.Project.Models.Product;
import com.example.Beta.Project.Services.PhotoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.EntityResponse;

import java.io.IOException;
import java.sql.SQLDataException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private IProduct service;
    @Autowired
    PhotoImp photoService;

    @GetMapping("/products")
    public ResponseEntity<?> getAll(){
        Map<String, Object> response = new HashMap<String, Object>();

        try {

        }catch(DataAccessException ex){

        }
        return null;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id){
        Product p = null;
        Map<String, Object> response = new HashMap<>();
        try {
            p = service.getOneById(id);
        }catch (DataAccessException ex){
            response.put("msg", "Error al realizar la consulta en el servidor");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        if (p == null){
            response.put("msg", "El producto: ".concat(id.toString().concat(" no se encuentra en stock")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(p, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<?> insert(@RequestBody Product p){
        Product product = null;
        Map<String, Object> response = new HashMap<>();
        try {
            product = service.save(p);
        }catch (DataAccessException ex){
            response.put("msg", "Error al realizar la inserción del producto");
            response.put("Error: ", ex.getMessage().concat(" : ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("msg", "El producto ha sido ingresado con éxito");
        response.put("producto", product);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PostMapping("/product/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo")MultipartFile archivo, @RequestParam("id") String id){
        Map<String, Object> response = new HashMap<>();
        Product p = service.getOneById(id);
        if (!archivo.isEmpty()){
            String nombreArchivo = null;
            try {
                nombreArchivo = photoService.uplooad(archivo);
            }catch (IOException ex){
                response.put("msg","Error al intentar subir la imagen");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String anterior = p.getPhoto();

            photoService.removePic(anterior);
            p.setPhoto(nombreArchivo);
            service.save(p);

            response.put("product", p);
            response.put("msg", "Imagen subida con éxito");
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    
}
