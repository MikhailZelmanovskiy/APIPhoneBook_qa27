package okHttpTests;

import com.google.gson.Gson;
import dto.AuthRequestDto;
import dto.AuthResponseDto;
import dto.ErrorDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class OkHttpLoginTest {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Test
    public void loginTest() throws IOException {
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("john9090@mail.com")
                .password("Aa12345~").build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto),JSON);
        Request request = new Request.Builder().url("https://contacts-telran.herokuapp.com/api/login").post(requestBody).build();
        Response response = client.newCall(request).execute();

        if(response.isSuccessful()){
            String responseJson = response.body().string();
            AuthResponseDto responseDto = gson.fromJson(responseJson,AuthResponseDto.class);
            System.out.println(responseDto.getToken());
        }else{
            System.out.println("Response Code" + response.code());
            ErrorDto errorDto = gson.fromJson(response.body().string(), ErrorDto.class);
            System.out.println("Error message: " + errorDto.getMessage());
        }
    }

    @Test
    public void loginTestPositive() throws IOException {
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("john9090@mail.com")
                .password("Aa12345~").build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto), JSON);

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();


        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
    }
    @Test
    public void loginTestNegative() throws IOException {
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("john9090@mail.com")
                .password("Aa12345").build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto), JSON);
        Request request = new Request.Builder().url("https://contacts-telran.herokuapp.com/api/login").post(requestBody).build();
        Response response = client.newCall(request).execute();
        ErrorDto errorDto = gson.fromJson(response.body().string(), ErrorDto.class);
        Assert.assertEquals(errorDto.getCode(), 400);
    }
}
