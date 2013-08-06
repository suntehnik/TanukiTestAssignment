package org.dvaletin.apps.tanukitestassignment.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class CategoryContent {
	/*
	 * See more at
	 * https://github.com/500px/api-documentation/blob/master/basics/formats_and_terms.md#categories 
	 * 0 Uncategorized 
	 * 10 Abstract 
	 * 11 Animals 
	 * 5 Black and White 
	 * 1 Celebrities 
	 * 9 City and Architecture 
	 * 15 Commercial 
	 * 16 Concert 
	 * 20 Family 
	 * 14 Fashion 
	 * 2 Film 
	 * 24 Fine Art 
	 * 23 Food 
	 * 3 Journalism 
	 * 8 Landscapes 
	 * 12 Macro 
	 * 18 Nature 
	 * 4 Nude 
	 * 7 People 
	 * 19 Performing Arts 
	 * 17 Sport
	 * 6 Still Life 
	 * 21 Street 
	 * 26 Transportation 
	 * 13 Travel 
	 * 22 Underwater 
	 * 27 Urban Exploration 
	 * 25 Wedding
	 */

	

	/* HashTable is MT-safe implementation of a <Key, Value> Map
	 * we can safely add or delete items from table using static instance
	 */
	public static Hashtable<Integer, String> categories = null;
	
	/* Instance of the CategoryContent class 
	 * 
	 */
	private static CategoryContent self = null;

	
	/**
	 * Initialize the class static content, return static instance of the class
	 * @return shared instance of CategoryContent class
	 */
	public static CategoryContent init() {
		if(self != null) {
			return self;
		}
		self = new CategoryContent();
		categories = new Hashtable<Integer, String>();
		categories.put(0, "Uncategorized");
		categories.put(10, "Abstract");
		categories.put(11, "Animals");
		categories.put(5, "Black and White");
		categories.put(1, "Celebrities");
		categories.put(9, "City and Architecture");
		categories.put(15, "Commercial");
		categories.put(16, "Concert");
		categories.put(20, "Family");
		categories.put(14, "Fashion");
		categories.put(2, "Film");
		categories.put(24, "Fine Art");
		categories.put(23, "Food");
		categories.put(3, "Journalism");
		categories.put(8, "Landscapes");
		categories.put(12, "Macro");
		categories.put(18, "Nature");
		categories.put(4, "Nude");
		categories.put(7, "People");
		categories.put(19, "Performing Arts");
		categories.put(17, "Sport");
		categories.put(6, "Still Life");
		categories.put(21, "Street");
		categories.put(26, "Transportation");
		categories.put(13, "Travel");
		categories.put(22, "Underwater");
		categories.put(27, "Urban Exploration");
		categories.put(25, "Wedding");
		return self;
	}

	/**
	 * 
	 * @return all categories titles as a List<String>
	 */
	public List<String> allItems() {
		return new ArrayList<String> (categories.values());
	}
	
	
	/**
	 * 
	 * @author valetin Class Category explains the 500px.com picture category
	 * 
	 */
	public class Category {
		public int id;
		public String title;

		/**
		 * Default constructor:
		 * 
		 * @param id
		 *            - id of the category
		 * @param title
		 *            - title of the category
		 */
		public Category(int id, String title) {
			this.id = id;
			this.title = title;
		}
	}
}
