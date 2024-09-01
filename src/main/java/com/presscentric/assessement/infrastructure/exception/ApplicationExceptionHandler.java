package com.presscentric.assessement.infrastructure.exception;

import com.netflix.graphql.types.errors.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.jetbrains.annotations.NotNull;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

@Component
public class ApplicationExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(@NotNull Throwable ex, @NotNull DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError()
                .errorType(this.getType(ex))
                .message(ex.getMessage())
                .location(env.getField().getSourceLocation())
                .path(env.getExecutionStepInfo().getPath())
                .build();
    }

    private ErrorType getType(Throwable ex){
        if(ex instanceof UserNotFoundException) return ErrorType.NOT_FOUND;
        if(ex instanceof UserDataFetcherOperationException) return ErrorType.INTERNAL;
        return ErrorType.UNKNOWN;
    }
}