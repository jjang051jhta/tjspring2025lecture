package com.jjang051.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String title;
    private String content;
    private int hit;

    //관계설정  board(多) : member(1)
    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private Member writer; //자바에서 참조하래 이름이다.

    //jpa sql을 직접다루지 않는다.

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
