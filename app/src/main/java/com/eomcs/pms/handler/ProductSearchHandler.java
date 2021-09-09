package com.eomcs.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Seller;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class ProductSearchHandler extends AbstractProductHandler {

  StockPrompt stockPrompt;
  ProductPrompt productPrompt;
  List<Seller> sellerPrivacyList;
  List<Product> productList;
  SellerPrompt sellerPrompt;
  public ProductSearchHandler(ProductPrompt productPrompt,  StockPrompt stockPrompt, List<Seller> sellerPrivacyList,  List<Product> productList, SellerPrompt sellerPrompt) {
    this.productPrompt = productPrompt;
    this.stockPrompt = stockPrompt;
    this.sellerPrivacyList = sellerPrivacyList;
    this.productList = productList;
    this.sellerPrompt = sellerPrompt;
  }

  @Override
  public void execute() {
    System.out.println("[상품검색]");

    HashMap<String, Seller> sellerInfo = sellerPrompt.findByAdress(Prompt.inputString("주소입력: "));   

    Product productName  = productPrompt.findByProduct(Prompt.inputString("상품입력: "));   

    System.out.println("==========상품 목록==========");

    while(true) {
      int size = 1;
      for(Product product : productList) {
        if(product.getProductName().equals(productName.getProductName())) {
          System.out.printf("상품번호: %d \n", size++);
          System.out.printf("상품명: %s\n", product.getProductName());
          System.out.printf("주종: %s\n", product.getProductType());
          System.out.printf("원산지: %s\n", product.getCountryOrigin());
          System.out.printf("품종: %s\n", product.getVariety());
          System.out.printf("알콜도수: %.2f\n", product.getAlcoholLevel()); 
          System.out.printf("당도: %d, 산도: %d, 바디감:%d\n", product.getSugerLevel(),product.getAcidity(),product.getWeight());
        }
      }

      System.out.println("===== 해당 주소 근처 판매처 ===== ");

      for (HashMap.Entry<String, Seller> entry : sellerInfo.entrySet()) {
        System.out.printf("가게명 : %s, 가게주소 : %s", 
            entry.getValue().getBusinessName(),
            entry.getValue().getBusinessAddress());
      }

      //구매자 id의 cartList에 상품 담기.
      int stocks = Prompt.inputInt("수량 : ");
      HashMap<String, Stock> hasoStock = stockPrompt.findBySellerId(Prompt.inputString("상품먕 : "));

      String storeName = Prompt.inputString("가게명을 선택하세요 >");


      //      for(SellerPrivacy member : sellerPrivacyList) {
      //        if(member.getStockList() == productName) {
      //          System.out.printf("상점 이름 : %s \n", member.getBusinessName());
      //          System.out.printf("상점 주소 : %s \n", member.getAddress());
      //          //          System.out.printf("재고 수량 : %d개 \n", member.getStockList().stocks());
      //          return;
      //        }
      //    }

      //      if(member.getStockList() == null) {
      //        System.out.println("해당 상품을 갖는 판매자가 없습니다.");
      //        return;
      //      }

    }
  }
}













