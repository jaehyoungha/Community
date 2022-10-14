package kopo.poly.controller;

import kopo.poly.service.IReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Slf4j
@Controller
public class FeedController {

    @Resource(name = "NoticeService")
    private IReviewService reviewService;
}
