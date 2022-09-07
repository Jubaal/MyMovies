package com.gft.moviesapi.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
@Entity
@Table(name = "user_movie")
public class UserMovie {
    @Id
    @Column(name = "userid", nullable = false)
    private int id;
    Boolean favorite;
    int personal_rating;
    int movieid;
    String notes;

}
