package action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Doctor;
import Entities.Specialty;
import exception.DataException;
import service.DoctorService;
import service.ServiceLocator;
import service.SpecialtyService;

public class SaveSpecialtyAction extends Action {

	public SaveSpecialtyAction(ServiceLocator serviceLocator) {
		super(serviceLocator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException {
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Specialty specialty = new Specialty();

		specialty.setName(request.getParameter("name-t"));
		
		specialty.setRate(Integer.valueOf(request.getParameter("rate-t")));

		String a = request.getParameter("narrow-t");

        if(request.getParameter("narrow-t") == null)
        specialty.setNarrow(false);
        else specialty.setNarrow(true);
              
        try {
        	specialty.setId(Integer.parseInt(request.getParameter("id-t")));
        } catch(NumberFormatException e) {}

        try {
        	SpecialtyService specialtyservice = getService(SpecialtyService.class);
            if(specialty.getId() == null) {
                
            	specialtyservice.AddSpecialty(specialty);
            } else {   
            	boolean narrow = specialtyservice.getSpecialtyById(specialty.getId()).getNarrow();
            	specialtyservice.UpdateSpecialty(specialty);
            	 DoctorService doctorservice = getService(DoctorService.class);
            	 if(narrow != specialty.getNarrow() && specialty.getNarrow()){
	            	for(Doctor doctor: doctorservice.getAllDoctors(specialty.getName())){
	            		doctor.setArea(0);
	            		doctorservice.UpdateDoctor(doctor);
	            	}
            	 }
            }

        } catch(Exception e) {
            throw new DataException(e);
        }


        try {
			response.sendRedirect(request.getContextPath() + "/index.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
