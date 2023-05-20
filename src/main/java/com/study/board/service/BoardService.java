package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired//new를 써야하지만, 스프링부트가 알아서 읽어와서 주입을해준다.
    private BoardRepository boardRepository;
    //글작성 처리
    public void write(Board board){
    boardRepository.save(board);

    }
    //게시물 리스트 처리
    public List<Board> boardList(){
        //findAll : 테스트보드라는 클래스가 담긴 List를 반환하는것을 확인할수있다.
        return boardRepository.findAll();
    }
    
    //특정 게시물 불러오기
    public Board boardView(Integer id){
        System.out.println(boardRepository.findById(id).get());
        return boardRepository.findById(id).get();

    }

    //글삭제2
    //특정게시글삭제
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);

    }
}
