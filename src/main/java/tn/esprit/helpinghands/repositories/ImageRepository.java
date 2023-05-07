package tn.esprit.helpinghands.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.helpinghands.entities.ImageClass;

@Repository
public interface ImageRepository extends CrudRepository<ImageClass, Long> {
    ImageClass findImageById(Long id);
}