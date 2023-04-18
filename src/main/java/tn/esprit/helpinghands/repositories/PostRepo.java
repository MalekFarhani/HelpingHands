package tn.esprit.helpinghands.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.helpinghands.entities.Post;

import java.io.IOException;
import java.util.List;
@Repository
public interface PostRepo extends JpaRepository<Post, Integer>{

}
