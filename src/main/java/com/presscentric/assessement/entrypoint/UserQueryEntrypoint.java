package com.presscentric.assessement.entrypoint;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.presscentric.assessement.domain.model.User;
import com.presscentric.assessement.domain.usecase.GetAllUsersUsecase;
import com.presscentric.assessement.domain.usecase.GetUserUsecase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class UserQueryEntrypoint {

    private final GetAllUsersUsecase getAllUsersUsecase;
    private final GetUserUsecase getUserUsecase;

    @Autowired
    public UserQueryEntrypoint(GetAllUsersUsecase getAllUsersUsecase, GetUserUsecase getUserUsecase) {
        this.getAllUsersUsecase = getAllUsersUsecase;
        this.getUserUsecase = getUserUsecase;
    }

    @DgsQuery
    public User get(@InputArgument UUID id) {
        return this.getUserUsecase.execute(id);
    }

    @DgsQuery
    public List<User> getAll() {
        return this.getAllUsersUsecase.execute(null);
    }
}
