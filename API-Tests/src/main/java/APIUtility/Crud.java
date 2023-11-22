package APIUtility;

import Base.APIBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Crud extends APIBase {

    public Crud(){
        super();
    }

    public String getRequest(String api_endpoint) {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get_request = new HttpGet(api_endpoint);
        get_request.addHeader("Content-Type", "Application/json");
        HttpResponse response = null;
        try {
            response = client.execute(get_request);
            if (response.getStatusLine().getStatusCode() != 200) {
//                test.log(LogStatus.FAIL, "Failed to send request to " + api_endpoint + " [HTTP error code]"
//                        + response.getStatusLine().getStatusCode());
//                extent.endTest(test);
                return "";
            }
            HttpEntity entity = response.getEntity();
            api_response = EntityUtils.toString(entity);
            ObjectMapper mapper = new ObjectMapper();
            //test.log(LogStatus.INFO, "RESPONSE : [  ' " +(api_response) + "  ' ]");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return api_response;
    }

}
