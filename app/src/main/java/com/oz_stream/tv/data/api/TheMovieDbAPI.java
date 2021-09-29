package com.oz_stream.tv.data.api;

import com.oz_stream.tv.Config;
import com.oz_stream.tv.dagger.modules.HttpClientModule;
import com.oz_stream.tv.data.models.Access_token;
import com.oz_stream.tv.data.models.Root;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface TheMovieDbAPI {


    //PAGE D'ACCUEIL
    @GET(HttpClientModule.ACCUEIL)
    Observable<Root> getHomePage();


    //FILTER BY CATEGORY
    @GET(HttpClientModule.FILTER_BY_CATEGORY + "/{category}")
    Observable<Root> getFilterByCategory(
            @Path("category") String category
    );

    //SEND ID CODE
    @FormUrlEncoded
    @POST(HttpClientModule.IDCODE + Config.API_KEY_URL + "/" + Config.ITEM_PURCHASE_CODE + "/")
    Observable<Access_token> codeID(@Field("code") String code);

    //AUTHENTIFICATION
    @FormUrlEncoded
    @POST(HttpClientModule.LOGIN)
    Observable<Access_token> login(@Field("phone") String phone, @Field("password") String password);

}
