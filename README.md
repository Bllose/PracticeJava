# PracticeJava
## Convert String into Integer array
``` Java
Arrays.asList(INPUT_STRING.split(",")).stream().mapToInt(Integer::parseInt).toArray();
```

## 二维数组取出部分组成新数组
``` Java
int[] 一维数组 = Arrays.stream(二维数组).mapToInt(二维数组 -> 二维数组[0]).toArray();
```
