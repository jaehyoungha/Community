package kopo.poly.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kopo.poly.dto.FeedDTO;
import kopo.poly.dto.ReviewDTO;
import kopo.poly.repository.Entity.FeedEntity;
import kopo.poly.repository.IFeedRepository;
import kopo.poly.service.IFeedService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@Service ("FeedService")
public class FeedService implements IFeedService {

    private final IFeedRepository feedRepository;


    @Override
    public List<FeedDTO> getFeedList() {
        log.info(this.getClass().getName()+ ".getFeedList Start!!");

        List<FeedEntity> fList = feedRepository.findAllByOrderByFeedSeqDesc();

        List<FeedDTO> nList = new ObjectMapper().convertValue(fList,
                new TypeReference<List<FeedDTO>>() {
                });

        log.info(this.getClass().getName()+ ".getFeedList End!!");
        return nList;
    }
    @Transactional
    @Override
    public void updateFeedInfo(FeedDTO fDTO) {

    }

    @Override
    public void deleteFeedInfo(FeedDTO fDTO) {

    }

    @Override
    public void InsertFeedInfo(FeedDTO fDTO) {
        log.info(this.getClass().getName() + ".InsertFeedInfo Start!!");

        String feedContents = CmmUtil.nvl(fDTO.getFeedContents());

        log.info("feedContents : " + feedContents);

        FeedEntity fEntity = FeedEntity.builder()
                        .feedContents(feedContents).marketId(fDTO.getMarketId())
                        .regDt(DateUtil.getDateTime("yyyy-MM-dd"))
                                .chgDt(DateUtil.getDateTime("yyyy-MM-dd"))
                                        .build();

        feedRepository.save(fEntity);

        log.info(this.getClass().getName() + ".InsertFeedInfo End!!");
    }
}
