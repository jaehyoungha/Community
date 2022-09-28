package kopo.poly.service;

import kopo.poly.dto.ReviewDTO;

import java.util.List;

public interface IReviewService {

    ReviewDTO getReviewInfo(ReviewDTO rDTO, boolean type) throws Exception;

    List<ReviewDTO> getReviewList();
    
    void updatereviewInfo(ReviewDTO eDTO);

    void deletereviewInfo(ReviewDTO pDTO);

    void InsertreviewInfo(ReviewDTO eDTO);
}