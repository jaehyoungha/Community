package kopo.poly.controller;

import kopo.poly.dto.ReviewDTO;
import kopo.poly.service.ICommunityService;
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

    @Resource (name = "CommunityService")
    private ICommunityService communityService;

    @GetMapping(value = "noticeList")
    public String noticeList(ModelMap model) {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".reviewList start!");

        // 공지사항 리스트 가져오기
        List<ReviewDTO> rList = communityService.getReviewList();

        if (rList == null) {
            rList = new ArrayList<ReviewDTO>();

        }

        // 조회된 리스트 결과값 넣어주기
        model.addAttribute("rList", rList);

        // 변수 초기화(메모리 효율화 시키기 위해 사용함)
        rList = null;

        // 로그 찍기(추후 찍은 로그를 통해 이 함수 호출이 끝났는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".review end!");

        // 함수 처리가 끝나고 보여줄 JSP 파일명(/WEB-INF/view/notice/NoticeList.jsp)
        return "/reviewList";
    }


    /**
     * 게시판 상세보기
     */
    @GetMapping(value = "reviewInfo")
    public String reviewInfo(HttpServletRequest request, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + ".reviewInfo Start!");

        /*
         * 게시판 글 등록되기 위해 사용되는 form객체의 하위 input 객체 등을 받아오기 위해 사용함
         */
        String rSeq = CmmUtil.nvl(request.getParameter("reviewSeq")); // 공지글번호(PK)

        /*
         * ####################################################################################
         * 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함 반드시 작성할 것
         * ####################################################################################
         */
        log.info("rSeq : " + rSeq);

        /*
         * 값 전달은 반드시 DTO 객체를 이용해서 처리함 전달 받은 값을 DTO 객체에 넣는다.
         */
        ReviewDTO rDTO = new ReviewDTO();
        rDTO.setReviewSeq(Long.parseLong(rSeq));

        // 공지사항 상세정보 가져오기
        ReviewDTO eDTO = communityService.getReviewInfo(rDTO, true);

        if (eDTO == null) {
            eDTO = new ReviewDTO();

        }

        // 조회된 리스트 결과값 넣어주기
        model.addAttribute("eDTO", eDTO);


        log.info(this.getClass().getName() + ".reviewInfo End!");

        return "/reviewInfo";
    }

    @GetMapping(value = "reviewEditInfo")
    public String noticeEditInfo(HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName() + ".reviewEditInfo Start!");

        String msg = "";

        try {

            String rSeq = CmmUtil.nvl(request.getParameter("reviewSeq")); // 공지글번호(PK)

            log.info("rSeq : " + rSeq);

            ReviewDTO rDTO = new ReviewDTO();

            rDTO.setReviewSeq(Long.parseLong(rSeq));

            ReviewDTO eDTO = communityService.getReviewInfo(rDTO, false);

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
            communityService.updatereviewInfo(eDTO);

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

        return "/MsgToList";
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
            communityService.deletereviewInfo(pDTO);

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

        return "/MsgToList";
    }

    /**
     * 게시판 작성 페이지 이동
     * <p>
     * 이 함수는 게시판 작성 페이지로 접근하기 위해 만듬. WEB-INF 밑에 존재하는 JSP는 직접 호출할 수 없음 따라서 WEB-INF 밑에
     * 존재하는 JSP를 호출하기 위해서는 반드시 Controller 등록해야함
     * <p>
     * GetMapping(value = "notice/NoticeReg") =>  GET방식을 통해 접속되는 URL이 notice/NoticeReg인 경우 아래 함수를 실행함
     */
    @GetMapping(value = "reivewReg")
    public String reviewReg() {

        log.info(this.getClass().getName() + ".reviewReg Start!");

        log.info(this.getClass().getName() + ".reviewReg End!");

        return "/reviewReg";
    }

    /**
     * 게시판 글 등록
     */
    @PostMapping(value = "reviewInsert")
    public String reviewInsert(HttpSession session, ReviewDTO rDTO, ModelMap model) {

        log.info(this.getClass().getName() + ".reviewInsert Start!");

        String msg = "";

        try {
            /*
             * 게시판 글 등록되기 위해 사용되는 form객체의 하위 input 객체 등을 받아오기 위해 사용함
             */
            String userid = CmmUtil.nvl((String) session.getAttribute("SESSION_USERID"));

            /*
             * ####################################################################################
             * 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함 반드시 작성할 것
             * ####################################################################################
             */
            log.info("user_id : " + rDTO.getUserId());
            log.info("title : " + rDTO.getTitle());
            log.info("contents : " + rDTO.getContents());

            ReviewDTO eDTO = new ReviewDTO();

            eDTO.setUserId(rDTO.getUserId());
            eDTO.setTitle(rDTO.getTitle());
            eDTO.setContents(rDTO.getContents());

            /*
             * 게시글 등록하기위한 비즈니스 로직을 호출
             */
            communityService.InsertreviewInfo(eDTO);

            // 저장이 완료되면 사용자에게 보여줄 메시지
            msg = "등록되었습니다.";


        } catch (Exception e) {

            // 저장이 실패되면 사용자에게 보여줄 메시지
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".reviewInsert End!");

            // 결과 메시지 전달하기
            model.addAttribute("msg", msg);

        }

        return "/MsgToList";
    }
}
