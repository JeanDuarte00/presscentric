package com.presscentric.assessement.domain.usecase;

import com.presscentric.assessement.domain.datafetcher.IUserDataFetcher;
import com.presscentric.assessement.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GetAllUsersUsecase implements IUsecase<Void, List<User>>{

    private final IUserDataFetcher dataFetcher;

    public GetAllUsersUsecase(IUserDataFetcher dataFetcher) {
        this.dataFetcher = dataFetcher;
    }

    @Override
    public List<User> execute(Void id) {
        var list = this.dataFetcher.getAll();
        log.info("Found users {}", list.stream().map(User::id).toList());
        return list;
    }
}