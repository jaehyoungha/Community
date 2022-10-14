package kopo.poly.service.impl;

import kopo.poly.repository.IFeedRepository;
import kopo.poly.service.IFeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service ("NoticeService")
public class FeedService implements IFeedService {

    private final IFeedRepository noticeRepository;

}
