package presentaion;

import java.util.List;

import core.Container;
import logic.Item;
import logic.ItemCatalog;
import logic.ItemCatalogImpl;

public class Main {
	public static void main(String[] args) throws Exception {
		Container con = new Container();
		ItemCatalog catalog = (ItemCatalog) con.getInstance(ItemCatalogImpl.class);
		List<Item> items = catalog.getAll();
		
		for(Item item : items) {
			System.out.println(item.getName());
		}
	}
}
