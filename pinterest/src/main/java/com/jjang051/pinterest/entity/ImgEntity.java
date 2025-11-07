package com.jjang051.pinterest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pinterest")
public class ImgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pinterest_seq")
    @SequenceGenerator(
            name="pinterest_seq",
            sequenceName = "PIN_SEQ",
            allocationSize = 1
    )
    private Integer id;
    private String img;
    private String title;
    private String description;
    private Double point;
    private String category;
}
