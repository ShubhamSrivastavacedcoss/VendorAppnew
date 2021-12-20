package com.example.vendorappnew.models

import com.google.gson.annotations.SerializedName

data class VendorAttributes(
    @SerializedName("Address Information")
    val addressInformation: List<AddressInformation>,
    @SerializedName("Company Information")
    val companyInformation: List<CompanyInformation>,
    @SerializedName("General Information")
    val generalInformation: List<GeneralInformation>,
    @SerializedName("SEO Information")
    val sEOInformation: List<SEOInformation>,
    @SerializedName("Support Information")
    val supportInformation: List<SupportInformation>
)