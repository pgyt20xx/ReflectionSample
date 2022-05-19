package logic;

import java.util.List;

import annotation.Resource;

public class ItemCatalogImpl implements ItemCatalog {
	
	@Resource
	private ItemDao dao;
	
	public List<Item> getAll() {
		return dao.findAll();
	}
	
}
