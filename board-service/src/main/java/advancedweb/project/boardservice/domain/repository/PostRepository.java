package advancedweb.project.boardservice.domain.repository;

import advancedweb.project.boardservice.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {

    Page<Post> findAll(Pageable pageable);
}
