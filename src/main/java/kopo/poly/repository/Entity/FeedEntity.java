package kopo.poly.repository.Entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Feed")
@DynamicUpdate
@DynamicInsert
@Builder
@Cacheable
@Entity
public class FeedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name =  "FEED_SEQ")
    private Long feedSeq;

    @Column(name = "MARKET_ID",updatable = false)
    private String marketId;

    @Column(name = "REG_ID", nullable = false)
    private String regId;

    @Column(name = "REG_DT", nullable = false)
    private String regDt;

    @Column(name = "CHG_ID", nullable = false)
    private String chgId;

    @Column(name = "CHG_DT", nullable = false)
    private String chgDt;

    @NonNull
    @Column(name = "FEED_CONTENTS",nullable = false)
    private String feedContents;

}
