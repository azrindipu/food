package com.azrin.food.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "food")
public class Food {
    @Id
    private String id;
    private String poster_id;
    private String title;
    private int person_count;
    private String posted_by;
    private String image_url;
    private Date current_time;
    private Date food_destroy_time;
    private double poster_current_lat;
    private double poster_current_longi;
    private String status;
    private String description;
}
