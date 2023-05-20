package com.study.board.repository;

import com.study.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//어떤 엔티티를 넣을 것이냐 어떤 ID를 넣을 것이냐
//어떤엔티티를 넣을것이냐, 엔티티의 id의 타입
@Repository
public interface BoardRepository extends JpaRepository <Board,Integer>{
    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);

}
