package com.presscentric.assessement.infrastructure.datafetcher;

import com.mongodb.MongoException;
import com.presscentric.assessement.domain.model.User;
import com.presscentric.assessement.infrastructure.exception.ApplicationException;
import com.presscentric.assessement.infrastructure.exception.UserDataFetcherOperationException;
import com.presscentric.assessement.infrastructure.exception.UserNotFoundException;
import com.presscentric.assessement.infrastructure.mapper.UserMapper;
import com.presscentric.assessement.infrastructure.repository.IUserMongoRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("User Data Fetcher Unit Test")
class UserDataFetcherTest {

    @InjectMocks
    UserDataFetcher dataFetcher;
    @Mock
    IUserMongoRepository repository;
    @Mock
    UserMapper mapper;

    @Test
    @DisplayName("Should be able to save user ")
    void shouldCreateUser() {
        var newUser = getUser();
        var document = new UserMapper().map(newUser);
        when(mapper.map(newUser)).thenReturn(document);
        when(mapper.map(document)).thenReturn(newUser);
        when(repository.insert(document)).thenReturn(document);

       var user = this.dataFetcher.create(newUser);

        assertEquals(newUser.email(), user.email());
        assertEquals(newUser.name(), user.name());
    }

    @Test
    @DisplayName("Should not save user when mongo throws exception")
    void shouldNotCreateUser() {
        var newUser = getUser();
        var document = new UserMapper().map(newUser);
        when(mapper.map(newUser)).thenReturn(document);
        when(repository.insert(document)).thenThrow(MongoException.class);

        ApplicationException exception = assertThrows(UserDataFetcherOperationException.class,
                () -> this.dataFetcher.create(newUser));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
    }

    @Test
    @DisplayName("Should be able to update user ")
    void shouldUpdateUser() {
        var expectedUser = getUser();
        var document = new UserMapper().map(expectedUser);
        when(mapper.map(expectedUser)).thenReturn(document);
        when(mapper.map(document)).thenReturn(expectedUser);
        when(repository.save(document)).thenReturn(document);

        var user = this.dataFetcher.update(expectedUser);

        assertEquals(expectedUser.email(), user.email());
        assertEquals(expectedUser.name(), user.name());
    }

    @Test
    @DisplayName("Should not update user when mongo throws exception")
    void shouldNotUpdateUser() {
        var newUser = getUser();
        var document = new UserMapper().map(newUser);
        when(mapper.map(newUser)).thenReturn(document);
        when(repository.save(document)).thenThrow(MongoException.class);

        ApplicationException exception = assertThrows(UserDataFetcherOperationException.class,
                () -> this.dataFetcher.update(newUser));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
    }

    @Test
    void shouldDeleteUser() {
        var expectedUser = getUser();
        var document = new UserMapper().map(expectedUser);
        when(mapper.map(expectedUser)).thenReturn(document);
        when(mapper.map(document)).thenReturn(expectedUser);
        when(repository.findById(expectedUser.id())).thenReturn(Optional.of(document));

        var user = this.dataFetcher.delete(expectedUser.id());

        assertEquals(expectedUser.email(), user.email());
        assertEquals(expectedUser.name(), user.name());
    }

    @Test
    @DisplayName("Should not delete user when mongo throws exception")
    void shouldNotDeleteUser() {
        var expectedUser = getUser();
        when(repository.findById(expectedUser.id())).thenThrow(MongoException.class);

        ApplicationException exception = assertThrows(UserDataFetcherOperationException.class,
                () -> this.dataFetcher.delete(expectedUser.id()));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
    }

    @Test
    @DisplayName("Should return user")
    void shouldGetUser() {
        var expectedUser = getUser();
        var document = new UserMapper().map(expectedUser);
        when(mapper.map(document)).thenReturn(expectedUser);
        when(repository.findById(expectedUser.id())).thenReturn(Optional.of(document));

        var user = this.dataFetcher.get(expectedUser.id());

        assertEquals(expectedUser.email(), user.email());
        assertEquals(expectedUser.name(), user.name());
    }

    @Test
    @DisplayName("Should not get user when not found")
    void shouldNotGetUser() {
        var expectedUser = getUser();
        when(repository.findById(expectedUser.id())).thenReturn(Optional.empty());

        ApplicationException exception = assertThrows(UserNotFoundException.class,
                () -> this.dataFetcher.delete(expectedUser.id()));

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    private static @NotNull User getUser() {
        return new User("Test Name", "testEmail@gmail.com");
    }
}