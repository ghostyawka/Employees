package service.impl;

import models.Person;
import models.enums.Position;
import service.DBHelper;
import service.MainService;

import java.util.List;
import java.util.Scanner;

public class MainServiceImpl implements MainService {

    Scanner scanner = new Scanner(System.in);
    DBHelper dbHelper = new DBHelperImpl();
    @Override
    public void start() {
        dbHelper.addLocalPerson();
        int mainAnswer=0;
        while (mainAnswer==0) {
        System.out.println("Выберите действие");
        System.out.println("1. Заведение нового сотрудника");
        System.out.println("2. Просмотр списка");
        System.out.println("3. Анализ");
        int answer = scanner.nextInt();

        switch (answer) {
            case 1:
                addNewPerson();
                break;
            case 2:
                viewPersonList();
                break;
            case 3:
                analize();
                break;
            default:
                throw new RuntimeException("Неверно указан ответ");
        }
        }
    }

    private void analize() {
        System.out.println("Выберите анализ");
        System.out.println(" 1.Вывести средний возраст сотрудников\n" +
                "2.Вывести мах и мин возраст\n" +
                "3 Вывести среднюю зп\n" +
                "4 Вывести сотрудника с самой большой зп\n" +
                "5 Вывести сотрудника с самой низкой зп\n" +
                "6 Сгруппировать всех по должности\n" +
                "7 Сгруппировать всех по возрасту\n" +
                "8 Вывести общую стоимость затрат на зп за год\n" +
                "9 Вывести только тех у кого зп меньше 10000\n" +
                "10 Вывести список возрастов сотрудников в определенной должности на ваш выбор");
        int localAnswer=scanner.nextInt();
        switch (localAnswer) {
            case 1:
                dbHelper.printAverageAge();
                break;
            case 2:
                dbHelper.printMaxMinAge();
                break;
            case 3:
                dbHelper.printAverageSalary();
                break;
            case 4:
                dbHelper.printPersonWithHighest();
                break;
            case 5:
                dbHelper.printPersonWithLowest();
                break;
            case 6:
                dbHelper.printGroupBy();
                break;
            case 7:
                dbHelper.printGroupByAge();
                break;
            case 8:
                dbHelper.printTotalSalary();
                break;
            case 9:
                dbHelper.printSalaryUnder();
                break;
            case 10:
                dbHelper.printByPosition();
                break;
            default:
                System.out.println("Неверный номер");
        }
    }

    private void viewPersonList() {
        int localAnswer=0;
        while (localAnswer==0) {
            System.out.println("Выберите способ сортировки");
            int answer = 0;
            System.out.println("По имени 1");
            System.out.println("От новых к старым 2");
            System.out.println("От старых к новым 3");
            answer = scanner.nextInt();
            List<Person> personList = dbHelper.getSortedPerson(answer);
            System.out.println(personList);

            System.out.println("Хотите продолжить? ");
            System.out.println("да 1, нет 0");
            if(scanner.nextInt()==1){
                localAnswer=0;
            }else {
                localAnswer=1;
            }
        }
    }

    private void addNewPerson() {
        int localAnswer = 0;
        while (localAnswer==0) {
            System.out.println("Укажите имя сотрудника");
            String name = scanner.next();
            System.out.println("Укажите возраст сотрудника");
            int age = scanner.nextInt();
            if (age < 18) {
                System.out.println("Возраст не может быть меньше 18");
                return;
            }
            System.out.println("Укажите зарплату сотрудника");
            double salary = scanner.nextDouble();
            if (salary < 0) {
                System.out.println("Неверная сумма зарплаты");
                return;
            }
            System.out.println("Выберите позицию");
            for (Position position : Position.values()) {
                System.out.println((position.getVal() + 1) + "." + position);
            }
            int positionVal = scanner.nextInt();
            Position position = Position.values()[positionVal - 1];

            Person person = dbHelper.savePerson(name, age, salary, position);
            System.out.println("Сотрудник удачно сохранен");
            System.out.println(person);
            System.out.println("Хотите продолжить? ");
            System.out.println("Да 1, нет 0");
            if (scanner.nextInt() == 1) {
                localAnswer = 0;
            } else {
                localAnswer = 1;
            }
        }
    }
}

