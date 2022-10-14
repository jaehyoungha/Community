package kopo.poly.repository.Entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Notice")
@DynamicUpdate
@DynamicInsert
@Builder
@Cacheable
@Entity
public class FeedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name =  "FEED_SEQ")
    private Long noticeSeq;

    @NonNull
    @Column(name = "d",length = 500,nullable = false)
    private String title;

    @NonNull
    @Column(name = "contents",nullable = false)
    private String contents;


    @Column(name = "userId",updatable = false)
    private String userId;

    @Column(name = "readCnt",nullable = false)
    private Long readCnt;

    @Column(name = "regDt", nullable = false)
    private String regDt;

    @Column(name = "chgDt")
    private String chgDt;
}
