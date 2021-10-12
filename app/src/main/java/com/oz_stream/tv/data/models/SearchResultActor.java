package com.oz_stream.tv.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResultActor {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("firstName")
    @Expose
    private String firstName = null;
    @SerializedName("lastName")
    @Expose
    private String lastName = null;
    @SerializedName("sex")
    @Expose
    private String sex = null;
    @SerializedName("birthDate")
    @Expose
    private String birthDate = null;
    @SerializedName("email")
    @Expose
    private String email = null;
    @SerializedName("email_verified_at")
    @Expose
    private String email_verified_at = null;
    @SerializedName("phone")
    @Expose
    private String phone = null;
    @SerializedName("cardNumber")
    @Expose
    private String cardNumber = null;
    @SerializedName("credit")
    @Expose
    private String credit = null;
    @SerializedName("maxDevice")
    @Expose
    private String maxDevice = null;
    @SerializedName("tokenSpace")
    @Expose
    private String tokenSpace = null;
    @SerializedName("fcmToken")
    @Expose
    private String fcmToken = null;
    @SerializedName("referent")
    @Expose
    private String referent = null;
    @SerializedName("accountStatus")
    @Expose
    private String accountStatus = null;
    @SerializedName("avatarLink")
    @Expose
    private String avatarLink = null;
    @SerializedName("cover")
    @Expose
    private String cover = null;
    @SerializedName("bibliographie")
    @Expose
    private String bibliographie = null;
    @SerializedName("update_role_at")
    @Expose
    private String update_role_at = null;
    @SerializedName("created_at")
    @Expose
    private String created_at = null;
    @SerializedName("updated_at")
    @Expose
    private String updated_at = null;
    @SerializedName("language_id")
    @Expose
    private String language_id = null;
    @SerializedName("country_id")
    @Expose
    private String country_id = null;
    @SerializedName("town_id")
    @Expose
    private String town_id = null;
    @SerializedName("role_id")
    @Expose
    private String role_id = null;
    @SerializedName("role_given_by")
    @Expose
    private String role_given_by = null;
    @SerializedName("created_by")
    @Expose
    private String created_by = null;
    @SerializedName("updated_by")
    @Expose
    private String updated_by = null;
    @SerializedName("language")
    @Expose
    private Language language = null;
    @SerializedName("country")
    @Expose
    private Country country = null;
    @SerializedName("town")
    @Expose
    private Town town = null;
    @SerializedName("role")
    @Expose
    private Role role = null;
    @SerializedName("partner")
    @Expose
    private List<Partner> partner = null;
    @SerializedName("current_page")
    @Expose
    private String current_page = null;
    @SerializedName("data")
    @Expose
    private List<SearchResultActor> data = null;
    @SerializedName("first_page_url")
    @Expose
    private String first_page_url = null;
    @SerializedName("from")
    @Expose
    private String from = null;
    @SerializedName("last_page")
    @Expose
    private String last_page = null;
    @SerializedName("last_page_url")
    @Expose
    private String last_page_url = null;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;
    @SerializedName("next_page_url")
    @Expose
    private String next_page_url = null;
    @SerializedName("path")
    @Expose
    private String path = null;
    @SerializedName("per_page")
    @Expose
    private String per_page = null;
    @SerializedName("prev_page_url")
    @Expose
    private String prev_page_url = null;
    @SerializedName("to")
    @Expose
    private String to = null;
    @SerializedName("total")
    @Expose
    private String total = null;
    @SerializedName("medias")
    @Expose
    private List<Media> medias = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getMaxDevice() {
        return maxDevice;
    }

    public void setMaxDevice(String maxDevice) {
        this.maxDevice = maxDevice;
    }

    public String getTokenSpace() {
        return tokenSpace;
    }

    public void setTokenSpace(String tokenSpace) {
        this.tokenSpace = tokenSpace;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getReferent() {
        return referent;
    }

    public void setReferent(String referent) {
        this.referent = referent;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getBibliographie() {
        return bibliographie;
    }

    public void setBibliographie(String bibliographie) {
        this.bibliographie = bibliographie;
    }

    public String getUpdate_role_at() {
        return update_role_at;
    }

    public void setUpdate_role_at(String update_role_at) {
        this.update_role_at = update_role_at;
    }

    public String getCreated_at() {
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

    public String getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(String language_id) {
        this.language_id = language_id;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getTown_id() {
        return town_id;
    }

    public void setTown_id(String town_id) {
        this.town_id = town_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_given_by() {
        return role_given_by;
    }

    public void setRole_given_by(String role_given_by) {
        this.role_given_by = role_given_by;
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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Partner> getPartner() {
        return partner;
    }

    public void setPartner(List<Partner> partner) {
        this.partner = partner;
    }

    public String getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public List<SearchResultActor> getData() {
        return data;
    }

    public void setData(List<SearchResultActor> data) {
        this.data = data;
    }

    public String getFirst_page_url() {
        return first_page_url;
    }

    public void setFirst_page_url(String first_page_url) {
        this.first_page_url = first_page_url;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getLast_page() {
        return last_page;
    }

    public void setLast_page(String last_page) {
        this.last_page = last_page;
    }

    public String getLast_page_url() {
        return last_page_url;
    }

    public void setLast_page_url(String last_page_url) {
        this.last_page_url = last_page_url;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPer_page() {
        return per_page;
    }

    public void setPer_page(String per_page) {
        this.per_page = per_page;
    }

    public String getPrev_page_url() {
        return prev_page_url;
    }

    public void setPrev_page_url(String prev_page_url) {
        this.prev_page_url = prev_page_url;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Media> getMedias() {
        return medias;
    }

    public void setMedias(List<Media> medias) {
        this.medias = medias;
    }

    private static class Link{
        @SerializedName("url")
        @Expose
        private String url = null;
        @SerializedName("label")
        @Expose
        private String label = null;
        @SerializedName("active")
        @Expose
        private boolean active = false;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }
    }



    private static class Partner{
        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("firstName")
        @Expose
        private String firstName = null;
        @SerializedName("lastName")
        @Expose
        private String lastName = null;
        @SerializedName("sex")
        @Expose
        private String sex = null;
        @SerializedName("birthDate")
        @Expose
        private String birthDate = null;
        @SerializedName("email")
        @Expose
        private String email = null;
        @SerializedName("email_verified_at")
        @Expose
        private String email_verified_at = null;
        @SerializedName("phone")
        @Expose
        private String phone = null;
        @SerializedName("cardNumber")
        @Expose
        private String cardNumber = null;
        @SerializedName("credit")
        @Expose
        private String credit = null;
        @SerializedName("maxDevice")
        @Expose
        private String maxDevice = null;
        @SerializedName("tokenSpace")
        @Expose
        private String tokenSpace = null;
        @SerializedName("fcmToken")
        @Expose
        private String fcmToken = null;
        @SerializedName("referent")
        @Expose
        private String referent = null;
        @SerializedName("accountStatus")
        @Expose
        private String accountStatus = null;
        @SerializedName("avatarLink")
        @Expose
        private String avatarLink = null;
        @SerializedName("cover")
        @Expose
        private String cover = null;
        @SerializedName("bibliographie")
        @Expose
        private String bibliographie = null;
        @SerializedName("update_role_at")
        @Expose
        private String update_role_at = null;
        @SerializedName("created_at")
        @Expose
        private String created_at = null;
        @SerializedName("updated_at")
        @Expose
        private String updated_at = null;
        @SerializedName("language_id")
        @Expose
        private String language_id = null;
        @SerializedName("country_id")
        @Expose
        private String country_id = null;
        @SerializedName("town_id")
        @Expose
        private String town_id = null;
        @SerializedName("role_id")
        @Expose
        private String role_id = null;
        @SerializedName("role_given_by")
        @Expose
        private String role_given_by = null;
        @SerializedName("created_by")
        @Expose
        private String created_by = null;
        @SerializedName("updated_by")
        @Expose
        private String updated_by = null;
        @SerializedName("pivot")
        @Expose
        private Pivot pivot = null;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmail_verified_at() {
            return email_verified_at;
        }

        public void setEmail_verified_at(String email_verified_at) {
            this.email_verified_at = email_verified_at;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getMaxDevice() {
            return maxDevice;
        }

        public void setMaxDevice(String maxDevice) {
            this.maxDevice = maxDevice;
        }

        public String getTokenSpace() {
            return tokenSpace;
        }

        public void setTokenSpace(String tokenSpace) {
            this.tokenSpace = tokenSpace;
        }

        public String getFcmToken() {
            return fcmToken;
        }

        public void setFcmToken(String fcmToken) {
            this.fcmToken = fcmToken;
        }

        public String getReferent() {
            return referent;
        }

        public void setReferent(String referent) {
            this.referent = referent;
        }

        public String getAccountStatus() {
            return accountStatus;
        }

        public void setAccountStatus(String accountStatus) {
            this.accountStatus = accountStatus;
        }

        public String getAvatarLink() {
            return avatarLink;
        }

        public void setAvatarLink(String avatarLink) {
            this.avatarLink = avatarLink;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getBibliographie() {
            return bibliographie;
        }

        public void setBibliographie(String bibliographie) {
            this.bibliographie = bibliographie;
        }

        public String getUpdate_role_at() {
            return update_role_at;
        }

        public void setUpdate_role_at(String update_role_at) {
            this.update_role_at = update_role_at;
        }

        public String getCreated_at() {
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

        public String getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(String language_id) {
            this.language_id = language_id;
        }

        public String getCountry_id() {
            return country_id;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }

        public String getTown_id() {
            return town_id;
        }

        public void setTown_id(String town_id) {
            this.town_id = town_id;
        }

        public String getRole_id() {
            return role_id;
        }

        public void setRole_id(String role_id) {
            this.role_id = role_id;
        }

        public String getRole_given_by() {
            return role_given_by;
        }

        public void setRole_given_by(String role_given_by) {
            this.role_given_by = role_given_by;
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

        public Pivot getPivot() {
            return pivot;
        }

        public void setPivot(Pivot pivot) {
            this.pivot = pivot;
        }
    }



    private static class Language{
        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("name")
        @Expose
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class Country{
        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("name")
        @Expose
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class Town{
        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("name")
        @Expose
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class Role{
        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("role")
        @Expose
        private String role = null;
        @SerializedName("created_at")
        @Expose
        private String created_at = null;
        @SerializedName("updated_at")
        @Expose
        private String updated_at = null;
        @SerializedName("created_by")
        @Expose
        private String created_by = null;
        @SerializedName("updated_by")
        @Expose
        private String updated_by = null;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getCreated_at() {
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
    }
}
