package pl.edu.agh.bd.mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.ascending;

public class MongoLab {
	private MongoClient mongoClient;
	private MongoDatabase db;

	public MongoLab() throws UnknownHostException {
		mongoClient = new MongoClient();
		db = mongoClient.getDatabase("KrzysztofHardekA");
	}

	private void showCollections(){
		for(String name : db.listCollectionNames()){
			System.out.println("colname: "+name);
		}
	}
	public int getFiveStarPlaces(){
		MongoCollection<Document> collection = db.getCollection("business");
		var query = new BasicDBObject("stars", new BasicDBObject("$eq", 5));
		var docs = new ArrayList<Document>();
		collection.find(query).into(docs);
		return docs.size();
	}

	public AggregateIterable<Document> getCityRestaurants(){
		Document match = new Document("$match", new Document("categories", "Restaurants"));

		Document groupFields = new Document("_id", "$city");
		groupFields.put("quantity", new Document("$sum", 1));
		Document group = new Document("$group", groupFields);

		Document sort = new Document("$sort", new Document("quantity", -1));

		MongoCollection<Document> collection = db.getCollection("business");

		List<Document> pipeline = Arrays.asList(match, group, sort);
		return collection.aggregate(pipeline);
	}

	public AggregateIterable<Document> getHotelAmount(){
		Document match = new Document("$match", new Document("categories", "Hotels"));
		match.put("$match", new Document("attributes.Wi-Fi", "free"));

		Document groupFields = new Document("_id", "$state");
		groupFields.put("quantity", new Document("$sum", 1));
		Document group = new Document("$group", groupFields);

		MongoCollection<Document> collection = db.getCollection("business");

		List<Document> pipeline = Arrays.asList(match, group);
		return collection.aggregate(pipeline);
	}

	String getBestUser(){
		MongoCollection<Document> collection = db.getCollection("user");
		var query = new Document("average_stars", new Document("$gt", 4.5));
		var docs = new ArrayList<Document>();
		collection.find(query).into(docs);
		Document bestUser = null;

		for(Document user: docs){
			if(bestUser == null || user.getInteger("review_count") > bestUser.getInteger("review_count")){
				bestUser = user;
			}
		}

		return (String) bestUser.get("name");
	}

	ArrayList<Integer> getVotesCounts(){
		ArrayList<Integer> result = new ArrayList<>();

		MongoCollection<Document> collection = db.getCollection("user");
		int funny_count = 0;
		int useful_count = 0;
		int cool_count = 0;

		var docs = new ArrayList<Document>();
		FindIterable fit = collection.find(new Document("votes", new Document("$exists", true)));
		fit.into(docs);

		for(Document user: docs){
			Document votes = (Document) user.get("votes");
			if(votes.getInteger("funny") > 0) funny_count++;
			if(votes.getInteger("useful") > 0) useful_count++;
			if(votes.getInteger("cool") > 0) cool_count++;
		}
		result.add(funny_count);
		result.add(useful_count);
		result.add(cool_count);

		return result;
	}

	public AggregateIterable<Document> getAllCities(){
		Document group = new Document("$group", new Document("_id", "$city"));
		Document sort = new Document("$sort", new Document("_id", 1));

		MongoCollection<Document> collection = db.getCollection("business");

		List<Document> pipeline = Arrays.asList(group, sort);
		return collection.aggregate(pipeline);
	}

	public long getReviewsAfter2011(){
		MongoCollection<Document> collection = db.getCollection("review");
		var query = new Document("date", new BasicDBObject("$gte", "2011"));
		var docs = new ArrayList<Document>();

		return collection.countDocuments(query);
	}

	public FindIterable<Document> getClosedBusiness(){
		MongoCollection<Document> collection = db.getCollection("business");
		return collection.find(eq("open", false)).projection(include("name", "full_address", "stars"));
	}

	public FindIterable<Document> getBadUsers(){
		MongoCollection<Document> collection = db.getCollection("user");

		return collection.find(and(
				eq("votes.funny", 0),
				eq("votes.useful", 0)
		)).projection(include("name")).sort(ascending("name"));
	}

	public AggregateIterable<Document> getBusinessTips() {
		MongoCollection<Document> collection = db.getCollection("tip");
		return
				collection.aggregate(
						Arrays.asList(
								Aggregates.match(regex("date", "2012-*")),
								Aggregates.group("$business_id", Accumulators.sum("total", 1)),
								Aggregates.sort(ascending("total")),
								Aggregates.lookup("business", "_id", "business_id", "b"),
								Aggregates.project(
										Projections.fields(
												Projections.include("b.name", "total")
										)
								)
						)
				);
	}

	public AggregateIterable<Document> getAvgBusinessStars() {
		MongoCollection<Document> collection = db.getCollection("review");
		return
				collection.aggregate(
						Arrays.asList(
								Aggregates.match(gte("stars", 4)),
								Aggregates.group("$business_id", Accumulators.avg("avg", "$stars")),
								Aggregates.lookup("business", "_id", "business_id", "b"),
								Aggregates.unwind("$b"),
								Aggregates.project(
										Projections.fields(
												Projections.include("b.name", "avg")
										)
								)
						)
				);
	}

	public DeleteResult removeBadBusiness() {
		MongoCollection<Document> collection = db.getCollection("business");
		return collection.deleteMany(eq("stars", 2));
	}

	public static void main(String[] args) throws UnknownHostException {
		MongoLab mongoLab = new MongoLab();
		DeleteResult r;
		r = mongoLab.removeBadBusiness();
		System.out.println(r);
	}
}

