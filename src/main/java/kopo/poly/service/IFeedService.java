package kopo.poly.service;

import kopo.poly.dto.FeedDTO;
import kopo.poly.dto.ReviewDTO;

import java.util.List;

public interface IFeedService {

    List<FeedDTO> getFeedList(); // 피드 보여주기

    void updateFeedInfo(FeedDTO fDTO); // 피드 수정

    void deleteFeedInfo(FeedDTO fDTO); // 피드 삭제

    void InsertFeedInfo(FeedDTO fDTO); // 피드 작성
}
