package org.collections;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Employee {
    private String id;
    private String name;
    private String department;
    private int salary;
    private Date joinDate;
    private int age;

    public Employee(String id, String name, String department, int salary, Date joinDate, int age) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.joinDate = joinDate;
        this.age = age;
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public Date getJoinDate() {
        return joinDate;
    }
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", joinDate=" + joinDate +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        try{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        ArrayList<Employee> employeeList = new ArrayList<Employee>() ;
        employeeList.add(new Employee("01", "Alina Smith", "HR", 4000, sdf.parse("2020-04-15"), 36));
        employeeList.add(new Employee("02", "Bob John", "IT", 60000, sdf.parse("2019-08-23"), 35));
        employeeList.add(new Employee("03", "Charlie Brown", "Finance", 55000, sdf.parse("2021-01-10"), 28));
        employeeList.add(new Employee("04", "Popov Ana", "Marketing", 65000, sdf.parse("2018-11-05"), 40));
        employeeList.add(new Employee("05", "Emma Frank", "IT", 500, sdf.parse("2020-07-19"), 32));
        employeeList.add(new Employee("06", "Millie Bobby", "Sales", 45000, sdf.parse("2022-03-14"), 29));
        employeeList.add(new Employee("07", "Peaky Blinders", "HR", 52000, sdf.parse("2019-06-29"), 34));
        employeeList.add(new Employee("08", "Smith Christina", "Sales", 50000, sdf.parse("2022-04-15"), 28));

        //(ex.1)
        int maxSalaryIT = getMaxSalaryForDepartment(employeeList, "IT");
        System.out.println("Ex1. Max salary in IT department: " + maxSalaryIT);
        System.out.println("-------------------");
        //(ex.2)
        OptionalDouble avgSalaryHR = getAverageSalaryForDepartment(employeeList, "HR");
        System.out.println ("Ex2. Average salary in HR department: " + avgSalaryHR.getAsDouble());
        System.out.println("-------------------");
        //(ex.3)
        long numberOfEmployeeSales = getNumberOfEmployees(employeeList,"Sales");
        System.out.println("Ex3. Number of Employees in Sales: " + numberOfEmployeeSales);
        System.out.println("-------------------");
        //(ex.4)
        Long numberOfEmployeeExceptIT = getNumberOfEmployeeExceptIT(employeeList, excludeDepartment -> excludeDepartment.equals("IT"));
        System.out.println("Ex4. Number of Employees except IT department: " + numberOfEmployeeExceptIT);
        System.out.println("-------------------");
        //(ex.5)
        System.out.println("Ex5. Group by Job Department: " + groupByJobDepartment(employeeList));
        System.out.println("-------------------");
        //(ex.6)
        System.out.println("Ex6.");
        List<Employee> filteredEmployees = printEmployeesJoinedAfter2020WithSalaryAbove1000(employeeList, sdf.parse("2020-01-01"), 1000);
        filteredEmployees.forEach(employee -> {
            System.out.println("Name: " + employee.getName() + ", Join Date: " + employee.getJoinDate() + ", Salary: " + employee.getSalary());
        });
        System.out.println("-------------------");
        //(ex.7)
        System.out.println("Ex7.");
        Map<String, Integer> result = getLast3Employees(employeeList);
        result.forEach((name, age) -> System.out.println(name + " : " + age));
        System.out.println("-------------------");
        //(ex.8)
        int sumOfAllEmployees = calculateSumOfAllSalary(employeeList);
        System.out.println("Ex8. Sum of all Salaries: " + sumOfAllEmployees);
        System.out.println("-------------------");
        //(ex.9)
        Map<String, List<String>> salariesPerDepartment = returnAllSalaryPerDepartment(employeeList);
        System.out.println("Ex9. Display all salaries per Department:");
        salariesPerDepartment.forEach((department, salaries) ->
        System.out.println("Department: " + department + "," + salaries));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //(ex.1)
    public static int getMaxSalaryForDepartment(ArrayList<Employee> employees, String department) {
        return employees.stream()
                .filter(i -> i.getDepartment().equals(department))
                .map(Employee::getSalary).max((Integer::compareTo)).get();
    }
    //(ex.2)
    public static OptionalDouble getAverageSalaryForDepartment(ArrayList<Employee> employees, String department) {
        return employees.stream()
                .filter(i -> i.getDepartment().equals(department))
                .mapToInt(Employee::getSalary)
                .average();
    }
    //(ex.3)
    public static long getNumberOfEmployees(ArrayList<Employee> employees, String department) {
        return employees.stream()
                .filter(i -> i.getDepartment().equals(department))
                .count();
    }
    //(ex.4)
    public static long getNumberOfEmployeeExceptIT(ArrayList<Employee> employees, Predicate<String> exceptDepartment) {
        return employees.stream()
                .filter(employee -> !exceptDepartment.test(employee.getDepartment()))
                .count();
    }
    //(ex.5)
    public static Map<String, List<Employee>> groupByJobDepartment(List<Employee> employeeList) {
        return employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
    //(ex.6)
    public static List<Employee> printEmployeesJoinedAfter2020WithSalaryAbove1000(List<Employee> employeeList, Date startDate, int salary) {
        return employeeList.stream()
                .filter(employee -> employee.getJoinDate().after(startDate) && employee.getSalary() > salary)
                .collect(Collectors.toList());
    }
    //(ex.7)
    public static Map<String, Integer> getLast3Employees(List<Employee> employees) {
        List<Employee> sortedEmployees = employees.stream()
                .sorted((e1, e2) -> e2.getJoinDate().compareTo(e1.getJoinDate()))
                .collect(Collectors.toList());

        List<Employee> getLastThree = sortedEmployees.stream().limit(3).collect(Collectors.toList());

        Map<String, Integer> employeeMap = new LinkedHashMap<>();
        for (Employee emp : getLastThree) {
            employeeMap.put(emp.getName(), emp.getAge());
        }
        return employeeMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
    //(ex.8)
    public static int calculateSumOfAllSalary(ArrayList<Employee> employeeList) {
        int sum = 0;
        for (Employee i : employeeList) {
            sum += i.getSalary();
        }
        return sum;
    }
    //(ex.9)
    public static Map<String, List<String>> returnAllSalaryPerDepartment(ArrayList<Employee> employeeList) {
        Map<String, List<String>> resultMap = new HashMap<>();
        for (Employee i : employeeList) {
            String department = i.getDepartment();
            String salary = String.valueOf(i.getSalary());

            if (!resultMap.containsKey(department)) {
                resultMap.put(department, new ArrayList<>());
            };
            resultMap.get(department).add(salary);
        };
        return resultMap;
    }}