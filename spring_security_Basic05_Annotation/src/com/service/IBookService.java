package com.service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import com.bean.Book;

public interface IBookService {

	@PreAuthorize("hasRole('ROLE_WRITE')") // 인터페이스에 거는 애노테이션, preauth> 입력값 제어
	// hasRole('ROLE_WRITE') 권한이 있는 사람만 북 추가 가능(함수를 태우기 전에 권한 검사)
	void addBook(Book book);
	
	
	@PostAuthorize("returnObject.owner == authentication.name")
	Book getBook();
	/*
	   현재 로그인한 계정 : hong 
	   Book b = new Book("구운몽","kglim")
	   
	   "kglim" != "hong" > false 접근권한 예외 
	   return  b; 
	   
	   returnObject >> Book 객체
	   
	   postauth >> 함수 실행 후 검사
	*/
	
	/*
	 Book bo = new Book("홍길동전","kglim")
	 (bo.kglim == "kglim")
	 delelteBook(bo)
	 */
	@PreAuthorize("#book.owner == authentication.name")
	void deleteBook(Book book);
	// preauth > 함수를 실행하기 전에 파라미터(북 객체)를 검사할 수 있다 >
}









