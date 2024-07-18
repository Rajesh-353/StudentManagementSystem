package in.naveen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.naveen.entity.Filterdata;
import in.naveen.entity.Student;
import in.naveen.repo.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {
    
	@Autowired
    private StudentRepo srepo;
	
	@Override
	public boolean saveStudent(Student s) {
		Student save = srepo.save(s);
		if(save.getSid()!=null) {
			return true;
		}
		return false;
	}
	@Override
	public Integer GetTotalEnq(Integer cid) {
		Integer totalEnq = srepo.GetTotalEnq(cid);
		 
		System.out.println(totalEnq);
		return totalEnq;
	}

	@Override
	public Integer EnrollTotalEnq(Integer cid) {
		 Integer enrollTotalEnq = srepo.EnrollTotalEnq(cid, "Enrolled");
		
		return enrollTotalEnq;
	}

	@Override
	public Integer LostTotalEnq(Integer cid) {
		Integer lostEnq = srepo.LostTotalEnq(cid, "Lost");
		return lostEnq;
	}
	@Override
	public List<Student> getStudents(Integer cid) {
		List<Student> students = srepo.getStudents(cid);
		return students;
	}
	@Override
	public List<Student> Filter(Filterdata filter,Integer cid) {
	        
		Student s = new Student();
		   s.setCid(cid);
		   
		   if(filter.getMode()!=null &&  !filter.getMode().equals("")) {
			   s.setMode(filter.getMode());
		   }
		   if(filter.getCourse()!=null &&  !filter.getCourse().equals("")) {
			   s.setCourse(filter.getCourse());
		   }
		   if(filter.getStatus()!=null &&  !filter.getStatus().equals("")) {
			   s.setStatus(filter.getStatus());
		   }
		
		   Example<Student> of = Example.of(s);
		          List<Student> list = srepo.findAll(of);
		return list;
	}
	
	

}
