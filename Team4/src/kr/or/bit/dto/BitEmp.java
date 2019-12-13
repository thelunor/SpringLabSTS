package kr.or.bit.dto;

public class BitEmp {
   private int empNo; //사번
   private String eName; //이름
   private String job; //직책
   private int sal; // 연봉
   private String dName; //부서이름
   private String originFileName; //원본 파일 이름
   private String saveFileName; //저장 파일 이름
   
   public BitEmp() {}
   
   
   public BitEmp(int empNo, String eName, String job, int sal, String dName, String originFileName,
         String saveFileName) {
      super();
      this.empNo = empNo;
      this.eName = eName;
      this.job = job;
      this.sal = sal;
      this.dName = dName;
      this.originFileName = originFileName;
      this.saveFileName = saveFileName;
   }


   public int getEmpNo() {
      return empNo;
   }
   public void setEmpNo(int empNo) {
      this.empNo = empNo;
   }
   public String geteName() {
      return eName;
   }
   public void seteName(String eName) {
      this.eName = eName;
   }
   public String getJob() {
      return job;
   }
   public void setJob(String job) {
      this.job = job;
   }
   public int getSal() {
      return sal;
   }
   public void setSal(int sal) {
      this.sal = sal;
   }
   public String getdName() {
      return dName;
   }
   public void setdName(String dName) {
      this.dName = dName;
   }
   public String getOriginFileName() {
      return originFileName;
   }
   public void setOriginFileName(String originFileName) {
      this.originFileName = originFileName;
   }
   public String getSaveFileName() {
      return saveFileName;
   }
   public void setSaveFileName(String saveFileName) {
      this.saveFileName = saveFileName;
   }


   @Override
   public String toString() {
      return "BitEmp [empNo=" + empNo + ", eName=" + eName + ", job=" + job + ", sal=" + sal + ", dName=" + dName
            + ", originFileName=" + originFileName + ", saveFileName=" + saveFileName + ", toString()="
            + super.toString() + "]";
   }
   
   
}