package dao;

import java.util.Collection;

import Entities.Doctor;
import exception.DataException;

public interface DoctorDao extends Dao<Doctor> {
	Collection<Doctor> readAllDoctors(String name) throws DataException;
	Integer getCount(String name)throws DataException;
}
