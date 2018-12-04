import java.util.HashMap;
import java.util.List;
public class Month extends Categories {
	private HashMap<String, Double> hm;
	private HashMap<String, Boolean> hmt;
	private boolean rentSwitch;
	public static boolean debug = true;

	public Month(){
		super();
		hm = new HashMap<String, Double>();
		hmt = new HashMap<String, Boolean>();
		for(String e : list){
			hm.put(e, 0.0);
			hmt.put(e, false);
		}
		rentSwitch = false;
	}
	public Month(String [] i){
		super();
		hm = new HashMap<String, Double>();
		hmt = new HashMap<String, Boolean>();
		for(String e : list){
			hm.put(e, 0.0);
			hmt.put(e, false);
			for(String s : i)
				if(s.equals(e)){
					hmt.replace(e, true); break;}
		}
		rentSwitch = false;
	}

	private String removeSpaceAfterMinus(String out){
		for(int j = 0; j < out.length(); j++){
			if(j < out.length())
				if(out.charAt(j) == '-' && out.charAt(j+1) == ' ')
					return (new StringBuilder(out).deleteCharAt(j+1)).toString();
		}
		return out;
	}

	private String trim(String out){
		return out.replace(",", "").replace("$", "").trim();
	}

	private void print(){
		for(String e : list)
			if(hmt.get(e)){
				if(e.equals("Advertising"))
					System.out.println();
				if(debug)
					System.out.print(e+", ");
				boolean x = e.equals("Amortization") ||
										e.equals("Interest") ||
										e.equals("Depreciation");
				System.out.println(!x ? hm.get(e) : "");
			}
		System.out.println();
		double round = Math.round((hm.get("Amortization") +
															 hm.get("Interest") +
															 hm.get("Depreciation"))*100)/100.0;
		System.out.println("AID: " + round);
	}

	private int findMatch(String out){
		out = out.toLowerCase();
		for(int i = 0; i < list.length; i++){
			switch(i){
				case 3: case 11: case 12: case 34: case 39:
					if(out.contains(matcher[i]) && out.contains("tota"))
						return i;
					break;
				case 20: case 21:
					if(out.contains(matcher[i]) && rentSwitch)
						return i;
					break;
				case 22:
					if(out.contains(matcher[i]) && !out.contains("tota"))
						return i;
					break;
				default:
					if(out.contains(matcher[i]))
						return i;
			}
		}
		return -1;
	}

	public void parseList(List<List<Object>> l){
		if(debug){
		for (List row : l) {
			for(int i = 0; i < row.size(); i++){
				String out = trim((String)row.get(i));
				if(out.contains("-")){
					out = removeSpaceAfterMinus(out);
				}
				System.out.printf("%s\\", out);
			}
			System.out.println();
		}
		System.out.println("======================");}
		for (List row : l){
			if(row.size() > 0){
				String cat = trim((String)row.get(0));
				if(cat.toLowerCase().contains("rent"));
					rentSwitch = true;
				if(cat.toLowerCase().contains("repa"))
					rentSwitch = false;
				int t = findMatch(cat);
				if(t > -1){
					hmt.replace(list[t], true);
					if(row.size() > 1)
						hm.replace(list[t], Double.parseDouble(trim((String)row.get(1))));
				}
			}
		}
		double round = Math.round((hm.get("Payroll") - hm.get("Wages"))*100)/100.0;
		hm.replace("Payroll", round);
		// print();
	}

	public HashMap<String, Double> getHM(){
		return hm;
	}
	public HashMap<String, Boolean> getHMT(){
		return hmt;
	}
}
