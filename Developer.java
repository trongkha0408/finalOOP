import java.util.*;
import java.io.*;
public class Developer extends Employee{
	
	protected String teamName;
	protected ArrayList<String> programmingLanguages = new ArrayList<String>();
	protected int expYear;
	
	public Developer(String empID, String empName, int baseSal, String teamName, ArrayList<String> programmingLanguages, int expYear){
		super(empID, empName, baseSal);
		this.teamName=teamName;
		this.expYear=expYear;
		this.programmingLanguages=programmingLanguages;
	}
	public String getTeamName(){
		return this.teamName;
	}
	public ArrayList<String> getProgrammingLanguages(){
		return this.programmingLanguages;
	}
	public int getExpYear(){
		return this.expYear;
	}
	public double getSalary(){
		if(this.expYear>=5){
			return baseSal+expYear*2000000;
		}
		if(expYear<5&&expYear>=3){
			return baseSal+expYear*1000000;
		}
		return baseSal;
	}
	public String toString(){
		return empID+"_"+empName+"_"+baseSal+"_"+teamName+"_"+programmingLanguages+"_"+expYear;
	}
}