package com.presscentric.assessement.domain.usecase;

import com.presscentric.assessement.domain.datafetcher.IUserDataFetcher;
import com.presscentric.assessement.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UpdateUserUsecase implements IUsecase<User, User> {

    private final IUserDataFetcher dataFetcher;

    public UpdateUserUsecase(IUserDataFetcher dataFetcher) {
        this.dataFetcher = dataFetcher;
    }

    @Override
    public User execute(User data) {
        var response = this.dataFetcher.update(data);
        log.info("User {} created", response);
        return response;
    }
}

