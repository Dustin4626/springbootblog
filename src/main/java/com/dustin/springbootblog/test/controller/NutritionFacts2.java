package com.dustin.springbootblog.test.controller;

public class NutritionFacts2 {
    private  int servingSize;
    private  int servings;
    private  int calories;
    private  int fat;
    private  int sodium;
    private  int carbohydrate;
    
    
	private NutritionFacts2(Builder builder) {
		this.servingSize = builder.servingSize;
		this.servings = builder.servings;
		this.calories = builder.calories;
		this.fat = builder.fat;
		this.sodium = builder.sodium;
		this.carbohydrate = builder.carbohydrate;
	}
	
	public static Builder builderFrom(NutritionFacts2 nutritionFacts2) {
		return new Builder(nutritionFacts2);
	}
	
	public static final class Builder {
		private int servingSize;
		private int servings;
		private int calories;
		private int fat;
		private int sodium;
		private int carbohydrate;

		public Builder(int servingSize, int servings) {
			this.servingSize = servingSize;
			this.servings = servings;
		}

		private Builder(NutritionFacts2 nutritionFacts2) {
			this.servingSize = nutritionFacts2.servingSize;
			this.servings = nutritionFacts2.servings;
			this.calories = nutritionFacts2.calories;
			this.fat = nutritionFacts2.fat;
			this.sodium = nutritionFacts2.sodium;
			this.carbohydrate = nutritionFacts2.carbohydrate;
		}

		public Builder withCalories(int calories) {
			this.calories = calories;
			return this;
		}

		public Builder withFat(int fat) {
			this.fat = fat;
			return this;
		}

		public Builder withSodium(int sodium) {
			this.sodium = sodium;
			return this;
		}

		public Builder withCarbohydrate(int carbohydrate) {
			this.carbohydrate = carbohydrate;
			return this;
		}

		public NutritionFacts2 build() {
			return new NutritionFacts2(this);
		}
	}
    
    
}