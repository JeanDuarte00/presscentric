package com.presscentric.assessement.domain.usecase;

import com.presscentric.assessement.domain.model.User;
import com.presscentric.assessement.infrastructure.datafetcher.UserDataFetcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Get All Users Usecase Unit Test")
class GetAllUsersUsecaseTest {

    @InjectMocks
    GetAllUsersUsecase usecase;
    @Mock
    UserDataFetcher dataFetcher;

    @Test
    @DisplayName("Should get all users")
    void execute() {
        var expectedUserList = List.of(new User("Test Name", "testEmail@gmail.com"));
        when(dataFetcher.getAll()).thenReturn(expectedUserList);
        var userList = this.usecase.execute(null);

        assertEquals(1, userList.size());
    }
}