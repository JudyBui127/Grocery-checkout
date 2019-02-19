import java.util.ArrayList;
import java.util.Comparator;;
public class FoodTypeCalsComparator implements Comparator {
	public int compare(Object o1,Object o2) {
		int cals1=((FoodType) o1).getCalories();
		int cals2=((FoodType) o2).getCalories();
		if(cals1>cals2) return -1;
		if(cals1<cals2) return 1;
		return 0;
	}
}
class SugarComparator implements Comparator {
	public int compare(Object o1,Object o2) {
		int sug1=((FoodType) o1).getSugar();
		int sug2=((FoodType) o2).getSugar();
		if(sug1>sug2) return -1;
		if(sug1<sug2) return 1;
		return 0;
	}
}
class FatComparator implements Comparator {
	public int compare(Object o1,Object o2) {
		int fat1=((FoodType) o1).getFat();
		int fat2=((FoodType) o2).getFat();
		if(fat1>fat2) return -1;
		if(fat1<fat2) return 1;
		return 0;
	}
}
class CarbsComparator implements Comparator {
	public int compare(Object o1,Object o2) {
		int carb1=((FoodType) o1).getCarbs();
		int carb2=((FoodType) o2).getCarbs();
		if(carb1>carb2) return -1;
		if(carb1>carb2) return 1;
		return 0;
	}
}
