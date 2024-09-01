package com.presscentric.assessement.domain.usecase;

import com.presscentric.assessement.domain.datafetcher.IUserDataFetcher;
import com.presscentric.assessement.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class GetUserUsecase implements IUsecase<UUID, User>{

    private final IUserDataFetcher dataFetcher;

    public GetUserUsecase(IUserDataFetcher dataFetcher) {
        this.dataFetcher = dataFetcher;
    }

    @Override
    public User execute(UUID id) {
        var data = this.dataFetcher.get(id);
        log.info("Found user {}", data.id());
        return data;
    }
}
