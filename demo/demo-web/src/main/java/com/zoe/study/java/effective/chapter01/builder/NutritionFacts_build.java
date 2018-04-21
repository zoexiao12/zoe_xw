package com.zoe.study.java.effective.chapter01.builder;

public class NutritionFacts_build {
	private  int servingSize;
	private  int servings;
	private  int calories;
	private  int fat;
	private  int sodium;
	private  int carbohydrate;
	
	public static class Builder{
		private  int servingSize;
		private  int servings;
		private  int calories;
		private  int fat;
		private  int sodium;
		private  int carbohydrate;
		
		public Builder(int servingSize, int servings) {
			this.servingSize = servingSize;
			this.servings = servings;
		}
		public Builder  calories(int val) {
			calories = val;
			return this;
		}
		public Builder fat(int val) {
			fat = val;
			return this;
		}
		public Builder sodium(int val) {
			sodium = val;
			return this;
		}
		public Builder carbohydrate(int val) {
			carbohydrate = val;
			return this;
		}
		public NutritionFacts_build build(){
			return new NutritionFacts_build(this);
		}
		
	}
	
	public NutritionFacts_build(Builder builder) {
		this.servingSize = builder.servingSize;
		this.servings = builder.servings;
		this.calories = builder.calories;
		this.fat = builder.fat;
		this.sodium = builder.sodium;
		this.carbohydrate = builder.carbohydrate;
	}

	public static void main(String[] args) {
		NutritionFacts_build nt = new Builder(240, 8).calories(100).build();

	}

}
