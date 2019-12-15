package ncontroller;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dao.NoticeDao;
import vo.Notice;

@Controller
@RequestMapping("/customer/")
public class CustomerController {
	private NoticeDao noticedao;
	
	@Autowired
	public void setNoticedao(NoticeDao noticedao) { //setter 로 Injection
		this.noticedao = noticedao;
	}
	/*
	method 리턴 타입이 [String] 이면 [리턴값]은 [뷰 이름]으로 사용된다.
	view 에 데이터 전달 위해 Model interface 사용
	Model model > 함수의 parameter 사용 시 자동 객체 생성 > Spring
	*/
	
	//리스트
	@RequestMapping("notice.htm")
	public String listBoard(String pg, String f, String p, Model model){
		//default
		int page =1;
		String field = "TITLE";
		String query = "%%";
				
		//조건처리
		if(pg != null && !pg.equals("")) {
			page = Integer.parseInt(pg);
		}
				
		if(f != null && !f.equals("")) {
			field = f;
		}
				
		if(p != null && !p.equals("")) {
			query = p;
		}
				
		//DAO 데이터 받아오기
		List<Notice> list = null;
		
		try {
			list = noticedao.getNotices(page, field, query);
		} catch (Exception e) {
			System.out.println("list : " + e.getMessage());
		} 
		
		model.addAttribute("list", list);
		//알아서 view 단 까지 간다...
		//ModelAndView 써도 무관
		//return "notice";
		return "customer.notice";
	}
	
	//상세보기
	@RequestMapping("noticeDetail.htm")
	public String detailBoard(String seq, Model model){
		
		Notice notice = null;
		try {
			notice = noticedao.getNotice(seq);
		} catch (Exception e) {
			System.out.println("detail : " + e.getMessage());
		}
		
		model.addAttribute("notice", notice);
		//ModelAndView 써도 무관				
		//return "noticeDetail";
		return "customer.noticeDetail";
	}
	
	//글쓰기화면
	@RequestMapping(value="noticeReg.htm", method = RequestMethod.GET)
	public String writeBoard() {
		//return "noticeReg";
		return "customer.noticeReg";
	}
	
	//글쓰기처리
	@RequestMapping(value="noticeReg.htm", method = RequestMethod.POST)
	public String writeBoardOk(Notice notice, HttpServletRequest request) {
		//파일업로드 해야한다..
		//lib 추가
		//DTO 만들고..
		
		List<CommonsMultipartFile> files = notice.getFiles();
		List<String> filenames = new ArrayList<String>();
		
		if(files != null && files.size() >0) {
			for(CommonsMultipartFile mutifile : files) {
				
				String filename = mutifile.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/customer/upload"); //서버의 실 경로
				
				String fpath = path + "\\" + filename;
				
				if(!filename.equals("")) { //실제 파일 업로드
					FileOutputStream fs=null;

					try {
						fs = new FileOutputStream(fpath);
						fs.write(mutifile.getBytes());
						fs.close();
						
						filenames.add(filename);
					} catch (Exception e) {
						System.out.println("filewrite : " + e.getMessage());
					}
				}
			}
		}
				
		/////////////////////////////////////////////////////////////////////////////////
		//이후 DB저장 DAO 만들어서 insert..
		
		notice.setFileSrc(filenames.get(0));
		notice.setFileSrc2(filenames.get(1));
		
		try {
			noticedao.insert(notice);
		} catch (Exception e) {
			System.out.println("insert : " + e.getMessage());
		}
		
		return "redirect:notice.htm"; //이렇게 안하면 F5 시 글쓰기가 계속 실행된다.
		//들어오는 주소라 Tiles 써도 변경하지 않음. (redirect)
	}
	
	//글삭제(noticeDel.htm) : seq(patameter)
	//return "redirect:notice.htm";
	@RequestMapping("noticeDel.htm")
	public String deleteBoard(String seq) {
		try {
			noticedao.delete(seq);
		} catch (Exception e) {
			System.out.println("delete : " + e.getMessage());
		}
		return "redirect:notice.htm";
	}
	
	//글수정(화면 : select ... where seq = ?) : GET : seq(parameter)
	//noticedao.getNotice(seq)
	//Model model >> 화면 >> 데이터 >> noticeDeit.jsp
	@RequestMapping(value="noticeEdit.htm", method=RequestMethod.GET)
	public String editboard(String seq, Model model) {
		Notice notice = null;
		try {
			notice = noticedao.getNotice(seq);
		} catch (Exception e) {
			System.out.println("editview : " + e.getMessage());
		}
		
		model.addAttribute("notice", notice);
		//ModelAndView 써도 무관
		
		//return "noticeEdit";
		return "customer.noticeEdit";
	}
	
	//글수정하기(처리 : update ... where seq=?) : POST
	//parameter Notice n >> Insert 동일 >> 무조건 파일 첨부 하는 형태로
	//return :redirect:noticeDetail.html?seq="+n.getseq();
	@RequestMapping(value="noticeEdit.htm", method=RequestMethod.POST)
	public String editboardOk(Notice notice,HttpServletRequest request) {
		List<CommonsMultipartFile> files = notice.getFiles();
		List<String> filenames = new ArrayList<String>();
		
		if(files != null && files.size() >0) {
			for(CommonsMultipartFile mutifile : files) {
				
				String filename = mutifile.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/customer/upload"); //서버의 실 경로
				
				String fpath = path + "\\" + filename;
				
				if(!filename.equals("")) { //실제 파일 업로드
					FileOutputStream fs=null;

					try {
						fs = new FileOutputStream(fpath);
						fs.write(mutifile.getBytes());
						fs.close();
						
						filenames.add(filename);
					} catch (Exception e) {
						System.out.println("filewrite : " + e.getMessage());
					}
				}
			}
		}
				
		/////////////////////////////////////////////////////////////////////////////////
		//이후 DB저장 DAO 만들어서 insert..
		
		notice.setFileSrc(filenames.get(0));
		notice.setFileSrc2(filenames.get(1));
		
		try {
			noticedao.update(notice);
		} catch (Exception e) {
			System.out.println("edit : " + e.getMessage());
		}
		return "redirect:noticeDetail.htm?seq="+notice.getSeq();
	}
	
}
