package advancedweb.project.welfareservice.domain.repository;

import advancedweb.project.welfareservice.domain.entity.Welfare;
import advancedweb.project.welfareservice.infra.repository.WelfareRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WelfareRepository extends MongoRepository<Welfare, String>, WelfareRepositoryCustom {
}
