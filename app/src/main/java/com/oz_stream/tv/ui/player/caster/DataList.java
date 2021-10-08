/**
 * Copyright 2020 Google LLC. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oz_stream.tv.ui.player.caster;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oz_stream.tv.data.models.Actor;
import com.oz_stream.tv.data.models.BandeAnonce;
import com.oz_stream.tv.data.models.Categories;
import com.oz_stream.tv.data.models.Category;
import com.oz_stream.tv.data.models.Comment;
import com.oz_stream.tv.data.models.Data;
import com.oz_stream.tv.data.models.Diffuser;
import com.oz_stream.tv.data.models.Episode;
import com.oz_stream.tv.data.models.Frees;
import com.oz_stream.tv.data.models.Gender;
import com.oz_stream.tv.data.models.News;
import com.oz_stream.tv.data.models.Photo;
import com.oz_stream.tv.data.models.Playlist;
import com.oz_stream.tv.data.models.Populars;
import com.oz_stream.tv.data.models.Previews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class DataList {
    private static final String TAG = "DataList";

    //News
    private static final String TAG_TOTAL_NEWS = "total";

    //DATA
    private static final String TAG_TITLE = "title";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_PREVIEW = "isPreview";
    private static final String TAG_COMMING = "isComming";
    private static final String TAG_TYPE = "type";
    private static final String TAG_STATUS = "status";
    private static final String TAG_FREE = "isFree";
    private static final String TAG_CREDIT = "credit";
    private static final String TAG_SHOW = "showAt";
    private static final String TAG_UNAVAILABLE = "unavailableAt";
    private static final String TAG_NUMBER_LIKE = "nber_like";
    private static final String TAG_NUMBER_DISLIKE = "nber_dislike";
    private static final String TAG_NUMBER_DOWNLOAD = "nber_download";
    private static final String TAG_NUMBER_STARS = "stars";
    private static final String TAG_NUMBER_BANDE_ANNONCE_LOOKED = "nber_bandeLooked";
    private static final String TAG_NUMBER_STREAMLOOKED = "nber_streamLooked";
    private static final String TAG_NUMBER_TIMELINELOOKSTREAM = "nber_timeLineLookStream";
    private static final String TAG_NUMBER_TIMELINELOOKBANDE = "nber_timeLineLookBande";
    private static final String TAG_NUMBER_POPULARITY = "popularity";
    //diffuser
    private static final String TAG_FIRST_NAME = "firstName";
    private static final String TAG_LAST_NAME = "lastName";
    private static final String TAG_SEX = "sex";
    private static final String TAG_BIRTH_DAY = "birthDate";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_PHONE = "phone";
    private static final String TAG_ACCOUNT_STATUT = "accountStatus";
    private static final String TAG_AVATAR_LINK = "AvatarLink";
    private static final String TAG_COVER = "cover";
    private static final String TAG_BIBLIOGRAPHIE = "bibliographie";
    //Photo
    private static final String TAG_PHOTO_LINK = "link";

    //Episode
    private static final String TAG_EP_NAME = "name";
    private static final String TAG_EP_DESCRIPTION = "description";

    /*
    //Saison
    private static final String TAG_SAISON_NAME = "name";
    private static final String TAG_SAISON_DESCRIPTION = "description";

    //Serie
    private static final String TAG_SERIE_TITLE = "title";
    private static final String TAG_SERIE_DESCRIPTION = "description";*/

    //Bande d'annonce
    private static final String TAG_BA_NAME = "name";
    private static final String TAG_BA_LINK = "link";
    private static final String TAG_BA_DESCRIPTION = "description";

    //ACTORS
    private static final String TAG_ACTOR_FIRSTNAME = "firstName";
    private static final String TAG_ACTOR_LASTNAME = "lastName";
    private static final String TAG_ACTOR_BIRTH = "birthDate";
    private static final String TAG_ACTOR_HEIGHT = "height";
    private static final String TAG_ACTOR_BIBLIOGRAPHIE = "bibliographie";
    private static final String TAG_ACTOR_AVATAR = "avatar";
    private static final String TAG_ACTOR_PHONE = "phone";

    //COMMENT
    private static final String TAG_COMMENT_CONTENT = "content";
    private static final String TAG_COMMENT_VALIDATED = "isValidated";

    //CATEGORIE
    private static final String TAG_CATEGORIE_TITLE = "title";
    private static final String TAG_CATEGORIE_DESCRIPTION = "description";

    //GENRE
    private static final String TAG_GENRE_TITLE = "title";
    private static final String TAG_GENRE_DESCRIPTION = "description";


    private static final String NEWS = "news";
    private static final String DATAS = "datas";
    private static final String DIFFUSER = "diffuser";
    private static final String EPISODES = "episodes";
    /*private static final String SAISON = "saison";
    private static final String SERIE = "serie";*/
    private static final String PHOTO = "photo";
    private static final String BANDE_ANONCE = "bande_anonce";
    private static final String ACTORS = "actors";
    private static final String COMMENTS = "comments";

    //ROOT
    private static final String PREVIEWS = "Previews";
    private static final String POPULARS = "Populars";
    //private static final String WILLBEPOSTES = "willbePostes";
    private static final String FREES = "frees";
    private static final String GENDERS = "genders";
    private static final String CATEGORY = "category";
    private static final String PLAYLISTS = "playlists";

    private static News news;
    private static Previews previews;
    private static Populars populars;
    //private static WillbePostes willbePostes;
    private static Frees frees;
    private static Playlist playlist;
    private static int count = 0;

    private static Diffuser diffus;
    private static List<Episode> episodeList;
    private static Photo tofs;
    private static BandeAnonce b_annonce;
    private static List<Actor> actorList;
    private static List<Comment> commentList;
    private static Category category;
    private static List<Gender> genderList;
    private static List<Data> list;

    public static List<Data> getList() {
        return list;
    }


    public static List<Data> setupMovies(String url) throws JSONException {
        Log.w("TAG", "setupMovies: " + url);
        if (null != list) {
            return list;
        }
        list = new ArrayList<>();
        //Map<String, String> urlPrefixMap = new HashMap<>();
        JSONObject jsonObj = new DataList().parseUrl(url);
        //Log.w("TAG", "setupMovies: " + jsonObj );

        JSONObject newsJSONObject = jsonObj.getJSONObject(NEWS);
        int totalNews = newsJSONObject.getInt(TAG_TOTAL_NEWS);
        JSONArray datasArray = newsJSONObject.getJSONArray(DATAS);
        if (null != datasArray) {

            for (int i = 0; i < datasArray.length(); i++) {
                JSONObject datasJSONObject = datasArray.getJSONObject(i);
                String datas_title = datasJSONObject.getString(TAG_TITLE);
                String datas_desc = datasJSONObject.getString(TAG_DESCRIPTION);
                String datas_preview = datasJSONObject.getString(TAG_PREVIEW);
                String datas_coming = datasJSONObject.getString(TAG_COMMING);
                String datas_type = datasJSONObject.getString(TAG_TYPE);
                String datas_status = datasJSONObject.getString(TAG_STATUS);
                String datas_free = datasJSONObject.getString(TAG_FREE);
                String datas_credit = datasJSONObject.getString(TAG_CREDIT);
                String datas_show = datasJSONObject.getString(TAG_SHOW);
                String datas_unavailable = datasJSONObject.getString(TAG_UNAVAILABLE);
                String datas_num_like = datasJSONObject.getString(TAG_NUMBER_LIKE);
                String datas_num_dislike = datasJSONObject.getString(TAG_NUMBER_DISLIKE);
                String datas_num_download = datasJSONObject.getString(TAG_NUMBER_DOWNLOAD);
                String data_stars = datasJSONObject.getString(TAG_NUMBER_STARS);
                String data_num_ba_looked = datasJSONObject.getString(TAG_NUMBER_BANDE_ANNONCE_LOOKED);
                String data_num_streamlooked = datasJSONObject.getString(TAG_NUMBER_STREAMLOOKED);
                String data_num_timelinelookstream = datasJSONObject.getString(TAG_NUMBER_TIMELINELOOKSTREAM);
                String data_num_timelinelookbande = datasJSONObject.getString(TAG_NUMBER_TIMELINELOOKBANDE);
                String data_num_popularity = datasJSONObject.getString(TAG_NUMBER_POPULARITY);

                //diffuser
                JSONObject diffuserJsonObject = datasJSONObject.getJSONObject(DIFFUSER);
                String diffuser_first_name = diffuserJsonObject.getString(TAG_FIRST_NAME);
                String diffuser_last_name = diffuserJsonObject.getString(TAG_LAST_NAME);
                String diffuser_sex = diffuserJsonObject.getString(TAG_SEX);
                String diffuser_birth = diffuserJsonObject.getString(TAG_BIRTH_DAY);
                String diffuser_email = diffuserJsonObject.getString(TAG_EMAIL);
                String diffuser_phone = diffuserJsonObject.getString(TAG_PHONE);
                String diffuser_account_statut = diffuserJsonObject.getString(TAG_ACCOUNT_STATUT);
                String diffuser_avatar_link = diffuserJsonObject.getString(TAG_AVATAR_LINK);
                String diffuser_cover = diffuserJsonObject.getString(TAG_COVER);
                String diffuser_bibliographie = diffuserJsonObject.getString(TAG_BIBLIOGRAPHIE);

                //Episodes
                JSONArray episodesArrayList = datasJSONObject.getJSONArray(EPISODES);
                /*if (null != episodesArrayList) {
                    for (int j = 0; j < episodesArrayList.length(); j++) {
                        JSONObject episodesJSONObject = episodesArrayList.getJSONObject(j);
                        String episode_name = episodesJSONObject.getString(TAG_EP_NAME);
                        String episode_desc = episodesJSONObject.getString(TAG_EP_DESCRIPTION);

                        //Saison
                        JSONObject saisonJSONObject = episodesJSONObject.getJSONObject(SAISON);
                        String saison_name = saisonJSONObject.getString(TAG_SAISON_NAME);
                        String saison_desc = saisonJSONObject.getString(TAG_SAISON_DESCRIPTION);

                        //Serie
                        JSONObject serieJSONObject = saisonJSONObject.getJSONObject(SERIE);
                        String serie_title = serieJSONObject.getString(TAG_SERIE_TITLE);
                        String serie_desc = serieJSONObject.getString(TAG_SERIE_DESCRIPTION);

                    }
                }*/

                //photo
                JSONObject photoJsonObject = datasJSONObject.getJSONObject(PHOTO);
                String photo_url = photoJsonObject.getString(TAG_PHOTO_LINK);


                //Bande d'annonce
                JSONObject baJsonObject = datasJSONObject.getJSONObject(BANDE_ANONCE);
                String ba_name = baJsonObject.getString(TAG_BA_NAME);
                String ba_link = baJsonObject.getString(TAG_BA_LINK);
                String ba_desc = baJsonObject.getString(TAG_BA_DESCRIPTION);


                //Actors
                JSONArray actorsArrayList = datasJSONObject.getJSONArray(ACTORS);
                if (null != actorsArrayList) {
                    for (int K = 0; K < actorsArrayList.length(); K++) {
                        JSONObject actorsJSONObject = actorsArrayList.getJSONObject(K);
                        String actor_firstname = actorsJSONObject.getString(TAG_ACTOR_FIRSTNAME);
                        String actor_lastname = actorsJSONObject.getString(TAG_ACTOR_LASTNAME);
                        String actor_birth = actorsJSONObject.getString(TAG_ACTOR_BIRTH);
                        String actor_height = actorsJSONObject.getString(TAG_ACTOR_HEIGHT);
                        String actor_bibliographie = actorsJSONObject.getString(TAG_ACTOR_BIBLIOGRAPHIE);
                        String actor_avatar = actorsJSONObject.getString(TAG_ACTOR_AVATAR);
                        String actor_phone = actorsJSONObject.getString(TAG_ACTOR_PHONE);
                    }
                }

                //Comment
                JSONArray commentArrayList = datasJSONObject.getJSONArray(COMMENTS);
                if (null != commentArrayList) {
                    for (int l = 0; l < commentArrayList.length(); l++) {
                        JSONObject commentJSONObject = commentArrayList.getJSONObject(l);
                        String comment_content = commentJSONObject.getString(TAG_COMMENT_CONTENT);
                        String comment_validated = commentJSONObject.getString(TAG_COMMENT_VALIDATED);
                    }
                }

                //Categories
                JSONObject categoryJsonObject = datasJSONObject.getJSONObject(CATEGORY);

                //Genres
                JSONArray genreArrayList = datasJSONObject.getJSONArray(GENDERS);
                if (null != genreArrayList) {
                    for (int m = 0; m < genreArrayList.length(); m++) {
                        JSONObject genreJSONObject = genreArrayList.getJSONObject(m);
                        String genre_title = genreJSONObject.getString(TAG_GENRE_TITLE);
                        String genre_description = genreJSONObject.getString(TAG_GENRE_DESCRIPTION);
                    }
                }

                //CONVERT JSONOBJECT TO OBJECT SOURCE
                diffus = new Gson().fromJson(diffuserJsonObject.toString(), new TypeToken<Diffuser>() {
                }.getType());
                tofs = new Gson().fromJson(photoJsonObject.toString(), new TypeToken<Photo>() {
                }.getType());
                b_annonce = new Gson().fromJson(baJsonObject.toString(), new TypeToken<BandeAnonce>() {
                }.getType());
                category = new Gson().fromJson(categoryJsonObject.toString(), new TypeToken<Category>() {
                }.getType());

                //CONVERT JSONARRAY TO LIST
                episodeList = new Gson().fromJson(episodesArrayList.toString(), new TypeToken<List<Episode>>() {
                }.getType());
                actorList = new Gson().fromJson(actorsArrayList.toString(), new TypeToken<List<Actor>>() {
                }.getType());
                commentList = new Gson().fromJson(commentArrayList.toString(), new TypeToken<List<Comment>>() {
                }.getType());
                genderList = new Gson().fromJson(genreArrayList.toString(), new TypeToken<List<Gender>>() {
                }.getType());


                //Log.w("TAG", "NDEMBA0000----------: " + url_sources);
                list.add(buildMovieInfo(
                        datas_title,
                        datas_desc,
                        datas_preview,
                        datas_coming,
                        datas_type,
                        datas_status,
                        datas_free,
                        datas_credit,
                        datas_show,
                        datas_unavailable,
                        datas_num_like,
                        datas_num_dislike,
                        datas_num_download,
                        data_stars,
                        data_num_ba_looked,
                        data_num_streamlooked,
                        data_num_timelinelookstream,
                        data_num_timelinelookbande,
                        data_num_popularity,
                        diffus,
                        episodeList,
                        tofs,
                        b_annonce,
                        actorList,
                        commentList,
                        category,
                        genderList
                ));
            }
        }
        return list;
    }

    private static Data buildMovieInfo(
            String title,
            String description,
            String isPreview,
            String isComming,
            String type,
            String status,
            String isFree,
            String credit,
            String showAt,
            String unavailableAt,
            String nber_like,
            String nber_dislike,
            String nber_download,
            String stars,
            String nber_bandeLooked,
            String nber_streamLooked,
            String nber_timeLineLookStream,
            String nber_timeLineLookBande,
            String popularity,
            //List<ThisUserStatVideo> this_user_stat_videos,
            Diffuser diffuser,
            List<Episode> episodes,
            Photo photo,
            BandeAnonce bandeAnonce,
            List<Actor> actors,
            List<Comment> comments,
            Category category,
            List<Gender> genders) {

        Data data = new Data();
        data.setCount(count++);
        data.setTitle(title);
        data.setDescription(description);
        data.setIsPreview(isPreview);
        data.setIsComming(isComming);
        data.setType(type);
        data.setStatus(status);
        data.setIsFree(isFree);
        data.setCredit(credit);
        data.setShowAt(showAt);
        data.setUnavailableAt(unavailableAt);
        data.setNber_like(nber_like);
        data.setNber_dislike(nber_dislike);
        data.setNber_download(nber_download);
        data.setStars(stars);
        data.setNber_bandeLooked(nber_bandeLooked);
        data.setNber_streamLooked(nber_streamLooked);
        data.setNber_timeLineLookStream(nber_timeLineLookStream);
        data.setNber_timeLineLookBande(nber_timeLineLookBande);
        data.setPopularity(popularity);
        //data.setThis_user_stat_videos(this_user_stat_videos);
        data.setDiffuser(diffuser);
        data.setEpisodes(episodes);
        data.setPhoto(photo);
        data.setBande_anonce(bandeAnonce);
        data.setActors(actors);
        data.setComments(comments);
        data.setCategory(category);
        data.setGenders(genders);
        return data;
    }

    protected JSONObject parseUrl(String urlString) {
        InputStream is = null;
        try {
            java.net.URL url = new java.net.URL(urlString);
            URLConnection urlConnection = url.openConnection();
            is = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream(), "iso-8859-1"), 1024);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String json = sb.toString();
            return new JSONObject(json);
        } catch (Exception e) {
            Log.d(TAG, "Failed to parse the json for media list", e);
            return null;
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

}