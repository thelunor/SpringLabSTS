package vo;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Notice {
	private String seq;
	private String title;
	private String writer;
	private Date regdate;
	private String fileSrc;
	private String fileSrc2; // getter, setter 추가 생성
	private int hit;
	private String content;
	
//	// Point Start(단일 파일)
//	private CommonsMultipartFile file; // spring 제공
//	// <input type="file" name="file"> 변수명과 name 속성값이 같아야 한다
//
//	public CommonsMultipartFile getFile() {
//		return file;
//	}
//	
//	public void setFile(CommonsMultipartFile file) {
//		this.file = file;
//	}
//	// Point End
	
	// 다중파일 업로드 >> List
	private List<CommonsMultipartFile> files;
	
	public List<CommonsMultipartFile> getFiles() {
		return files;
	}
	
	public void setFiles(List<CommonsMultipartFile> files) {
		this.files = files;
	}
	// 다중파일 업로드 End
	
	public String getFileSrc() {
		return fileSrc;
	}
	
	public void setFileSrc(String fileSrc) {
		this.fileSrc = fileSrc;
	}
	
	public String getFileSrc2() {
		return fileSrc2;
	}

	public void setFileSrc2(String fileSrc2) {
		this.fileSrc2 = fileSrc2;
	}

	public String getSeq() {
		return seq;
	}
	
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public Date getRegdate() {
		return regdate;
	}
	
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public int getHit() {
		return hit;
	}
	
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
