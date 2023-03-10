package com.ecore.roles.exception;

import com.ecore.roles.model.Role;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class ResourceNotFoundException extends RuntimeException {

    public <T> ResourceNotFoundException(Class<T> resource, UUID id) {
        super(format("%s %s not found", resource.getSimpleName(), id));
    }

}
