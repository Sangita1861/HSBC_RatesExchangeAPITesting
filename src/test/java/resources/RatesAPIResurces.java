package resources;

public enum RatesAPIResurces {

	LatestRatesAPI("latest"),
	LatestRatesBaseAPI("latest"),
	LatestRatesSymbolAPI("latest"),
	LatestRatesBaseSymbolAPI("latest"),
	LatestRatesInvalidAPI("latestst"),
	PastRatesAPI("2010-01-12"),
	PastRatesBaseAPI("2010-01-12"),
	PastRatesSymbolAPI("2010-01-12"),
	PastRatesBaseSymbolAPI("2010-01-12");
	 private String resource;
	 RatesAPIResurces(String resource) {
	// TODO Auto-generated constructor stub
	
	this.resource = resource;
}

public String getResources()
{
return resource;
	
}
	
}
