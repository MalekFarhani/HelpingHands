package tn.esprit.helpinghands.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.helpinghands.repositories.ImageRepository;
import tn.esprit.helpinghands.entities.ImageClass;
import tn.esprit.helpinghands.entities.Product;

import tn.esprit.helpinghands.services.ProductService;

@Service

public class ImageService implements ImageServiceImpl{
    @Autowired
    public ImageRepository imageRepository;
    @Override
    public ImageClass uplaodImage(MultipartFile file) throws IOException {
        // TODO Auto-generated method stub
        System.out.println("Original Image Byte Size - " + file.getBytes().length);

        ImageClass img = new ImageClass(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()));
        return	imageRepository.save(img);
    }


    @Override
    public ImageClass getImage(Long id) throws IOException {
        ImageClass retrivedImage = imageRepository.findImageById(id);
        ImageClass img = new ImageClass(retrivedImage.getName(),retrivedImage.getType(),
                decompressBytes(retrivedImage.getPicByte()));
        return img;
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}