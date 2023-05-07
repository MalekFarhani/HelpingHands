package tn.esprit.helpinghands.Controllers;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.helpinghands.entities.ImageClass;
import tn.esprit.helpinghands.repositories.ImageRepository;

import tn.esprit.helpinghands.services.ImageServiceImpl;




@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class ImageUploadController {
    @Autowired
    ImageRepository imageRepository;
    @Autowired

    public ImageServiceImpl imageService;
    //http://localhost:9090/image/upload/
    @PostMapping("/upload")
    public ResponseEntity<ImageClass> uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException{
        ImageClass image =  imageService.uplaodImage(file);
        return new ResponseEntity<>(image ,HttpStatus.CREATED);

    }
    @CrossOrigin(origins = "http://localhost:4200")
    //http://localhost:8084/image/get/
    @GetMapping("/get/{id}")
    public ImageClass getImage(@PathVariable("id") Long id) throws IOException {
        ImageClass img=imageService.getImage(id);
        return img;

    }





}