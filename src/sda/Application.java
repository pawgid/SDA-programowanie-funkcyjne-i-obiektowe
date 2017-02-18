package sda;

import sda.workers.AbstractEmployee;
import sda.workers.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        List<AbstractEmployee> employeeList = new ArrayList<>();
        employeeList.add(new AbstractEmployee("Jan", "Kowalski", 5000, "Java"));
        employeeList.add(new AbstractEmployee("Szymon", "Nowak", 2000, "Java"));
        employeeList.add(new AbstractEmployee("Anna", "Wisniewska", 3000, "HR"));
        employeeList.add(new AbstractEmployee("Karolina", "Nowak", 3000, "PM"));
        employeeList.add(new AbstractEmployee("Andrzej", "Duda", 5000, "Director"));
        //1.Filtr ludzi z dzialu Java
        employeeList.stream()
                .filter(e -> e.getDepartment().equals("Java"))
                .forEach(e -> System.out.println(e));
        System.out.println();
        //2.Filtr kobiet
        employeeList.stream()
                .filter(e -> e.getFirstName().endsWith("a"))
                .forEach(e -> System.out.println(e));
        System.out.println();
        //3. Filtr po pensji
        employeeList.stream()
                .filter(e -> e.getSalary() >= 3000)
                .forEach(e -> System.out.println(e));
        System.out.println();
        //4. Filtr po pensji i dziale
        employeeList.stream()
                .filter(e -> e.getSalary() >= 3000)
                .filter((e -> e.getDepartment().equals("Java")))
                .forEach((e -> System.out.println(e)));
        //5.zapisanie do listy pracownikow java
        List<AbstractEmployee> javaEmployees = employeeList.stream()
                .filter(e -> e.getDepartment().equals("Java"))
                .collect(Collectors.toList());
        System.out.println(javaEmployees);
        System.out.println();
        //6.szukamy pracownikow po nazwisku
        employeeList.stream()
                .filter(e -> e.getLastName().equals("Nowak"))
                .forEach(e -> System.out.println(e));
        System.out.println();
        //7.szukamy po nazwisku mateoda starts with
        employeeList.stream()
                .filter(e -> e.getLastName().startsWith("Now"))
                .forEach(e -> System.out.println(e));
        System.out.println();
        //8.dzielimy liste na mape ludzi gdzie klucz to ich imie, a wartosc to AbstractEmployee
        Map<String, AbstractEmployee> map = employeeList.stream()
                .collect(Collectors.toMap((e -> e.getFirstName()), e -> e));
        map.forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println();

        //9.zwracamy liste Employee szukane po imie + " " + nazwisko
        employeeList.stream()
                .filter(e -> (e.getFirstName() + " " + e.getLastName()).equals("Szymon Nowak"))
                .forEach(e -> System.out.println(e));
        System.out.println();

        //10. posortuj po salary
        System.out.println("Posortuj po salary");
        employeeList.sort((e1, e2) -> e1.getSalary() > e2.getSalary() ? 1 : -1);
        employeeList.forEach(e -> System.out.println(e.getFirstName() + ": " + e.getSalary()));
        System.out.println();

        //11. wyswietl employee ktory zarabia najwiecej
        employeeList.sort((e1, e2) -> e1.getSalary() < e2.getSalary() ? 1 : -1);
        System.out.println(employeeList.get(0));
        System.out.println();
        //11. wyswietl employee ktory zarabia najwiecej, metoda 2
        AbstractEmployee richestEmployee = employeeList.stream()
                .max((e1, e2) -> e1.getSalary() > e2.getSalary() ? 1 : -1)
                .get();
        System.out.println(richestEmployee);
        System.out.println();
        //12. wyswietl employee ktory zarabia najmniej
        AbstractEmployee poorestEmployee = employeeList.stream()
                .min((e1, e2) -> e1.getSalary() > e2.getSalary() ? 1 : -1)
                .get();
        System.out.println(poorestEmployee);
        System.out.println();

        Map<String, List<AbstractEmployee>> map1 = listToMap(employeeList);
        List<AbstractEmployee> tmpList = new ArrayList<>();
        map1.entrySet().stream()
                .forEach(e -> {
                    List<AbstractEmployee> value = e.getValue();
                    value.stream()
                            .filter(e1 -> e1.getSalary() >= 3000)
                            .forEach(e1 -> tmpList.add(e1));
                });
        System.out.println(tmpList);
    }

    public static Map<String, List<AbstractEmployee>> listToMap(List<AbstractEmployee> employees) {
        Map<String, List<AbstractEmployee>> map = new HashMap<>();
        for (AbstractEmployee employee : employees) {
            if (map.containsKey((employee.getDepartment()))) {
                List<AbstractEmployee> listOfEmployees = map.get(employee.getDepartment());
                listOfEmployees.add(employee);
            } else {
                ArrayList<AbstractEmployee> listOfEmployees = new ArrayList<>();
                listOfEmployees.add(employee);
                map.put(employee.getDepartment(), listOfEmployees);
            }
        }
        return map;
    }
}

