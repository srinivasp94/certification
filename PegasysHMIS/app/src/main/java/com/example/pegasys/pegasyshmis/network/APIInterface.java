package com.example.pegasys.pegasyshmis.network;

import com.example.pegasys.pegasyshmis.model.LoginResponseModel;
import com.example.pegasys.pegasyshmis.model.PdfModel;
import com.example.pegasys.pegasyshmis.model.ResponseModel;
import com.example.pegasys.pegasyshmis.model.uploadDocModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * Created by sys on 12/6/2017.
 */

public interface APIInterface {
    @POST
    Call<Object> callPost(@Url String path, @Body Object o);

    @GET
    Call<Object> callGet(@Body Object o);

    @FormUrlEncoded
    @POST("pdfparser/api/MobileService/signupService")
    Call<ResponseModel> callSignup(@Field("firstname") String firstName,
                                   @Field("lastname") String lastName,
                                   @Field("username") String userName,
                                   @Field("email") String eMail,
                                   @Field("password") String password,
                                   @Field("deviceid") String deviceId,
                                   @Field("devicelocation") String deviceLocation);

    @FormUrlEncoded
    @POST("pdfparser/api/MobileService/loginService")
    Call<LoginResponseModel> callLogin(@Field("username") String userName,
                                       @Field("password") String Password);

    @FormUrlEncoded
    @POST("pdfparser/api/MobileService/userpinService")
    Call<ResponseModel> callupdatePin(@Field("userid") String userId,
                                      @Field("pin") String pin);

    @FormUrlEncoded
    @POST("pdfparser/api/MobileService/viewlistpdfs")
    Call<PdfModel> callviewlistpdfs(@Field("userid") String userId);

    @FormUrlEncoded
    @POST("pdfparser/api/MobileService/parsepdfservice")
    Call<uploadDocModel> callParserPdfs(@Field("userid") String userId,
                                  @Field("filename") String filename);

    @Multipart
    @POST("pdfparser/api/MobileService/pdffileupload")
    Call<uploadDocModel> uploadPDFfile(@Part("userid") RequestBody userid,
                                       @Part MultipartBody.Part file);

}
