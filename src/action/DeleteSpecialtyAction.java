package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.DataException;
import service.ServiceLocator;
import service.SpecialtyService;

public class DeleteSpecialtyAction extends Action {

	public DeleteSpecialtyAction(ServiceLocator serviceLocator) {
		super(serviceLocator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException {
		
		SpecialtyService specialtyservice = getService(SpecialtyService.class);
		
		for(String id : request.getParameterValues("id")) {
            try {
            	specialtyservice.DeleteSpecialty(Integer.parseInt(id));
            } catch(NumberFormatException e) {

            } catch(Exception e) {
                throw new DataException(e);
                
            }
        }
        try {
			response.sendRedirect(request.getContextPath() + "/index.html");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
