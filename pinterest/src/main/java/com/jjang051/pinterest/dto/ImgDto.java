package com.jjang051.pinterest.dto;

import com.jjang051.pinterest.entity.ImgEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImgDto {
    private int id;
    private String img;
    private String title;
    private String description;
    private Double point;
    private String category;

    public static ImgDto from(ImgEntity e){
        return ImgDto.builder()
                .id(e.getId())
                .img(e.getImg())
                .title(e.getTitle())
                .description(e.getDescription())
                .point(e.getPoint())
                .category(e.getCategory())
                .build();
    }
    public ImgEntity toEntity(){
        return ImgEntity.builder()
                .img(this.getImg())
                .title(this.getTitle())
                .description(this.getDescription())
                .point(this.getPoint())
                .category(this.getCategory())
                .build();
    }
}
