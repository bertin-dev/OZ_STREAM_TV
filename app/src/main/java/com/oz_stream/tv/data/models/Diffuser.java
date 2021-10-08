package com.oz_stream.tv.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Diffuser implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;

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

    @SerializedName("fcm-token")
    @Expose
    private String fcmToken = null;

    @SerializedName("AvatarLink")
    @Expose
    private String AvatarLink = null;
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


    protected Diffuser(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        firstName = in.readString();
        lastName = in.readString();
        sex = in.readString();
        birthDate = in.readString();
        email = in.readString();
        email_verified_at = in.readString();
        phone = in.readString();
        cardNumber = in.readString();
        credit = in.readString();
        maxDevice = in.readString();
        tokenSpace = in.readString();
        fcmToken = in.readString();
        AvatarLink = in.readString();
        referent = in.readString();
        accountStatus = in.readString();
        avatarLink = in.readString();
        cover = in.readString();
        bibliographie = in.readString();
        update_role_at = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        language_id = in.readString();
        country_id = in.readString();
        town_id = in.readString();
        role_id = in.readString();
        role_given_by = in.readString();
        created_by = in.readString();
        updated_by = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(sex);
        dest.writeString(birthDate);
        dest.writeString(email);
        dest.writeString(email_verified_at);
        dest.writeString(phone);
        dest.writeString(cardNumber);
        dest.writeString(credit);
        dest.writeString(maxDevice);
        dest.writeString(tokenSpace);
        dest.writeString(fcmToken);
        dest.writeString(AvatarLink);
        dest.writeString(referent);
        dest.writeString(accountStatus);
        dest.writeString(avatarLink);
        dest.writeString(cover);
        dest.writeString(bibliographie);
        dest.writeString(update_role_at);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeString(language_id);
        dest.writeString(country_id);
        dest.writeString(town_id);
        dest.writeString(role_id);
        dest.writeString(role_given_by);
        dest.writeString(created_by);
        dest.writeString(updated_by);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Diffuser> CREATOR = new Creator<Diffuser>() {
        @Override
        public Diffuser createFromParcel(Parcel in) {
            return new Diffuser(in);
        }

        @Override
        public Diffuser[] newArray(int size) {
            return new Diffuser[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getAvatarLink() {
        return AvatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        AvatarLink = avatarLink;
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

    public static Creator<Diffuser> getCREATOR() {
        return CREATOR;
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
}
