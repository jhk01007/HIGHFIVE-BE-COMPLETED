package advancedweb.project.welfareservice.domain.repository;

import advancedweb.project.welfareservice.domain.entity.Welfare;
import advancedweb.project.welfareservice.domain.entity.enums.Area;
import advancedweb.project.welfareservice.domain.entity.enums.Target;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface WelfareRepository extends MongoRepository<Welfare, String> {

    @Query("""
    {
      "$or": [
        { "areas": { "$size": 0 } },
        { "areas": ?0 },
        { "targets": { "$size": 0 } },
        { "targets": ?1 }
      ]
    }
    """)
    List<Welfare> findByAreaOrTargetIncludingEmpty(Area area, Target target);
}
