package kopo.poly.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kopo.poly.dto.ReviewDTO;
import kopo.poly.repository.Entity.ReviewEntity;
import kopo.poly.repository.IReviewRepository;
import kopo.poly.service.IReviewService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service ("ReviewService")
public class ReviewService implements IReviewService {

    private final IReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getReviewList() {
        log.info(this.getClass().getName() + ".getreviewList Start!");

        // 공지사항 전체 리스트 조회하기
        List<ReviewEntity> rList = reviewRepository.findAllByOrderByReviewSeqDesc();

        // 엔티티의 값들을 DTO에 맞게 넣어주기
        List<ReviewDTO> nList = new ObjectMapper().convertValue(rList,
                new TypeReference<List<ReviewDTO>>() {
                });

        log.info(this.getClass().getName() + ".getreviewList End!");

        return nList;
    }


    @Transactional
    @Override
    public ReviewDTO getReviewInfo(ReviewDTO rDTO, boolean type) throws Exception {
        log.info(this.getClass().getName() + ".getReviewInfo Start!");

        if (type){
            // 조회수 증가하기
            int res = reviewRepository.updateReadCnt(rDTO.getReviewSeq());

            // 조회수 증가 성공여부 체크
            log.info("res : " + res);
        }

        // 공지사항 상세내역 가져오기
        ReviewEntity rEntity = reviewRepository.findByReviewSeq(rDTO.getReviewSeq());

        // 엔티티의 값들을 DTO에 맞게 넣어주기
        ReviewDTO eDTO = new ObjectMapper().convertValue(rEntity, ReviewDTO.class);

        log.info(this.getClass().getName() + ".getReviewInfo End!");

        return eDTO;
    }

    @Override
    public void updatereviewInfo(ReviewDTO eDTO) {
        log.info(this.getClass().getName() + ".updatereviewInfo Start!");

        Long reviewSeq = eDTO.getReviewSeq();

        String title = CmmUtil.nvl(eDTO.getTitle());
        String contents = CmmUtil.nvl(eDTO.getContents());
        String userId = CmmUtil.nvl(eDTO.getUserId());

        log.info("reviewSeq : " + reviewSeq);
        log.info("title : " + title);
        log.info("contents : " + contents);
        log.info("userId : " + userId);

        // 현재 공지사항 조회수 가져오기
        ReviewEntity rEntity = reviewRepository.findByReviewSeq(reviewSeq);

        // 수정할 값들을 빌더를 통해 엔티티에 저장하기
        ReviewEntity eEntity = ReviewEntity.builder()
                .reviewSeq(reviewSeq).title(title).contents(contents).userId(userId)
                .readCnt(rEntity.getReadCnt())
                .build();

        // 데이터 수정하기
        reviewRepository.save(eEntity);

        log.info(this.getClass().getName() + ".updatereviewInfo End!");

    }

    @Override
    public void deletereviewInfo(ReviewDTO pDTO) {
        log.info(this.getClass().getName() + ".deletereviewInfo Start!");

        Long reviewSeq = pDTO.getReviewSeq();

        log.info("noticeSeq : " + reviewSeq);

        // 데이터 수정하기
        reviewRepository.deleteById(reviewSeq);


        log.info(this.getClass().getName() + ".deletereviewInfo End!");

    }

    @Override
    public void InsertreviewInfo(ReviewDTO eDTO) {
        log.info(this.getClass().getName() + ".InsertreviewInfo Start!");

        String title = CmmUtil.nvl(eDTO.getTitle());
        String contents = CmmUtil.nvl(eDTO.getContents());
//        String userId = CmmUtil.nvl(eDTO.getUserId());

        log.info("title : " + title);
        log.info("contents : " + contents);
//        log.info("userId : " + userId);

        ReviewEntity rEntity = ReviewEntity.builder()
                .title(title).userId(eDTO.getUserId()).contents(contents).readCnt(0L)
                .regDt(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"))
                .chgDt(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"))
                .build();

        // 공지사항 저장하기
        reviewRepository.save(rEntity);

        log.info(this.getClass().getName() + ".InsertNoticeInfo End!");

    }
}
