package com.oz_stream.tv.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title = null;
    @SerializedName("description")
    @Expose
    private String description = null;
    @SerializedName("isPreview")
    @Expose
    private String isPreview = null;
    @SerializedName("isComming")
    @Expose
    private String isComming = null;
    @SerializedName("type")
    @Expose
    private String type = null;
    @SerializedName("status")
    @Expose
    private String status = null;
    @SerializedName("isFree")
    @Expose
    private String isFree = null;
    @SerializedName("credit")
    @Expose
    private String credit = null;
    @SerializedName("showAt")
    @Expose
    private String showAt = null;
    @SerializedName("unavailableAt")
    @Expose
    private String unavailableAt = null;
    @SerializedName("link")
    @Expose
    private String link = null;
    @SerializedName("nber_like")
    @Expose
    private String nber_like = null;
    @SerializedName("nber_dislike")
    @Expose
    private String nber_dislike = null;
    @SerializedName("nber_download")
    @Expose
    private String nber_download = null;
    @SerializedName("stars")
    @Expose
    private String stars = null;
    @SerializedName("nber_bandeLooked")
    @Expose
    private String nber_bandeLooked = null;
    @SerializedName("nber_streamLooked")
    @Expose
    private String nber_streamLooked = null;
    @SerializedName("nber_timeLineLookStream")
    @Expose
    private String nber_timeLineLookStream = null;
    @SerializedName("nber_timeLineLookBande")
    @Expose
    private String nber_timeLineLookBande = null;
    @SerializedName("popularity")
    @Expose
    private String popularity = null;

    /*@SerializedName("created_at")
    @Expose
    private String created_at = null;
    @SerializedName("updated_at")
    @Expose
    private String updated_at = null;
    @SerializedName("user_id")
    @Expose
    private String user_id = null;
    @SerializedName("language_id")
    @Expose
    private String language_id = null;
    @SerializedName("photo_id")
    @Expose
    private String photo_id = null;
    @SerializedName("bande_anonce_id")
    @Expose
    private String bande_anonce_id = null;
    @SerializedName("created_by")
    @Expose
    private String created_by = null;
    @SerializedName("updated_by")
    @Expose
    private String updated_by = null;
    @SerializedName("this_user_stat_videos")
    @Expose
    private List<ThisUserStatVideo> this_user_stat_videos = null;*/
    @SerializedName("diffuser")
    @Expose
    private Diffuser diffuser = null;
    /*@SerializedName("language")
    @Expose
    private String language = null;*/
    @SerializedName("episodes")
    @Expose
    private List<Episode> episodes = null;
    @SerializedName("photo")
    @Expose
    private Photo photo = null;
    @SerializedName("bande_anonce")
    @Expose
    private BandeAnonce bande_anonce = null;
    @SerializedName("actors")
    @Expose
    private List<Actor> actors = null;
    @SerializedName("comments")
    @Expose
    private List<Comment> comments = null;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("genders")
    @Expose
    private List<Gender> genders = null;

    private PaletteColors paletteColors;
    private String director;
    private int count;

    public Data() {
    }

    protected Data(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        isPreview = in.readString();
        isComming = in.readString();
        type = in.readString();
        status = in.readString();
        isFree = in.readString();
        credit = in.readString();
        showAt = in.readString();
        unavailableAt = in.readString();
        link = in.readString();
        nber_like = in.readString();
        nber_dislike = in.readString();
        nber_download = in.readString();
        stars = in.readString();
        nber_bandeLooked = in.readString();
        nber_streamLooked = in.readString();
        nber_timeLineLookStream = in.readString();
        nber_timeLineLookBande = in.readString();
        popularity = in.readString();
        /*created_at = in.readString();
        updated_at = in.readString();
        user_id = in.readString();
        language_id = in.readString();
        photo_id = in.readString();
        bande_anonce_id = in.readString();
        created_by = in.readString();
        updated_by = in.readString();
        language = in.readString();*/
        episodes = in.createTypedArrayList(Episode.CREATOR);
        actors = in.createTypedArrayList(Actor.CREATOR);
        categories = in.createTypedArrayList(Category.CREATOR);
        genders = in.createTypedArrayList(Gender.CREATOR);
        paletteColors = in.readParcelable(PaletteColors.class.getClassLoader());
        director = in.readString();
        count = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(isPreview);
        dest.writeString(isComming);
        dest.writeString(type);
        dest.writeString(status);
        dest.writeString(isFree);
        dest.writeString(credit);
        dest.writeString(showAt);
        dest.writeString(unavailableAt);
        dest.writeString(link);
        dest.writeString(nber_like);
        dest.writeString(nber_dislike);
        dest.writeString(nber_download);
        dest.writeString(stars);
        dest.writeString(nber_bandeLooked);
        dest.writeString(nber_streamLooked);
        dest.writeString(nber_timeLineLookStream);
        dest.writeString(nber_timeLineLookBande);
        dest.writeString(popularity);
        /*dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeString(user_id);
        dest.writeString(language_id);
        dest.writeString(photo_id);
        dest.writeString(bande_anonce_id);
        dest.writeString(created_by);
        dest.writeString(updated_by);
        dest.writeString(language);*/
        dest.writeTypedList(episodes);
        dest.writeTypedList(actors);
        dest.writeTypedList(categories);
        dest.writeTypedList(genders);
        dest.writeParcelable(paletteColors, flags);
        dest.writeString(director);
        dest.writeInt(count);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsPreview() {
        return isPreview;
    }

    public void setIsPreview(String isPreview) {
        this.isPreview = isPreview;
    }

    public String getIsComming() {
        return isComming;
    }

    public void setIsComming(String isComming) {
        this.isComming = isComming;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getShowAt() {
        return showAt;
    }

    public void setShowAt(String showAt) {
        this.showAt = showAt;
    }

    public String getUnavailableAt() {
        return unavailableAt;
    }

    public void setUnavailableAt(String unavailableAt) {
        this.unavailableAt = unavailableAt;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNber_like() {
        return nber_like;
    }

    public void setNber_like(String nber_like) {
        this.nber_like = nber_like;
    }

    public String getNber_dislike() {
        return nber_dislike;
    }

    public void setNber_dislike(String nber_dislike) {
        this.nber_dislike = nber_dislike;
    }

    public String getNber_download() {
        return nber_download;
    }

    public void setNber_download(String nber_download) {
        this.nber_download = nber_download;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getNber_bandeLooked() {
        return nber_bandeLooked;
    }

    public void setNber_bandeLooked(String nber_bandeLooked) {
        this.nber_bandeLooked = nber_bandeLooked;
    }

    public String getNber_streamLooked() {
        return nber_streamLooked;
    }

    public void setNber_streamLooked(String nber_streamLooked) {
        this.nber_streamLooked = nber_streamLooked;
    }

    public String getNber_timeLineLookStream() {
        return nber_timeLineLookStream;
    }

    public void setNber_timeLineLookStream(String nber_timeLineLookStream) {
        this.nber_timeLineLookStream = nber_timeLineLookStream;
    }

    public String getNber_timeLineLookBande() {
        return nber_timeLineLookBande;
    }

    public void setNber_timeLineLookBande(String nber_timeLineLookBande) {
        this.nber_timeLineLookBande = nber_timeLineLookBande;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    /*public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(String language_id) {
        this.language_id = language_id;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

    public String getBande_anonce_id() {
        return bande_anonce_id;
    }

    public void setBande_anonce_id(String bande_anonce_id) {
        this.bande_anonce_id = bande_anonce_id;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public List<ThisUserStatVideo> getThis_user_stat_videos() {
        return this_user_stat_videos;
    }

    public void setThis_user_stat_videos(List<ThisUserStatVideo> this_user_stat_videos) {
        this.this_user_stat_videos = this_user_stat_videos;
    }*/

    public Diffuser getDiffuser() {
        return diffuser;
    }

    public void setDiffuser(Diffuser diffuser) {
        this.diffuser = diffuser;
    }

    /*public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }*/

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public BandeAnonce getBande_anonce() {
        return bande_anonce;
    }

    public void setBande_anonce(BandeAnonce bande_anonce) {
        this.bande_anonce = bande_anonce;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Gender> getGenders() {
        return genders;
    }

    public void setGenders(List<Gender> genders) {
        this.genders = genders;
    }

    public PaletteColors getPaletteColors() {
        return paletteColors;
    }

    public void setPaletteColors(PaletteColors paletteColors) {
        this.paletteColors = paletteColors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isPreview='" + isPreview + '\'' +
                '}';
    }
}
