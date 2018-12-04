public class Store extends Categories{
  private Month [] month;
  private HashMap<String, Boolean> hmt;

  public Store(){
    super();
    month = new Month[0];
    hmt = new HashMap<String, Boolean>();
  }
  public Store(Month [] m){
    super();
    month = m;
    hmt = m[m.length - 1].getHMT();
  }

  private void print(){
		for(String e : list)
      for(int i = 0; i < month.length; i++){
  			if(hmt.get(e)){
  				if(e.equals("Advertising"))
  					System.out.println();
  				if(debug)
  					System.out.print(e+", ");
  				boolean x = e.equals("Amortization") ||
  										e.equals("Interest") ||
  										e.equals("Depreciation");
  				System.out.print(!x ? hm.get(e) + ", " : "");
        }
        System.out.println();
			}
		System.out.println();
		double round = Math.round((hm.get("Amortization") +
															 hm.get("Interest") +
															 hm.get("Depreciation"))*100)/100.0;
		System.out.println("AID: " + round);
	}
}
