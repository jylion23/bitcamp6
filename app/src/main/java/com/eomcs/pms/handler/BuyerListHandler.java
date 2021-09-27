package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class BuyerListHandler extends AbstractBuyerHandler {
  public BuyerListHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    Loop : while(true) {
      System.out.println("[회원 목록] || 이전(0)\n");
      System.out.printf("%-3s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\n",
          "회원번호", "아이디", "이름", "닉네임", "등급","등록일");
      System.out.println("--------------------------------------------------------------------------");

      for (Member buyer : memberList) {
        if (buyer instanceof Buyer) {
          System.out.printf("%-6d\t%-6s\t%-6s\t%-6s\t%-6d\t%-6s\n", 
              buyer.getNumber(),
              buyer.getId(), 
              buyer.getName(),
              buyer.getNickname(),
              buyer.getLevel(),
              buyer.getRegisteredDate());
        }
      }
      System.out.println();
      while (true) {
        String choose = Prompt.inputString("선택할 아이디 : ");
        System.out.println();
        request.setAttribute("Id", choose);
        if (choose.equals("0")) {return;}
        request.getRequestDispatcher("/buyer/detail").forward(request);
        continue Loop;
      }
    }
  }
}







