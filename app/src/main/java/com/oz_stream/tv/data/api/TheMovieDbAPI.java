package com.oz_stream.tv.data.api;

import com.oz_stream.tv.Config;
import com.oz_stream.tv.dagger.modules.HttpClientModule;
import com.oz_stream.tv.data.models.Actor;
import com.oz_stream.tv.data.models.Root;
import com.oz_stream.tv.data.models.Saison;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface TheMovieDbAPI {

    @GET(HttpClientModule.ACCUEIL)
    Observable<Root> getNowPlayingMovies();


    /*@GET(HttpClientModule.ACTOR_DETAILS + "{id}/" + Config.API_KEY_URL + "/" + Config.ITEM_PURCHASE_CODE + "/")
    Observable<List<Poster>> getFilmographieDetails(
            @Path("id") int id
    );


    @GET(HttpClientModule.RANDOM_GENRE + "{genres}/" + Config.API_KEY_URL + "/" + Config.ITEM_PURCHASE_CODE + "/")
    Observable<List<Poster>> getRandomMovies(
            @Path("genres") String genres
    );


    @GET(HttpClientModule.ROLE_BY_POSTER + "{id}/" + Config.API_KEY_URL + "/" + Config.ITEM_PURCHASE_CODE + "/")
    Observable<List<Actor>> getRolesByPoster(
            @Path("id") int id
    );



    //RECHERCHE PAR ACTEURS
    @GET(HttpClientModule.SEARCH_BY_ACTOR + "{page}/{search}/" + Config.API_KEY_URL + "/" + Config.ITEM_PURCHASE_CODE + "/")
    Observable<List<Actor>> getActorsList(
            @Path("page") Integer page,
            @Path("search") String search
    );


    //RECHERCHE GLOBALE
    @GET(HttpClientModule.GLOBAL_SEARCH + "{query}/" + Config.API_KEY_URL + "/" + Config.ITEM_PURCHASE_CODE + "/")
    Observable<Movie> searchData(@Path("query") String query);


    //LISTE DES FILMS PAR FILTRE
    @GET(HttpClientModule.MOVIES + "{genre}/{order}/{page}/" + Config.API_KEY_URL + "/" + Config.ITEM_PURCHASE_CODE + "/")
    Observable<List<Poster>> getMoviesByFiltres(
            @Path("genre") Integer genre,
            @Path("order") String order,
            @Path("page") Integer page
    );


    //LISTE DES SERIES PAR FILTRE
    @GET(HttpClientModule.SERIES + "{genre}/{order}/{page}/" + Config.API_KEY_URL + "/" + Config.ITEM_PURCHASE_CODE + "/")
    Observable<List<Poster>> getSeriesByFiltres(
            @Path("genre") Integer genre,
            @Path("order") String order,
            @Path("page") Integer page
    );


    //LOGIN
    @FormUrlEncoded
    @POST(HttpClientModule.LOGIN + Config.API_KEY_URL + "/" + Config.ITEM_PURCHASE_CODE + "/")
    Observable<ApiResponse> login(
            @Field("username") String username,
            @Field("password") String password
    );

    //EDIT TOKEN
    @FormUrlEncoded
    @POST(HttpClientModule.EDIT_TOKEN + Config.API_KEY_URL + "/" + Config.ITEM_PURCHASE_CODE + "/")
    Observable<ApiResponse> editToken(
            @Field("user") Integer user,
            @Field("key") String key,
            @Field("token_f") String token_f,
            @Field("name") String name
    );


    //REGISTER
    @FormUrlEncoded
    @POST(HttpClientModule.REGISTER + Config.API_KEY_URL + "/" + Config.ITEM_PURCHASE_CODE + "/")
    Observable<ApiResponse> register(
            @Field("name") String name,
            @Field("username") String username,
            @Field("password") String password,
            @Field("type") String type,
            @Field("image") String image
    );



        //LIST DES SAISONS ET EPISODES
    @GET(HttpClientModule.SEASON + "{id}/" + Config.API_KEY_URL + "/" + Config.ITEM_PURCHASE_CODE + "/")
    Observable<List<Saison>> getSeasonsBySerie(@Path("id") Integer id);
    */



}
