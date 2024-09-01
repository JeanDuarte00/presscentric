package com.presscentric.assessement.domain.usecase;

import com.presscentric.assessement.domain.model.User;
import com.presscentric.assessement.infrastructure.datafetcher.UserDataFetcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Get User Usecase Unit Test")
class GetUserUsecaseTest {

    @InjectMocks
    GetUserUsecase usecase;
    @Mock
    UserDataFetcher dataFetcher;

    @Test
    @DisplayName("Should get an user")
    void execute() {
        var expectedUser = new User("Test Name", "testEmail@gmail.com");
        when(dataFetcher.get(expectedUser.id())).thenReturn(expectedUser);
        var user = this.usecase.execute(expectedUser.id());

        assertEquals(expectedUser.email(), user.email());
        assertEquals(expectedUser.name(), user.name());
    }
}