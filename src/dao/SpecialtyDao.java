package dao;

import java.util.Collection;

import Entities.Specialty;
import exception.DataException;

public interface SpecialtyDao extends Dao<Specialty> {
	Collection<Specialty> readAllSpecialties() throws DataException;
	Specialty readSpecialtyByName(String name)throws DataException;
}
