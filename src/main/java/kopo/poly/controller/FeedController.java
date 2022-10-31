package kopo.poly.controller;

import kopo.poly.dto.FeedDTO;
import kopo.poly.service.IFeedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class FeedController {

    @Resource(name = "FeedService")
    private IFeedService feedService;

    private String alt_title = "";
    private String alt_state = "";
    private String msg = "";
    private String url = "";

@GetMapping(value = "feedList")
    public String feedList(ModelMap model) throws Exception{
    log.info(this.getClass().getName()+ ".feedList Start!!!");

    List<FeedDTO> rList = feedService.getFeedList();

    if (rList == null) {
        rList = new ArrayList<FeedDTO>();
    }

    model.addAttribute("rList" , rList);

    rList = null;

    log.info(this.getClass().getName()+ ".feedList Start!!!");
    return "feed/feedList";
}
@GetMapping(value = "feedRegForm")
public String feedRegForm() {
    log.info(this.getClass().getName()+ ".feedRegForm Start!!");
    log.info(this.getClass().getName()+ ".feedRegForm Start!!");
    return "feed/feedReg";
}

@GetMapping(value = "feedReg")
    public String feedReg(FeedDTO fDTO, ModelMap model) throws Exception {
    log.info(this.getClass().getName()+ ".feedReg Start!!");

    try{
        
    }

    log.info(this.getClass().getName()+ ".feedReg End!!");
}
}
