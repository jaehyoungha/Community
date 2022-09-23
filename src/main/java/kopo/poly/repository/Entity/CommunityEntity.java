package kopo.poly.repository.Entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "Community")
@DynamicUpdate
@DynamicInsert
@Builder
@Cacheable
@Entity
public class CommunityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name =  "noticeSeq")
    private long noticeSeq;

    @NonNull
    @Column(name = "title",length = 500,nullable = false)
    private String title;

    @NonNull
    @Column(name = "contents",nullable = false)
    private String contents;

    @NonNull
    @Column(name = "userId",updatable = false)
    private String userId;

    @Column(name = "readCnt",nullable = false)
    private String reaCnt;

    @Column(name = "regDt", nullable = false)
    private String regDt;

    @Column(name = "chgDt")
    private String chgDt;
}
