<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
<style> 
.col-sm-10 {
position: absolute;
  xborder : 2px solid red;
  border-radius:50%; 
  width : 50px;
  height: 50px;
}
.col-sm-10 img {
    position: absolute;
    border-radius:50%; 
    top: 0;
    left: 0;
    width: 50px;
    height: 50px;
    object-fit: fill;
}

.review-content {
  position : relative;
  top : 10px;
  left : 65px;
  width : 800px;
  xborder : 2px solid green;
}
/* @font-face {
  font-family: "NotoSansKR";
  src: url("../fonts/notoSansKR/NotoSans-Bold.woff") format("woff");
  font-style: normal;
}

@font-face {
  font-family: "NotoSansKR";
  src: url("../fonts/openSans/OpenSans-SemiBold.woff") format("woff");
  unicode-range: U+0020-007E;
  font-style: normal;
} */



#product_top_detail{
 float:right;
 margin-right: 50%;
 xfont-family: 'Praise', cursive;
}

#product_bottom_detail{
font-size: 17px; 
margin-top:10%;
clear:both;
}


#product_title{
font-size: 27px; 
font-weight: bold; 
}

#product_type{
font-size: 22px; 
color: #777777;
}


#product_detail2{
position: absolute; 
font-size: 20px; 
}

#product_detail_label{
font-weight: bold; 
font-size: 19px; 
color: #777777;
}

a {
    text-decoration: none;
    color: black;
}

 button {
  position: relative; 
  xz-index:10;
} 

/* h2 
.star-rating {width:205px;}
.star-rating,.star-rating span {display:inline-block; height:39px; overflow:hidden; background:url(../image/icon/star.png)no-repeat; }
.star-rating span{background-position:left bottom; line-height:0; vertical-align:top; }
 */
f-rate{
font-size: 30px; 
display:inline-block;
}

.product-menulist {
  xborder : 2px solid yellow;
  position : relative;
  left : 750px;
  top : -100px;
  width : 200px;
  white-space:nowrap;
}

#find-product { 
    z-index:2;
    float:right;
    margin-right: 25%;
    margin-top: 350px;
    width: 130px;
    height: 42px; 
    padding: 7px; 
    border: none; 
    border-radius: 4px; 
    color: white; 
    font-weight: bold;
    background-color: #3a3a3a; 
    cursor: pointer; 
    outline: none; 
  } 
</style>

<img id="f-photo-image" src="../../upload/product/${product.photo}_1000x1000.jpg" 
        align="left" width="300px" height="500px">
<form action='update' method='post' enctype="multipart/form-data">
    <input type='hidden' id='f-productNumber' type='text' name='productNumber' class="form-control" value='${product.productNumber}' readonly>
    
<div id="product_top_detail" >        
 <div id="product_type">
  <div id='f-name'> ${product.productType.type} > ${product.productType.subType} </div>
 </div>
  <br>
  <div id="product_title">
    <div id='f-name'> ${product.productName} </div>
  </div>
 
  <div id="product_detail2">
<div class="wrap-star">
    <div class='star-rating'>
        <span style="width:40%"></span>
    </div>
</div>

<div id='f-rat2' onchange="isSame()" > &nbsp;&nbsp;<span id="same"></span></div>

   <div id='f-sugarLevel'> 
    <c:choose> 
  <c:when  test="${product.sugarLevel eq 1}">
    ☆ ☆ ☆ ☆ ★
   </c:when>
     <c:when  test="${product.sugarLevel eq 2}">
    ☆ ☆ ☆ ★ ★
   </c:when>
     <c:when  test="${product.sugarLevel eq 3}">
    ☆ ☆ ★ ★ ★
   </c:when>
     <c:when  test="${product.sugarLevel eq 4}">
    ☆ ★ ★ ★ ★
   </c:when>
     <c:when  test="${product.sugarLevel eq 5}">
    ★ ★ ★ ★ ★
   </c:when>
   </c:choose>
   (당도)
   </div>
   <div id='f-acidity'>
    <c:choose> 
  <c:when  test="${product.acidity eq 1}">
    ☆ ☆ ☆ ☆ ★
   </c:when>
     <c:when  test="${product.acidity eq 2}">
    ☆ ☆ ☆ ★ ★
   </c:when>
     <c:when  test="${product.acidity eq 3}">
    ☆ ☆ ★ ★ ★
   </c:when>
     <c:when  test="${product.acidity eq 4}">
    ☆ ★ ★ ★ ★
   </c:when>
     <c:when  test="${product.acidity eq 5}">
    ★ ★ ★ ★ ★
   </c:when>
   </c:choose>
   (산도)
   </div>
   <div id='f-weight'> 
    <c:choose> 
  <c:when  test="${product.weight eq 1}">
    ☆ ☆ ☆ ☆ ★
   </c:when>
     <c:when  test="${product.weight eq 2}">
    ☆ ☆ ☆ ★ ★
   </c:when>
     <c:when  test="${product.weight eq 3}">
    ☆ ☆ ★ ★ ★
   </c:when>
     <c:when  test="${product.weight eq 4}">
    ☆ ★ ★ ★ ★
   </c:when>
     <c:when  test="${product.weight eq 5}">
    ★ ★ ★ ★ ★
   </c:when>
   </c:choose>
   (바디감) 
  </div>  <br>
  <div id='f-weight'> 
  <label for='f-countryOrigin'>원산지 : ${product.countryOrigin}</label><br><br>
  </div>
    <div id='f-weight'> 
