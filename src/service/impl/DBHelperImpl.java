package service.impl;

import javafx.geometry.Pos;
import models.Person;
import models.enums.Position;
import service.DBHelper;

import java.util.*;
import java.util.stream.Collectors;

public class DBHelperImpl implements DBHelper {
    List<Person> personList = new ArrayList<>();
    Long idGenerate =5l;

    @Override
    public void addLocalPerson() {
        personList.add(new Person(1l,"Aidai",23,100000d,Position.DEVELOPER));
        personList.add(new Person(2l,"Aibek",16,1000d,Position.MANAGER));
        personList.add(new Person(3l,"Jazgul",20,130000d,Position.CEO));
        personList.add(new Person(4l,"Timur",18,18000d,Position.SELLER));
    }

    @Override
    public Person savePerson(String name, int age, Double salary, Position position) {
        Person person = new Person();
        person.setId(idGenerate++);
        person.setAge(age);
        person.setName(name);
        person.setSalary(salary);
        person.setPosition(position);
        personList.add(person);

        return person;
    }

    @Override
    public List<Person> getSortedPerson(int answer) {
        switch (answer){
            case 1:
                Collections.sort(personList,Comparator.comparing(Person::getName));
                break;
            case 2:
                Collections.sort(personList,Comparator.comparing(Person::getId));
                break;
            case 3:
                Collections.sort(personList,Comparator.comparing(Person::getId));
                Collections.reverse(personList);
        }
        return personList;
    }

    @Override
    public void printAverageAge() {
        double averageAge = personList.stream().mapToDouble(x->x.getAge()).average().orElse(0);
        System.out.println(averageAge);
    }

    @Override
    public void printMaxMinAge() {
        int maxAge = personList.stream().mapToInt(x->x.getAge()).max().orElse(0);
        int minAge = personList.stream().mapToInt(x->x.getAge()).min().orElse(0);
        System.out.println(maxAge);
        System.out.println(minAge);
    }

    @Override
    public void printAverageSalary() {
        double averageSalary = personList.stream().mapToDouble(x-> x.getSalary()).average().orElse(0);
        System.out.println(averageSalary);
    }

    @Override
    public void printPersonWithHighest() {
        double maxSalary = personList.stream().mapToDouble(x->x.getSalary()).max().orElse(0);
        System.out.println(maxSalary);
    }

    @Override
    public void printPersonWithLowest() {
        double minSalary = personList.stream().mapToDouble(x->x.getSalary()).min().orElse(0);
        System.out.println(minSalary);
    }

    @Override
    public void printGroupBy() {
        Map< Position, List <Person>> groupByPosition = personList.stream()
                .collect(Collectors.groupingBy(Person::getPosition));
        System.out.println(groupByPosition);
    }

    @Override
    public void printGroupByAge() {
        Map< Integer, List <Person>> groupAge = personList.stream()
                .collect(Collectors.groupingBy(Person::getAge));
        System.out.println(groupAge);
    }

    @Override
    public void printTotalSalary() {
        double totalSalary = personList.stream().mapToDouble(x->x.getSalary()).sum()*12;
        System.out.println(totalSalary);
    }

    @Override
    public void printSalaryUnder() {
        List <Person> underSalary = personList.stream().filter(x->x.getSalary()<10000).collect(Collectors.toList());
        System.out.println(underSalary);
    }

    @Override
    public void printByPosition() {
        Scanner scanner = new Scanner (System.in);
        System.out.println("Выберите должность");
        for (Position position : Position.values()) {
            System.out.println((position.getVal() + 1) + "." + position);
        }
        int positionForAnalyze = scanner.nextInt();
        Position position = Position.values()[positionForAnalyze - 1];
        List <Integer> byAges = personList.stream().filter(x->x.getPosition()==position).map(x->x.getAge())
                                                                        .collect(Collectors.toList());
        System.out.println("Для должности " + position);
        for (Integer byAge:byAges) {
            System.out.println(byAge);
        }

    }


}
