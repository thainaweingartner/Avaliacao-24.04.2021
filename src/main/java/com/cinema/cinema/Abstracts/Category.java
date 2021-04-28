package com.cinema.cinema.Abstracts;

public enum Category {
    ACTION,
    COMEDY,
    DRAMA,
    FANTASY,
    HORROR,
    MYSTERY,
    ROMANCE,
    THRILLER;

    public static Category getCategory(String category){
        for(Category movie_category:Category.values()){
            if(category.toString().equals(category.toUpperCase())){
                return movie_category;
            }
        }
        return null;
    }
}
