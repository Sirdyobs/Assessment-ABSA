package APITestSuite;

import APIUtility.Crud;
import Data.Endpoints;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DogBreeds {

    Crud crud = new Crud();
    Endpoints ep = new Endpoints();
    JSONObject myObject;

    @Test(priority = 1)
    public void all_dog_breeds() throws JsonProcessingException {
        String payload = crud.getRequest(ep.all_dog_breeds());
        myObject = new JSONObject(payload);
        Assert.assertNotNull(myObject);
    }

    @Test(priority = 2)
    public void retriever_in_list() throws JsonProcessingException {
        String payload = crud.getRequest(ep.all_dog_breeds());
        myObject = new JSONObject(payload);
        Assert.assertTrue(myObject.toString().contains("retriever"));
    }

    @Test(priority = 3)
    public void retriever_sub_breeds() throws JsonProcessingException {
        String payload = crud.getRequest(ep.retriever_subBreed());
        myObject = new JSONObject(payload);
        Assert.assertNotNull(myObject);
    }

    @Test(priority = 4)
    public void sub_breed_golden_random_image() throws JsonProcessingException {
        String payload = crud.getRequest(ep.retriever_golden_subBreed_random_image());
        myObject = new JSONObject(payload);
        Assert.assertNotNull(myObject);
    }

}
