package com.tokbox.android.opentokrtc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class ServiceHandler {

	static String response = null;
	public final static int GET = 1;
	public final static int POST = 2;

	public ServiceHandler() {

	}

	/*
	 * Making service call
	 * @url - url to make request
	 * @method - http request method
	 * */
	public String makeServiceCall(String url, int method) throws JSONException {
		return this.makeServiceCall(url, method, null);
	}

	/*
	 * Making service call
	 * @url - url to make request
	 * @method - http request method
	 * @params - http request params
	 * */
	public String makeServiceCall(String url, int method,
			ArrayList<NameValuePair> params) throws JSONException {
		try {
			// http client
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpEntity httpEntity = null;
			HttpResponse httpResponse = null;
			
			
			
			// Checking http request method type
			if (method == POST) {
//				HttpPost httpPost = new HttpPost(url);
//				// adding post params
//				if (params != null) {
//					httpPost.setEntity(new UrlEncodedFormEntity(params));
//				}
//
//				httpResponse = httpClient.execute(httpPost);
				 //instantiates httpclient to make request
			    DefaultHttpClient httpclient = new DefaultHttpClient();

			    //url with the post data
			    HttpPost httpost = new HttpPost(url);
			    

			    //convert parameters into JSON object
			    JSONObject holder = getJsonObjectFromMap("user",params);

			    //passes the results to a string builder/entity
			    StringEntity se = new StringEntity(holder.toString());

			    //sets the post request as the resulting string
			    httpost.setEntity(se);
			    //sets a request header so the page receving the request
			    //will know what to do with it
			    httpost.setHeader("Accept", "application/json");
			    httpost.setHeader("Content-type", "application/json");

			    //Handles what is returned from the page 
			   // ResponseHandler responseHandler = new BasicResponseHandler();
			    httpResponse = httpclient.execute(httpost);

			} else if (method == GET) {
				// appending params to url
				if (params != null) {
					String paramString = URLEncodedUtils
							.format(params, "utf-8");
					url += "?" + paramString;
				}
				HttpGet httpGet = new HttpGet(url);

				httpResponse = httpClient.execute(httpGet);

			}
			httpEntity = httpResponse.getEntity();
			response = EntityUtils.toString(httpEntity);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return response;

	}
	private static JSONObject getJsonObjectFromMap(String key,ArrayList<NameValuePair> params) throws JSONException {

	    //all the passed parameters from the post request
	    //iterator used to loop through all the parameters
	    //passed in the post request
	    //Iterator iter = params.entrySet().iterator();

	    //Stores JSON
	    JSONObject holder = new JSONObject();


	        //object for storing Json
	        JSONObject data = new JSONObject();

	        //gets the value
	        Iterator iter2 = params.iterator();
	        while (iter2.hasNext()) 
	        {
	            NameValuePair pairs2 = (NameValuePair) iter2.next();
	            data.put((String)pairs2.getName(), (String)pairs2.getValue());
	        }

	        //puts email and 'foo@bar.com'  together in map
	        holder.put(key, data);
	    
	    return holder;
	}
}
