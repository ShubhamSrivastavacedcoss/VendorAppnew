package com.example.vendorappnew.models

data class GeneralInformation(
    val field_name: String,
    val field_to_post: String,
    val input_type: String,
    val is_required: String,
    val options: List<OptionX>,
    val saved_value: String,
    val type: String
)