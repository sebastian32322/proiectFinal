package URL_Req;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL; 

import org.json.JSONObject;

public class Test_URL_Req {

	public static void main(String[] args) {
		try {
			 String url = "https://gps.sctpiasi.ro/json";
			 URL obj = new URL(url);
		     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		     
		     int responseCode = con.getResponseCode();
		     System.out.println("\nSending 'GET' request to URL : " + url);
		     System.out.println("Response Code : " + responseCode);
		     
		     BufferedReader in = new BufferedReader(
		             new InputStreamReader(con.getInputStream()));
		     String inputLine;
		     StringBuffer response = new StringBuffer();
		     while ((inputLine = in.readLine()) != null) {
		     	response.append(inputLine);
		     }
		     in.close();
		   
		     
		     
		     
		     String myresponse = response.toString().substring(1, response.toString().length() - 1);
		     System.out.println(myresponse);

		     
		     
		     
		     String parts[] = myresponse.split("}");
		     String names[] = new String[500];
		     String lat[] = new String[500];
		     String lon[] = new String[500];
	       	    
		     
		     for(int i = 1; i < parts.length; ++i) {
		        parts[i] = parts[i].substring(1);  
		        }
		    
		     for(int i = 0; i < parts.length; ++i) {
		        	
		     
	        	parts[i] = parts[i] + "}";  //Metoda Alex
	       	    JSONObject firstRequest = new JSONObject(parts[i].toString());
	       	    	
	       	    names[i] = firstRequest.getString("vehicleName");
	       	    lat[i] = firstRequest.getString("vehicleLat");
	       	    lon[i] = firstRequest.getString("vehicleLong");
	  
		     	}
		 
		     
		     String goodnames[] = new String[500];
		     String goodlat[] = new String[500];
		     String goodlon[] = new String[500];
		     
		     int j = 0;
		     for(int i = 0; i < parts.length; ++i) {
		    	 if( names[i].charAt(0) == '2' && names[i].length() == 4)
		    	 {
		    		 goodnames[j] = names[i];
		    		 goodlat[j] = lat[i];
		    		 goodlon[j] = lon[i];
		    		 j++;
		    	 }
		    		 
		    	 
		     }
		     
		     for(int i = 0; i < goodnames.length; ++i) {
		    	 if( goodnames[i] != null)
		    	 {
		    		 	System.out.println(goodnames[i]);
		    		 	System.out.println(goodlat[i]);
		    		 	System.out.println(goodlon[i]);
		    	 }
		     }
		     
		     System.out.println("--------------------------------------------------");
		     Thread.sleep(30000);
		     
		     
		     String url1 = "https://gps.sctpiasi.ro/json";
			 URL obj1 = new URL(url1);
		     HttpURLConnection con1 = (HttpURLConnection) obj1.openConnection();
		     
		     int responseCode1 = con1.getResponseCode();
		     System.out.println("\nSending 'GET' request to URL : " + url1);
		     System.out.println("Response Code : " + responseCode1);
		     
		     BufferedReader in1 = new BufferedReader(
		             new InputStreamReader(con1.getInputStream()));
		     String inputLine1;
		     StringBuffer response1 = new StringBuffer();
		     while ((inputLine1 = in1.readLine()) != null) {
		     	response1.append(inputLine1);
		     }
		     in1.close();
		   
		     
		     
		     
		     String myresponse1 = response1.toString().substring(1, response1.toString().length() - 1);
		     System.out.println(myresponse1);

		     
		     
		     
		     String parts1[] = myresponse1.split("}");
		     String names1[] = new String[500];
		     String lat1[] = new String[500];
		     String lon1[] = new String[500];
	       	    
		     
		     for(int i = 1; i < parts1.length; ++i) {
		        parts1[i] = parts1[i].substring(1);  
		        }
		    
		     for(int i = 0; i < parts1.length; ++i) {
		        	
		     
	        	parts1[i] = parts1[i] + "}";  //Metoda Alex
	       	    JSONObject secondRequest = new JSONObject(parts1[i].toString());
	       	    	
	       	    names1[i] = secondRequest.getString("vehicleName");
	       	    lat1[i] = secondRequest.getString("vehicleLat");
	       	    lon1[i] = secondRequest.getString("vehicleLong");
	  
		     	}
		 
		     
		     String goodnames1[] = new String[500];
		     String goodlat1[] = new String[500];
		     String goodlon1[] = new String[500];
		     
		     int k = 0;
		     for(int i = 0; i < parts1.length; ++i) {
		    	 if( names1[i].charAt(0) == '2' && names1[i].length() == 4)
		    	 {
		    		 goodnames1[k] = names1[i];
		    		 goodlat1[k] = lat1[i];
		    		 goodlon1[k] = lon1[i];
		    		 k++;
		    	 }
		    		 
		    	 
		     }
		     
		     for(int i = 0; i < goodnames1.length; ++i) {
		    	 if( goodnames1[i] != null)
		    	 {
		    		 	System.out.println(goodnames1[i]);
		    		 	System.out.println(goodlat1[i]);
		    		 	System.out.println(goodlon1[i]);
		    	 }
		     }
		    
		    double a=27.57239;
		    double b = 47.174618;
		    double c=27.590581;
		    double d = 47.164221;
		     a = Math.toRadians(a);
		     c = Math.toRadians(c);
		     b = Math.toRadians(b);
		     d = Math.toRadians(d);
		     
		     double dlon = c - a;
		        double dlat = d - b;
		        double x = Math.pow(Math.sin(dlat / 2), 2)
		                 + Math.cos(b) * Math.cos(d)
		                 * Math.pow(Math.sin(dlon / 2),2);
		             
		        double y = 2 * Math.asin(Math.sqrt(x));
		 
		        // Radius of earth in kilometers. Use 3956
		        // for miles
		        double r = 6371;
		 
		        // calculate the result
		        System.out.println(y * r);
		     
			
		} catch(Exception e){
			System.out.println(e);
		}
		
		

	}

}
