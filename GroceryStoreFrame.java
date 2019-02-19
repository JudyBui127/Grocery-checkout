import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.*;
public class GroceryStoreFrame extends JFrame {
	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 700;
	private static final int AREA_ROWS = 20;
	private static final int AREA_COLUMNS = 23;
	private JLabel cart, register, nutrition;
	private JButton refill, scan, checkout, scanNutrition,clear,cal,carb,fat,sugar;
	private JTextArea cartArea, checkoutArea, nutritionArea;
	private GroceryCart cartObject = new GroceryCart();
	private CashRegister cashRegisterObject = new CashRegister();
public GroceryStoreFrame() {
	cartArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
	checkoutArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
	nutritionArea = new JTextArea(12,68);
    cartArea.setEditable(false);
    checkoutArea.setEditable(false);
    nutritionArea.setEditable(false);
    createGroceryCartPanel();
    createScanItemsPanel();
    createCashRegisterPanel();
    createNutritionScannerPanel();
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
}
public void createGroceryCartPanel() {
	
	JPanel cartPanel = new JPanel();
	cartPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
	cartPanel.setPreferredSize(new Dimension(270, 430));
	cart = new JLabel("GROCERY CART");
	cartPanel.add(cart, BorderLayout.WEST);
	refill= new JButton("REFILL");
	cartPanel.add(refill, BorderLayout.EAST);
	class RefillListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(cartArea.getText().isEmpty()) {
			cartObject.fill();
			cartObject.display(cartArea);}
		}
	}
		ActionListener listener = new RefillListener();
	    refill.addActionListener(listener);
	JScrollPane cartScroll = new JScrollPane(cartArea); 
	cartPanel.add(cartScroll, BorderLayout.SOUTH);
	add(cartPanel, BorderLayout.WEST);
}
public void createScanItemsPanel() {
	JPanel scanPanel = new JPanel();
	scanPanel.setPreferredSize(new Dimension(260, 430));
	scan = new JButton("SCAN ITEMS");
	class ScanListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(!cartArea.getText().isEmpty()) {
			GroceryItem removedItem = cartObject.removeTopItem();
			cartArea.setText("");
			cartObject.display(cartArea);
			cashRegisterObject.scanItem(removedItem);
			checkoutArea.setText("");
			cashRegisterObject.displayAll(checkoutArea);}
		}
	}
		ActionListener listener1 = new ScanListener();
	    scan.addActionListener(listener1);
	scanPanel.add(scan, BorderLayout.CENTER);
	add(scanPanel, BorderLayout.CENTER);
	
}
public void createCashRegisterPanel(){
	JPanel cashRegisterPanel = new JPanel();
	cashRegisterPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
	cashRegisterPanel.setPreferredSize(new Dimension(270, 430));
	register = new JLabel("CASH REGISTER");
	cashRegisterPanel.add(register, BorderLayout.WEST);
	checkout= new JButton("CHECKOUT");
	class CheckoutListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			checkoutArea.setText("");
			cashRegisterObject.clear();
		}
	}
		ActionListener listener2 = new CheckoutListener();
	    checkout.addActionListener(listener2);
	cashRegisterPanel.add(checkout, BorderLayout.EAST);
	JScrollPane cashRegisterScroll = new JScrollPane(checkoutArea); 
	cashRegisterPanel.add(cashRegisterScroll, BorderLayout.SOUTH);
	add(cashRegisterPanel, BorderLayout.EAST);
}
public void createNutritionScannerPanel() {
	NutritionScanner NuObject = new NutritionScanner();
	nutrition = new JLabel("CART NUTRITION INFORMATION");
	scanNutrition = new JButton("SCAN FOOD ITEMS");
	class ScanListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(cartObject.scanCondition()) {
			NuObject.scanFoodCode(cartObject.returnNext().getFoodCode());
			nutritionArea.setText("");
			NuObject.displayAll(nutritionArea);
			}
		}
	}
		ActionListener listener3 = new ScanListener();
	    scanNutrition.addActionListener(listener3);
	clear = new JButton("CLEAR");
	class ClearListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			nutritionArea.setText("");
			cartObject.resetNext();
			NuObject.clear();
		}
	}
		ActionListener listener4 = new ClearListener();
	    clear.addActionListener(listener4);
	cal = new JButton("Cals");
	carb = new JButton("Carbs");
	fat = new JButton("Fat");
	sugar = new JButton("Sugar");
	class CalListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			NuObject.sortCal();
			nutritionArea.setText("");
			NuObject.displayAll(nutritionArea);
		}
	}
	ActionListener listener5 = new CalListener();
    cal.addActionListener(listener5);
    
    class SugarListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			NuObject.sortSugar();
			nutritionArea.setText("");
			NuObject.displayAll(nutritionArea);
		}
	}
	ActionListener listener6 = new SugarListener();
    sugar.addActionListener(listener6);
   
    
    class FatListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			NuObject.sortFat();
			nutritionArea.setText("");
			NuObject.displayAll(nutritionArea);
		}
	}
	ActionListener listener7 = new FatListener();
    fat.addActionListener(listener7);
    
    class CarbsListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			NuObject.sortCarbs();
			nutritionArea.setText("");
			NuObject.displayAll(nutritionArea);
		}
	}
	ActionListener listener8 = new CarbsListener();
    carb.addActionListener(listener8);
	JPanel nutritionPanel = new JPanel();
	nutritionPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
	nutritionPanel.setPreferredSize(new Dimension(800, 270));
	nutritionPanel.add(nutrition, BorderLayout.NORTH);
	nutritionPanel.add(scanNutrition, BorderLayout.NORTH);
	nutritionPanel.add(clear, BorderLayout.NORTH);
	JScrollPane nutritionScroll = new JScrollPane(nutritionArea); 
	nutritionPanel.add(nutritionScroll, BorderLayout.CENTER);
	nutritionPanel.add(cal, BorderLayout.SOUTH);
	nutritionPanel.add(carb, BorderLayout.SOUTH);
	nutritionPanel.add(fat, BorderLayout.SOUTH);
	nutritionPanel.add(sugar, BorderLayout.SOUTH);
	add(nutritionPanel, BorderLayout.SOUTH);
	
}
}
