package in.naveen.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.naveen.entity.Counselors;

public interface CounselorsRepo extends JpaRepository<Counselors, Integer> {
	
	@Query( value="select * from counselors where email=:email and password=:password",nativeQuery = true)
	public Counselors getByemailAndpassword(@Param("email") String email,@Param("password") String password);
	
	public Counselors findByEmail(String email);
	
}
