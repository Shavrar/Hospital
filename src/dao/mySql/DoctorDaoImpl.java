package dao.mySql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import Entities.Doctor;
import dao.DoctorDao;
import exception.DataException;

public class DoctorDaoImpl extends BaseDao implements DoctorDao {

	public DoctorDaoImpl(Connection connection) {
		super(connection);
		if(map==null)
			map = new HashMap<>();
	}

	@Override
	public Integer create(Doctor entity) throws DataException {
		String sql = "INSERT INTO doctors (name, birthday, workday, area, salary, sex, domain_name, specialty_id) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, (SELECT id FROM specialtys WHERE  name = ?))";
		     
		     PreparedStatement s = null;
		     try {
		         		         
		         s = connection.prepareStatement(sql);
		         
		         s.setString(1, entity.getName());
		         s.setDate(2, entity.getBirtday());
		         s.setDate(3, entity.getWorkday());
		         s.setInt(4, entity.getArea());
		         s.setInt(5, 0);		         
		         s.setString(6, entity.getSex());
		         s.setString(7, entity.getDomain_name());
		         s.setString(8, entity.getSpecialty());
		         s.executeUpdate();	
		         return 1;
		     }
		     catch(SQLException e){
		    	 throw new DataException();
		     }      
		     finally {
		         try {
		             s.close();
		         } catch(NullPointerException | SQLException e) {}		        
		     }

	}

	@Override
	public Doctor read(Integer identity) throws DataException {		
		String sql = "select doctors.*,specialtys.name as sname from doctors join specialtys on doctors.specialty_id = specialtys.id  where doctors.id = ?";
        PreparedStatement s = null;
        ResultSet r = null;
        try {           
            
            s = connection.prepareStatement(sql);
            
            s.setInt(1, identity);
            
            r = s.executeQuery();
            
            Doctor temp = new Doctor();
            
            if(r.next()) {

                temp.setId(r.getInt("id"));

                temp.setName(r.getString("name"));

                temp.setBirtday(Date.valueOf(r.getString("birthday")));

                temp.setWorkday(Date.valueOf(r.getString("workday")));

                temp.setArea(r.getInt("area"));

                temp.setSalary(0);

                temp.setSpecialty(r.getString("sname"));

                temp.setSex(r.getString("sex"));
                
                temp.setDomain_name(r.getString("domain_name"));
            }            
            return temp;
        }
        catch(SQLException e){
        	throw new DataException();
        }      
        finally {
            try {
                r.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}         
        }

	}

	@Override
	public void update(Doctor entity) throws DataException {
		String sql = "UPDATE doctors SET "
                + "name = ?, birthday = ?, workday = ?, area = ?, salary = ?, sex = ?, domain_name = ? "
                + "WHERE id = ?";
     
     PreparedStatement s = null;
     try {
                 
         s = connection.prepareStatement(sql);
        
         
         s.setString(1, entity.getName());
         s.setDate(2, entity.getBirtday());
         s.setDate(3, entity.getWorkday());
         s.setInt(4, entity.getArea());
         s.setInt(5, 0);        
         s.setString(6, entity.getSex());
         s.setString(7, entity.getDomain_name());
         s.setInt(8, entity.getId());
         s.executeUpdate();
         
     }
     catch(SQLException e){
     	throw new DataException();
     }      
     finally {
         try {
             s.close();
         } catch(NullPointerException | SQLException e) {}
     }


	}

	@Override
	public void delete(Integer identity) throws DataException {
		  String sql = "DELETE FROM doctors "
                  + "WHERE id = ?";
       
       PreparedStatement s = null;
       try {
          
           s = connection.prepareStatement(sql);
           s.setInt(1, identity);
           s.executeUpdate();
           
       }
       catch(SQLException e){
       throw new DataException();       	
       }      
       finally {
           try {
               s.close();
           } catch(NullPointerException | SQLException e) {}       
       }


	}

	@Override
	public Collection<Doctor> readAllDoctors(String name) throws DataException {
		//String sql = "select * from doctors where specialty = '"+name+"' order by name";
		String sql = "select doctors.*,specialtys.name as sname from doctors join specialtys on doctors.specialty_id = specialtys.id"
				+ "  where specialtys.name = '"+name+"' order by doctors.name";
        Statement s = null;
        ResultSet r = null;
        try {
           
            s = connection.createStatement();
            r = s.executeQuery(sql);
            Collection<Doctor> doctors = new ArrayList<>();
            while(r.next()) {
            	Doctor temp = new Doctor();
    			
                temp.setId(r.getInt("id"));
				                				
                temp.setName(r.getString("name"));
                
                temp.setBirtday(Date.valueOf(r.getString("birthday")));
                
                temp.setWorkday(Date.valueOf(r.getString("workday")));

                temp.setArea(r.getInt("area"));

                temp.setSalary(0);

                temp.setSpecialty(r.getString("sname"));

                temp.setSex(r.getString("sex"));
                
                temp.setDomain_name(r.getString("domain_name"));
				                             
                doctors.add(temp);
            }
            
            return doctors;
        }
        catch(SQLException e){
        	throw new DataException();
        }      
        finally {
            try {
                r.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}          
        }

	}

	@Override
	public Integer getCount(String name) throws DataException {
		String sql = "select Count(*) as temp from doctors join specialtys on doctors.specialty_id = specialtys.id"
				+ "  where specialtys.name = '"+name+"' order by doctors.name";
		//String sql = "Select Count(*) as temp From doctors "
                //+ "WHERE specialty = '"+name+"'";     
     Statement s = null;
     ResultSet r = null;
     try {      
         s = connection.createStatement();
     
         r = s.executeQuery(sql);
         
         Integer temp=0;
         while(r.next()) {
         	temp=r.getInt("temp");
         }       
         return temp;
     } 
     catch(SQLException e){
     	throw new DataException();
     }      
     finally {
         try {
             r.close();
         } catch(NullPointerException | SQLException e) {}
         try {
             s.close();
         } catch(NullPointerException | SQLException e) {}        
     }

	}

}
