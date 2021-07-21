package xyz.vecho.MongoAPI;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assumptions;

public class TestMongoAPI {

	@Test
	public void test1() {
		MongoAPI api = new MongoAPI("testString", "testDatabase");
		
		// skip if not connected (if you wanna test change the parameters with a correct database parameters)
		Assumptions.assumeFalse(api.isConnected());
	
		List<String> names = api.collectionsAsList();
		if (names.contains("deneme_collection")) {
			api.dropCollectionIfExists("deneme_collection");
		}
		names = api.collectionsAsList();
		assertFalse(names.contains("deneme_collection"));
		
		api.createCollectionIfNotExists("deneme_collection");
		names = api.collectionsAsList();
		assertTrue(names.contains("deneme_collection"));
	}
	
}
