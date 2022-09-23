package kopo.poly.repository;

import kopo.poly.repository.Entity.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommunityRepository extends JpaRepository<CommunityEntity, Long> {
    List<CommunityEntity> findAllByOrderByNoticeSeqDesc();

    CommunityEntity findByNoticeSeq(Long noticeSeq);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE COMMUNITY A SET A.READ_CNT =IFNULL(A.READ_CNT,0) + 1 WHERE A.NOTICESEQ = :noticeSeq",
    nativeQuery = true)
    int updateReadCnt(@Param("noticeSeq") Long noticeSeq);
}
