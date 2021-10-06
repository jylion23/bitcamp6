package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BookingDao;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;

public class BookingDetailHandler implements Command {
  BookingDao bookingDao;
  BuyerDao buyerDao;
  SellerDao sellerDao;

  public BookingDetailHandler(BookingDao bookingDao, BuyerDao buyerDao, SellerDao sellerDao) {
    this.bookingDao = bookingDao;
    this.buyerDao = buyerDao;
    this.sellerDao = sellerDao;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();
    if (ClientApp.getLoginUser().getAuthority()==Menu.ACCESS_BUYER) {
      System.out.println("[내 픽업 예약 상세보기]");

      int No = Prompt.inputInt("예약번호 :");
      Booking booking = bookingDao.findByNoId(No, nowLoginId);

      //      BookingList bookingList = null;

      if (booking == null) {
        System.out.println("해당 상품의 예약이 없습니다.");
        return;
      }

      String sellerId = booking.getCart().getSellerId();
      Seller seller = sellerDao.findById(sellerId);

      System.out.printf("가게명: %s\n", seller.getBusinessName());
      System.out.printf("판매자: %s\n", seller.getNickname());
      System.out.printf("가게주소: %s\n", seller.getBusinessAddress());
      System.out.printf("가게번호: %s\n", seller.getBusinessPlaceNumber());
      System.out.printf("상품명: %s\n", booking.getCart().getStock().getProduct().getProductName());
      System.out.printf("수량: %d\n", booking.getBookingStocks());
      System.out.printf("금액: %d원\n",  booking.getBookingPrice());
      System.out.println();

      request.setAttribute("bookingNo", No);

      while(true) {
        System.out.println(" 예약변경(U) / 예약취소(D) / 이전(0)");
        String choose = Prompt.inputString("선택 > ");
        System.out.println();
        //                bookingList = bookingDao.findAll(id).getBooking();
        //        bookingList = bookingDao.findAll(nowLoginId);
        switch(choose) {
          case "u" :
          case "U" : 
            request.getRequestDispatcher("/booking/update").forward(request); return;
            //          case "R" : if(bookingList.get(0).isConfirm() == false) {
            //            request.getRequestDispatcher("/booking/update").forward(request); return;
            //          } { System.out.println("이미 픽업을 완료한 상품입니다."); return;}
          case "d" :
          case "D" : 
            request.getRequestDispatcher("/booking/delete").forward(request); return;
            //          case "D" : if(bookingList.get(0).isConfirm() == false) {
            //            request.getRequestDispatcher("/booking/delete").forward(request); return;
            //          } { System.out.println("이미 픽업을 완료한 상품입니다."); return;}
          case "0" : return;
        }
      }


    } else if (ClientApp.getLoginUser().getAuthority()==Menu.ACCESS_SELLER) {
      System.out.println("[고객 예약 상세보기]");

      int No = Prompt.inputInt("예약번호 :");
      String id =ClientApp.getLoginUser().getId();
      Booking booking = bookingDao.findByNoId(No, id);

      if (booking == null) {
        System.out.println("해당 상품의 예약이 없습니다.");
        return;
      }

      Buyer buyer = buyerDao.findById(booking.getCart().getId());

      System.out.printf("예약자: %s\n", booking.getId());
      System.out.printf("연락처: %s\n", buyer.getPhoneNumber());
      System.out.printf("상품명: %s\n", booking.getCart().getStock().getProduct().getProductName());
      System.out.printf("수량: %d\n", booking.getBookingStocks());
      System.out.printf("금액: %d원\n",  booking.getBookingPrice());

      System.out.println();


      System.out.println("");

      request.setAttribute("bookingNo", No);
      while(true) {
        System.out.println("\n 예약변경(U) / 예약확정(C) / 이전(0)");
        String choose = Prompt.inputString("선택 > ");
        System.out.println();
        //        BookingList bookingList = bookingDao.findAll(id);
        switch(choose) {
          case "u" :
          case "U" : request.getRequestDispatcher("/booking/update").forward(request); return;
          //          case "U" : if(bookingList.get(0).isConfirm()==false){
          //            request.getRequestDispatcher("/booking/update").forward(request); return;
          //          }{ System.out.println("픽업이 완료된 예약은 변경이 불가합니다."); continue; }
          //          case "c" :
          //          case "C" :
          //            if(bookingList.get(0).isConfirm() == false) {
          //              String input = Prompt.inputString("정말 예약을 확정하시겠습니까?(y/N) ");
          //              if(input.equalsIgnoreCase("y")) {
          //                bookingList.get(0).setConfirm(true);
          //                bookingList.get(1).setConfirm(true);
          //                System.out.println("예약확정을 완료했습니다.\n");  
          //                return;
          //              } else if (input.equalsIgnoreCase("n")) {
          //                System.out.println("예약 확정을 취소하였습니다.\n");
          //                return;
          //              }
          //            }
          //            if(bookingList.get(0).isConfirm() == true) {
          //              String input = Prompt.inputString("정말 예약 확정을 취소하시겠습니까?(y/N) ");
          //              if(input.equalsIgnoreCase("y")) {
          //                bookingList.get(0).setConfirm(false);
          //                bookingList.get(1).setConfirm(false);
          //                System.out.println("예약확정을 취소했습니다.\n");  
          //                return;
          //              } else if (input.equalsIgnoreCase("n")) {
          //                System.out.println("취소하였습니다.\n");
          //                return;
          //              }
          //            } System.out.println("잘못입력하셨습니다."); continue;
          case "0" : return;
        }
      }
    }
  }
}
