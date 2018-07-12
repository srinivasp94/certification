package com.srinivas.com.distrct.network;

import com.srinivas.com.distrct.models.Categories;
import com.srinivas.com.distrct.models.Mandals;
import com.srinivas.com.distrct.models.SubCategoriesModel;
import com.srinivas.com.distrct.models.Villages;
import com.srinivas.com.distrct.models.educationModels;
import com.srinivas.com.distrct.models.simpleResponse;
import com.srinivas.com.distrct.models.videomodels;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by rk on 03-Jun-18.
 */

public interface ApiInterface {

    /*@FormUrlEncoded
    @Headers({"Content-Type: application/json"})
    @POST("User/Add")
    Call<simpleResponse> registerCall(@Field("Title") String title,
                                      @Field("Name") String name,
                                      @Field("Phonenumber") String phone,
                                      @Field("Landmark") String Landmark,
                                      @Field("Category") int category,
                                      @Field("SubCategory") int subcat,
                                      @Field("Mandal") int mandal,
                                      @Field("Image") String imagePath,
                                      @Field("CreatedBy") int createdby,
                                      @Field("ModifiedBY") int modifiedby,
                                      @Field("Address") String address
    );*/
    @Multipart
//    @Headers({"Content-Type: application/json"})
    @POST("User/Add")
    Call<simpleResponse> registerCall(@Part("Title") String title,
                                      @Part("Name") String name,
                                      @Part("Phonenumber") String phone,
                                      @Part("Landmark") String Landmark,
                                      @Part("Category") int category,
                                      @Part("SubCategory") int subcat,
                                      @Part("Mandal") int mandal,
                                      @Part("Village") int village,
                                      @Part("Image") String imagePath,
                                      @Part("CreatedBy") int createdby,
                                      @Part("ModifiedBY") int modifiedby,
                                      @Part("Address") String address
    );

    @GET("Master/GetVillages/1")
    Call<List<Villages>> villagesCall();


    @GET("Master/GetCategory")
    Call<List<Categories>> getCategories();

    @GET("Master/GetSubCategory/1")
    Call<List<SubCategoriesModel>> getSubcategories();

    @GET("Master/GetMandal")
    Call<List<Mandals>> getMandal();

    @GET("Master/getAllCategories/education")
    Call<List<educationModels>> getedu();

    @GET("Master/getAllCategories/entertainment")
    Call<List<educationModels>> getentertainment();

    @GET("Master/GetVideos")
    Call<videomodels> getvideolist();

    /*/entertainment
    /traveltoursim
/consruction
/shopping
/hospitals
/fooddining
/automobiles
/eletrical
/homegarden
/helpline
/service
/beautySpa*/

    @GET("Master/getAllCategories/traveltoursim")
    Call<List<educationModels>> gettravel();

    @GET("Master/getAllCategories/consruction")
    Call<List<educationModels>> getconstruction();

    @GET("Master/getAllCategories/shopping")
    Call<List<educationModels>> getshopping();

    @GET("Master/getAllCategories/hospitals")
    Call<List<educationModels>> gethospitals();

    @GET("Master/getAllCategories/fooddining")
    Call<List<educationModels>> getfooddining();

    @GET("Master/getAllCategories/automobiles")
    Call<List<educationModels>> getautomobiles();

    @GET("Master/getAllCategories/eletrical")
    Call<List<educationModels>> geteletrical();

    @GET("Master/getAllCategories/homegarden")
    Call<List<educationModels>> gethomegarden();

    @GET("Master/getAllCategories/helpline")
    Call<List<educationModels>> gethelpline();

    @GET("Master/getAllCategories/service")
    Call<List<educationModels>> getservice();

    @GET("Master/getAllCategories/beautySpa")
    Call<List<educationModels>> getbeautySpa();


}
