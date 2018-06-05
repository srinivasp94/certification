package com.srinivas.com.distrct.network;

import com.srinivas.com.distrct.models.Categories;
import com.srinivas.com.distrct.models.Mandals;
import com.srinivas.com.distrct.models.SubCategoriesModel;
import com.srinivas.com.distrct.models.simpleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by rk on 03-Jun-18.
 */

public interface ApiInterface {
    @FormUrlEncoded
    @POST("User/Add")
    Call<simpleResponse> registerCall(@Field("Title") String title,
                                      @Field("Name") String name,
                                      @Field("Phonenumber") String phone,
                                      @Field("Landmark") String Landmark,
                                      @Field("Category") int category ,
                                      @Field("SubCategory") int subcat ,
                                      @Field("Mandal") int mandal,
                                      @Field("Image") String imagePath,
                                      @Field("CreatedBy") int createdby,
                                      @Field("ModifiedBY") int modifiedby,
                                      @Field("Address") String address
                                      );


    @GET("Master/GetCategory")
    Call<List<Categories>> getCategories();

    @GET("Master/GetSubCategory/1")
    Call<List<SubCategoriesModel>> getSubcategories();

    @GET("Master/GetMandal")
    Call<List<Mandals>> getMandal();

}
