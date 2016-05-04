package action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Specialty;
import exception.DataException;

import service.ServiceLocator;
import service.SpecialtyService;

public class SpecialtiesAction extends Action{

	public SpecialtiesAction(ServiceLocator serviceLocator) {
		super(serviceLocator);
	}

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException {
		try {
			SpecialtyService specialtyservice = getService(SpecialtyService.class);
            Collection<Specialty> specialties = specialtyservice.getAllSpecialties();
            
            for(Specialty specialty : specialties){
            	specialty.setCount(specialtyservice.getCountOfDoctors(specialty));
            	specialty.setSumcost(specialtyservice.calculateSumCost(specialty));
            }
            
            request.setAttribute("specialties", specialties);
            request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);

        } catch(Exception e) {
            throw new DataException();
        }
		
	}

}
