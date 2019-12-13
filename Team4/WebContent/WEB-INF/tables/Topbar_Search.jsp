<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
select {
	color: #fff;
	background-color: #ff6863;
	border: 2px solid;
	border-color: #ff6863;
	padding: 0.8rem 2rem;
	margin-bottom: 1rem;
}

select {
  position: relative;
  display: inline-block;
}

option {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

option{
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

option:hover {background-color: #f1f1f1}

select:hover option {
  display: block;
}

select:hover select {
	background-color: #eee;
	border-color: #eee;
	border: 2px solid #eee;
	color: #ff6863;
}
</style>
<br>
<div class="row">
<div class="col-md-2">
<select>
  <option value="search">검색 조건</option>
  <option value="eName">이름</option>
  <option value="empNo">사번</option>
  <option value="dName" id="dname">부서명</option>
</select>
</div>
<div class="col-md-4">
    <div class="input-group" style="height: 40px; float:left;">
        <input type="text" class="form-control bg-light border-0" placeholder="회원 검색" aria-label="Search"
            aria-describedby="basic-addon2" id="member_search" name="member_search" style="height:50px;">
        <div class="input-group-append">
            <button type="submit" class="btn btn-primary" type="button" id="searchbtn"> Click </button>
        </div>
    </div>
</div>
</div>
<br>
<br>