package com.zoe.study.java.effective.chapter01.builder;

public class NutritionFacts_tc {
	private  int servingSize;
	private  int servings;
	private  int calories;
	private  int fat;
	private  int sodium;
	private  int carbohydrate;
	
	public NutritionFacts_tc(int servingSize, int servings){
		this(servingSize,servings,0);
	}
	
	public NutritionFacts_tc(int servingSize, int servings,int calories){
		this(servingSize,servings,calories,0);
	}
	
	public NutritionFacts_tc(int servingSize, int servings,int calories,int fat){
		this(servingSize,servings,calories,fat,0);
	}
	
	public NutritionFacts_tc(int servingSize, int servings,int calories,int fat,int sodium){
		this(servingSize,servings,calories,fat,sodium,0);
	}
	public NutritionFacts_tc(int servingSize, int servings,int calories,int fat,int sodium,int carbohydrate){
		this.servingSize = servingSize;
		this.servings = servings;
		this.calories = calories;
		this.fat = fat;
		this.sodium = sodium;
		this.carbohydrate = carbohydrate;
	}
	
	public static void main(String [] args){
		NutritionFacts_tc nt = new NutritionFacts_tc(240,8,100,0,35,27);
		System.out.println(nt);
	}

}
