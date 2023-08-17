# IMPORTS
``` Java
import java.util.Math;

import java.util.Queue;
import java.util.LinkedList;

import java.util.Stack;
```

# FUNCTIONS

## java.util.*
``` Java
int theSqrt = (int) Math.sqrt(NUM); // 对一个数开平方。 square root 平方根
```

``` Java
Queue<int[]> q = new LinkedList<>();
q.offer(new int[]{x,y}); // 添加元素
```

# ARRAY && LIST
## Converting String into Integer(char) Array
``` Java
int[] result = Arrays.asList(STRING.split("SYMBOL")).stream().mapToInt(Integer::parseInt).toArray();
int[] result = STRING.chars().toArray(); // 没有分隔符，直接分割每一个字符
char[] result = STRING.toCharArray();    // 没有分隔符，直接分割每一个字符
```

``` Java
int[] result = Arrays.stream(STRING.split("SYMBOL")).mapToInt(Integer::parseInt).toArray();
```

## Converting String into String Array
``` Java
String[] result = Arrays.stream(STRING.split("SYMBOL")).toArray(String[]::new);
String[] result = STRING.split("SYMBOL");
```

## Converting String into Integer List
``` Java
List<Integer> result = Arrays.stream(STRING.split("SYMBOL").map(Integer::parseInt).collect(Collectors.toList());
```


## 二维数组取出部分组成新数组
``` Java
int[] 一维数组 = Arrays.stream(二维数组).mapToInt(二维数组 -> 二维数组[0]).toArray();
```

## 初始化指定值的列表
``` Java
new ArrayList<>(Collections.nCopies(SIZE, INIT_VALUE));
```

# Collectors
Implementations of Collector that implement various useful reduction operations, such as accumulating elements into collections, summarizing elements according to various criteria, etc.  
The following are examples of using the predefined collectors to perform common mutable reduction tasks:
``` Java
// Accumulate names into a List
List<String> list = people.stream().map(Person::getName).collect(Collectors.toList());

// Accumulate names into a TreeSet
Set<String> set = people.stream().map(Person::getName).collect(Collectors.toCollection(TreeSet::new));

// Convert elements to strings and concatenate them, separated by commas
String joined = things.stream()
                      .map(Object::toString)
                      .collect(Collectors.joining(", "));

// Compute sum of salaries of employee
int total = employees.stream()
                      .collect(Collectors.summingInt(Employee::getSalary)));

// Group employees by department
Map<Department, List<Employee>> byDept = employees.stream()
                                                  .collect(Collectors.groupingBy(Employee::getDepartment));

// Compute sum of salaries by department
Map<Department, Integer> totalByDept = employees.stream()
                                                .collect(Collectors.groupingBy(Employee::getDepartment,
                                                         Collectors.summingInt(Employee::getSalary)));

// Partition students into passing and failing
Map<Boolean, List<Student>> passingFailing = students.stream()
                                                     .collect(Collectors.partitioningBy(s -> s.getGrade() >= PASS_THRESHOLD));
```

# 自定义排序
## Arrays
``` Java
Arrays.sort(二维数组, Comparator.comparing((Integer[] arr) -> arr[0]).thenComparing((Integer[] arr) -> -arr[1]));
```
其中 `-arr[1]` 意味着针对数组第二个元素进行从大到小的排序。
