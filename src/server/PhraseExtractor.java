package server;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import server.NLPText;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class PhraseExtractor {
/*
	public String[] documents ={ "Doctor Bastian Schweinsteiger atiende Sabados de 8 horas  a 12 horas en la colon",
    		"Martes y Jueves a las 5 pm cambia de horario los micros","Viernes Sabados y Domingos a las 19 horas show en la costa",
    		"Desde las 10 am a las 7 pm maratón de series","Reunion optativa Cloud Lunes 11 am en Sala de Conf ISISTAN"
    		};

	public String[] regex={"[a-zA-Z]+_np00000 [a-zA-Z]+_sp000 [0-9]+_z0 [a-z]+_nc0p000 .*_sp000 [0-9]+_z0 [a-z]+_nc0p000",
    		"[a-zA-Z]+_w [a-zA-Z]+_cc [a-zA-Z]+_w [a-zA-Z]+_sp000 [a-zA-Z]+_da0000 [0-9]+_dn0000 [a-z]+_nc0s000",
    		"[a-zA-Z]+_np00000 [a-zA-Z]+_np00000 [a-z]+_cc [a-zA-Z]+_np00000 [a-z]+_sp000 [a-z]+_da0000 [0-9]+_z0 [a-z]+_nc0p000",
    		 "[a-zA-Z]+_sp000 [a-z]+_da0000 [0-9]+_w [a-z]+_np00000 [a-z]+_sp000 [a-z]+_da0000 [0-9]+_w [a-z]+_nc0s000",
    		 "[a-zA-Z]+_w [0-9]+_z0 [a-z]+_nc0n000"
    };
	*/

	public String[] documents ={ "Doctor Bastian Schweinsteiger atiende Sabados de 8 horas  a 12 horas en la colon",
    		"Martes y Jueves a las 5 pm cambia de horario los micros","Viernes Sabados y Domingos a las 19 horas show en la costa",
    		"Desde las 10 am a las 7 pm maratón de series","Reunion optativa Cloud Lunes 11 am en Sala de Conf ISISTAN",
    		"Mañana a las 16 hs  empiezo la dieta","Verificar el auto Jueves 15 a las 4","Lavadero mañana a las 6","Domingo a la tarde duermo la siesta"
    		};
	
	 

	public String[] regex={
			"[a-zA-Zñ]+_np00000 [a-zñ]+_sp000 [0-9]+_z0 [a-zñ]+_nc0p000 [a-z]+_sp000 [0-9]+_z0 [a-z]+_nc0p000",
			"[a-zA-Zñ]+_w [a-zA-Zñ]+_cc [a-zA-Zñ]+_w [a-zA-Zñ]+_sp000 [a-zA-Zñ]+_da0000 [0-9]+_dn0000 [a-zñ]+_nc0s000",
    		"[a-zA-Zñ]+_np00000 [a-zA-Zñ]+_np00000 [a-zñ]+_cc [a-zA-Zñ]+_np00000 [a-zñ]+_sp000 [a-zñ]+_da0000 [0-9]+_z0 [a-zñ]+_nc0p000",
    		 "[a-zA-Zñ]+_sp000 [a-z]+_da0000 [0-9]+_w [a-zñ]+_np00000 [a-zñ]+_sp000 [a-zñ]+_da0000 [0-9]+_w [a-zñ]+_nc0s000",
    		 "[a-zA-Zñ]+_w [0-9]+_z0 [a-zñ]+_nc0n000","[a-zA-Zñ]+_rg [a-z]+_sp000 [a-zñ]+_da0000 [0-9]+_dn0000 [a-zñ]+_nc0p000",
    		 "[a-zA-Zñ]+_w [0-9]+_z0 [a-zñ]+_sp000 [a-zñ]+_da0000 [0-9]+_w", "[a-zA-Zñ]+_rg [a-zñ]+_sp000 [a-zñ]+_da0000 [0-9]+_z0",
    		 "[a-zA-Zñ]+_np00000 [a-zñ]+_sp000 [a-zñ]+_da0000 [a-zñ]+_nc0s000"
    };
	
	
	public String taggers_location;
	public MaxentTagger tagger;
	
	public PhraseExtractor(){
	
		taggers_location= System.getenv("TAGGERS");
		tagger = new MaxentTagger(taggers_location+"/spanish.tagger");
	
	}
	
	public String regexp(String tagged){
		
		String result = "";
		
		for (int i=0;i < regex.length;i++){
			Pattern pattern = Pattern.compile(regex[i]);
			Matcher matcher = pattern.matcher(tagged);
			boolean find = false;
			try{
							int j=0;
							while(matcher.find() && !find) {
								result+=matcher.group(j);
								find = true;
								j++;
						}
				}
					catch(Exception e){
							e.printStackTrace();
					}
			}
		
		result = result.replaceAll("_[a-zA-Z0-9]+","");
		return result;
	
	}
	
	public String tag(String text){
			String tagged1 = tagger.tagString(text);
            return tagged1;
	}
		
	public NLPText  extract(String text){
		
		NLPText nlpText = new NLPText("","","","");
		String tagged = tag(text);
		String match = regexp(tagged);
		String date = match;
		String event =  text.replaceAll(match," ") ;
		nlpText.date=date;
		nlpText.event=event;
		return nlpText;
		
	}

}
