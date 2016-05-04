package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Specialty;
import exception.DataException;
import service.ServiceLocator;
import service.SpecialtyService;

public class EditSpecialtyAction extends Action {

	public EditSpecialtyAction(ServiceLocator serviceLocator) {
		super(serviceLocator);	
	}

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException {
		try {
       	 if(request.getParameter("id")!=null){
            	Integer id = Integer.parseInt(request.getParameter("id"));	
            	Specialty specialty = getService(SpecialtyService.class).getSpecialtyById(id);
            
            	request.setAttribute("specialty", specialty);
            
            }
            else{
            	Specialty specialty = new Specialty();            	
           	 	request.setAttribute("specialty", specialty);
            
            }
           
       	request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/editSpecialty.jsp").forward(request, response);
       } catch(NumberFormatException e) {

       } catch(Exception e) {
           throw new DataException();

       }
       

	}

}
