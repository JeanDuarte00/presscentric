package com.presscentric.assessement.domain.usecase;


public interface IUsecase<I, O> {
    O execute(I data);
}
