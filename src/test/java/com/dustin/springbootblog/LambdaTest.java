package com.dustin.springbootblog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

public class LambdaTest {

	@Test
	public void test1() {
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				System.out.println("t");
			}
		};
		r1.run();
		System.out.println("***********************");
		Runnable r2 = () -> System.out.println("t");
		r2.run();
	}

	@Test
	public void t2() {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				System.out.println("t2-r");
			}
		};
		r.run();
		Runnable r2 = () -> System.out.println("t2-r");
		r2.run();
	}

	@Test
	public void t3() {
		Comparator<Integer> com = new Comparator<Integer>() {
			@Override
			public int compare(Integer t1, Integer t2) {
				return Integer.compare(t1, t2);
			}
		};
		int compare = com.compare(3, 1);
		System.out.println(compare);

		Comparator<Integer> com1 = (t1, t2) -> {
			return Integer.compare(t1, t2);
		};
		int compare1 = com1.compare(3, 1);
		System.out.println(compare1);

		Comparator<Integer> com2 = (t1, t2) -> Integer.compare(t1, t2);
		int compare2 = com2.compare(3, 1);
		System.out.println(compare2);
	}
	
	@Test
	public void t4() {
		Comparator<Integer> com = (n1, n2) -> Integer.compare(n1, n2);
		int compare = com.compare(1, 2);
		System.out.println(compare);
	}
	
	@Test
	public void test2() {
//		List<String> list = Arrays.asList("A", "AB", "B", "BC", "ABC", "AC");
//		List<String> filterStrs = filterString(list, new Predicate<String>() {
//			@Override
//			public boolean test(String s) {
//				return s.contains("B");
//			}
//		});
//		System.out.println(filterStrs);

//		List<String> filterStrs1 = filterString(list, s -> s.contains("B"));
//		System.out.println(filterStrs1);
		
//		List<String> list = Arrays.asList("A", "C", "B", "D");
		
		String[] sArray = {"A", "C", "B", "D"};
		
		for(String t:sArray)
			System.out.println(t);
		Arrays.sort(sArray, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		System.out.println("*********************");
		for(String t:sArray)
			System.out.println(t);
		
//		list.forEach(new Consumer<String>() {
//			@Override
//			public void accept(String t) {
//				System.out.println(t);
//			}
//		});
//		System.out.println("**************************");
//		list.forEach(t -> System.out.println(t));
		
	}

    public List<String> filterString(List<String> list, Predicate<String> pre){
        ArrayList<String> filterList = new ArrayList<>();
        for(String s : list){
            if(pre.test(s)){
                filterList.add(s);
            }
        }
        return filterList;
    }

    @Test
	public void test3() {
		List<String> list = Arrays.asList("A", "AB", "B", "BC", "ABC", "AC");
		List<String> filterStrs = filterString(list, new Predicate<String>() {
			@Override
			public boolean test(String s) {
				return s.contains("B");
			}
		});
		System.out.println(filterStrs);

		List<String> filterStrs1 = filterString(list, s -> s.contains("B"));
		System.out.println(filterStrs1);
	}
    
    @Test
	public void test5() {
    	Consumer<String> con = new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		};
		con.accept("t");
    	
    	System.out.println("*****************");
    	
    	Consumer<String> con1 = str -> System.out.println(str);
    	con1.accept("tt");
    }
}
