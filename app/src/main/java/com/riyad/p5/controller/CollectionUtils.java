package com.riyad.p5.controller;

import androidx.annotation.Nullable;

import java.util.Collection;

class CollectionUtils {

    public static boolean isEmpty(@Nullable Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
