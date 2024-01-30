package com.cursos.springsecuritycourse.util;

import lombok.AllArgsConstructor;
import lombok.Getter;


import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public enum Role {

    CUSTOMER(Arrays.asList(Permission.READ_ALL_PRODUCTS)),
    ADMINISTRATOR(Arrays.asList(Permission.READ_ALL_PRODUCTS, Permission.SAVE_ONE_PRODUCT));

    private List<Permission> permissions;


}
