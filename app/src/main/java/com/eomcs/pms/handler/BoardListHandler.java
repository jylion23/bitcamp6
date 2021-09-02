package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Board;

public class BoardListHandler extends AbstractBoardHandler {
  @Override
  public void execute(int i) {}

  public BoardListHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void execute() {
    System.out.println("[게시글 목록]");

    Board[] boards = new Board[boardList.size()];

    boardList.toArray(boards);

    for (Board board : boards) {
      System.out.printf("%d, %s, %s, %s, %d, %s, %d\n", 
          board.getNumber(), 
          board.getTitle(), 
          board.getWriter(),
          board.getRegistrationDate(),
          board.getViews(), 
          board.getTag(), 
          board.getLikes());
    }
  }
}






