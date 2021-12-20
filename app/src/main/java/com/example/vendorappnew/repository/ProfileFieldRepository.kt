package com.example.vendorappnew.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vendorappnew.api.EndPointInterface
import com.example.vendorappnew.models.ProfileFieldRequest
import com.example.vendorappnew.models.ProfileFieldsResponse
import retrofit2.http.Body

/*
class ProfileFieldRepository(private val endPointInterface: EndPointInterface) {

    var profileFiledMutableData= MutableLiveData<ProfileFieldsResponse>()

    val profileField: LiveData<ProfileFieldsResponse>
        get() = profileFiledMutableData


    suspend fun getProfileDetails(profileFieldRequest: ProfileFieldRequest){
        val result= endPointInterface.getProfileDetails(profileFieldRequest)

    }




}*/
