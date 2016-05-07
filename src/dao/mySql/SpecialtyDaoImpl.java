package dao.mySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import Entities.Specialty;
import dao.SpecialtyDao;
import exception.DataException;

public class SpecialtyDaoImpl extends BaseDao implements SpecialtyDao {

	public SpecialtyDaoImpl(Connection connection) {
		super(connection);
		if(map==null)
			map = new HashMap<>();
	}

	@Override
	public Integer create(Specialty entity) throws DataException {
		String sql = "INSERT INTO specialtys (name, rate, narrow) VALUES "
                + "(?, ?, ?)";
     
	     PreparedStatement s = null;
	     try {
	        
	         s = connection.prepareStatement(sql);
	         
	         s.setString(1, entity.getName());
	         s.setInt(2, entity.getRate());
	         s.setBoolean(3, entity.getNarrow());
	         
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
	public Specialty read(Integer identity) throws DataException {
		 String sql = "select * from specialtys where id = ?";        
	        PreparedStatement s = null;
	        ResultSet r = null;
	        try {
	            s = connection.prepareStatement(sql);
	            s.setInt(1, identity);
	            r = s.executeQuery();
	            Specialty temp = new Specialty();
	            if(r.next()) {

	                temp.setId(r.getInt("id"));

	                temp.setName(r.getString("name"));

	                temp.setRate(r.getInt("rate"));

	                temp.setNarrow(r.getBoolean("narrow"));

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
	public void update(Specialty entity) throws DataException {
		 String sql = "UPDATE specialtys SET "
                 + "name = ?, rate = ?, narrow = ?"
                 + " WHERE id = ?";  
		 
      PreparedStatement s = null;
      try {
        
          s = connection.prepareStatement(sql);         
          s.setString(1, entity.getName());
          s.setInt(2, entity.getRate());
          s.setBoolean(3, entity.getNarrow());       
          s.setInt(4, entity.getId());
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
		 String sql = "DELETE FROM specialtys "
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
	public Collection<Specialty> readAllSpecialties() throws DataException {
		String sql = "select * from specialtys order by name";        
        Statement s = null;
        ResultSet r = null;
        try {
            
            s = connection.createStatement();
            r = s.executeQuery(sql);
            
            Collection<Specialty> specialties = new ArrayList<>();
            
            while(r.next()) {
            	Specialty temp = new Specialty();
    			
                temp.setId(r.getInt("id"));
				                				
                temp.setName(r.getString("name"));
                
                temp.setRate(r.getInt("rate"));

                temp.setNarrow(r.getBoolean("narrow"));
				                             
                specialties.add(temp);
            }           
            return specialties;
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
	public Specialty readSpecialtyByName(String name) throws DataException {
		 String sql = "select * from specialtys where name = ?";	        
	        PreparedStatement s = null;
	        ResultSet r = null;
	        try {
	            s = connection.prepareStatement(sql);
	            s.setString(1, name);
	            r = s.executeQuery();
	            Specialty temp = new Specialty();
	            if(r.next()) {

	                temp.setId(r.getInt("id"));

	                temp.setName(r.getString("name"));

	                temp.setRate(r.getInt("rate"));

	                temp.setNarrow(r.getBoolean("narrow"));

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
