// Need to get it to take arguments and generate url for search, but otherwise works like python script.
// Like BeautifulSoup script, this is still printing meanings for which there are not translations in the target language.  
// only working for first translation (i.e., verb, but not non). need to loop through these
import java.util.ArrayList;
import java.io.IOException;
// Jsoup
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
// regex
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class wikt_scrape_en {
   public static void main(String[] args) throws IOException {
		String search_url = "https://en.wiktionary.org/wiki/";
		for (int i = 0; i < args.length - 1; i++){
			String a = args[i] + "_";
			search_url += a;
		}
		search_url += args[args.length - 1];
	   test_parser(search_url);
//	   check_linked_translations();
   }
	public static void check_linked_translations(Document doc) {
		Elements links = doc.select("a[href]");
		String pattern = ".*/wiki/.*/translations#";
		Pattern p = Pattern.compile(pattern);
		for (Element link : links) {
			Matcher m = p.matcher(link.attr("href"));	
			if(m.find()){
				String linked_page = "https://en.wiktionary.org" + link.attr("href");
				test_parser(linked_page);
			}
		}
	}
	public static void test_parser(String url) {	
	try {
		
     		Document document = Jsoup.connect(url).get();

	      
		Elements trans = document.getElementsByClass("translations");
	for (Element table : trans) {

	      Elements spans = table.select("span");
		String gloss = table.attr("data-gloss");
		System.out.println(gloss);
	      for (Element span : spans) {
		String lang = span.attr("lang");
		String pattern = "^de.*";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(span.attr("lang"));
		if(m.find()){
		      System.out.println("\t" + span.text());
		    }
		    }
	}	
	check_linked_translations(document);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	}
}
