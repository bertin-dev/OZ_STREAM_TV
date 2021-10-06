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
    @SerializedName("duration")
    @Expose
    private String duration = null;
    @SerializedName("unavailableAt")
    @Expose
    private String unavailableAt = null;
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
    private String language = null;
    @SerializedName("episodes")
    @Expose
    private List<Episode> episodes = null;*/
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
    @SerializedName("category")
    @Expose
    private Category category = null;
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
        duration = in.readString();
        unavailableAt = in.readString();
        nber_like = in.readString();
        nber_dislike = in.readString();
        nber_download = in.readString();
        stars = in.readString();
        nber_bandeLooked = in.readString();
        nber_streamLooked = in.readString();
        nber_timeLineLookStream = in.readString();
        nber_timeLineLookBande = in.readString();
        popularity = in.readString();
        diffuser = in.readParcelable(Diffuser.class.getClassLoader());
        photo = in.readParcelable(Photo.class.getClassLoader());
        bande_anonce = in.readParcelable(BandeAnonce.class.getClassLoader());
        actors = in.createTypedArrayList(Actor.CREATOR);
        comments = in.createTypedArrayList(Comment.CREATOR);
        category = in.readParcelable(Category.class.getClassLoader());
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
        dest.writeString(duration);
        dest.writeString(unavailableAt);
        dest.writeString(nber_like);
        dest.writeString(nber_dislike);
        dest.writeString(nber_download);
        dest.writeString(stars);
        dest.writeString(nber_bandeLooked);
        dest.writeString(nber_streamLooked);
        dest.writeString(nber_timeLineLookStream);
        dest.writeString(nber_timeLineLookBande);
        dest.writeString(popularity);
        dest.writeParcelable(diffuser, flags);
        dest.writeParcelable(photo, flags);
        dest.writeParcelable(bande_anonce, flags);
        dest.writeTypedList(actors);
        dest.writeTypedList(comments);
        dest.writeParcelable(category, flags);
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUnavailableAt() {
        return unavailableAt;
    }

    public void setUnavailableAt(String unavailableAt) {
        this.unavailableAt = unavailableAt;
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

    public Diffuser getDiffuser() {
        return diffuser;
    }

    public void setDiffuser(Diffuser diffuser) {
        this.diffuser = diffuser;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public static Creator<Data> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isPreview='" + isPreview + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
