package com.eomcs.pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;
import com.eomcs.request.RequestAgent;

public class NetBoardDao implements BoardDao{

  RequestAgent requestAgent;

  public NetBoardDao(RequestAgent requestAgent) {
    this.requestAgent =  requestAgent;
  }

  @Override
  public void insert(Board board) throws Exception {
    requestAgent.request("addNumber.board", null);
    int no = requestAgent.getObject(Integer.class);
    board.setBoardNumber(no);

    requestAgent.request("board.insert", board);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("게시글 데이터 저장 실패");
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    requestAgent.request("board.selectList", null);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 데이터 조회 실패");
    }
    return new ArrayList<>(requestAgent.getObjects(Board.class));
  }

  @Override
  public void insert(Comment comment) throws Exception {
    requestAgent.request("board.comment.insert", comment);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("댓글 데이터 저장 실패");   }
  }

  @Override
  public List<Comment> findAll(int boardNo) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("boardNo", String.valueOf(boardNo));

    requestAgent.request("board.comment.SelectList", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(requestAgent.getObject(String.class));
      return null;
      //      throw new Exception("댓글 데이터 조회 실패");
    }
    return new ArrayList<>(requestAgent.getObjects(Comment.class));
  }

  @Override
  public Board findByNo(int no) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("board.selectOne", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      return null;
    }
    return requestAgent.getObject(Board.class);
  }

  @Override
  public Board findByBoard(String keyword) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("keyword", keyword);

    requestAgent.request("board.selectOne", params);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      return null;
    }
    return requestAgent.getObject(Board.class);
  }

  @Override
  public void update(Board board) throws Exception {
    requestAgent.request("board.update", board);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 데이터 변경 실패");
    }
  }

  @Override
  public void delete(int no) throws Exception {    
    HashMap<String, String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("board.delete", params);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 데이터 삭제 실패");
    }
  }
}
