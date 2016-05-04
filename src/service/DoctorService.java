package service;

import java.util.Collection;

import Entities.Doctor;
import exception.DataException;

public interface DoctorService extends Service {
	Collection<Doctor> getAllDoctors(String name) throws DataException;
	Doctor getDoctorById (int identity) throws DataException;
	void AddDoctor(Doctor doctor) throws DataException;
	void UpdateDoctor (Doctor doctor) throws DataException;
	void DeleteDoctor (int identity) throws DataException;
	
}
