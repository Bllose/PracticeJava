# PracticeJava
## Converting String into Integer array
``` Java
Arrays.asList(INPUT_STRING.split(",")).stream().mapToInt(Integer::parseInt).toArray();
```

## 二维数组取出部分组成新数组
``` Java
int[] 一维数组 = Arrays.stream(二维数组).mapToInt(二维数组 -> 二维数组[0]).toArray();
```

# 自定义排序
## Arrays
``` Java
Arrays.sort(二维数组, Comparator.comparing((Integer[] arr) -> arr[0]).thenComparing((Integer[] arr) -> -arr[1]));
```
其中 `-arr[1]` 意味着针对数组第二个元素进行从大到小的排序。
