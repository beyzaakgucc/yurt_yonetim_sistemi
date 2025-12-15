package com.yurt.factory;

import com.yurt.domain.AbstractUser;

public interface UserFactory<T> {
    AbstractUser create(T dto);
}

