package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.util.Prompt;

public class BookingDetailHandler extends AbstractBookingHandler {

  public BookingDetailHandler(List<Booking> bookList) {
    super(bookList);
  }


  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == Menu.ACCESS_LOGOUT || App.getLoginUser().getAuthority() == Menu.ACCESS_ADMIN) {
      System.out.println("권한이 없습니다. 구매자 또는 판매자 기능입니다.");
      return;
    }
    System.out.println("[예약 상세보기]");

    Booking book = findBooking(Prompt.inputString("상품명 : "));

    if (book == null) {
      System.out.println("예약이 없는 상품입니다.");
      return;
    }

    System.out.printf("예약번호 : %s", book.getBookingNumber());
    System.out.printf("상품명 : %s", book.getCart().getStock().getProduct().getProductName());
    System.out.printf("결제 금액 : %d", book.getCart().getCartPrice());
    System.out.printf("예약시간 : %s\n", book.getReservation());
  }

}






