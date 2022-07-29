package com.ssafy.sai.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Enterprise {

    @Id @GeneratedValue
    @Column(name = "enterprise_id")
    private Long id;

    private String name;

//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "favorite_enterprise_id")
//    private FavoriteEnterprise favoriteEnterprise;


}
