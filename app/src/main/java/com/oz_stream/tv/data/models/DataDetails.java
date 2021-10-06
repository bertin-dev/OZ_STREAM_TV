package com.oz_stream.tv.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataDetails {

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
    @SerializedName("diffuser")
    @Expose
    private Diffuser diffuser = null;
    /*@SerializedName("episodes")
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
    private Categories category = null;
    @SerializedName("genders")
    @Expose
    private List<Gender> genders = null;

    private PaletteColors paletteColors;
    private String director;
    private int count;


    public DataDetails() {
    }

    public DataDetails(int id, String title, String description, String isPreview, String isComming, String type, String status, String isFree, String credit, String showAt, String unavailableAt, String link, String nber_like, String nber_dislike, String nber_download, String stars, String nber_bandeLooked, String nber_streamLooked, String nber_timeLineLookStream, String nber_timeLineLookBande, String popularity, Diffuser diffuser, Photo photo, BandeAnonce bande_anonce, List<Actor> actors, List<Comment> comments, Categories category, List<Gender> genders, PaletteColors paletteColors, String director, int count) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isPreview = isPreview;
        this.isComming = isComming;
        this.type = type;
        this.status = status;
        this.isFree = isFree;
        this.credit = credit;
        this.showAt = showAt;
        this.unavailableAt = unavailableAt;
        this.link = link;
        this.nber_like = nber_like;
        this.nber_dislike = nber_dislike;
        this.nber_download = nber_download;
        this.stars = stars;
        this.nber_bandeLooked = nber_bandeLooked;
        this.nber_streamLooked = nber_streamLooked;
        this.nber_timeLineLookStream = nber_timeLineLookStream;
        this.nber_timeLineLookBande = nber_timeLineLookBande;
        this.popularity = popularity;
        this.diffuser = diffuser;
        this.photo = photo;
        this.bande_anonce = bande_anonce;
        this.actors = actors;
        this.comments = comments;
        this.category = category;
        this.genders = genders;
        this.paletteColors = paletteColors;
        this.director = director;
        this.count = count;
    }

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

    public Diffuser getDiffuser() {
        return diffuser;
    }

    public void setDiffuser(Diffuser diffuser) {
        this.diffuser = diffuser;
    }

    /*public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }*/

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

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
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
}
