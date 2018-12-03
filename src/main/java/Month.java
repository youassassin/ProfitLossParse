import java.util.HashMap;
import java.util.List;
public class Month {
	private HashMap<String,Double> hm;
	private HashMap<String,Boolean> hmt;
	private String [] list;
	private String [] matcher;
	private String [] sMatcher;

	public Month(){
		hm = new HashMap<String, Double>();
		hmt = new HashMap<String, Boolean>();
		list = new String[]{
							"Administration",
							"Amortization",
							"Automobile Expense",
							"Bank Service Charges",
							"Business Meals",
							"Business Promotion",
							"Cash Discounts",
							"Contract Labor",
							"Customer Refunds",
							"Damages",
							"Depreciation",
							"Insurance Liability",
							"Interest",
							"Licenses and Permits",
							"Meals and Entertainment",
							"Office Supplies",
							"Postage and Delivery",
							"Preemployment Screening",
							"Professional Fees",
							"Property Taxes",
							"Rent Building",
							"Rent Equipment",
							"Repairs Building",
							"Repairs Landscaping",
							"Repairs Equipment",
							"Repairs Janitorial",
							"Shop Supplies",
							"Small Tools",
							"Telephone",
							"Towing",
							"Training",
							"Travel",
							"Uncategorized",
							"Uniforms",
							"Utilities",
							"Waste Oil Disposal Fee",
							"Advertising",
							"Royalty",
							"Wages",
							"Payroll"
		};
		matcher = new String[]{
							"admi",
							"amor",
							"auto",
							"meal",
							"prom",
							"cash d",
							"cont",
							"cust",
							"dama",
							"depr",
							"lice",
							"ente",
							"offi",
							"post",
							"pree",
							"profe",
							"prop",
							"shop",
							"smal",
							"tele",
							"towi",
							"trai",
							"trav",
							"unca",
							"unif",
							"wast",
							"adve",
							"roya",
							"wage"
		};
		sMatcher = new String[]{
							"bank", //total 3
							"insu", //total 11
							"inter", //total 12
							"buil",//\rent 20
							"equi",//\rent 21
							"buil",//\repa 22
							"land",//\repa 23
							"equi",//\repa 24
							"jani",//\repa 25
							"util",//total 34
							"lyon" //total - wage 39
		};
		for(String e : list){
			hm.put(e, 0.0);
			hmt.put(e, false);
		}
	}
	public void parseList(List<List<Object>> l){
	for (List row : values) {
		for(int i = 0; i < row.size(); i++)
			System.out.printf("%s\\", ((String)row.get(i)).trim());
			System.out.println();
	}
		if(l.size() > 1){

		}
	}
}
