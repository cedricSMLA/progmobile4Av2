package cedric.smla.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRest {
        private static DBZ SwAPI_CLIENT;
        private static final String API_URL = "https://dragon-ball-api.herokuapp.com/api/";

        static {
            loadClient();
        }

        public static DBZ get() {
            return SwAPI_CLIENT;
        }


        private static void loadClient() {

            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient)
                    .build();
            SwAPI_CLIENT = retrofit.create(DBZ.class);
        }
    }

