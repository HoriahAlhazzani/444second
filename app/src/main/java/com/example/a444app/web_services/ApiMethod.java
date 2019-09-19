package com.example.a444app.web_services;

import com.example.a444app.web_services.objs.GeneralResponse;
import com.example.a444app.web_services.objs.UserResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiMethod {

    interface Methods{

        //todo check spelling after write php pages
        String LOGIN="login.php";
        String FORGOT="forget_password.php";
        String SIGNUP="signup.php";
        String PROFILE="get_profile.php";
        String LOGOUT="logout.php";

        String GET_LOCKERS="get_lockers.php";
        String LOCKER="locker.php"; // to get locker info(availability)
        String BOOK_LOCKER="book_locker.php";

    }//end inner interface


    //todo check request body

    @POST(Methods.LOGIN)
    @FormUrlEncoded
        //needed when post do not include file
    Call<UserResponse> getLoginRequest(@Field("email") String email,
                                       @Field("password") String password,
                                       @Field("language") String language,
                                       @Field("fb_token") String fbToken);

    @GET(Methods.FORGOT)
    Call<GeneralResponse> getForgetRequest(@Query("email") String email,
                                           @Query("language") String language);

    @Multipart
    @POST(Methods.SIGNUP)
    Call<UserResponse> getSignUpRequest(@Part("name") RequestBody name,
                                        @Part("email")RequestBody email,
                                        @Part("password")RequestBody password,
                                        @Part MultipartBody.Part file,
                                        @Part("language")RequestBody language,
                                        @Part("fb_token")RequestBody fbToken);


    @POST(Methods.PROFILE)
    @FormUrlEncoded
    Call<UserResponse> getProfileRequest(@Field("user_id") String userId);


    @GET(Methods.LOGOUT)
    Call<GeneralResponse> getLogoutRequest(
            @Query("user_id") String userId
    );



    //---------------- locker request

    //GET_LOCKERS , BOOK_LOCKER

}//end interface

