package pe.com.capacitacion.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

/**
 * ConfigurationData_02
 * @author cguerra
 **/
 @Data
 @Configuration
 @ConfigurationProperties( "ingress.dns" )
 public class ConfigurationData_02{
	
	    private String employee;
	    private String department;
	    private String organization;
	    
		public String getEmployee() {
			   return employee;
		}
		
		public void setEmployee(String employee) {
			   this.employee = employee;
		}
		
		public String getDepartment() {
			   return department;
		}
		
		public void setDepartment(String department) {
			   this.department = department;
		}
		
		public String getOrganization() {
			   return organization;
		}
		
		public void setOrganization(String organization) {
			   this.organization = organization;
		}
 
 }

 