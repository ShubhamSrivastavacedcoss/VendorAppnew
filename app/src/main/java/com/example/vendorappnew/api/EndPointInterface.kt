package com.example.vendorappnew.api

import com.example.vendorappnew.models.ProfileFieldRequest
import com.example.vendorappnew.models.ProfileFieldsResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface EndPointInterface {


    /*   @POST("/magento/dev/marketplace_plat/pub/vendorapi/index/getProfileFields")
       @Headers("Accept:application/json",
           "Content-Type:application/json")
       fun getProfileDetails(@Body requestBody: ProfileFieldRequest): Call<ProfileFieldsResponse>*/

    @FormUrlEncoded
    @POST("/magento/dev/marketplace_plat/pub/vendorapi/index/getProfileFields")
    fun getProfileDetails(
        @Field("hashkey") hashKey: String,
        @Field("vendor_id") vendor_id: Int,
    ): Call<ProfileFieldsResponse>
}