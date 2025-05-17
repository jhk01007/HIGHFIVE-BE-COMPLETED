package advancedweb.project.welfareservice.infra.repository;

import advancedweb.project.welfareservice.domain.entity.Welfare;
import advancedweb.project.welfareservice.domain.entity.enums.Area;
import advancedweb.project.welfareservice.domain.entity.enums.Target;
import com.mongodb.client.model.Filters;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WelfareRepositoryImpl implements WelfareRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public WelfareRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Welfare> findByAreaOrTargetIncludingEmpty(Area area, Target target) {
        List<Criteria> orCriterias = new ArrayList<>();

        orCriterias.add(Criteria.where("summary.areas").size(0));
        if (area != null) {
            orCriterias.add(Criteria.where("summary.areas").is(area));
        }

        orCriterias.add(Criteria.where("summary.targets").size(0));
        if (target != null) {
            orCriterias.add(Criteria.where("summary.targets").is(target));
        }

        Query query = new Query(new Criteria().orOperator(orCriterias));
        return mongoTemplate.find(query, Welfare.class);
    }

}
