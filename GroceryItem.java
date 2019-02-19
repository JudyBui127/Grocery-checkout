import java.util.*;
import java.text.*;
import java.awt.*;
import javax.swing.*;
public class GroceryItem {
	private String label;
	private double price;
	private int foodcode;
	public GroceryItem(String label,double price, int foodcode) {
		this.label=label;
		this.price=price;
		this.foodcode=foodcode;
	}
	public String getLabel() {return label;}
	public double getPrice() {return price;}
	public int getFoodCode() {return foodcode;}
	public void setLabel(String a) {label=a;}
	public void setPrice(double b) {price=b;}
	public void setFoodcode(int c) {foodcode=c;}
}
class Dairy extends GroceryItem{
	private double volume;
	public Dairy(String label, double price, int foodcode, double volume) {
		super(label, price, foodcode);
		this.volume=volume;
	}
	public double getPrice() {
		return super.getPrice()*volume;
	}
}
class Meat extends GroceryItem{
	private double weight;
	public Meat(String label, double price, int foodcode, double weight) {
		super(label, price, foodcode);
		this.weight=weight;
	}
	public double getPrice() {
		return super.getPrice()*weight;
	}
}
class GroceryCart {
	private int nextItem=0;
	private ArrayList<GroceryItem> arr=new ArrayList<GroceryItem>();
	public void fill() {
		arr.add(new GroceryItem("Banana",5.0,9007531));
		arr.add(new GroceryItem("Apple",3.5,9008642));
		arr.add(new GroceryItem("Grape",2.99,9001234));
		arr.add(new Dairy("Milk",4.15,7009731,0.5));
		arr.add(new Meat("Steak",11.99,5008642,0.5));
		arr.add(new GroceryItem("Lettuce",1.99,9009182));
		arr.add(new GroceryItem("Grape",2.99,9001234));
		arr.add(new Dairy("Goat Milk",7.99,7009354,0.5));
		arr.add(new Meat("Roast Beef",9.99,5007364,0.75));
		arr.add(new GroceryItem("Banana",5.0,9007531));
		arr.add(new GroceryItem("Cooked Rice",5.75,9003920));
		arr.add(new GroceryItem("Lettuce",1.99,9009182));
	}
	public GroceryItem topItem() {
		return arr.get(0);
	}
	public GroceryItem removeTopItem() {
		GroceryItem item = arr.get(0);
		arr.remove(0);
		return item ;
	}
	public void display(JTextArea area) {
		for(GroceryItem x:arr) {area.append(x.getLabel() + "\n");
		area.setFont(new Font("Arial Black", Font.BOLD, 11));
		}
	}
	public GroceryItem returnNext() {
		int temp=nextItem;
		++nextItem;
		return arr.get(temp);
	}
	public void resetNext() {
		nextItem=0;
	}
	public boolean scanCondition() {
		return (nextItem<arr.size());
	}
}
class CashRegister{
	private ArrayList<GroceryItem> arr2=new ArrayList<GroceryItem>();
	private double totalPrice=0;
	public void scanItem(GroceryItem item){
			arr2.add(item);
			totalPrice = totalPrice+ arr2.get(arr2.size()-1).getPrice();
	}	
	public void displayAll(JTextArea area2) {
		for(GroceryItem x:arr2) {area2.append(x.getLabel() +"   " +x.getPrice()+ "\n");}
		area2.append("-----------------\nTotal: "+ String.format("%.2f",totalPrice)+"\n");
		Date dateTime = new Date();
		SimpleDateFormat formatDate=new SimpleDateFormat("E MMM dd HH:mm:ss yyyy");
		area2.append(formatDate.format(dateTime));
		area2.setFont(new Font("Arial Black", Font.BOLD, 11));
	}
	public void clear() {
		arr2.clear();
		totalPrice=0;
	}
}
class FoodType implements Comparable<FoodType>{
	private String label;
	private int foodCode, measure, calories, sugar, fat, carbs;
	public FoodType(String label, int foodCode, int measure, int calories, int sugar, int fat, int carbs) {
		this.label=label;
		this.foodCode=foodCode;
		this.measure=measure;
		this.calories=calories;
		this.sugar=sugar;
		this.fat=fat;
		this.carbs=carbs;
	}
	public String getLabel() {return label;	}
	public void measureIncrement() {++measure;}
	public int getMea() {return measure;}
	public int getCalories() {return calories*measure;	}
	public int getSugar() {return sugar*measure;}
	public int getFat() {return fat*measure;}
	public int getCarbs() {return carbs*measure;}
	public int getFoodCode() {return foodCode;}
	public int compareTo(FoodType other) {
		if(this.getCalories()>other.getCalories()) return 1;
		if(this.getCalories()<other.getCalories()) return -1;
		return 0;
	}
	public String toString() {
		return getLabel() +": Cals "+getCalories()+" Sugar "+getSugar()+" Fat "+getFat()+" Carbs "+getCarbs(); 
	}
}
class NutritionChart {
	ArrayList<FoodType> arrFood = new ArrayList<FoodType>(); 
	public NutritionChart() {
		arrFood.add(new FoodType("Banana",9007531,1,130,20,0,30));
		arrFood.add(new FoodType("Apple",9008642,1,100,30,0,40));
		arrFood.add(new FoodType("Grape",9001234,1,90,40,0,20));
		arrFood.add(new FoodType("Lettuce",9009182,1,60,0,0,17));
		arrFood.add(new FoodType("Cooked Rice",9003920,1,250,60,78,100));
		arrFood.add(new FoodType("Milk",7009731,1,100,20,10,5));
		arrFood.add(new FoodType("Goat Milk",7009354,1,130,20,20,15));
		arrFood.add(new FoodType("Pork Ribs",5008642,1,150,0,30,14));
		arrFood.add(new FoodType("Roasted Beef",5007364,1,120,0,18,9));
	}
	public FoodType getFoodType(int foodCode) {
		 for(FoodType i:arrFood) {
			 if(i.getFoodCode()==foodCode) {
				 FoodType copyOfi= new FoodType(i.getLabel(),i.getFoodCode(),i.getMea(),i.getCalories(),i.getSugar(),i.getFat(),i.getCarbs());
				 return copyOfi;
			 }
		 }
		 return null;
	}
}
class NutritionScanner{
	NutritionChart NuChaObject=new NutritionChart();
	ArrayList<FoodType> items=new ArrayList<FoodType>();
	public void scanFoodCode(int foodCode) {
		if(items.size()<1) items.add(NuChaObject.getFoodType(foodCode));
		else { int count=0;
				for(int i=0; i<items.size();i++) {
					if(items.get(i).getFoodCode()==foodCode) { 
						items.get(i).measureIncrement();	
						count++;}
				}
				if(count==0) items.add(NuChaObject.getFoodType(foodCode));
		}
	}
	public void displayAll(JTextArea displayArea) {
		for(FoodType m:items) { displayArea.append(m.toString()+"\n");
		displayArea.setFont(new Font("Arial Black", Font.BOLD, 11));}
	}
	public void clear() {
		items.clear();
	}
	public void sortCal() {
		Collections.sort(items, new FoodTypeCalsComparator());
	}
	public void sortSugar() {
		Collections.sort(items, new SugarComparator());
	}
	public void sortFat() {
		Collections.sort(items, new FatComparator());
	}
	public void sortCarbs() {
		Collections.sort(items, new CarbsComparator());
	}
}
