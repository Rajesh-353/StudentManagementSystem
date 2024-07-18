package in.naveen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.naveen.Utils;
import in.naveen.entity.Counselors;
import in.naveen.entity.LoginData;
import in.naveen.repo.CounselorsRepo;

@Service
public class CounselorServiceImpl implements CouseloerService {
	@Autowired
	private CounselorsRepo crepo;
	@Autowired
	private Utils util;

	@Override
	public String SaveCouselor(Counselors c) {
		 Counselors findByEmail = crepo.findByEmail(c.getEmail());
		 if(findByEmail!=null) {
			 return "Your Email is already exists ";
		 }
		Counselors counselors = crepo.save(c);
		if (counselors!= null) {
			return "Registration Success";
		}
		return "Registration Failed";
	}

	@Override
	public Counselors getCounselors(LoginData l) {
		  Counselors counselors = crepo.getByemailAndpassword(l.getEmail(), l.getPassword());
		    if(counselors!=null) {
		    	return counselors;
		    }
		return null;
	}

	@Override
	public boolean RecoverEmail(String email) {
	Counselors byEmail = crepo.findByEmail(email);
	           if(byEmail != null) {
	        	   String subject="Email recover ,"+"Wow Tech Technology";
	        	   String body="<h1>Your Password is:"+byEmail.getPassword()+"</h1>";
	        	   return util.Sendmail(email, subject, body);
	           }
		  return false;
	}
	
	
}
	
