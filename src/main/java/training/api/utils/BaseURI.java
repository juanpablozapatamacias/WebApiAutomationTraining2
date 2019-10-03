package training.api.utils;

public class BaseURI {
	private String baseURI;
	private String basePath;

	
	public BaseURI(String u, String p) {
		this.baseURI = u;
		this.basePath = p;
	}

	public String getBaseURI() {
		return baseURI;
	}

	public void setBaseURI(String baseURI) {
		this.baseURI = baseURI;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	
	public void resetBaseURI() {
		this.baseURI = null;
	}
	
	public void resetBasePath() {
		this.basePath = null;
	}
	
	public String getFullBaseURI() {
		return this.getBaseURI() + this.getBasePath();
	}

}
