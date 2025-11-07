package com.jjang051.pinterest.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImgDto {
    private String img;
    private String title;
    private String description;
    private Double point;
    private String category;
}
