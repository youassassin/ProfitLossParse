import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
public class Store extends Categories{
  private List<Month> month;
  private HashMap<String, Boolean> hmt;
	public static boolean debug = false;
	public static boolean cate = false;

  public Store(){
    super();
    month = new ArrayList<Month>();
    hmt = new HashMap<String, Boolean>();
  }
  public Store(Month [] m){
    super();
    month = new ArrayList<Month>();
    for(Month e : m)
      month.add(e);
    hmt = m[m.length - 1].getHMT();
  }
  public void addMonth(Month m){
    month.add(m);
    int x = 0, y = 0;
    Object [] b  = m.getHMT().values().toArray();
    for(int i = 0; i < b.length; i++)
      x += (Boolean)b[i] ? 1 : 0;
    b  = hmt.values().toArray();
    for(int i = 0; i < b.length; i++)
      y += (Boolean)b[i] ? 1 : 0;
    hmt = x > y ? m.getHMT() : hmt;
  }
  public void addCategories(String [] str){
    for(String s : str){
      hmt.replace(s, true);
    }
  }

  public void print(){
		for(String e : list){
      if(hmt.get(e) && cate)
        System.out.print(e +": ");
      if(e.equals("Advertising"))
        System.out.println();
      for(int i = 0; i < month.size(); i++){
  			if(hmt.get(e)){
  				if(debug)
  					System.out.print(e+", ");
  				boolean x = e.equals("Amortization") ||
  										e.equals("Interest") ||
  										e.equals("Depreciation");
  				System.out.print(!x ? month.get(i).getHM().get(e) + ", " : "");
        }
			}
			if(hmt.get(e))
        System.out.println();
    }
		System.out.println("\nAID: ");
    for(int i = 0; i < month.size(); i++){
    	double round = Math.round((month.get(i).getHM().get("Amortization") +
    														 month.get(i).getHM().get("Interest") +
    														 month.get(i).getHM().get("Depreciation"))*100)/100.0;
    	System.out.print(round + ", ");
    }
	}
}
