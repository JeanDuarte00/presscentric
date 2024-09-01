package com.presscentric.assessement.entrypoint.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.DataFetchingEnvironment;

import java.util.Map;

public class PayloadDataMapper {

    private static final ObjectMapper mapper = new ObjectMapper();

    private PayloadDataMapper(){}

    public static  <T> T map(DataFetchingEnvironment dataFetchingEnvironment, Class<T> type){
        Map<String, Object> input = dataFetchingEnvironment.getArgument("input");
        return mapper.convertValue(input, type);
    }

}
