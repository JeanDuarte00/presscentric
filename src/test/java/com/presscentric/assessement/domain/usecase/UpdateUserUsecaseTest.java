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
@DisplayName("Update User Usecase Unit Test")
class UpdateUserUsecaseTest {

    @InjectMocks
    UpdateUserUsecase usecase;
    @Mock
    UserDataFetcher dataFetcher;

    @Test
    @DisplayName("Should update an user")
    void execute() {
        var expectedUser = new User("Test Name", "testEmail@gmail.com");
        when(dataFetcher.update(expectedUser)).thenReturn(expectedUser);
        var user = this.usecase.execute(expectedUser);

        assertEquals(expectedUser.email(), user.email());
        assertEquals(expectedUser.name(), user.name());
    }
}