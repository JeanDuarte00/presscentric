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
@DisplayName("Create User Usecase Unit Test")
class CreateUserUsecaseTest {

    @InjectMocks
    CreateUserUsecase usecase;
    @Mock
    UserDataFetcher dataFetcher;

    @Test
    @DisplayName("Should save new user")
    void execute() {
        var newUser = new User("Test Name", "testEmail@gmail.com");
        when(dataFetcher.create(newUser)).thenReturn(newUser);
        var user = this.usecase.execute(newUser);

        assertEquals(newUser.email(), user.email());
        assertEquals(newUser.name(), user.name());
    }

}
