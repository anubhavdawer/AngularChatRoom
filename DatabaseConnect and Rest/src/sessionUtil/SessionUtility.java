package sessionUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class SessionUtility {

	/**
	 * This method starts a session and returns a session Object
	 * @return
	 */
	public Session startSession(){
		Session session =new AnnotationConfiguration().configure().buildSessionFactory().openSession();
		return session;
	}
	
	/**
	 * This method closes the session once the transactions are completed
	 * It is required as an open session can result in memory leakage
	 * @param session
	 */
	public void closeSession(Session session){
		if(session!=null){
			session.close();
		}
	}
}
