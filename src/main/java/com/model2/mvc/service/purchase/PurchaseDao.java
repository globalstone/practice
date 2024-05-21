package com.model2.mvc.service.purchase;

import com.model2.mvc.service.domain.Purchase;

import java.util.List;
import java.util.Map;

public interface PurchaseDao {
	
	public void insertPurchase(Purchase purchase) throws Exception;
	
	public Purchase findPurchase(int pordNo) throws Exception;
	
	public void updatePurchase(Purchase purchase) throws Exception;
	
	public void updateTranCode(Purchase purchase) throws Exception;

	public List<Purchase> getPurchaseList(Map<String,Object> parmas) throws Exception;

	public int getTotalCount(Map<String,Object> params) throws Exception;

}
