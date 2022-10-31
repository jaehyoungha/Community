package kopo.poly.repository;

import kopo.poly.repository.Entity.FeedEntity;
import kopo.poly.repository.Entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFeedRepository extends JpaRepository<FeedEntity, Long> {
    List<FeedEntity> findAllByOrderByFeedSeqDesc();

    FeedEntity findByFeedSeq(Long feedSeq);

}
