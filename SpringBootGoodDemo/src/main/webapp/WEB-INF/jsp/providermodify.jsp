<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<div class="right">
  <div class="location">
    <strong>你现在所在的位置是:</strong>
    <span>供应商管理页面 >> 供应商修改页</span>
  </div>
  <div class="providerAdd">
    <form id="providerForm" name="providerForm" method="post" action="jsp/provider.do">
      <input type="hidden" name="method" value="modifysave">
      <input type="hidden" name="id" value="${SmbmsProvider.id}">
      <!--div的class 为error是验证错误，ok是验证成功-->
      <div class="">
        <label for="proCode">供应商编码：</label> <input type="text" name="procode" id="proCode" value="${SmbmsProvider.procode}" readonly="readonly">
      </div>
      <div>
        <label for="proName">供应商名称：</label> <input type="text" name="proname" id="proName" value="${SmbmsProvider.proname}"> <font color="red"></font>
      </div>
      <div>
        <label for="proContact">联系人：</label> <input type="text" name="procontact" id="proContact" value="${SmbmsProvider.procontact}"> <font
          color="red"></font>
      </div>
      <div>
        <label for="proPhone">联系电话：</label> <input type="text" name="prophone" id="proPhone" value="${SmbmsProvider.prophone}"> <font color="red"></font>
      </div>
      <div>
        <label for="proAddress">联系地址：</label> <input type="text" name="proaddress" id="proAddress" value="${SmbmsProvider.proaddress}">
      </div>
      <div>
        <label for="proFax">传真：</label> <input type="text" name="profax" id="proFax" value="${SmbmsProvider.profax}">
      </div>
      <div>
        <label for="proDesc">描述：</label>
        <textarea  name="prodesc" id="proDesc" style="width: 275px">${SmbmsProvider.prodesc}</textarea>
      </div>
      <div class="providerAddBtn">
        <input type="button" name="save" id="save" value="保存"> <input type="button" id="back" name="back" value="返回">
      </div>
    </form>
  </div>
</div>
</section>
<%@include file="common/foot.jsp"%>
<script type="text/javascript" src="static/js/providermodify.js"></script>