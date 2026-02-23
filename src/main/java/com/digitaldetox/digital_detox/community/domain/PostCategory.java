package com.digitaldetox.digital_detox.community.domain;

import lombok.Getter;

@Getter
public enum PostCategory {
    REVIEW("REVIEW", "후기"),
    QNA("QNA", "질문"),
    FREE("FREE", "자유");

    private String categoryName;
    private String displayCategoryName;

    PostCategory(String categoryName, String displayCategoryName) {
        this.categoryName = categoryName;
        this.displayCategoryName = displayCategoryName;
    }
}