<c:choose> 
  <c:when test="${product.productType.type eq '와인'}">
      <label for='f-variety' >품종 : ${product.variety}</label><br><br>
  </c:when>
</c:choose>
  </div>
  <div id='f-weight'> 
 <label for='f-volume' >용량 : ${product.volume}</label><br><br>
   </div>
  <div id='f-weight'> 
 <label for='f-alcoholLevel' >도수 : ${product.alcoholLevel}</label><br><br>
  </div>
</div>
</div>
<br>
<br>
<br>
<a href="../stock/sellerList?no=${product.productNumber}" class="btn btn-primary" id="find-product">상품 찾아보기</a><br>

<div class="product-menulist">
<c:choose> 
  <c:when test="${loginUser.authority eq 8}">
 <div>
   <button type="button" onclick="location.href='detail?no=${product.productNumber}'" class="btn btn-outline-secondary btn-sm">상품정보수정</button>
  <a href="list" class="btn btn-outline-secondary btn-sm">목록</a>
  
   </div>
  </c:when> 
  <c:when test="${loginUser.authority eq 2}">
  <div>
  <a href="list" class="btn btn-outline-secondary btn-sm">목록</a>
     <button type="button" onclick="location.href='../stock/sellerList?no=${product.productNumber}'" class="btn btn-outline-secondary btn-sm">장바구니등록</button>
  </div>
  </c:when>
  <c:when test="${loginUser.authority eq 4}">
  <div>
  <a href="list" class="btn btn-outline-secondary btn-sm">목록</a>
  <button type="button" onclick="btn_add(${product.productNumber})" class="btn btn-outline-secondary btn-sm" >재고등록</button>
   <button type="button"  onclick="location.href='detail?no=${product.productNumber}'" class="btn btn-outline-secondary btn-sm">상품정보수정</button>
  </div>
  </c:when>
  <c:otherwise>
<a href="list" class="btn btn-outline-secondary btn-sm">목록</a>
  </c:otherwise>
</c:choose> 
</div>
<div id="product_bottom_detail" > 
<br/>
<hr/>

</div>



</form>

<h4>리뷰</h4>

<c:choose> 
  <c:when test="${loginUser.authority eq 2}">
  <i class="far fa-hand-point-right"></i>
    <a href='review/form?no=${product.productNumber}'> 리뷰남기기</a><br>
  </c:when>
</c:choose>

<c:forEach items="${reviewList}" var="review">
<fieldset>
    <div class="col-sm-10">
        <img id="f-photo-image" src="${contextPath}/upload/member/${review.member.photo}_100x100.jpg" onError="this.src='${contextPath}/upload/member/profile.png'">
    </div>
    <div class='review-content'>
    <p style='font-size:normal; font-weight: bold;'>${review.member.id}</p>
    <a style='font-size:large;' href='review/detail?no=${review.no}'>${review.comment}</a><br><br>
     <i class="far fa-thumbs-up"></i> ${review.score} / 
     <i class="far fa-calendar-alt"></i> ${review.registeredDate}
     <hr />
     </div>
</fieldset>
</c:forEach>

<script>
function isSame(){
var rate=(${product.rate}/5)*100;
document.ex_form.target_name.value = "100";
return rate;
}
</script>


<script>
function btn_add(no){
	var xhr = new XMLHttpRequest();
	xhr.addEventListener("load", function() {
	      if (this.responseText == "false") {
	          location.href='../stock/form?no='+ no;
	      } else {
	          alert("이미 재고에 추가된 상품입니다.");
	          return false;
	      }
	    })
	xhr.open("get", "../stock/checkStock?no=" + no);
	xhr.send();
}
</script>
