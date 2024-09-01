package com.presscentric.assessement.domain.usecase;

import com.presscentric.assessement.domain.datafetcher.IUserDataFetcher;
import com.presscentric.assessement.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class DeleteUserUsecase implements IUsecase<UUID, User>{

    private final IUserDataFetcher dataFetcher;

    @Autowired
    public DeleteUserUsecase(IUserDataFetcher dataFetcher) {
        this.dataFetcher = dataFetcher;
    }

    @Override
    public User execute(UUID id) {
        var data = this.dataFetcher.delete(id);
        log.info("User {} deleted", id);
        return data;
    }

}
