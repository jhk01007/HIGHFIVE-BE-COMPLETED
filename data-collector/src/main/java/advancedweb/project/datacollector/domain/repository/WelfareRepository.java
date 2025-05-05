package advancedweb.project.datacollector.domain.repository;

import advancedweb.project.datacollector.domain.entity.Welfare;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WelfareRepository extends MongoRepository<Welfare, String> {
}
