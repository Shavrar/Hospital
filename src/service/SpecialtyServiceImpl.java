package service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Objects;

import Entities.Doctor;
import Entities.Specialty;
import dao.DaoManager;
import dao.DoctorDao;
import dao.SpecialtyDao;
import exception.DataException;

public class SpecialtyServiceImpl extends ServiceImpl implements SpecialtyService {

	public SpecialtyServiceImpl(DaoManager manager) {
		super(manager);		
	}

	@Override
	public Collection<Specialty> getAllSpecialties() throws DataException {
		SpecialtyDao specialtydao = getManager().createDao(SpecialtyDao.class);
		return specialtydao.readAllSpecialties();
	}

	@Override
	public Specialty getSpecialtyById(int identity) throws DataException {
		SpecialtyDao specialtydao = getManager().createDao(SpecialtyDao.class);
		return specialtydao.read(identity);
	}

	@Override
	public void AddSpecialty(Specialty specialty) throws DataException {
		SpecialtyDao specialtydao = getManager().createDao(SpecialtyDao.class);
		specialtydao.create(specialty);
	}

	@Override
	public void UpdateSpecialty(Specialty specialty) throws DataException {
		SpecialtyDao specialtydao = getManager().createDao(SpecialtyDao.class);
		specialtydao.update(specialty);
	}

	@Override
	public void DeleteSpecialty(int identity) throws DataException {
		SpecialtyDao specialtydao = getManager().createDao(SpecialtyDao.class);
		specialtydao.delete(identity);
	}

	@Override
	public Specialty getSpecialtyByName(String name) throws DataException {
		SpecialtyDao specialtydao = getManager().createDao(SpecialtyDao.class);
		return specialtydao.readSpecialtyByName(name);
	}

	@Override
	public Integer calculateSumCost(Specialty specialty) throws DataException {
		Integer sumcost = 0;
		DoctorDao doctordao = getManager().createDao(DoctorDao.class);
        Collection<Doctor> asd = doctordao.readAllDoctors(specialty.getName());
        for(Doctor doctor : asd)
        {         
            Integer a = calculateSalary(doctor);
            if (Objects.equals(doctor.getSpecialty(), specialty.getName())) sumcost += a;
        }
        return sumcost;
	}

	@Override
	public Integer getCountOfDoctors(Specialty specialty) throws DataException {
		DoctorDao doctordao = getManager().createDao(DoctorDao.class);
		return doctordao.getCount(specialty.getName());
	}

	@Override
	public Integer calculateSalary(Doctor doctor) throws DataException {
		Integer workYear = doctor.getWorkday().getYear();
		Integer currentYear = Calendar.getInstance().getTime().getYear();
		Integer berthday = doctor.getBirtday().getYear();
		Specialty s = getSpecialtyByName(doctor.getSpecialty());
		int rate = s.getRate();

		int salary = rate;


		if(currentYear - workYear >= 5 && currentYear - workYear < 10)
		{
			salary = rate + Double.valueOf(0.05 * rate).intValue();
		}

		if(currentYear - workYear >= 10 && currentYear - workYear < 20)
		{
			salary = rate + Double.valueOf(0.1 * rate).intValue();
		}

		if(currentYear - workYear >= 20 && currentYear - workYear < 35)
		{
			salary = rate + Double.valueOf(0.15 * rate).intValue();
		}

		if(currentYear - workYear >= 35 && currentYear - workYear < 55 && Objects.equals(doctor.getSex(), "F"))
		{
			salary = rate + Double.valueOf(0.2 * rate).intValue();
		}

		if(currentYear - workYear >= 35 && currentYear - workYear < 60 && Objects.equals(doctor.getSex(), "M"))
		{
			salary = rate + Double.valueOf(0.2 * rate).intValue();
		}

		if((currentYear - berthday) > 55 && Objects.equals(doctor.getSex(), "F"))
		{
			salary = rate + Double.valueOf(0.2 * rate).intValue()+Double.valueOf(0.5 * rate).intValue();
		}

		if((currentYear - berthday > 60) && Objects.equals(doctor.getSex(), "M"))
		{
			salary = rate + Double.valueOf(0.2 * rate).intValue()+Double.valueOf(0.5 * rate).intValue();
		}
		
		return salary;
	}

}
