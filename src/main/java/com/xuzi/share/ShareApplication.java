package com.xuzi.share;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.LinkedList;

@SpringBootApplication
public class ShareApplication {

	public static void main(String[] args) {
		ArrayList<Integer> list1 = new ArrayList<>();
		list1.add(11);
		list1.add(12);
		list1.add(13);
		ArrayList<Integer> list = new ArrayList();
		list.add(1);
		list.addAll(list1);
//        list.add(2);
		System.out.println(list.get(2));
//        LinkedList<String> linkedList = new List<String>();
		SpringApplication.run(ShareApplication.class, args);
	}


}
