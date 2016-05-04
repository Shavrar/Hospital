package service;

import java.util.Collection;

import Entities.Doctor;
import dao.DaoManager;
import dao.DoctorDao;
import exception.DataException;

public class DoctorServiceImpl extends ServiceImpl implements DoctorService {

	public DoctorServiceImpl(DaoManager manager) {
		super(manager);		
	}

	@Override
	public Collection<Doctor> getAllDoctors(String name) throws DataException {
		DoctorDao doctordao = getManager().createDao(DoctorDao.class);
		return doctordao.readAllDoctors(name);
	}

	@Override
	public Doctor getDoctorById(int identity) throws DataException {
		DoctorDao doctordao = getManager().createDao(DoctorDao.class);
		return doctordao.read(identity);
	}

	@Override
	public void AddDoctor(Doctor doctor) throws DataException {
		DoctorDao doctordao = getManager().createDao(DoctorDao.class);
		doctordao.create(doctor);
	}

	@Override
	public void UpdateDoctor(Doctor doctor) throws DataException {
		DoctorDao doctordao = getManager().createDao(DoctorDao.class);
		doctordao.update(doctor);
	}

	@Override
	public void DeleteDoctor(int identity) throws DataException {
		DoctorDao doctordao = getManager().createDao(DoctorDao.class);
		doctordao.delete(identity);
	}

}
