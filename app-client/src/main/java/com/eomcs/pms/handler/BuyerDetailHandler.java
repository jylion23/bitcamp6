package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.util.Prompt;

public class BuyerDetailHandler implements Command {
  BuyerDao buyerDao;
  public BuyerDetailHandler (BuyerDao buyerDao) {
    this.buyerDao = buyerDao;
  } 

  @Override
  public void execute(CommandRequest request) throws Exception {
    if (ClientApp.getLoginUser().getAuthority()!=Menu.ACCESS_ADMIN) {
      System.out.println("[개인정보 상세보기]");

      String id = ClientApp.getLoginUser().getId();

      Buyer buyer = buyerDao.findById(id);

      //      Buyer buyer = (Buyer) findById(ClientApp.getLoginUser().getId());
      System.out.printf("이름 : %s\n", buyer.getName());
      System.out.printf("닉네임 : %s\n", buyer.getNickname());
      System.out.printf("이메일 : %s\n", buyer.getEmail());
      System.out.printf("생일 : %s\n", buyer.getBirthday());
      System.out.printf("사진 : %s\n", buyer.getPhoto());
      System.out.printf("전화 : %s\n", buyer.getPhoneNumber());
      System.out.printf("주소 : %s\n", buyer.getAddress());
      System.out.printf("등록일 : %s\n", buyer.getRegisteredDate());
      System.out.printf("권한등급 : %d\n", buyer.getAuthority());

      while(true) {
        System.out.println("\n개인정보변경(U) / 회원탈퇴(D) / 이전(0)");
        String choose = Prompt.inputString("선택 > ");
        System.out.println();
        switch (choose) {
          case "u":
          case "U": request.getRequestDispatcher("/buyer/update").forward(request);return;
          case "d":
          case "D": request.getRequestDispatcher("/buyer/delete").forward(request);return;
          case "0": return;
        }
      }

    } else {
      System.out.println("[구매자 상세보기] || 이전(0)");
      String id = (String)request.getAttribute("id");

      Buyer buyer = buyerDao.findById(id);

      if (buyer == null) {
        System.out.println("해당 아이디를 갖는 회원이 없습니다.\n");
        return;
      }
      System.out.printf("회원번호 : %s\n", buyer.getNumber());
      System.out.printf("이름 : %s\n", buyer.getName());
      System.out.printf("닉네임 : %s\n", buyer.getNickname());
      System.out.printf("등급 : %s\n", buyer.getLevel());
      System.out.printf("이메일 : %s\n", buyer.getEmail());
      System.out.printf("사진 : %s\n", buyer.getPhoto());
      System.out.printf("전화 : %s\n", buyer.getPhoneNumber());
      System.out.printf("주소 : %s\n", buyer.getAddress());
      System.out.printf("등록일 : %s\n", buyer.getRegisteredDate());
      request.setAttribute("id", buyer.getId());

      while(true) {
        System.out.println("\n등급변경(U) / 회원탈퇴(D)");
        String choose = Prompt.inputString("선택 > ");
        System.out.println();
        switch (choose) {
          case "u":
          case "U": request.getRequestDispatcher("/buyer/update").forward(request); return;
          case "d":
          case "D": request.getRequestDispatcher("/buyer/delete").forward(request); return;
          case "0": return;
        }
      }
    }
  }
}







