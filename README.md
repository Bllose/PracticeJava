# IMPORTS
``` Java
import java.util.Math;

import java.util.Queue;
import java.util.LinkedList;

import java.util.Stack;

import java.util.stream.Collectors;
import java.util.Comparator;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
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

``` Java
Stack<String> s = new Starck<>();
s.push(STRING);
s.pop();
s.peek();

s.getLast();
s.getFirst();
s.removeLast();
s.removeFirst(); // 实际上 LinkedList 下 pop 方法调用的就是 removeFirst 方法。
```

``` Java
Arrays.stream(int[] ARRAY).sum(); // 直接获取求和结果
```

## substring
``` Java
String exp = "(sub (mul 2 4) (div 9 3))";
exp.charAt(0); // '('
exp.substring(1, 4); // "sub" 4-1=3:子字符串长度为3; 1:直接作为index，指定起始字符所在坐标。 4:作为结束字符，但是该字符不会存在子字符串中。
```

## Character
``` Java
char[] cs = STRING.toCharArray();
for(char c: cs) {
  if(Character.isLetter(c)) {
    // 针对字符的处理逻辑
  } else if (Character.isDigit(c)) {
    // 针对数字的处理逻辑
  }
}
```

# ARRAY && LIST
## Converting String into Integer(char) Array
``` Java
int[] result = Arrays.asList(STRING.split("SYMBOL")).stream().mapToInt(Integer::parseInt).toArray();
int[] result = Arrays.stream(STRING.split("SYMBOL")).mapToInt(Integer::parseInt).toArray();
int[] result = STRING.chars().toArray(); // 没有分隔符，直接分割每一个字符
char[] result = STRING.toCharArray();    // 没有分隔符，直接分割每一个字符
```

``` Java
int[] result = Arrays.stream(STRING.split("SYMBOL")).mapToInt(Integer::parseInt).toArray();
```

### 以此类推
``` Java
Arrays.stream(STRING.split("SYMBOL")).mapToLong(Long::parseLong).toArray();
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
List<Integer> NEW_LIST = new ArrayList<>(Collections.nCopies(SIZE, INIT_VALUE));
List<String> NEW_LIST = new ArrayList<>(Collections.nCopies(SIZE, STRING_VALUE));
```
``` Java
int[]  res = new int[LENGTH];
Arrays.fill(res, INITIAL_NUM);
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

## Map

### 针对Key、value有特定顺序场景下的解决办法
``` Java
Map<Integer, Integer> recorder = new HashMap<>();
List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(recorder.entrySet());

// 方法一
Collection.sort(entries, (o1, o2) -> {
  if(o1.getValue() == o2.getValue()) {
    return o1.getKey() - o2.getValue(); // 顺序
  }
  return o2.getValue() - o1.getValue(); // 逆序
});

// 方法二
entries.sort(Comparator
                  .<Map.Entry<Integer, Integer>>comparingInt(Map.Entry::getValue)
                  .reversed()
                  .thenComparing(Map.Entry::getKey));
```

``` Java
/*
利用Java对象的特性达到目的。
TreeMap由于其红白树构建平衡二叉树的特性，使得查找效率提升。 同时它元素唯一、构建时可以指明顺序或逆序。
LinkedHashSet严格按照插入顺序
*/
TreeMap<Integer, LinkedHashSet<Integer>> map = new TreeMap<>(Collections.reverseOrder());
```
