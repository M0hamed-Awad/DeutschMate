package com.example.deutschmate.models;

public class CategoryModel {
    private final String categoryName;
    private final String categorySubText;
    private final int color;
    private final int imageId;

    public CategoryModel(
            String categoryName,
            String categorySubText,
            int color,
            int imageId
    ) {
        this.categoryName = categoryName;
        this.categorySubText = categorySubText;
        this.color = color;
        this.imageId = imageId;
    }

    public int getColor(){
        return color;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getImageId() {
        return imageId;
    }

    public String getCategorySubText() {
        return categorySubText;
    }
}
