package com.zoe.study.java.effective.chapter01.builder;

public class NutritionFacts_jb {
	private  int servingSize;
	private  int servings;
	private  int calories;
	private  int fat;
	private  int sodium;
	private  int carbohydrate;
	public int getServingSize() {
		return servingSize;
	}
	public void setServingSize(int servingSize) {
		this.servingSize = servingSize;
	}
	public int getServings() {
		return servings;
	}
	public void setServings(int servings) {
		this.servings = servings;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public int getFat() {
		return fat;
	}
	public void setFat(int fat) {
		this.fat = fat;
	}
	public int getSodium() {
		return sodium;
	}
	public void setSodium(int sodium) {
		this.sodium = sodium;
	}
	public int getCarbohydrate() {
		return carbohydrate;
	}
	public void setCarbohydrate(int carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	
	public static void main(String [] args){
		NutritionFacts_jb nt = new NutritionFacts_jb();
		nt.setServingSize(240);
		nt.setServings(8);
		nt.setCalories(100);
		nt.setSodium(35);
		nt.setCarbohydrate(27);
	}
}
