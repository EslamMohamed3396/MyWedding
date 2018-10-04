package com.example.eslam.mywedding.API.ApiInterFace;

import com.example.eslam.mywedding.Models.CarPreparation.CarPreparation;
import com.example.eslam.mywedding.Models.Cars.Cars;
import com.example.eslam.mywedding.Models.CarsDetails.CarDetials;
import com.example.eslam.mywedding.Models.Celebration.Celebrations;
import com.example.eslam.mywedding.Models.ContactUs;
import com.example.eslam.mywedding.Models.ContryModel.ContryModel;
import com.example.eslam.mywedding.Models.ForgetPassModel;
import com.example.eslam.mywedding.Models.HallsModels.ListHallsModles;
import com.example.eslam.mywedding.Models.Hotels.Hotels;
import com.example.eslam.mywedding.Models.Kitchen.Kitchen;
import com.example.eslam.mywedding.Models.LogInModel;
import com.example.eslam.mywedding.Models.Logout;
import com.example.eslam.mywedding.Models.MyReservation.ReservatioCancel;
import com.example.eslam.mywedding.Models.MyReservation.Reservatios;
import com.example.eslam.mywedding.Models.NearbyLocations.NearbyLocation;
import com.example.eslam.mywedding.Models.Offers.Offers;
import com.example.eslam.mywedding.Models.ProfileModle;
import com.example.eslam.mywedding.Models.Rests.ListRestsModles;
import com.example.eslam.mywedding.Models.Services.ListServciesModel;
import com.example.eslam.mywedding.Models.SignUpMessageModel;
import com.example.eslam.mywedding.Models.UpdateProfileModel;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    /*-------------------------------------------Countries---------------------------------------------*/

    String APP_ID = "App-Id: $2y$10$7PKgSEfvsGmH9FM0c2/qGe2Sxu2mEBBFPRI1aGZy62Hb6WqIYprFq";
    String USER_AGENT = "User-Agent: $2y$10$k3cq7kn8mkHkq5N9qIMmGurxg5NDhIAKqnio3.BwqpZXNhQC8jZmu";
    String ACCEPT = "Accept: application/json";

    @Headers({
            APP_ID,
            USER_AGENT
    })
    @GET("countries")
    Call<ContryModel> getCountryList();

    /*---------------------------------------------------Registers------------------------------------------------------*/
    @Headers({
            APP_ID,
            USER_AGENT
    })
    @Multipart
    @POST("register")
    Call<SignUpMessageModel> signUP(@Part("fname") RequestBody fname,
                                    @Part("lname") RequestBody lname,
                                    @Part("email") RequestBody email,
                                    @Part("password") RequestBody password,
                                    @Part("password_confirmation") RequestBody password_confirmation,
                                    @Part("phone") RequestBody phone,
                                    @Part("address") RequestBody address,
                                    @Part MultipartBody.Part image,
                                    @Part("country") RequestBody country);

    /*---------------------------------------------------Login------------------------------------------------------*/

    @Headers({
            APP_ID,
            USER_AGENT
    })
    @Multipart
    @POST("login")
    Call<LogInModel> logIn(@Part("email") RequestBody email,
                           @Part("password") RequestBody password);

    /*---------------------------------------------------ForgetPass------------------------------------------------------*/

    @Headers({
            APP_ID,
            USER_AGENT
    })
    @Multipart
    @POST("forget-password")
    Call<ForgetPassModel> forgetPass(@Part("email") RequestBody email);

    /*---------------------------------------------------GetProfile------------------------------------------------------*/
    @Headers({
            APP_ID,
            USER_AGENT,
            ACCEPT
    })
    @GET("profile")
    Call<ProfileModle> getProfilea(@Header("Authorization") String auth);

    /*---------------------------------------------------LogOut------------------------------------------------------*/
    @Headers({
            APP_ID,
            USER_AGENT,
    })
    @GET("logout")
    Call<Logout> logOut();

    /*---------------------------------------------------UpdateProfile------------------------------------------------------*/
    @Headers({APP_ID,
            USER_AGENT,
            ACCEPT})
    @Multipart
    @POST("update-profile")
    Call<UpdateProfileModel> update(
            @Header("Authorization") String auth,
            @Query("fname") String fname,
            @Query("lname") String lname,
            @Query("address") String address,
            @Query("phone") String phone,
            @Part MultipartBody.Part image,
            @Query("country") Integer country
    );

    /*---------------------------------------------------ListOffers------------------------------------------------------*/
    @Headers({
            APP_ID,
            USER_AGENT,
    })
    @GET("offers")
    Call<Offers> getListOffers();

    /*---------------------------------------------------ListHalls------------------------------------------------------*/
    @Headers({
            APP_ID,
            USER_AGENT,
    })
    @GET("listings/halls")
    Call<ListHallsModles> getListHalls();

    /*---------------------------------------------------ListRests------------------------------------------------------*/
    @Headers({
            APP_ID,
            USER_AGENT,
    })
    @GET("listings/resting")
    Call<ListRestsModles> getRestsHalls();

    /*---------------------------------------------------ListServices------------------------------------------------------*/
    @Headers({
            APP_ID,
            USER_AGENT,
    })
    @GET("listings/services")
    Call<ListServciesModel> getServices();

    /*---------------------------------------------------celebration------------------------------------------------------*/
    @Headers({
            APP_ID,
            USER_AGENT,
    })
    @GET("celebrations")
    Call<Celebrations> getCelebrations();

    /*---------------------------------------------------Cars------------------------------------------------------*/
    @Headers({
            APP_ID,
            USER_AGENT,
    })
    @GET("cars/brands")
    Call<Cars> getCars();

    /*---------------------------------------------------Cars Details------------------------------------------------------*/
    @Headers({
            APP_ID,
            USER_AGENT,
    })
    @GET("cars/brand/{id}")
    Call<CarDetials> getCarsbyId(@Path("id") String id);

    /*---------------------------------------------------CarsPreparation------------------------------------------------------*/
    @Headers({
            APP_ID,
            USER_AGENT,
    })
    @GET("preparation/cars")
    Call<CarPreparation> getCarsPreparation();

    /*---------------------------------------------------Services---------------------------------------------------------------------*/

    @Headers({
            APP_ID,
            USER_AGENT,
    })
    @GET("services")
    Call<ListServciesModel> getServicesToBooking();
    /*------------------------------------------------------------------------Hotels----------------------------------------------------*/

    @Headers({
            APP_ID,
            USER_AGENT,
    })
    @GET("hotels")
    Call<Hotels> getHotels();

    /*-------------------------------------------Contact Us---------------------------------------------*/

    @Headers({
            APP_ID,
            USER_AGENT,
    })
    @Multipart
    @POST("contact-us")
    Call<ContactUs> contactUs(@Part("fname") RequestBody fname,
                              @Part("lname") RequestBody lname,
                              @Part("email") RequestBody email,
                              @Part("phone") RequestBody phone,
                              @Part("message") RequestBody message);
    /*-------------------------------------------myReservation---------------------------------------------*/

    @Headers({
            APP_ID,
            USER_AGENT,
    })
    @GET("my_reservation")
    Call<Reservatios> myReservation(@Header("Authorization") String auth);
    /*-------------------------------------------ReservationCancel---------------------------------------------*/

    @Headers({
            APP_ID,
            USER_AGENT,
    })
    @Multipart
    @POST("reservation-cancel")
    Call<ReservatioCancel> reservationCancel(@Header("Authorization") String auth, @Part("id") int id);

    /*-------------------------------------------NearbyLocations---------------------------------------------*/

    @Headers({
            APP_ID,
            USER_AGENT,
    })
    @POST("nearby")
    Call<NearbyLocation> getNearByLocation(@Body HashMap<String, String> body);

    /*-------------------------------------------Reservation---------------------------------------------*/

    @Headers({
            APP_ID,
            USER_AGENT,
            ACCEPT
    })
    @POST("reservation")
    Call<UpdateProfileModel> makeReservationHalls(@Header("Authorization") String auth
            , @Query("date") String date
            , @Query("time") String time
            , @Query("hall") Integer hallId
            , @Query("quantity") Integer quantity
            , @Query("celebration") Integer celebration
            , @Query("guest") Integer guest
            , @Query("services") String services
            , @Query("kitchens") String kitchens
            , @Query("car") Integer car
            , @Query("preparation") Integer preparation
            , @Query("hotel") Integer hotel
            , @Query("days") Integer days
            , @Query("country") Integer countryId);
    /*-------------------------------------------ReservationOffers---------------------------------------------*/

    @Headers({
            APP_ID,
            USER_AGENT,
            ACCEPT
    })
    @POST("reservation")
    Call<UpdateProfileModel> makeReservationOffers(@Header("Authorization") String auth
            , @Query("date") String date
            , @Query("time") String time
            , @Query("offer") Integer offerId
            , @Query("quantity") Integer quantity
            , @Query("celebration") Integer celebration
            , @Query("guest") Integer guest
            , @Query("services") String services
            , @Query("kitchens") String kitchens
            , @Query("car") Integer car
            , @Query("preparation") Integer preparation
            , @Query("hotel") Integer hotel
            , @Query("days") Integer days
            , @Query("country") Integer countryId);
    /*-------------------------------------------ReservationResting---------------------------------------------*/

    @Headers({
            APP_ID,
            USER_AGENT,
            ACCEPT
    })
    @POST("reservation")
    Call<UpdateProfileModel> makeReservationResting(@Header("Authorization") String auth
            , @Query("date") String date
            , @Query("time") String time
            , @Query("resting") Integer restingId
            , @Query("quantity") Integer quantity
            , @Query("celebration") Integer celebration
            , @Query("guest") Integer guest
            , @Query("services") String services
            , @Query("kitchens") String kitchens
            , @Query("car") Integer car
            , @Query("preparation") Integer preparation
            , @Query("hotel") Integer hotel
            , @Query("days") Integer days
            , @Query("country") Integer countryId);
    /*-------------------------------------------ReservationServices---------------------------------------------*/

    @Headers({
            APP_ID,
            USER_AGENT,
            ACCEPT
    })
    @POST("services-reservation")
    Call<UpdateProfileModel> makeReservationServices(@Header("Authorization") String auth
            , @Query("date") String date
            , @Query("time") String time
            , @Query("service_id") Integer restingId);
    /*-------------------------------------------kitchens---------------------------------------------*/

    @Headers({
            APP_ID,
            USER_AGENT,
    })
    @GET("kitchens")
    Call<Kitchen> getKitchens();
}
