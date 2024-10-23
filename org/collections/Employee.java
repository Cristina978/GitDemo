package org.collections;

import java.util.*;
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
        ArrayList<Employee> employeeList = new ArrayList<Employee>() ;
        employeeList.add(new Employee("01", "Alina Smith", "HR", 4000, new Date(2020, 4, 15), 36));
        employeeList.add(new Employee("02", "Bob John", "IT", 60000, new Date(2019, 8, 23), 35));
        employeeList.add(new Employee("03", "Charlie Brown", "Finance", 55000, new Date(2021, 1, 10), 28));
        employeeList.add(new Employee("04", "Popov Ana", "Marketing", 65000, new Date(2018, 11, 5), 40));
        employeeList.add(new Employee("05", "Emma Frank", "IT", 70000, new Date(2020, 7, 19), 32));
        employeeList.add(new Employee("06", "Millie Bobby", "Sales", 45000, new Date(2022, 3, 14), 29));
        employeeList.add(new Employee("07", "Peaky Blinders", "HR", 52000, new Date(2019, 6, 29), 34));
        employeeList.add(new Employee("08", "Smith Christina", "Sales", 50000, new Date(2022, 4, 15), 28));

        //(1)
        int maxSalaryIT = getMaxSalaryForDepartment(employeeList, "IT");
        System.out.println("Max salary in IT department: " + maxSalaryIT);
        System.out.println("-------------------");
        //(2)
        double avgSalaryHR = getAverageSalaryForDepartment(employeeList, "HR");
        System.out.println ("Average salary in HR department: " + avgSalaryHR);
        System.out.println("-------------------");
        //(3)
        int numberOfEmployeeSales = getNumberOfEmployees(employeeList,"Sales");
        System.out.println("Number of Employees in Sales: " + numberOfEmployeeSales);
        System.out.println("-------------------");
        //(4)
        int numberOfEmployeeExceptIT = getNumberOfEmployeeExceptIT(employeeList, "IT");
        System.out.println("Number of Employees except IT department: " + numberOfEmployeeExceptIT);
        System.out.println("-------------------");
        //5)
        System.out.println("Group by Job Department: " + groupByJobDepartment(employeeList));
        System.out.println("-------------------");
        //(8)
        int sumOfAllEmployees = calculateSumOfAllSalary(employeeList);
        System.out.println("Sum of all Salaries: " + sumOfAllEmployees);
        System.out.println("-------------------");
        //(9)
        Map<String, List<String>> salariesPerDepartment = returnAllSalaryPerDepartment(employeeList);
        System.out.println("Display all salaries per Department:");
        salariesPerDepartment.forEach((department, salaries) ->
        System.out.println("Department: " + department + "," + salaries));

    }
    //(1)
    public static int getMaxSalaryForDepartment(ArrayList<Employee> employees, String department) {
        int maxSalary = 0;
        for (Employee i : employees) {
            if (i.getDepartment() == department) {
                if (i.getSalary() > maxSalary) {
                    maxSalary = i.getSalary();
                }
            }
        }
        return maxSalary;
    }
    //(2)
    public static double getAverageSalaryForDepartment(ArrayList<Employee> employees, String department) {
        int totalSalary = 0;
        int count = 0;

        for (Employee i : employees) {
            if (i.getDepartment() == department) {
                count ++;
                if (i.getSalary() > totalSalary) {
                    totalSalary = totalSalary + i.getSalary();
                }
            }
        }
        return totalSalary / count;
    }
    //(3)
    public static int getNumberOfEmployees(ArrayList<Employee> employees, String department) {
        int count = 0;
        for (Employee i : employees) {
            if(i.getDepartment() == department) {
                count ++;
            }
        }
        return count;
    }
    //(4)
    public static int getNumberOfEmployeeExceptIT(ArrayList<Employee> employees, String exceptDepartment) {
        int totalEmployee = 0;
        String department;
        for (Employee i : employees) {
            department = i.getDepartment();
            if(!"IT".equalsIgnoreCase(department) && !exceptDepartment.equalsIgnoreCase(department)) {
                totalEmployee++;
            }
        }
        return totalEmployee;
    }
    //(5)
//    public Map<String, ArrayList<Employee>> groupByJobDepartment(ArrayList<Employee> employeeList) {
//        Map<String, ArrayList<Employee>> resultMap = new HashMap<>();
//        for (int i = 0; i < employeeList.size(); i++) {
//            Employee employee = employeeList.get(i);
//            List<Employee> employeeSubList = resultMap.getOrDefault(employee.getDepartment(), new ArrayList<Employee>());
//            employeeSubList.add(employee);
//            resultMap.put(employee.getDepartment (), (ArrayList<Employee>) employeeSubList);
//        }
//        return resultMap;
//    }
    public static Map<String, List<Employee>> groupByJobDepartment(List<Employee> employeeList) {
        return employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
    //(7)
//    public static Date printEmployeesJoinedAfter2020 (List<Employee> employeeList) {
//        return employeeList.stream()
//                .filter(e -> e.joinDate.after(2020-01-01))
//                .collect(Collectors.toList());
//    }

    //(8)
    public static int calculateSumOfAllSalary(ArrayList<Employee> employeeList) {
        int sum = 0;
        for (Employee i : employeeList) {
            sum += i.getSalary();
        }
        return sum;
    }
    //(9)
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