package in.naveen.service;

import in.naveen.entity.Counselors;
import in.naveen.entity.LoginData;

public interface CouseloerService {
	
	public String SaveCouselor(Counselors c);
	
	public Counselors getCounselors(LoginData l);
	
	public boolean RecoverEmail(String email);

}
