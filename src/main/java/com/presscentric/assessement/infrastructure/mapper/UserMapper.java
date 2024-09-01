package com.presscentric.assessement.infrastructure.mapper;

import com.presscentric.assessement.domain.model.User;
import com.presscentric.assessement.infrastructure.document.UserDocument;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public User map(UserDocument data){
        return new User(data.id(), data.name(), data.email());
    }

    public UserDocument map(User data){
        return new UserDocument(data.id(), data.name(), data.email());
    }

    public List<User> map(List<UserDocument> documentList) {
        return documentList.stream().map(this::map).toList();
    }
}
