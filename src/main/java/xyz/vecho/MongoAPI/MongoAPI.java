package xyz.vecho.MongoAPI;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoAPI {

	private MongoClient client;
	private MongoDatabase database;
	private boolean isConnected = false;
	
	public MongoAPI(String connectionString, String db) {
		try {
			client = new MongoClient(new MongoClientURI(connectionString));
			database = client.getDatabase(db);
			isConnected = true;
		} catch (Exception e) {
			isConnected = false;
		}
	}
	
	public boolean isConnected() {
		return isConnected;
	}
	
	public MongoDatabase getDatabase() {
		return database;
	}
	
	public void createCollectionIfNotExists(String collectionName) {
		if (database == null || !isConnected()) return;
		if (collectionsAsList().contains(collectionName)) return;
		database.createCollection(collectionName);
	}
	
	public List<String> collectionsAsList() {
		if (database == null || !isConnected()) return null;
		return database.listCollectionNames().into(new ArrayList<String>());
	}
	
	public void dropCollectionIfExists(String collectionName) {
		if (database == null || !isConnected()) return;
		if (!collectionsAsList().contains(collectionName)) return;
		database.getCollection(collectionName).drop();
	}
	
}
