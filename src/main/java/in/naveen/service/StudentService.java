package in.naveen.service;

import java.util.List;

import in.naveen.entity.Filterdata;
import in.naveen.entity.Student;

public interface StudentService {
	
	public boolean saveStudent(Student s);
	
	
	public List<Student> getStudents(Integer cid);
	
	public Integer GetTotalEnq(Integer cid);
	public Integer EnrollTotalEnq(Integer cid);
	public Integer LostTotalEnq(Integer cid);
	
	public List<Student> Filter(Filterdata filter,Integer cid);

}
