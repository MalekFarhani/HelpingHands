package tn.esprit.helpinghands.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.helpinghands.entities.Post;
import tn.esprit.helpinghands.entities.User;
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
    @Override
    public ResponseEntity<?> addPost(Post post) throws IOException {
        User u = authenticationService.currentlyAuthenticatedUser();
        if (badWordService.Filtrage_bad_word(post.getBody()) == 0 && badWordService.Filtrage_bad_word(post.getPostTitle()) == 0) {
            post.setUser(u);
            u.getPosts().add(post);
            postRepo.save(post);
            return ResponseEntity.ok().body(post);
        } else
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Bads Word Detected");
    }

    @Override
    public ResponseEntity<?> Update_post(Post post, Integer idPost) throws IOException {
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
                postRepo.save(oldPost);
                return ResponseEntity.ok().body(oldPost);
            } else
                return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Bads Word Detected");
        }
        return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("You are not the owner of this post");

    }
      /*  if (postRepo.existsById(idPost)) {
            Post post1 = postRepo.findById(idPost).orElseThrow(() -> new EntityNotFoundException("post not found"));
            //User user = userRepo.findById(idUser).orElseThrow(() -> new EntityNotFoundException("User not found"));
            if (badWordService.Filtrage_bad_word(post.getBody()) == 0 && badWordService.Filtrage_bad_word(post.getPostTitle()) == 0) {
                if (post.getPostTitle().equals("") == false)
                    post1.setPostTitle(post.getPostTitle());
                if (post.getBody().equals("") == false)
                    post1.setBody(post.getBody());
                postRepo.save(post1);
                return ResponseEntity.ok().body(post);
*/


    @Override
    public String deletePost(Integer postId ) {
       /* User u = userRepo.findById(idUser).orElse(null);
        if (postRepo.findById(postId).get().getUser().getId()==u.getId()) {
            postRepo.delete(postRepo.findById(postId).get());
            return "Post deleted successfully";
        }
        return "You can't delete this post .You are not the owner of this post";*/
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
        List<Post> sp = new ArrayList<>();
        for (Post post : postRepo.findAll()) {
            if (post.getBody().contains(ch) || post.getPostTitle().contains(ch))
                sp.add(post);
        }
        return sp;
    }
    }

