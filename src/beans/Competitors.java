package beans;

public class Competitors {
	
	String name;
	String address;
	String latitude;
	String longitude;
	
	public Competitors(String name, String address, String latitude, String longitude) {
		
		this.name = name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Competitors [name=" + name + ", address=" + address
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
}