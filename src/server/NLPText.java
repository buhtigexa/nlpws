package server;


public class NLPText {

	public String docName;
	public String event;
	public String date;
	public String id;
	
	
	public NLPText(String docName,String event,String date,String id) {
	
		this.event=event;
		this.date=date;
		this.id=id;
		this.docName=docName;
	}
	
	
	public String toString(){
		
		String print="<"+docName+">"+"\n" +
		"  <id> " + this.id + " </id>\n"+
		"  <date> " + this.date + " </date>\n"+
		"  <event> " + this.event + " </event>\n"+
		"</"+docName+">\n";
		return print;
		
	}
	
	public void trim(){
		
		
		while (event.startsWith(" ")){
			event=event.substring(1, event.length());
		}
		
		while(event.endsWith(" ")){
		
			event=event.substring(0, event.length()-1);
		}
	
		event = event.replace(" ","+");
		event = event.replace("++","+");

		
		
		while (event.startsWith("+")){
			event=event.substring(1, event.length());
		}
		
		while(event.endsWith("+")){
		
			event=event.substring(0, event.length()-1);
		}
	}
}
