package service;

import javafx.geometry.Pos;
import models.Person;
import models.enums.Position;

import java.util.List;

public interface DBHelper {

    void addLocalPerson();

    Person savePerson (String name, int age, Double salary, Position position);

    List<Person> getSortedPerson(int answer);

    void printAverageAge();

    void printMaxMinAge();

    void printAverageSalary();

    void printPersonWithHighest();

    void printPersonWithLowest();

    void printGroupBy();

    void printGroupByAge();

    void printTotalSalary();

    void printSalaryUnder();

    void printByPosition();
}
