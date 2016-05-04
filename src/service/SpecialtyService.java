package service;

import java.util.Collection;

import Entities.Doctor;
import Entities.Specialty;
import exception.DataException;

public interface SpecialtyService extends Service{
	Collection<Specialty> getAllSpecialties() throws DataException;
	Specialty getSpecialtyById (int identity) throws DataException;
	void AddSpecialty (Specialty specialty) throws DataException;
	void UpdateSpecialty (Specialty specialty) throws DataException;
	void DeleteSpecialty (int identity) throws DataException;
	Specialty getSpecialtyByName(String name)throws DataException;
	Integer calculateSumCost(Specialty specialty) throws DataException;
	Integer getCountOfDoctors(Specialty specialty)throws DataException;
	Integer calculateSalary(Doctor doctor)throws DataException;
}
