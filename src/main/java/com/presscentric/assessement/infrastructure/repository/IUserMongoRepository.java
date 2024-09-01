package com.presscentric.assessement.infrastructure.repository;

import com.presscentric.assessement.infrastructure.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserMongoRepository extends MongoRepository<UserDocument, UUID> { }
