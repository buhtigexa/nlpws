package server;

import java.util.Hashtable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/nlp")
public class NLP {

	
    //PhraseExtractor pe;
	PhraseExtractor phraseExtractor;
	
	@Path("{text}")
	@GET
	@Produces("application/json")
	public Response getDates(@PathParam("text") String text) {
	
	
		
		String localhost=System.getenv("HOSTNAME");
		String id_host = System.getenv("NAME");	
		localhost=" ID :" + localhost + "  SERVICE : " + id_host;
		
		@SuppressWarnings("unused")
		String strText=text.replace("+"," ");
		phraseExtractor=new PhraseExtractor();
		NLPText nlpText=phraseExtractor.extract(strText);
		
		nlpText.docName="NLP";
		nlpText.id=localhost;
		
		Gson gson = new Gson();
		String nlpStr=gson.toJson(nlpText,NLPText.class);
		Response response = Response.status(200).entity(nlpStr).build();
		return response;
		
		
		
	
	}
	
	
}
