package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Doctor;
import exception.DataException;
import service.DoctorService;
import service.ServiceLocator;
import service.SpecialtyService;

public class EditDoctorAction extends Action {

	public EditDoctorAction(ServiceLocator serviceLocator) {
		super(serviceLocator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException {
		 try {
			 SpecialtyService specialtyservice = getService(SpecialtyService.class);
			 DoctorService doctorservice = getService(DoctorService.class);
			 
	            if(request.getParameter("id")!=null){
	            	Integer id = Integer.parseInt(request.getParameter("id"));	
	            	Doctor doctor = doctorservice.getDoctorById(id);	
	            	boolean indicator = specialtyservice.getSpecialtyByName(doctor.getSpecialty()).getNarrow(); 
	    	        if(!indicator){
	    	        	doctor.setArea(-1);
	    	        }
	            	request.setAttribute("Doctor", doctor);	            
	            }
	            else{
	            	Doctor doctor = new Doctor();
	            	doctor.setSpecialty(request.getParameter("SpecialtyName"));
	            	boolean indicator = specialtyservice.getSpecialtyByName(request.getParameter("SpecialtyName")).getNarrow(); 
	    	        if(!indicator){
	    	        	doctor.setArea(-1);
	    	        }
	            	request.setAttribute("Doctor", doctor);	       	            
	            }
	        
	        
	        } catch(NumberFormatException e) {

	        } catch(Exception e) {
	            //throw new ServletException(e);

	        }
	            
	        try {
				request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/editDoctor.jsp")
				                                              .forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
