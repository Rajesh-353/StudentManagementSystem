package in.naveen.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.naveen.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {
	
	@Query(value="SELECT COUNT(*) FROM student where cid=:cid",nativeQuery = true)
	public Integer GetTotalEnq(Integer cid);
	
	@Query(value="SELECT COUNT(*) FROM student where cid=:cid and status=:enrolled",nativeQuery = true)
	public Integer EnrollTotalEnq(Integer cid,String enrolled);
	
	@Query(value="SELECT COUNT(*) FROM student where cid=:cid and status=:lost",nativeQuery = true)
	public Integer LostTotalEnq(Integer cid,String lost);

	@Query(value="select * from student where cid=:cid",nativeQuery = true)
    public List<Student> getStudents(Integer cid);	
	
	public List<Student> findByCourseOrModeOrStatus(String course,String mode,String status);
	
}
