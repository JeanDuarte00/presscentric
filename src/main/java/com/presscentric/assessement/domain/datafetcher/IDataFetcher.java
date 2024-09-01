package com.presscentric.assessement.domain.datafetcher;


import java.util.List;

public interface IDataFetcher<ID, TYPE> {

    TYPE create(TYPE data);
    TYPE update(TYPE data);
    TYPE delete(ID data);
    TYPE get(ID data);
    List<TYPE> getAll();

}
