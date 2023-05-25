import java.util.*;
import java.io.*;
public class TeamLeader extends Developer{

    private double bonus_rate;

    public TeamLeader(String empID, String empName, int baseSal, String teamName, ArrayList<String> programmingLanguages, int expYear, double bonus_rate){
        super(empID, empName, baseSal, teamName, programmingLanguages, expYear);
        this.bonus_rate = bonus_rate;
    }
    public void getBonus_rate(double bonus_rate){
        this.bonus_rate = bonus_rate;
    }
    public double getSalary(){
        return super.getSalary()+bonus_rate*super.getSalary();
    }
}
