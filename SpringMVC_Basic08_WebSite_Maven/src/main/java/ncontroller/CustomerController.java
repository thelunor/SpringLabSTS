package ncontroller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
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
	public void setNoticedao(NoticeDao noticedao) { // 컨테이너 안에 noticedao 객체가 있어야 한다
		this.noticedao = noticedao;
	}
	
	/*
	(NoticeDao noticedao, HttpServletRequest request)가 필요없고 강사님 코드처럼
	@RequestMapping("/customer/notice.htm")
	public ModelAndView notice(NoticeDao noticedao, HttpServletRequest request) throws ClassNotFoundException, SQLException {
		// 검색처리
		String _page = request.getParameter("pg");
		String _field = request.getParameter("f");
		String _query = request.getParameter("p");
		
		// default
		int page = 1;
		String field = "Title";
		String query = "%%";
		
		// 조건처리
		if(_page != null && _page.equals("")) {
			page = Integer.parseInt(_page);
		}
		
		if(_field != null && _field.equals("")) {
			field = _field;
		}
		
		if(_query != null && _query.equals("")) {
			query = _query;
		}
		
		// DAO 데이터 받아오기
		List<Notice> list = noticedao.getNotices(page, field, query);
		
		// Spring에서는 ModelAndView or Model 객체 제공
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);
		mv.setViewName("notice.jsp");
		
		return mv;
	}
	
	@RequestMapping("/customer/noticeDetail.htm")
	public ModelAndView noticeDetail(NoticeDao noticedao, HttpServletRequest request) throws ClassNotFoundException, SQLException {
		String seq = request.getParameter("seq");
		Notice notice = noticedao.getNotice(seq);

		ModelAndView mav = new ModelAndView();
		mav.addObject("notice", notice);
		mav.setViewName("noticeDetail.jsp");
		
		return mav;
	}
	*/
	/*
	메서드의 리턴 타입이 [String]이면 [리턴 값]은 [뷰 이름]으로 사용된다
	View에 데이터 전달 위해 Model interface 사용
	Model model > 함수의 parameter 사용 시 자동 객체 생성
	
	클래스 상단에
	@RequestMapping("/customer/")
	*/
	
	@RequestMapping("notice.htm")
	public String notices(String pg, String f, String q, Model model) { // Model interface
		// default
		int page = 1;
		String field = "Title";
		String query = "%%";
		
		// 조건처리
		if(pg != null && pg.equals("")) {
			page = Integer.parseInt(pg);
		}
		
		if(f != null && f.equals("")) {
			field = f;
		}
		
		if(q != null && q.equals("")) {
			query = q;
		}
		
		// DAO 데이터 받아오기
		List<Notice> list = null;;
		try {
			list = noticedao.getNotices(page, field, query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list); // view까지 전달(forward)
		
		return "customer.notice"; // "notice.jsp";
	}
	
	@RequestMapping("noticeDetail.htm")
	public String noticeDetail(String seq, Model model) {
		Notice notice = null;
		try {
			notice = noticedao.getNotice(seq);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("notice", notice);
		
		return "customer.noticeDetail"; // "noticeDetail.jsp";
	}
	
	// 글쓰기 화면
	// @RequestMapping(value="noticeReg.htm", method=RequestMethod.GET)
	@RequestMapping(value = "noticeReg.htm", method = RequestMethod.GET)
	public String noticeReg() {
		return "customer.noticeReg"; // "noticeReg.jsp";
	}
	
	// 글쓰기 처리(noticeReg.htm)
	// @RequestMapping(value="noticeReg.htm", method=RequestMethod.POST)
	// return "redirect:notice.htm";
	@RequestMapping(value = "noticeReg.htm", method = RequestMethod.POST)
	public String noticeReg(Notice notice, HttpServletRequest request) {
		// parameter request 하는건 경로 추출하기 위해
		// CommonsMultipartFile imagefile = notice.getFile();
		
		// Notices DTO
		// priavte List<CommonsMultipartFile> files;
		// files[0] = new CommonsMultipartFile() >> 1.jpg
		// files[1] = new CommonsMultipartFile() >> 2.jpg
		List<CommonsMultipartFile> files = notice.getFiles(); // 업로드 된 파일들의 목록
		List<String> filenames = new ArrayList<String>(); // 파일명만 관리
		
		if (files != null && files.size() > 0) { // 최소 1개 이상의 업로드가 있다면
			for (CommonsMultipartFile multifile : files) {
				String filename = multifile.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/customer/upload");
				String fpath = path + "\\" + filename;
				
				if (!filename.equals("")) {
					FileOutputStream fs = null;
					try {
						fs = new FileOutputStream(fpath);
						try {
							fs.write(multifile.getBytes());
							fs.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				filenames.add(filename); // 파일명만 별도 관리(DB insert 사용)
			}
		}
		
		// String filename = imagefile.getFile().getOriginalFilename();
		// 강사님은 CommonsMultipartFile imagefile = notice.getFile(); 안 하고 바로 하셨음
		// String filename = imagefile.getOriginalFilename();
		// String filename = imagefile.getName(); // 파일 이름이 file로 저장됨
		// String path = request.getServletContext().getRealPath("/customer/upload");
		
		// String fpath = path + "\\" + filename;
		
		//DB 파일명 저장
		notice.setFileSrc(filenames.get(0));
		notice.setFileSrc(filenames.get(1));
		
//		FileOutputStream fs = null;
//		try {
//			fs = new FileOutputStream(fpath);
//			try {
//				fs.write(imagefile.getBytes());
//				fs.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} // 파일 생성
		
		
		try {
			noticedao.insert(notice);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:notice.htm"; // 이건 바꿀 필요가 없다
		// spring: "redirect:notice.htm";
		// servlet
		// 클라이언트 페이지 재요청(F5, 주소창에서 Enter)
		// location.href
	}
	
	// 글 삭제하기(noticeDel.htm): parameter로 seq
	// return "redirect:notice.htm"
	@RequestMapping(value = "noticeDel.htm", method = RequestMethod.GET)
	public String noticeDel(String seq) {
		try {
			noticedao.delete(seq);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:notice.htm";
	}
	
	// 글 수정하기(화면처리: select * from ... where seq=?): GET 방식임에도 불구하고 데이터 처리해야 함
	// parameter로 seq
	// noticedao.getNotice(seq)
	// Model model >> 화면에 데이터 출력 >> noticeEdit.jsp
	
	// 글 수정하기(처리: update... where seq=?): POST
	// parameter로 Notice 객체 >> insert와 동일 >> 일단 무조건 파일 첨부하는 형태로
	
	// return "redirect:noticeDetail.htm?seq=" + notice.getSeq();
	@RequestMapping(value = "noticeEdit.htm", method = RequestMethod.GET)
	public String noticeEdit(String seq, Model model) {
		try {
			Notice notice = noticedao.getNotice(seq);
			model.addAttribute("notice", notice); // view단에서 쓸 수 있는 (key값, 객체)
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "customer.noticeEdit"; // "noticeEdit.jsp"; // 함수 타입 String으로 했기 때문에 return view단 페이지
	}
	
	@RequestMapping(value = "noticeEdit.htm", method = RequestMethod.POST)
	public String noticeEdit(Notice notice, HttpServletRequest request) {
		// parameter request 하는건 경로 추출하기 위해
		// CommonsMultipartFile imagefile = notice.getFile();
		
		// Notices DTO
		// priavte List<CommonsMultipartFile> files;
		// files[0] = new CommonsMultipartFile() >> 1.jpg
		// files[1] = new CommonsMultipartFile() >> 2.jpg
		List<CommonsMultipartFile> files = notice.getFiles(); // 업로드 된 파일들의 목록
		List<String> filenames = new ArrayList<String>(); // 파일명만 관리
		
		if (files != null && files.size() > 0) { // 최소 1개 이상의 업로드가 있다면
			for (CommonsMultipartFile multifile : files) {
				String filename = multifile.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/customer/upload");
				String fpath = path + "\\" + filename;
				
				if (!filename.equals("")) {
					FileOutputStream fs = null;
					try {
						fs = new FileOutputStream(fpath);
						try {
							fs.write(multifile.getBytes());
							fs.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				filenames.add(filename); // 파일명만 별도 관리(DB insert 사용)
			}
		}
		
		// String filename = imagefile.getFile().getOriginalFilename();
		// 강사님은 CommonsMultipartFile imagefile = notice.getFile(); 안 하고 바로 하셨음
		// String filename = imagefile.getOriginalFilename();
		// String filename = imagefile.getName(); // 파일 이름이 file로 저장됨
		// String path = request.getServletContext().getRealPath("/customer/upload");
		
		// String fpath = path + "\\" + filename;
		
		//DB 파일명 저장
		notice.setFileSrc(filenames.get(0));
		notice.setFileSrc(filenames.get(1));
		
		try {
			noticedao.update(notice);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:noticeDetail.htm?seq=" + notice.getSeq();
	}
}
