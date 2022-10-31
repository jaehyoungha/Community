package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedDTO {

    private Long feedSeq; //피드번호
    private String marketId; //아이디
    private String feedContents; //피드내용
    private String regId; //등록자 ID
    private String regDt; //등록일
    private String chgId; //수정자 ID
    private String chgDt; //수정일

}