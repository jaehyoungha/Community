package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class CommunityDTO {

    private long noticeSeq; // 시퀀스
    private String title; //제목
    private String contents; //글 내용
    private String userId; //작성자
    private String readCnt; //조회수
    private String regDt; //등록일
    private String chgDt; // 수정일
}
