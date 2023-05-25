import java.util.*;
import java.io.*;

public class CompanyManagement<E>{
    private ArrayList<Employee> empList;

    //path: path of ListOfEmployees, path1: path of PLInfo.txt
    public CompanyManagement(String path, String path1) throws FileNotFoundException{
      empList = getEmployeeFromFile(path, path1);
    } 
    
	//Phan code cua sinh vien
	
    //path: path of ListOfEmployees, path1: path of PLInfo.txt
    public ArrayList<Employee> getEmployeeFromFile(String path, String path1) throws FileNotFoundException{	
		ArrayList<Employee> empList = new ArrayList<Employee>();
		try{
			File file = new File(path);
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()){				
				String s = sc.nextLine();
				String[] sp = s.split(",");	
				char c = sp[1].charAt(0);
				if(c=='D'){					
					File file1 = new File(path1);
					Scanner sc1 = new Scanner(file1);					
					while(sc1.hasNextLine()){						
						ArrayList<String> lg = new ArrayList<String>();
						String s1 = sc1.nextLine();
						String[] sp1 = s1.split(",");						
						if(sp[1].equals(sp1[0])){							
							for(int i=1;i<sp1.length;i++){
								lg.add(sp1[i]);
							}							
							if(sp[5].equals("L")){
								TeamLeader Tld = new TeamLeader(sp[1],sp[2],Integer.parseInt(sp[7]),sp[3],lg,Integer.parseInt(sp[4]),Double.parseDouble(sp[6]));
								empList.add(Tld);								
							}else{
								Developer Dvl = new Developer(sp[1],sp[2],Integer.parseInt(sp[5]),sp[3],lg,Integer.parseInt(sp[4]));
								empList.add(Dvl);
							}
						}
					}
					sc1.close();					
				}else{
					Tester Tt=new Tester(sp[1],sp[2],Integer.parseInt(sp[5]),Double.parseDouble(sp[3]),sp[4]);
					empList.add(Tt);
				}
			}
			sc.close();			
		}catch(FileNotFoundException e){
			System.out.println("Error!");
		}
		return empList;
    }
	
	public ArrayList<Developer> getDeveloperByProgrammingLanguage(String pl){
		ArrayList<Developer> Deve = new ArrayList<Developer>();
		for(Employee e:empList){
			if(e instanceof Developer){
				Developer a = (Developer) e;
				ArrayList<String> list;
				list = a.getProgrammingLanguages();
				for(String i:list){
					if(i.equals(pl))
						Deve.add(a);
				}
            }
		}
      return Deve;
    }
	
    public ArrayList<Tester> getTestersHaveSalaryGreaterThan(double value){
		ArrayList<Tester> T = new ArrayList<Tester>();
		for(Employee e:empList){
			if(e instanceof Tester){
				Tester a = (Tester) e;
				if(a.getSalary()>value){
					T.add(a);
				}
            }
		}
		return T;
    }
	
    public Employee getEmployeeWithHigestSalary(){
		Employee emp = empList.get(0);
		double max = 0;
		for(Employee e:empList){
			if(e.getSalary()>=max){
				max = e.getSalary();
				emp = e;
			}
		}
		return emp;
    }

    public TeamLeader getLeaderWithMostEmployees(){
		ArrayList<TeamLeader> T = new ArrayList<TeamLeader>();
		ArrayList<Developer> D = new ArrayList<Developer>();
		for(Employee e:empList){
			if(e instanceof TeamLeader){
				TeamLeader e1 = (TeamLeader) e;
				T.add(e1);	
			}
			if(e instanceof Developer){
				Developer e1 = (Developer) e;
				D.add(e1);
			}			
		}
		TeamLeader temp = T.get(0);
		int member, tMax = 0;
		for(TeamLeader e1:T){
				member = 1;
				for(Developer e2:D){
					if(e1.getTeamName().equals(e2.getTeamName())) 
						member++;
				}
				if(member>tMax){
					tMax = member;
					temp = e1;     
				}
				if(member==tMax){
					if(temp.getExpYear()<e1.getExpYear()){
						temp = e1;
					}
				}
		}
		return temp;
    }

    public ArrayList<Employee> sorted(){
		ArrayList<Employee> sort = empList;
		for(int i=0;i<sort.size()-1;i++){
			for(int j=i+1;j<sort.size();j++){
				if(sort.get(i).getSalary()<sort.get(j).getSalary()){
					Employee temp = sort.get(i);
					sort.set(i,sort.get(j));
					sort.set(j,temp);					
				}
				if(sort.get(i).getSalary()==sort.get(j).getSalary()){
					String name1[]=sort.get(i).getEmpName().split(" ");
					String name2[]=sort.get(j).getEmpName().split(" ");
					char c1 = name1[name1.length-1].charAt(0);
					char c2 = name2[name2.length-1].charAt(0);
					if(c1>c2){
						Employee temp = sort.get(j);
						sort.set(j,sort.get(i));
						sort.set(i,temp);
					}  
				}
			}
		}
		return sort;	
    }
	
	//-------------------------------------------------------------------
	
	//Print empList
	public void printEmpList(){
      for(Employee tmp : empList){
        System.out.println(tmp);
      }
    }
	
    //Methods for writing file
    public <E> boolean writeFile(String path, ArrayList<E> list){
      try {
        FileWriter writer = new FileWriter(path);
        for(E tmp : list){
          writer.write(tmp.toString());
          writer.write("\r\n");
        }
        writer.close();
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("Error.");
        return false;
      }
      return true;
    }

    public <E> boolean writeFile(String path, E object){
      try {
        FileWriter writer = new FileWriter(path);
        writer.write(object.toString());
        writer.close();
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("Error.");
        return false;
      }
      return true;
    }
}