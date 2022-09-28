package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {

    private long reviewSeq; // 시퀀스
    private String title; //제목
    private String contents; //글 내용
    private String userId; //작성자
    private String readCnt; //조회수
    private String regDt; //등록일
    private String chgDt; // 수정일
}
