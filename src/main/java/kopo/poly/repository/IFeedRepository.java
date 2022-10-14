package kopo.poly.repository;

import kopo.poly.repository.Entity.FeedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFeedRepository extends JpaRepository<FeedEntity, String> {
}
