package com.presscentric.assessement.infrastructure.config;

import com.presscentric.assessement.infrastructure.document.UserDocument;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

//@Configuration
//@DependsOn("mongoTemplate")
public class MongoConfig {

    private final MongoTemplate mongoTemplate;

    public MongoConfig(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostConstruct
    public void initIndexes() {
        mongoTemplate.indexOps(UserDocument.class)
                .ensureIndex(
                        new Index().on("email", Sort.Direction.ASC)
                );
    }

}
