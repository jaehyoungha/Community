package kopo.poly.controller;

import kopo.poly.dto.ReviewDTO;
import kopo.poly.service.IReviewService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ReviewController {

    @Resource (name = "ReviewService")
    private IReviewService reviewService;

    private String alt_title = "";
    private String alt_state = "";
    private String msg = "";
    private String url = "";
    @GetMapping(value = "main")
    public String mainPage() {
        log.info(this.getClass().getName()+ ".mainPage Start!!");
        log.info(this.getClass().getName()+ ".mainPage End!!");
        return "/main";
    }
    @GetMapping(value = "reviewList")
    public String reivewList(ModelMap model) {

        log.info(this.getClass().getName() + ".reviewList start!");

        // 리뷰 리스트 가져오기
        List<ReviewDTO> rList = reviewService.getReviewList();

        if (rList == null) {
            rList = new ArrayList<ReviewDTO>();

        }

        // 조회된 리스트 넣어주기
        model.addAttribute("rList", rList);

        rList = null;
        log.info(this.getClass().getName() + ".review end!");

        return "/reviewList";
    }

    @GetMapping(value = "reviewEditInfo")
    public String noticeEditInfo(HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName() + ".reviewEditInfo Start!");

        try {

            String rSeq = CmmUtil.nvl(request.getParameter("reviewSeq")); // 공지글번호(PK)

            log.info("rSeq : " + rSeq);

            ReviewDTO rDTO = new ReviewDTO();

            rDTO.setReviewSeq(Long.parseLong(rSeq));

            ReviewDTO eDTO = reviewService.getReviewInfo(rDTO, false);

            if (eDTO == null) {
                eDTO = new ReviewDTO();

            }

            model.addAttribute("eDTO", eDTO);

        } catch (Exception e) {
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".reviewEditInfo end!");

            // 결과 메시지 전달하기
            model.addAttribute("msg", msg);

        }

        log.info(this.getClass().getName() + ".noticeEditInfo end!");

        return "/reviewEditInfo";
    }

    /**
     * 게시판 글 수정
     */
    @PostMapping(value = "reivewUpdate")
    public String reviewUpdate(HttpSession session, ReviewDTO rDTO, ModelMap model) {

        log.info(this.getClass().getName() + ".reivewUpdate Start!");

        String msg = "";

        try {

            log.info("user_id : " + rDTO.getUserId());
            log.info("nSeq : " + rDTO.getReviewSeq());
            log.info("title : " + rDTO.getTitle());
            log.info("contents : " + rDTO.getContents());

            ReviewDTO eDTO = new ReviewDTO();

            eDTO.setUserId(rDTO.getUserId());
            eDTO.setReviewSeq(rDTO.getReviewSeq());
            eDTO.setTitle(rDTO.getTitle());
            eDTO.setContents(rDTO.getContents());

            // 게시글 수정하기 DB
            reviewService.updatereviewInfo(eDTO);

            msg = "수정되었습니다.";

        } catch (Exception e) {
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".reivewUpdate End!");

            // 결과 메시지 전달하기
            model.addAttribute("msg", msg);

        }

        return "/sweetalert";
    }

    /**
     * 게시판 글 삭제
     */
    @GetMapping(value = "reviewDelete")
    public String reivewDelete(HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName() + ".reviewDelete Start!");

        String msg = "";

        try {

            String rSeq = CmmUtil.nvl(request.getParameter("reviewSeq")); // 글번호(PK)

            log.info("rSeq : " + rSeq);

            ReviewDTO pDTO = new ReviewDTO();

            pDTO.setReviewSeq(Long.parseLong(rSeq));

            // 게시글 삭제하기 DB
            reviewService.deletereviewInfo(pDTO);

            msg = "삭제되었습니다.";

        } catch (Exception e) {
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".reviewDelete End!");

            // 결과 메시지 전달하기
            model.addAttribute("msg", msg);

        }

        return "/sweetalert";
    }
    @GetMapping(value = "reviewReg")
    public String reviewReg() {

        log.info(this.getClass().getName() + ".reviewReg Start!");

        log.info(this.getClass().getName() + ".reviewReg End!");

        return "/reviewReg";
    }

    @PostMapping(value = "reviewInsert")
    public String reviewInsert(HttpSession session, ReviewDTO rDTO, ModelMap model) { //리뷰 등록

        log.info(this.getClass().getName() + ".reviewInsert Start!");

        try {
//            String userid = CmmUtil.nvl((String) session.getAttribute("SESSION_USERID"));

            log.info("title : " + rDTO.getTitle());
            log.info("contents : " + rDTO.getContents());
            //TODO 나중에 게이트웨이 만들면 삭제
            session.setAttribute("userId","ha");
            ReviewDTO eDTO = new ReviewDTO();

            eDTO.setUserId((String) session.getAttribute("userId"));
            eDTO.setTitle(rDTO.getTitle());
            eDTO.setContents(rDTO.getContents());


            reviewService.InsertreviewInfo(eDTO);
            alt_title = "리뷰 작성";
            msg = "리뷰등록 성공";
            alt_state = "success";
            url = "/reviewList";

        } catch (Exception e) {
            alt_title = "리뷰 작성";
            msg = "리뷰등록 실패";
            alt_state = "fail";
            url = "/reviewReg";

        } finally {
            log.info(this.getClass().getName() + ".reviewInsert End!");

            model.addAttribute("alt_title", alt_title);
            model.addAttribute("alt_state", alt_state);
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);
        }

        return "/sweetalert";
    }
}
