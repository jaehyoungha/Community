package kopo.poly.repository;

import kopo.poly.repository.Entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  IReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findAllByOrderByReviewSeqDesc();


    ReviewEntity findByReviewSeq(Long reviewSeq);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE REVIEW A SET A.READ_CNT =IFNULL(A.READ_CNT,0) + 1 WHERE A.REVIEWSEQ = :reviewSeq",
            nativeQuery = true)
    int updateReadCnt(@Param("reviewSeq") Long reviewSeq);

}
