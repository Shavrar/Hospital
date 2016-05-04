package action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Doctor;
import exception.DataException;
import service.DoctorService;
import service.ServiceLocator;

public class SaveDoctorAction extends Action {

	public SaveDoctorAction(ServiceLocator serviceLocator) {
		super(serviceLocator);
		// TODO Auto-generated constructor stub
	}

		public static Boolean compareDate(Date x,Date y){
		
		String Xday=(x.toString()).substring(8, 10);
		String Yday=(y.toString()).substring(8, 10);
		String Xmonth=(x.toString()).substring(5, 7);
		String Ymonth=(y.toString()).substring(5, 7);
		String Xyear=(x.toString()).substring(0, 4);
		String Yyear=(y.toString()).substring(0, 4);
		
			if((Integer.parseInt(Yyear) - Integer.parseInt(Xyear) > 20)){
				return true;
			}
		return false;
	}
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws DataException {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		DoctorService doctorservice = getService(DoctorService.class);
        
		Boolean temp=true;

        Doctor doctor = new Doctor();

        try {
            doctor.setId(Integer.parseInt(req.getParameter("id-t")));
        } catch(NumberFormatException e) {}
        
        doctor.setSpecialty(req.getParameter("specialty-t"));

        doctor.setName(req.getParameter("name-t"));

        doctor.setBirtday(Date.valueOf(req.getParameter("birtday-t")));

        doctor.setWorkday(Date.valueOf(req.getParameter("workday-t")));

        doctor.setSalary(0);

        doctor.setArea(Integer.valueOf(req.getParameter("area-t")));

        doctor.setSex(req.getParameter("sex-t"));
        
        doctor.setDomain_name(req.getParameter("domain_name-t"));

        Boolean a = !compareDate(Date.valueOf(req.getParameter("birtday-t")), Date.valueOf(req.getParameter("workday-t")));

        //checking if beginning is earlier than planned
        if(!compareDate(Date.valueOf(req.getParameter("birtday-t")), Date.valueOf(req.getParameter("workday-t")))){
            temp=false;
            req.setAttribute("Doctor", doctor);
            req.setAttribute("Fail", "fail");
            try {
				req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/editDoctor.jsp")
				        .forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        if(temp){

            if(doctor.getId() == null) {
				doctorservice.AddDoctor(doctor);
			} else {
				doctorservice.UpdateDoctor(doctor);
			}


            try {
				resp.sendRedirect(req.getContextPath() + "/doctors.html?SpecialtyName="+req.getParameter("specialty-t"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }
	}

}

