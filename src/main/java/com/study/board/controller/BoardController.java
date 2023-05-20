package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") //localhost:8090/board/write
    public String boardWriteForm(){
        return"boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model){
        boardService.write(board);
        model.addAttribute("message","글작성이 완료되었습니다. ");
        model.addAttribute("searchUrl","/board/list");
        return "message";
    }
    @GetMapping("/board/list")
    public String boardList(Model model){
        //BoardService에서 만들어준 boardList가 반환되는데, list라는 이름으로 받아서 넘기겠다는 뜻
        model.addAttribute("list",boardService.boardList());
        return "boardList";
    }

    @GetMapping("/board/view") //localhost:8090/board/view?id=1 //(get방식 파라미터)
    public String boardView(Model model, Integer id){
        /*상세페이지4*/
        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id){
        // 서비스를 통해 게시물 삭제 처리
        boardService.boardDelete(id);
        //게시물삭제하고 게시물리스트로 넘어가야하므로
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model){
        model.addAttribute("board",boardService.boardView(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id,Board board){
        // 기존의 객체를 불러온다
        Board boardtemp = boardService.boardView(id);
        //새로운 객체를 덮어씌운다
        boardtemp.setTitle(board.getTitle());
        boardtemp.setContent(board.getContent());
        boardService.write(boardtemp);
        return "redirect:/board/list";
    }
}
