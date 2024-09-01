package com.presscentric.assessement.infrastructure.datafetcher;

import com.mongodb.MongoException;
import com.netflix.graphql.dgs.DgsComponent;
import com.presscentric.assessement.domain.datafetcher.IUserDataFetcher;
import com.presscentric.assessement.domain.model.User;
import com.presscentric.assessement.infrastructure.exception.UserDataFetcherOperationException;
import com.presscentric.assessement.infrastructure.exception.UserNotFoundException;
import com.presscentric.assessement.infrastructure.mapper.UserMapper;
import com.presscentric.assessement.infrastructure.repository.IUserMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Slf4j
@DgsComponent
public class UserDataFetcher implements IUserDataFetcher {

    private final IUserMongoRepository repository;
    private final UserMapper mapper;

    @Autowired
    public UserDataFetcher(IUserMongoRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User create(User data) {
        try {
            var document = this.mapper.map(data);
            var response = this.repository.insert(document);
            return this.mapper.map(response);
        } catch (MongoException exception) {
            log.error("Error to save user {}", data);
            throw new UserDataFetcherOperationException(String.format("Error to save user: %s", data), exception);
        }
    }

    @Override
    public User update(User data) {
        try {
            var document = this.mapper.map(data);
            var response = this.repository.save(document);
            return this.mapper.map(response);
        } catch (MongoException exception) {
            log.error("Error to update user {}", data);
            throw new UserDataFetcherOperationException(String.format("Error to update user: %s", data), exception);
        }
    }

    @Override
    public User delete(UUID data) {
        try {
            var user = this.get(data);
            this.repository.delete(this.mapper.map(user));
            return user;
        } catch (MongoException exception) {
            log.error("Error to delete user {}", data);
            throw new UserDataFetcherOperationException(String.format("Error to delete user: %s", data), exception);
        }
    }

    @Override
    public User get(UUID data) {
        try {
            var response = this.repository.findById(data).orElseThrow(() -> {
                log.error("User: {} not found", data);
                return new UserNotFoundException(String.format("User: %s not found", data));
            });
            return this.mapper.map(response);
        } catch (MongoException exception) {
            log.error("Error to find user {}", data);
            throw new UserDataFetcherOperationException(String.format("Error to find user: %s", data), exception);
        }
    }

    @Override
    public List<User> getAll() {
        try{
            var documentList = this.repository.findAll();
            return this.mapper.map(documentList);
        } catch (MongoException exception) {
            log.error("Error to retrieve all users");
            throw new UserDataFetcherOperationException("Error to find all users", exception);
        }
    }
}
