package org.dvaletin.apps.tanukitestassignment.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * JSON HTTP Client.
 *
 * @author Alexey Efimov
 */
public class JsonHttpClient {
	private static final String T = JsonHttpClient.class.getSimpleName();
	public static final int BUFFER_SIZE = 512;

	/**
	 * Will create new {@link DefaultHttpClient} and perform request.
	 *
	 * @param request Request data
	 * @param method  HTTP method
	 * @return JSON response in form '{httpStatus: {code: 200, ...}, response: {...}}' (even if http response code is not 200 OK)
	 * @throws JSONException If response body contains not a JSON
	 * @throws IOException   If network error
	 */
	public JSONObject request(String request, HttpEntityEnclosingRequestBase method) throws JSONException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		try {
			method.setHeader("Accept", "application/json");
			method.setHeader("Accept-Encoding", "gzip");
			method.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			method.setEntity(getEntity(request, method));

			long t = System.currentTimeMillis();
			HttpResponse response = httpClient.execute(method);
			long timeMs = System.currentTimeMillis() - t;
			StatusLine statusLine = response.getStatusLine();
			RequestLine requestLine = method.getRequestLine();
			Log.i(T, "\"" + requestLine.getMethod() + " " + requestLine.getUri() +
					" " + requestLine.getProtocolVersion() + "\" " + statusLine.getStatusCode() +
					" " + timeMs + " ms");
			Log.d(T, "Request: " + request + ", " + method.getEntity().getContentLength() + " bytes");

			String responseBody = readResponse(response);
			Log.d(T, "Response: " + responseBody);
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				// Get hold of the response entity (-> the data):
				// Return empty object
				return responseBody != null ? new JSONObject(responseBody) : new JSONObject();
			}
			throw new HttpResponseException(statusLine.getStatusCode(),
					responseBody != null && responseBody.startsWith("{") && responseBody.endsWith("}") ?
							new JSONObject(responseBody).toString() : statusLine.getReasonPhrase());
		} finally {
			try {
				httpClient.getConnectionManager().shutdown();
			} catch (Exception e) {
				Log.e(T, "Can't shutdown connection in http client", e);
			}
		}
	}
	
	public JSONObject request(HttpRequestBase method) throws JSONException, IOException{
		HttpClient httpClient = new DefaultHttpClient();
		try{
			method.setHeader("Accept", "application/json");
			method.setHeader("Accept-Encoding", "gzip");
			method.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			long t = System.currentTimeMillis();
			HttpResponse response = httpClient.execute(method);
			long timeMs = System.currentTimeMillis() - t;
			StatusLine statusLine = response.getStatusLine();
			RequestLine requestLine = method.getRequestLine();
			Log.i(T, "\"" + requestLine.getMethod() + " " + requestLine.getUri() +
					" " + requestLine.getProtocolVersion() + "\" " + statusLine.getStatusCode() +
					" " + timeMs + " ms");
			String responseBody = readResponse(response);
			Log.d(T, "Response: " + responseBody);
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				// Get hold of the response entity (-> the data):
				// Return empty object
				return responseBody != null ? new JSONObject(responseBody) : new JSONObject();
			}
			throw new HttpResponseException(statusLine.getStatusCode(),
					responseBody != null && responseBody.startsWith("{") && responseBody.endsWith("}") ?
							new JSONObject(responseBody).toString() : statusLine.getReasonPhrase());
		} finally{
			try{
				httpClient.getConnectionManager().shutdown();
			}catch (Exception e){
				Log.e(T, "Can't shutdown connection in http client", e);
			}
		}
	}

	public JSONArray requestArray(HttpRequestBase method) throws JSONException, IOException{
		HttpClient httpClient = new DefaultHttpClient();
		try{
			method.setHeader("Accept", "application/json");
			method.setHeader("Accept-Encoding", "gzip");
			method.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			long t = System.currentTimeMillis();
			HttpResponse response = httpClient.execute(method);
			long timeMs = System.currentTimeMillis() - t;
			StatusLine statusLine = response.getStatusLine();
			RequestLine requestLine = method.getRequestLine();
			Log.i(T, "\"" + requestLine.getMethod() + " " + requestLine.getUri() +
					" " + requestLine.getProtocolVersion() + "\" " + statusLine.getStatusCode() +
					" " + timeMs + " ms");
			String responseBody = readResponse(response);
			Log.d(T, "Response: " + responseBody);
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				// Get hold of the response entity (-> the data):
				// Return empty object
				return responseBody != null ? new JSONArray(responseBody) : new JSONArray();
			}
			throw new HttpResponseException(statusLine.getStatusCode(),
					responseBody != null && responseBody.startsWith("{") && responseBody.endsWith("}") ?
							new JSONObject(responseBody).toString() : statusLine.getReasonPhrase());
		} finally{
			try{
				httpClient.getConnectionManager().shutdown();
			}catch (Exception e){
				Log.e(T, "Can't shutdown connection in http client", e);
			}
		}
	}
	
	public InputStream requestFile(String url) throws IOException {
		URL u = new URL(url);
		HttpURLConnection c = (HttpURLConnection) u.openConnection();
//		c.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; U; Android 1.6; en-us; eeepc Build/Donut) AppleWebKit/528.5+ (KHTML, like Gecko) Version/3.1.2 Mobile Safari/525.20.1");
//		c.setRequestMethod("GET");
		Log.i(T, c.getRequestMethod()+" "+url);
		c.connect();
		return c.getInputStream();
//		HttpGet request = new HttpGet(url);
//		return new BufferedInputStream(requestFile(request));
	}
	
	public InputStream requestFile(HttpRequestBase method) throws IOException{
		HttpClient httpClient = new DefaultHttpClient();
		try{
//			method.setHeader("Accept", "application/json");
//			method.setHeader("Accept-Encoding", "gzip");
//			method.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			long t = System.currentTimeMillis();
			HttpResponse response = httpClient.execute(method);
			long timeMs = System.currentTimeMillis() - t;
			StatusLine statusLine = response.getStatusLine();
			RequestLine requestLine = method.getRequestLine();
			Log.i(T, "\"" + requestLine.getMethod() + " " + requestLine.getUri() +
					" " + requestLine.getProtocolVersion() + "\" " + statusLine.getStatusCode() +
					" " + timeMs + " ms");
			Log.i(T, "Content-length:"+String.valueOf(response.getEntity().getContentLength()));
			Log.i(T, "Content-type:"+response.getEntity().getContentType().getValue());
			InputStream is = response.getEntity().getContent();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				// Get hold of the response entity (-> the data):
				// Return empty object
				return is;
			}
			throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
		} finally{
			try{
				httpClient.getConnectionManager().shutdown();
			}catch (Exception e){
				Log.e(T, "Can't shutdown connection in http client", e);
			}
		}
	}

	
	protected HttpEntity getEntity(String request, HttpEntityEnclosingRequestBase method) throws IOException {
		return new StringEntity(request);
	}
		
	private static String readResponse(HttpResponse response) throws IOException {
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			// Read the content stream
			InputStream inputStream = entity.getContent();
			Header contentEncoding = response.getFirstHeader("Content-Encoding");
			if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
				inputStream = new GZIPInputStream(inputStream, BUFFER_SIZE);
			}
			try {
				// convert content stream to a String
				return readLines(inputStream);
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {
					Log.w(T, "Error while closing response stream: " + e);
				}
			}
		}
		return null;
	}

	private static String readLines(InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), BUFFER_SIZE);
		StringBuilder builder = new StringBuilder();

		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}

		return builder.toString();
	}

}
