package action;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import service.ServiceLocator;
import service.ServiceLocatorFactory;

import exception.DataException;

public class ActionManagerImpl implements ActionManager {
	private static Map<String, Class<? extends Action>> actions = new ConcurrentHashMap<String, Class<? extends Action>>();

	static {
		actions.put("", SpecialtiesAction.class);
		actions.put("index.html", SpecialtiesAction.class);
		actions.put("editSpecialty.html", EditSpecialtyAction.class);
		actions.put("saveSpecialty.html", SaveSpecialtyAction.class);
		actions.put("deleteSpecialty.html", DeleteSpecialtyAction.class);

		actions.put("login.html", LoginAction.class);
		actions.put("logout.html", LogoutAction.class);

		actions.put("deleteUser.html", DeleteUserAction.class);

		actions.put("editUser.html", EditUserAction.class);

		actions.put("saveUser.html", SaveUserAction.class);

		actions.put("editUsers.html", UsersAction.class);
		actions.put("deleteDoctor.html", DeleteDoctorAction.class);
		actions.put("editDoctor.html", EditDoctorAction.class);
		actions.put("saveDoctor.html", SaveDoctorAction.class);
		actions.put("doctors.html", DoctorsAction.class);
		
	}

	@Override
	public void execute(String actionName, HttpServletRequest request, HttpServletResponse response) throws DataException {
		try {
			ServiceLocator serviceLocator = ServiceLocatorFactory.getInstance();
			Class<? extends Action> actionClass = actions.get(actionName);
			if(actionClass != null) {
				Action action = actionClass.getConstructor(ServiceLocator.class).newInstance(serviceLocator);
				
				action.exec(request, response);
				
			}
		} catch(DataException e) {			
			throw e;
		} catch(Exception e) {
			
		}		
	}
}