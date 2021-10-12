package com.oz_stream.tv.data.api;

import com.oz_stream.tv.dagger.modules.HttpClientModule;
import com.oz_stream.tv.data.models.Access_token;
import com.oz_stream.tv.data.models.Root;
import com.oz_stream.tv.data.models.RootFilter;
import com.oz_stream.tv.data.models.SearchResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface TheMovieDbAPI {


    //PAGE D'ACCUEIL
    @GET(HttpClientModule.ACCUEIL)
    Observable<Root> getHomePage();


    //FILTER BY CATEGORY
    @GET(HttpClientModule.FILTER_BY_CATEGORY + "/{category}")
    Observable<RootFilter> getFilterByCategory(
            @Path("category") String category
    );

    //RECHERCHE PAR NON DE FILMS
    @GET(HttpClientModule.SEARCH_BY_USER)
    Observable<SearchResult> searchUserByName(@Query("search") String search);

    //RECHERCHE PAR ACTEUR
    @GET(HttpClientModule.SEARCH_BY_ACTOR)
    Observable<SearchResult> getActorsList(@Path("search") String search);


    //SEND ID CODE
    @FormUrlEncoded
    @POST(HttpClientModule.IDCODE)
    Observable<Access_token> codeID(@Field("code") String code);

    //AUTHENTIFICATION
    @FormUrlEncoded
    @POST(HttpClientModule.LOGIN)
    Observable<Access_token> login(@Field("phone") String phone, @Field("password") String password);

}
