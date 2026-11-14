package com.devops.quotegenerator.model;

public record Quote(
        int id,
        String text,
        String author
) {
}
