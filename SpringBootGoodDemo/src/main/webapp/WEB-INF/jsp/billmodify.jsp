<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<div class="right">
  <div class="location">
    <strong>你现在所在的位置是:</strong>
    <span>订单管理页面 >> 订单修改页面</span>
  </div>
  <div class="providerAdd">
    <form id="billForm" name="billForm" method="post" action="jsp/bill.do">
      <input type="hidden" name="method" value="modifysave">
      <input type="hidden" name="id" value="${SmbmBill.id}">
      <!--div的class 为error是验证错误，ok是验证成功-->
      <div class="">
        <label for="billCode">订单编码：</label> <input type="text" name="billcode" id="billCode" value="${SmbmBill.billcode}" readonly="readonly">
      </div>
      <div>
        <label for="productName">商品名称：</label> <input type="text" name="productname" id="productName" value="${SmbmBill.productname}"> <font
          color="red"></font>
      </div>
      <div>
        <label for="productUnit">商品单位：</label> <input type="text" name="productunit" id="productUnit" value="${SmbmBill.productunit}"> <font
          color="red"></font>
      </div>
      <div>
        <label for="productCount">商品数量：</label> <input type="text" name="productcount" id="productCount" value="${SmbmBill.productcount}"> <font
          color="red"></font>
      </div>
      <div>
        <label for="totalprice">总金额：</label> <input type="text" name="totalprice" id="totalPrice" value="${SmbmBill.totalprice}"> <font3
          color="red"></font3>
      </div>
      <div>
        <label for="providerId">供应商：</label>
        <input type="hidden" value="${SmbmBill.providerid}" id="pid" />
        <select name="providerid" id="providerId">
        </select>
        <font color="red"></font>
      </div>
      <div>
        <label>是否付款：</label>
        <c:if test="${SmbmBill.ispayment==1}">
          <input type="radio" name="ispayment" value="1" checked="checked">未付款
						<input type="radio" name="ispayment" value="2">已付款
					</c:if>
        <c:if test="${SmbmBill.ispayment==2}">
          <input type="radio" name="ispayment" value="1">未付款
						<input type="radio" name="ispayment" value="2" checked="checked">已付款
					</c:if>
      </div>
      <div class="providerAddBtn">
        <input type="button" name="save" id="save" value="保存"> <input type="button" id="back" name="back" value="返回">
      </div>
    </form>
  </div>
</div>
</section>
<%@include file="common/foot.jsp"%>
<script type="text/javascript" src="static/js/billmodify.js"></script>