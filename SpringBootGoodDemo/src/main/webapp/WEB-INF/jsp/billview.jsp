<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@include file="common/head.jsp"%>

<div class="right">
     <div class="location">
         <strong>你现在所在的位置是:</strong>
         <span>订单管理页面 >> 信息查看</span>
     </div>
     <div class="providerView">
         <p><strong>订单编号：</strong><span>${SmbmBill.billcode}</span></p>
         <p><strong>商品名称：</strong><span>${SmbmBill.productname}</span></p>
         <p><strong>商品单位：</strong><span>${SmbmBill.productunit}</span></p>
         <p><strong>商品数量：</strong><span>${SmbmBill.productcount}</span></p>
         <p><strong>总金额：</strong><span>${SmbmBill.totalprice}</span></p>
         <p><strong>供应商：</strong><span>${SmbmBill.provider.proname}</span></p>
         <p><strong>是否付款：</strong>
         	<span>
         		<c:if test="${SmbmBill.ispayment==1}">未付款</c:if>
				<c:if test="${SmbmBill.ispayment==2}">已付款</c:if>
			</span>
		</p>
		<div class="providerAddBtn">
         	<input type="button" id="back" name="back" value="返回" >
        </div>
     </div>
 </div>
</section>
<%@include file="common/foot.jsp" %>
<script type="text/javascript" src="static/js/billview.js"></script>