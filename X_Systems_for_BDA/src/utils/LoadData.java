package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class LoadData {

	private String FileName;

	private int lenFile;

	private Hashtable<Integer, String> CustomerName = new Hashtable<>();
	private Hashtable<Integer, Integer> CustomerAge = new Hashtable<>();
	private Hashtable<Integer, Integer> ProductId = new Hashtable<>();
	private Hashtable<Integer, Integer> CustomerId = new Hashtable<>();
	private Hashtable<Integer, String> ProductName = new Hashtable<>();
	private Hashtable<Integer, Float> ProductPrice = new Hashtable<>();
	private Hashtable<Integer, Integer> PurchaseId = new Hashtable<>();

	private Hashtable<String, Hashtable<Integer, ?>> Columns = new Hashtable<>();

	private String[] columnsnames = { "CustomerName", "CustomerAge", "CustomerId", "ProductId", "ProductName",
			"ProductPrice", "PurchaseId" };

	public LoadData(String fileName, int lenFile) {
		this.FileName = fileName;
		this.lenFile = lenFile;

		Columns.put("CustomerName", CustomerName);
		Columns.put("CustomerAge", CustomerAge);
		Columns.put("ProductId", ProductId);
		Columns.put("CustomerId", CustomerId);
		Columns.put("ProductName", ProductName);
		Columns.put("ProductPrice", ProductPrice);
		Columns.put("PurchaseId", PurchaseId);

	}

	public void read() {

		try (BufferedReader br = new BufferedReader(new FileReader(this.FileName))) {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {

				if (i >= 1) {
					String[] values = line.split(",");
					int id = Integer.parseInt(values[0]);
					int age = Integer.parseInt(values[2]);
					int pdid = Integer.parseInt(values[3]);
					float pdprice = Float.parseFloat(values[5]);
					int purch = Integer.parseInt(values[6]);

					CustomerId.put(i, id);
					CustomerName.put(i, values[1]);
					CustomerAge.put(i, age);
					ProductId.put(i, pdid);
					ProductName.put(i, values[4]);
					ProductPrice.put(i, pdprice);
					PurchaseId.put(i, purch);
				}
				i++;
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public String[] GetColumnsName() {
		return this.columnsnames;
	}

	public float[] readPrices() {
		float[] customerPrice = new float[lenFile];
		try (BufferedReader br = new BufferedReader(new FileReader(this.FileName))) {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (i >= 1) {
					String[] values = line.split(",");
					customerPrice[i - 1] = Float.parseFloat(values[1]);
				}
				i++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return customerPrice;

	}

	public Hashtable<String, Hashtable<Integer, ?>> GetColumns() {
		return this.Columns;
	}

}
