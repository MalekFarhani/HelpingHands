package tn.esprit.helpinghands.serviceImpl;

import com.cloudinary.Cloudinary;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.helpinghands.entities.Media;
import tn.esprit.helpinghands.entities.Post;
import tn.esprit.helpinghands.entities.User;
import tn.esprit.helpinghands.repositories.MediaRepo;
import tn.esprit.helpinghands.repositories.PostRepo;
import tn.esprit.helpinghands.repositories.UserRepo;
import tn.esprit.helpinghands.security.AuthenticationService;
import tn.esprit.helpinghands.services.PostIservice;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostIservice {

    PostRepo postRepo;
    UserRepo userRepo;
    private BadWordServiceImpl badWordService ;
    private AuthenticationService authenticationService;
    Cloudinary cloudinary;
    MediaRepo mediaRepo;

    @Override
    public ResponseEntity<?> addPost(List<MultipartFile> files, Post post) throws IOException {
        User u = authenticationService.currentlyAuthenticatedUser();
        if (badWordService.Filtrage_bad_word(post.getBody()) == 0 && badWordService.Filtrage_bad_word(post.getPostTitle()) == 0) {
            post.setUser(u);
            if (files==null||files.isEmpty()) {
                post.setMedias(null);
                post.setCreatedAt(LocalDateTime.now());
                postRepo.save(post);
                return ResponseEntity.ok().body(post);
            }
            else{
                List<Media> mediaList = new ArrayList<>();
                for (MultipartFile multipartFile : files) {
                    Media media = new Media();
                    String url = cloudinary.uploader()
                            .upload(multipartFile.getBytes(),
                                    Map.of("public_id", UUID.randomUUID().toString()))
                            .get("url")
                            .toString();
                    media.setImagenUrl(url);
                    media.setName(multipartFile.getName());
                    mediaList.add(media);
                }
                mediaRepo.saveAll(mediaList);
                post.setMedias(mediaList);
                post.setCreatedAt(LocalDateTime.now());
                postRepo.save(post);
                return ResponseEntity.ok().body(post);
            }
        } else
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Bads Word Detected");
    }

    @Override
    public ResponseEntity<?> Update_post(Post post, List<MultipartFile> files, Integer idPost) throws IOException {
        Post oldPost = postRepo.getReferenceById(idPost);
        User usr = authenticationService.currentlyAuthenticatedUser();
        if (oldPost.getUser().equals(usr)) {
            if (badWordService.Filtrage_bad_word(post.getBody()) == 0 && badWordService.Filtrage_bad_word(post.getPostTitle()) == 0) {

                if (post.getBody() != null) {
                    oldPost.setBody(post.getBody());
                }
                if (post.getPostTitle() != null) {
                    oldPost.setPostTitle(post.getPostTitle());
                }
                if (post.getMedias()
                        != null) {
                    List<Media> mediaList = new ArrayList<>();
                    for (MultipartFile multipartFile : files) {
                        Media media = new Media();
                        String url = cloudinary.uploader()
                                .upload(multipartFile.getBytes(),
                                        Map.of("public_id", UUID.randomUUID().toString()))
                                .get("url")
                                .toString();
                        media.setImagenUrl(url);
                        media.setName(multipartFile.getName());
                        mediaList.add(media);
                    }
                    mediaRepo.saveAll(mediaList);
                    oldPost.setMedias(mediaList);

                }

                postRepo.save(oldPost);
                return ResponseEntity.ok().body(oldPost);
            } else
                return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Bads Word Detected");
        }
        return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("You are not the owner of this post");
    }

    @Override
    public String deletePost(Integer postId) {
        User user = authenticationService.currentlyAuthenticatedUser();
        if (postRepo.findById(postId).get().getUser().getId()==user.getId()) {
            postRepo.delete(postRepo.findById(postId).get());
            return "Post deleted successfully";
        }
        return "You can't delete this post .You are not the owner of this post";
    }

    @Override
    public Post getPostById(Integer id) {
        Post p = postRepo.findById(id).orElse(null);
        return p;
    }

    @Override
    public List<Post> Get_all_post() {
        List<Post> findAll = postRepo.findAll();
        return findAll;
    }

    @Override
    public List<Post> Get_post_by_User(Integer idUser) {
        List<Post> postList = new ArrayList<>();
        for(Post post: postRepo.findAll())
        {
            if (post.getUser().getId()==idUser)
            {
                postList.add(post);
            }
        }
        return postList;
    }

    @Override
    public List<Post> Searchpost(String ch, Integer id) {
        List<Post> ll = new ArrayList<>();
        for (Post post : postRepo.findAll()) {
            if (post.getBody().contains(ch) || post.getPostTitle().contains(ch))
                ll.add(post);
        }
        return ll;
    }
    }


