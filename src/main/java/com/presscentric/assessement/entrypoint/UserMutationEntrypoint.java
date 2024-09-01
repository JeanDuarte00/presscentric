package com.presscentric.assessement.entrypoint;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.presscentric.assessement.domain.model.User;
import com.presscentric.assessement.domain.usecase.CreateUserUsecase;
import com.presscentric.assessement.domain.usecase.DeleteUserUsecase;
import com.presscentric.assessement.domain.usecase.IUsecase;
import com.presscentric.assessement.domain.usecase.UpdateUserUsecase;
import com.presscentric.assessement.entrypoint.mapper.PayloadDataMapper;
import com.presscentric.assessement.entrypoint.payload.CreateUserInput;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@DgsComponent
public class UserMutationEntrypoint {

    private final IUsecase<User, User> createUsecase;
    private final IUsecase<UUID, User> deleteUsecase;
    private final IUsecase<User, User> updateUsecase;

    @Autowired
    public UserMutationEntrypoint(CreateUserUsecase createUsecase, DeleteUserUsecase deleteUsecase, UpdateUserUsecase updateUsecase) {
        this.createUsecase = createUsecase;
        this.deleteUsecase = deleteUsecase;
        this.updateUsecase = updateUsecase;
    }

    @DgsMutation
    public User create(DataFetchingEnvironment dataFetchingEnvironment) {
        var userInput =  PayloadDataMapper.map(dataFetchingEnvironment, CreateUserInput.class);
        var data = new User(userInput.name(), userInput.email());
        return this.createUsecase.execute(data);
    }

    @DgsMutation
    public User update(@InputArgument UUID id, DataFetchingEnvironment dataFetchingEnvironment) {
        var userInput =  PayloadDataMapper.map(dataFetchingEnvironment, CreateUserInput.class);
        var data = new User(id, userInput.name(), userInput.email());
        return this.updateUsecase.execute(data);
    }

    @DgsMutation
    public User delete(@InputArgument UUID id) {
        return this.deleteUsecase.execute(id);
    }
}
