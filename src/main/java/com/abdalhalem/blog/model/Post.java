package com.abdalhalem.blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String content;
    private String imageFileName;

    public Post(String title, String content, String imageFileName) {
        this.title = title;
        this.content = content;
        this.imageFileName = imageFileName;
    }

    @Override
    public String toString() {
        return String.format(
                "blog[id=%d, title='%s', content='%s', image='%s']",
                id, title, content, imageFileName);
    }
}