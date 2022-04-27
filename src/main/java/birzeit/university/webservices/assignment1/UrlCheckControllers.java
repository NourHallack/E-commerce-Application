package birzeit.university.webservices.assignment1;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckControllers {

    private final String IS_SITE_UP = "Site is up!";
    private final String IS_SITE_DOWN = "Site is down!";
    private final String INCORRECT_URL = "URL is Inccorect";


    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url){
        String returnMessage = "";
        try {

            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responeCodeCategory = connection.getResponseCode()/100;

            if (responeCodeCategory != 2 && responeCodeCategory != 3){
                returnMessage = IS_SITE_DOWN ;
            }else{
                returnMessage = IS_SITE_UP ;
            }

        } catch (MalformedURLException e) {
            returnMessage = INCORRECT_URL ;
            
        } catch (IOException e) {
            returnMessage = IS_SITE_DOWN ; 
            
        }
        return returnMessage;

    }
    
}
