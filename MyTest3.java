import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.response.Response;
import resources.GetAPIData;
import resources.ReusableMethods;
public class MyTest3 {

	String token;                         
	String host;
	String name;
	String channel;
	String ChangedName;
	@BeforeClass
	public void getData() throws IOException{
	token = "xoxp-865758099127-855957835089-855958760209-334cdb15a488b67faffdf06adf17a4b1";
	host = "https://slack.com/api/";
	name = "channel3";
	ChangedName = "Changedchannel3";}
	
	@Test
	public void channelsCreate() {	
	RestAssured.baseURI = host;
	String response = given().
			queryParam("token",token).
			queryParam("name",name).
			when().
			post("channels.create").
			then().
			assertThat().
			statusCode(200).and().
			body("[0].ok",containsString("true")).and().
			contentType(ContentType.JSON).extract().asString();
	System.out.println("responseString : " + response);
	}
	@Test
	public void channelsJoin() {
		RestAssured.baseURI = host;
		String response = given().
				queryParam("token",token).
				queryParam("name",name).
	         when().
	post("channels.join").
	then().
	assertThat().
	statusCode(200).and().
	body("[0].ok",containsString("true")).and().
	contentType(ContentType.JSON).extract().asString();
System.out.println("responseString : " + response);
JsonPath js = new JsonPath(response);
channel = js.get("channel.id");
System.out.println("leadId : " + channel);
	}
@Test

public void channelsRename() {
RestAssured.baseURI = host;
String response = given().
		queryParam("token",token).
		queryParam("channel",channel).
		queryParam("name",ChangedName).
		when().
		post("channels.rename").
		then().
		assertThat().
		statusCode(200).and().
		body("channel.name",containsString("change")).and().
		contentType(ContentType.JSON).extract().asString();
System.out.println("responseString : " + response);
}
@Test
public void channelsArchive() {

RestAssured.baseURI = host;
String response = given().
		queryParam("token",token).
		queryParam("channel",channel).
		when().
		post("channels.archive").
		then().
		assertThat().
		statusCode(200).and().
		body("ok",equalTo("true")).and().
		contentType(ContentType.JSON).extract().asString();
System.out.println("responseString : " + response);
}

}
