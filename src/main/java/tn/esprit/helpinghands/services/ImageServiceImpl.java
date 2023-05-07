package tn.esprit.helpinghands.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.helpinghands.entities.ImageClass;

public interface ImageServiceImpl {
    public ImageClass	uplaodImage(MultipartFile file) throws IOException ;
    public ImageClass getImage(Long id) throws IOException;
}