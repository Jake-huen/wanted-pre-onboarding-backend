package com.example.wantedpreonboardingbackend.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class BulletResponseDTO {

    @ApiModelProperty(value = "제목", example = "제목입니다", required = true)
    private String title;
    @ApiModelProperty(value = "내용", example = "내용입니다", required = true)
    private String content;
    @ApiModelProperty(value = "게시글작성자", example = "tae77777@naver.com", required = true)
    private String member;

    @Builder
    public BulletResponseDTO(String title, String content){
        this.title = title;
        this.content = content;
    }
}
