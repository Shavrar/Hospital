package action;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Doctor;
import exception.DataException;
import service.DoctorService;
import service.ServiceLocator;
import service.SpecialtyService;

public class DoctorsAction extends Action {

	public DoctorsAction(ServiceLocator serviceLocator) {
		super(serviceLocator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException {
		try {
			SpecialtyService specialtyservice = getService(SpecialtyService.class);
			DoctorService doctorservice = getService(DoctorService.class);
        	String name = request.getParameter("SpecialtyName");
            Collection<Doctor> doctors = doctorservice.getAllDoctors(name);
            for(Doctor doctor: doctors){
            	doctor.setSalary(specialtyservice.calculateSalary(doctor));           	
            }
            request.setAttribute("doctors", doctors);
            request.setAttribute("SpecialtyName", name);
            try {
				request.getServletContext().
				                getRequestDispatcher("/WEB-INF/jsp/doctors.jsp")
				                                          .forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        } catch(Exception e) {
            throw new DataException(e);
        }

	}

}
