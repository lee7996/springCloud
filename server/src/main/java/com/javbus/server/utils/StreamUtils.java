package com.javbus.server.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamUtils {

//	Stream 就如同一个迭代器（Iterator），单向，不可往复，数据只能遍历一次，遍历过一次后即用尽了，就好比流水从面前流过，一去不复返。
//	有多种方式生成 Stream Source：
//
//	一、从 Collection 和数组
//
//	Collection.stream()
//	Collection.parallelStream()
//	Arrays.stream(T array) or Stream.of()
//
//	二、从 BufferedReader
//
//	java.io.BufferedReader.lines()
//
//		1、静态工厂
//		java.util.stream.IntStream.range()
//		java.nio.file.Files.walk()
//
//		2、自己构建
//		java.util.Spliterator
//
//		3、其它
//		Random.ints()
//		BitSet.stream()
//		Pattern.splitAsStream(java.lang.CharSequence)
//		JarFile.stream()
//
//	流(stream)的操作类型分为两种：
//
//	一、Intermediate：一个流可以后面跟随零个或多个 intermediate 操作。其目的主要是打开流，做出某种程度的数据映射/过滤，然后返回一个新的流，交给下一个操作使用。这类操作都是惰性化的（lazy），就是说，仅仅调用到这类方法，并没有真正开始流的遍历。
//
//	二、Terminal：一个流只能有一个 terminal 操作，当这个操作执行后，流就被使用“光”了，无法再被操作。所以这必定是流的最后一个操作。Terminal 操作的执行，才会真正开始流的遍历，并且会生成一个结果，或者一个 side effect。
//
//	在对于一个 Stream 进行多次转换操作 (Intermediate 操作)，每次都对 Stream 的每个元素进行转换，而且是执行多次，这样时间复杂度就是 N（转换次数）个 for 循环里把所有操作都做掉的总和吗？其实不是这样的，转换操作都是 lazy 的，多个转换操作只会在 Terminal 操作的时候融合起来，一次循环完成。我们可以这样简单的理解，Stream 里有个操作函数的集合，每次转换操作就是把转换函数放入这个集合中，在 Terminal 操作的时候循环 Stream 对应的集合，然后对每个元素执行所有的函数。
//
//	还有一种操作被称为 short-circuiting。用以指：
//	对于一个 intermediate 操作，如果它接受的是一个无限大（infinite/unbounded）的 Stream，但返回一个有限的新 Stream。
//	对于一个 terminal 操作，如果它接受的是一个无限大的 Stream，但能在有限的时间计算出结果。
	
	
	public static void main(String[] args) {
		/**
		    * 构造
		 */
		// 1. Individual values
		Stream stream = Stream.of("a", "b", "c");
		// 2. Arrays
		String [] strArray = new String[] {"a", "b", "c"};
		stream = Stream.of(strArray);
		stream = Arrays.stream(strArray);
		// 3. Collections
		List<String> list = Arrays.asList(strArray);
		stream = list.stream();
		
		//需要注意的是，对于基本数值型，目前有三种对应的包装类型 Stream：
		//IntStream、LongStream、DoubleStream。当然我们也可以用 Stream<Integer>、Stream<Long> >、Stream<Double>，但是 boxing 和 unboxing 会很耗时，所以特别为这三种基本数值型提供了对应的 Stream。
		
		/**
		    * 数值流构造
		 */
		IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
		IntStream.range(1, 3).forEach(System.out::println);
		IntStream.rangeClosed(1, 3).forEach(System.out::println);
		
		/**
		    *  流转换为其它数据结构
		 */
		//一个 Stream 只可以使用一次，上面的代码只是示例，为了简洁而重复使用了数次(正常开发只能使用一次)。
		// 1. Array
		//String[] strArray1 = stream.toArray(String[]::new);
		// 2. Collection
		//List<String> list1 = stream.collect(Collectors.toList());
		//List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
		//Set set1 = stream.collect(Collectors.toSet());
		//Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
		// 3. String
		String str = stream.collect(Collectors.joining()).toString();
		
//		流的操作
//		接下来，当把一个数据结构包装成 Stream 后，就要开始对里面的元素进行各类操作了。常见的操作可以归类如下。
//
//		Intermediate：
//
//		map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
//
//		Terminal：
//
//		forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
//
//		Short-circuiting：
//
//		anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
		
		//典型用法示例

		//map/flatMap

		//转换大写，wordList为单词集合List<String>类型
		List<String> wordList = new ArrayList<>();
		wordList.add("hello");
		wordList.add("world");
		List<String> output = wordList.stream().map(String::toUpperCase).collect(Collectors.toList());
		//求平方数

		//这段代码生成一个整数 list 的平方数 {1, 4, 9, 16}。
		List<Integer> nums = Arrays.asList(1, 2, 3, 4);
		List<Integer> squareNums = nums.stream().map(n -> n * n).collect(Collectors.toList());
		//从上面例子可以看出，map 生成的是个 1:1 映射，每个输入元素，都按照规则转换成为另外一个元素。还有一些场景，是一对多映射关系的，这时需要 flatMap。

		//将最底层元素抽出来放到一起，最终 output 的新 Stream 里面已经没有 List 了，都是直接的数字。
		Stream<List<Integer>> inputStream = Stream.of(
		 Arrays.asList(1),
		 Arrays.asList(2, 3),
		 Arrays.asList(4, 5, 6)
		 );
		Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());
		List<Integer> list1 =outputStream.collect(Collectors.toList());
		System.out.println(list1.toString());
		
		//filter

		//filter 对原始 Stream 进行某项测试，通过测试的元素被留下来生成一个新 Stream。

		//留下偶数，经过条件“被 2 整除”的 filter，剩下的数字为 {2, 4, 6}。
		Integer[] sixNums = {1, 2, 3, 4, 5, 6};
		Integer[] evens =Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);
		//把每行的单词用 flatMap 整理到新的 Stream，然后保留长度不为 0 的，就是整篇文章中的全部单词了。
		/**
		 * REGEXP为正则表达式，具体逻辑具体分析
		 */
		//List<String> output1 = reader.lines().flatMap(line -> Stream.of(line.split(REGEXP))).filter(word -> word.length() > 0).collect(Collectors.toList());
		
		//forEach

		//forEach 方法接收一个 Lambda 表达式，然后在 Stream 的每一个元素上执行该表达式。

		//打印所有男性姓名，roster为person集合类型为List<Pserson>
		// Java 8
		//roster.stream().filter(p -> p.getGender() == Person.Sex.MALE).forEach(p -> System.out.println(p.getName()));
		// Pre-Java 8
//		for (Person p : roster) {
//		    if (p.getGender() == Person.Sex.MALE) {
//		        System.out.println(p.getName());
//		    }
//		}
	}
	
	
}
