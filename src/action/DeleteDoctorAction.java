package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.DataException;
import service.DoctorService;
import service.ServiceLocator;

public class DeleteDoctorAction extends Action {

	public DeleteDoctorAction(ServiceLocator serviceLocator) {
		super(serviceLocator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException {
		String temp=null;
		DoctorService doctorservice = getService(DoctorService.class);
        for(String id : request.getParameterValues("id")) {
            try {
            	
            	temp = doctorservice.getDoctorById(Integer.parseInt(id)).getSpecialty();
            	doctorservice.DeleteDoctor(Integer.parseInt(id));
            } catch(NumberFormatException e) {

            } catch(Exception e) {
                throw new DataException(e);

            }
        }
        
        try {
			response.sendRedirect(request.getContextPath() + "/doctors.html?SpecialtyName="+temp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
