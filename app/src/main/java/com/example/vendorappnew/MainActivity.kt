package com.example.vendorappnew

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.vendorappnew.databinding.ActivityMainBinding
import com.example.vendorappnew.models.ProfileFieldsResponse
import com.example.vendorappnew.viewmodel.ProfileFieldViewModel
import kotlinx.android.synthetic.main.edittext_item.*
import android.widget.LinearLayout
import com.example.vendorappnew.models.GeneralInformation
import kotlinx.android.synthetic.main.image_item.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityMainBinding
    lateinit var viewModel:ProfileFieldViewModel
    lateinit var progressBar: ProgressDialog
    lateinit var type: String





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//     setContentView(R.layout.activity_main)

        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initViewModel()
       // Toast.makeText(applicationContext, "no", Toast.LENGTH_LONG).show()
       fetchData1()
      //fetchData2()

    }

    private fun fetchData1() {
        /* viewModel.getProfileFieldsData(profileFieldRequest =
         ProfileFieldRequest("rNRccT7K6NVaZ6wgvsuLEUMow22IPYqp", 1))*/
        progressBar = ProgressDialog.show(this, "", "Loading...")

        viewModel.getProfileFieldsData()

        viewModel.setProfileDetailsLiveData.observe(this, Observer {
            if (it != null) {

                if (it.data.success == true) {

                    progressBar.isShowing()




                    for (i in 0 until it.data.vendor_attributes.generalInformation.count()
                    ) {
                     //   type = it.data.vendor_attributes.generalInformation[i].type
                        if (it.data.vendor_attributes.generalInformation[i].type == "text") {


                                val hiddenInfo: View =
                                    layoutInflater.inflate(R.layout.edittext_item, null, false)

                                var etName:EditText= hiddenInfo.findViewById(R.id.et_name)
                                (etName as TextView).text =  it.data.vendor_attributes.generalInformation[i].saved_value


                                var tvName:TextView= hiddenInfo.findViewById(R.id.name_tv)
                                tvName.text =  it.data.vendor_attributes.generalInformation[i].field_name
                                dataBinding.linearLayout2?.addView(hiddenInfo)
                      //      }


                        } else if (it.data.vendor_attributes.generalInformation[i].type == "image") {

                            val view = LayoutInflater.from(this@MainActivity)
                                .inflate(R.layout.image_item, null, false)
                            val imgview = view.findViewById<ImageView>(R.id.profilepics_iv)
                            Glide.with(this@MainActivity)
                                .load( it.data.vendor_attributes.generalInformation[i].saved_value)
                                .placeholder(R.drawable.ic_launcher_foreground)
                                .error(R.drawable.ic_launcher_foreground)
                                .into(imgview)

                            view.findViewById<TextView>(R.id.profilepicc_tv).setText(
                                it.data.vendor_attributes.generalInformation[i].field_name
                            )
                            dataBinding.linearLayout2?.addView(view)

                        }

                        else if (it.data.vendor_attributes.generalInformation[i].type == "select") {
                            var categroy =
                                it.data.vendor_attributes.generalInformation[i].options
                             //   general_Information.getJSONObject(i).getJSONArray("options")
                            Log.v("spinner", "my data is" + categroy)


                            for (i in 0 until categroy.count()) {

                                   categroy.get(i).label
                                  //  categroy.getJSONObject(i).getString("label")



                                //Log.v("selgen", "is" + arraylist_genralinformation)
                            }

                            var view = LayoutInflater.from(this@MainActivity)
                                .inflate(R.layout.spinner_item, null, false)

                            val arrayAdapter = ArrayAdapter(
                                this@MainActivity,
                                android.R.layout.simple_spinner_dropdown_item,
                                categroy
                            )

                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                            view.findViewById<Spinner>(R.id.genderssSpinner)
                                .setAdapter(arrayAdapter)


                        /*    val arrayAdapterState =
                                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                                    it.data.vendor_attributes.addressInformation[3].options)

                            dataBinding.stateSpinner.adapter = arrayAdapterState*/



                            view.findViewById<TextView>(R.id.gendersstv).setText(
                                it.data.vendor_attributes.generalInformation[i].field_name
                            )

                            dataBinding.linearLayout2?.addView(view)

                            Log.d("spi", "is " + arrayAdapter)


                        }


                        /*else if (type.equals("select")) {
                            var categroy =
                                general_Information.getJSONObject(i).getJSONArray("options")
                            Log.v("spinner", "my data is" + categroy)

                            for (i in 0 until categroy.length()) {
                                arraylist_genralinformation.add(
                                    categroy.getJSONObject(i).getString("label")
                                )


                                Log.v("selgen", "is" + arraylist_genralinformation)
                            }

                            var view = LayoutInflater.from(this@MainActivity)
                                .inflate(R.layout.spinner_vendor, null, false)

                            val arrayAdapter = ArrayAdapter(
                                this@MainActivity,
                                android.R.layout.simple_spinner_item,
                                arraylist_genralinformation
                            )

                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                            view.findViewById<Spinner>(R.id.spiner_vendors)
                                .setAdapter(arrayAdapter)

                            parentLinearLayout?.addView(view)

                            Log.d("spi", "is " + arrayAdapter)


                        }*/

                       /* else if (type.equals("image")) {

                            val view = LayoutInflater.from(this@MainActivity)
                                .inflate(R.layout.vendor_images, null, false)
                            val imgview = view.findViewById<ImageView>(R.id.img_vendor)
                            Glide.with(this@MainActivity)
                                .load(general_Information.getJSONObject(i).getString("saved_value"))
                                .placeholder(R.drawable.ic_launcher_foreground)
                                .error(R.drawable.ic_launcher_foreground)
                                .into(imgview)

                            view.findViewById<TextView>(R.id.tv_img_vendor).setText(
                                general_Information.getJSONObject(i).getString("field_name")
                            )
                            parentLinearLayout?.addView(view)

                        }
                        else if (type.equals("select")) {
                            var categroy =
                                general_Information.getJSONObject(i).getJSONArray("options")
                            Log.v("spinner", "my data is" + categroy)

                            for (i in 0 until categroy.length()) {
                                arraylist_genralinformation.add(
                                    categroy.getJSONObject(i).getString("label")
                                )


                                Log.v("selgen", "is" + arraylist_genralinformation)
                            }

                            var view = LayoutInflater.from(this@MainActivity)
                                .inflate(R.layout.spinner_vendor, null, false)

                            val arrayAdapter = ArrayAdapter(
                                this@MainActivity,
                                android.R.layout.simple_spinner_item,
                                arraylist_genralinformation
                            )

                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                            view.findViewById<Spinner>(R.id.spiner_vendors)
                                .setAdapter(arrayAdapter)

                            parentLinearLayout?.addView(view)

                            Log.d("spi", "is " + arrayAdapter)


                        }*/ else {
/*
                          /*     Toast.makeText(this@MainActivity, "error show", Toast.LENGTH_LONG)
                                .show()*/*/
                        }
                    }




                    for (i in 0 until it.data.vendor_attributes.addressInformation.count()
                    ) {
                        //   type = it.data.vendor_attributes.generalInformation[i].type
                        if (it.data.vendor_attributes.addressInformation[i].type == "text") {


                            val hiddenInfo: View =
                                layoutInflater.inflate(R.layout.edittext_item, null, false)

                            var etName:EditText= hiddenInfo.findViewById(R.id.et_name)
                            (etName as TextView).text =  it.data.vendor_attributes.addressInformation[i].saved_value


                            var tvName:TextView= hiddenInfo.findViewById(R.id.name_tv)
                            tvName.text =  it.data.vendor_attributes.addressInformation[i].field_name
                            dataBinding.linearLayout2?.addView(hiddenInfo)
                            //      }


                        } else if (it.data.vendor_attributes.addressInformation[i].type == "image") {

                            val view = LayoutInflater.from(this@MainActivity)
                                .inflate(R.layout.image_item, null, false)
                            val imgview = view.findViewById<ImageView>(R.id.profilepics_iv)
                            Glide.with(this@MainActivity)
                                .load( it.data.vendor_attributes.addressInformation[i].saved_value)
                                .placeholder(R.drawable.ic_launcher_foreground)
                                .error(R.drawable.ic_launcher_foreground)
                                .into(imgview)

                            view.findViewById<TextView>(R.id.profilepicc_tv).setText(
                                it.data.vendor_attributes.addressInformation[i].field_name
                            )
                            dataBinding.linearLayout2?.addView(view)

                        }

                        else if (it.data.vendor_attributes.addressInformation[i].type == "select") {
                            var categroy =
                                it.data.vendor_attributes.addressInformation[i].options
                            //   general_Information.getJSONObject(i).getJSONArray("options")
                            Log.v("spinner", "my data is" + categroy)

                            /*  for (i in 0 until categroy.count()) {
                                  arraylist_genralinformation.add(
                                      categroy.getJSONObject(i).getString("label")
                                  )


                                  Log.v("selgen", "is" + arraylist_genralinformation)
                              }*/

                            var view = LayoutInflater.from(this@MainActivity)
                                .inflate(R.layout.spinner_item, null, false)

                            val arrayAdapter = ArrayAdapter(
                                this@MainActivity,
                                android.R.layout.simple_spinner_dropdown_item,
                                categroy
                            )

                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                            view.findViewById<Spinner>(R.id.genderssSpinner)
                                .setAdapter(arrayAdapter)


                            /*    val arrayAdapterState =
                                    ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                                        it.data.vendor_attributes.addressInformation[3].options)

                                dataBinding.stateSpinner.adapter = arrayAdapterState*/



                            view.findViewById<TextView>(R.id.gendersstv).setText(
                                it.data.vendor_attributes.addressInformation[i].field_name
                            )

                            dataBinding.linearLayout2?.addView(view)

                            Log.d("spi", "is " + arrayAdapter)


                        }

                        else {

                          /*     Toast.makeText(this@MainActivity, "error show", Toast.LENGTH_LONG)
                                .show()*/
                        }
                    }



                    for (i in 0 until it.data.vendor_attributes.companyInformation.count()
                    ) {
                        //   type = it.data.vendor_attributes.generalInformation[i].type
                        if (it.data.vendor_attributes.companyInformation[i].type == "text") {

                            /*  Toast.makeText(this@MainActivity, "xyz", Toast.LENGTH_LONG)
                                  .show()*/
                            //   val hiddenLayout = findViewById<View>(R.id.root_layoutt)
                            //     if (hiddenLayout == null) {


                            //Inflate the Hidden Layout Information View
                            // val myLayout = dataBinding.linearLayout2
                            val hiddenInfo: View =
                                layoutInflater.inflate(R.layout.edittext_item, null, false)

                            var etName:EditText= hiddenInfo.findViewById(R.id.et_name)
                            (etName as TextView).text =  it.data.vendor_attributes.companyInformation[i].saved_value


                            var tvName:TextView= hiddenInfo.findViewById(R.id.name_tv)
                            tvName.text =  it.data.vendor_attributes.companyInformation[i].field_name
                            dataBinding.linearLayout2?.addView(hiddenInfo)
                            //      }


                        } else if (it.data.vendor_attributes.companyInformation[i].type == "image") {

                            val view = LayoutInflater.from(this@MainActivity)
                                .inflate(R.layout.image_item, null, false)
                            val imgview = view.findViewById<ImageView>(R.id.profilepics_iv)
                            Glide.with(this@MainActivity)
                                .load( it.data.vendor_attributes.companyInformation[i].saved_value)
                                .placeholder(R.drawable.ic_launcher_foreground)
                                .error(R.drawable.ic_launcher_foreground)
                                .into(imgview)

                            view.findViewById<TextView>(R.id.profilepicc_tv).setText(
                                it.data.vendor_attributes.companyInformation[i].field_name
                            )
                            dataBinding.linearLayout2?.addView(view)

                        }



                        else {

                          /*     Toast.makeText(this@MainActivity, "error show", Toast.LENGTH_LONG)
                                .show()*/
                        }
                    }



                    for (i in 0 until it.data.vendor_attributes.supportInformation.count()
                    ) {
                        //   type = it.data.vendor_attributes.generalInformation[i].type
                        if (it.data.vendor_attributes.supportInformation[i].type == "text") {

                            /*  Toast.makeText(this@MainActivity, "xyz", Toast.LENGTH_LONG)
                                  .show()*/
                            //   val hiddenLayout = findViewById<View>(R.id.root_layoutt)
                            //     if (hiddenLayout == null) {


                            //Inflate the Hidden Layout Information View
                            // val myLayout = dataBinding.linearLayout2
                            val hiddenInfo: View =
                                layoutInflater.inflate(R.layout.edittext_item, null, false)

                            var etName:EditText= hiddenInfo.findViewById(R.id.et_name)
                            (etName as TextView).text =  it.data.vendor_attributes.supportInformation[i].saved_value


                            var tvName:TextView= hiddenInfo.findViewById(R.id.name_tv)
                            tvName.text =  it.data.vendor_attributes.supportInformation[i].field_name
                            dataBinding.linearLayout2?.addView(hiddenInfo)
                            //      }


                        } else if (it.data.vendor_attributes.supportInformation[i].type == "image") {

                            val view = LayoutInflater.from(this@MainActivity)
                                .inflate(R.layout.image_item, null, false)
                            val imgview = view.findViewById<ImageView>(R.id.profilepics_iv)
                            Glide.with(this@MainActivity)
                                .load( it.data.vendor_attributes.supportInformation[i].saved_value)
                                .placeholder(R.drawable.ic_launcher_foreground)
                                .error(R.drawable.ic_launcher_foreground)
                                .into(imgview)

                            view.findViewById<TextView>(R.id.profilepicc_tv).setText(
                                it.data.vendor_attributes.supportInformation[i].field_name
                            )
                            dataBinding.linearLayout2?.addView(view)

                        }


                        else {

                          /*     Toast.makeText(this@MainActivity, "error show", Toast.LENGTH_LONG)
                                .show()*/
                        }
                    }



                    for (i in 0 until it.data.vendor_attributes.sEOInformation.count()
                    ) {
                        //   type = it.data.vendor_attributes.generalInformation[i].type
                        if (it.data.vendor_attributes.sEOInformation[i].type == "text") {

                            /*  Toast.makeText(this@MainActivity, "xyz", Toast.LENGTH_LONG)
                                  .show()*/
                            //   val hiddenLayout = findViewById<View>(R.id.root_layoutt)
                            //     if (hiddenLayout == null) {


                            //Inflate the Hidden Layout Information View
                            // val myLayout = dataBinding.linearLayout2
                            val hiddenInfo: View =
                                layoutInflater.inflate(R.layout.edittext_item, null, false)

                            var etName:EditText= hiddenInfo.findViewById(R.id.et_name)
                            (etName as TextView).text =  it.data.vendor_attributes.sEOInformation[i].saved_value


                            var tvName:TextView= hiddenInfo.findViewById(R.id.name_tv)
                            tvName.text =  it.data.vendor_attributes.sEOInformation[i].field_name
                            dataBinding.linearLayout2?.addView(hiddenInfo)
                            //      }


                        } else if (it.data.vendor_attributes.sEOInformation[i].type == "image") {

                            val view = LayoutInflater.from(this@MainActivity)
                                .inflate(R.layout.image_item, null, false)
                            val imgview = view.findViewById<ImageView>(R.id.profilepics_iv)
                            Glide.with(this@MainActivity)
                                .load( it.data.vendor_attributes.sEOInformation[i].saved_value)
                                .placeholder(R.drawable.ic_launcher_foreground)
                                .error(R.drawable.ic_launcher_foreground)
                                .into(imgview)

                            view.findViewById<TextView>(R.id.profilepicc_tv).setText(
                                it.data.vendor_attributes.sEOInformation[i].field_name
                            )
                            dataBinding.linearLayout2?.addView(view)

                        }



                        else {

                          /*     Toast.makeText(this@MainActivity, "error show", Toast.LENGTH_LONG)
                                .show()*/
                        }
                    }

                    //Check if the Layout already exists



                /*    dataBinding.publicNametv.text =
                        it.data.vendor_attributes.generalInformation[0].field_name
                    dataBinding.nametv.text =
                        it.data.vendor_attributes.generalInformation[1].field_name
                    dataBinding.profilepicTv.text =
                        it.data.vendor_attributes.generalInformation[3].field_name
                    dataBinding.gendertv.text =
                        it.data.vendor_attributes.generalInformation[2].field_name
                    dataBinding.emailtv.text =
                        it.data.vendor_attributes.generalInformation[4].field_name
                    dataBinding.contacttv.text =
                        it.data.vendor_attributes.generalInformation[5].field_name
                    (dataBinding.etFirstName as TextView).text =
                        it.data.vendor_attributes.generalInformation[0].saved_value
                    (dataBinding.etLastName as TextView).text =
                        it.data.vendor_attributes.generalInformation[1].saved_value
                    //  (dataBinding.genderSpinner as Spinner).text= it.data.vendor_attributes.generalInformation[1].saved_value
                    (dataBinding.etEmailName as TextView).text =
                        it.data.vendor_attributes.generalInformation[4].saved_value
                    (dataBinding.etContactNumber as TextView).text =
                        it.data.vendor_attributes.generalInformation[5].saved_value
                    Glide.with(this)
                        .load(it.data.vendor_attributes.generalInformation[3].saved_value)
                        .into(dataBinding.profilepicIv)


                    //company info
                    dataBinding.companyNametv.text =
                        it.data.vendor_attributes.companyInformation[0].field_name
                    dataBinding.abouttv.text =
                        it.data.vendor_attributes.companyInformation[1].field_name
                    dataBinding.companylogoTv.text =
                        it.data.vendor_attributes.companyInformation[2].field_name
                    dataBinding.companybannerTv.text =
                        it.data.vendor_attributes.companyInformation[3].field_name
                    dataBinding.companyaddresstv.text =
                        it.data.vendor_attributes.companyInformation[4].field_name
                    (dataBinding.etCompanyName as TextView).text =
                        it.data.vendor_attributes.companyInformation[0].saved_value
                    (dataBinding.etAbout as TextView).text =
                        it.data.vendor_attributes.companyInformation[1].saved_value
                    (dataBinding.etCompanyaddress as TextView).text =
                        it.data.vendor_attributes.companyInformation[4].saved_value
                    Glide.with(this)
                        .load(it.data.vendor_attributes.companyInformation[2].saved_value)
                        .into(dataBinding.companylogoIv)
                    Glide.with(this)
                        .load(it.data.vendor_attributes.companyInformation[3].saved_value)
                        .into(dataBinding.companybannerIv)


                    //support info
                    dataBinding.supportnumbertv.text =
                        it.data.vendor_attributes.supportInformation[0].field_name
                    dataBinding.supportemailtv.text =
                        it.data.vendor_attributes.supportInformation[1].field_name
                    dataBinding.facebookidTv.text =
                        it.data.vendor_attributes.supportInformation[2].field_name
                    dataBinding.twitteridTv.text =
                        it.data.vendor_attributes.supportInformation[3].field_name
                    (dataBinding.etSupportnumber as TextView).text =
                        it.data.vendor_attributes.supportInformation[0].saved_value
                    (dataBinding.etSupportemail as TextView).text =
                        it.data.vendor_attributes.supportInformation[1].saved_value
                    (dataBinding.etFacebookid as TextView).text =
                        it.data.vendor_attributes.supportInformation[2].saved_value
                    (dataBinding.etTwitterid as TextView).text =
                        it.data.vendor_attributes.supportInformation[3].saved_value


                    //seo info
                    dataBinding.metakeywordtv.text =
                        it.data.vendor_attributes.sEOInformation[0].field_name
                    dataBinding.metadescriptionTv.text =
                        it.data.vendor_attributes.sEOInformation[1].field_name
                    (dataBinding.etMetakeyword as TextView).text =
                        it.data.vendor_attributes.sEOInformation[0].saved_value
                    (dataBinding.etMetadescription as TextView).text =
                        it.data.vendor_attributes.sEOInformation[1].saved_value

                    //address info
                    dataBinding.aaddresstv.text =
                        it.data.vendor_attributes.addressInformation[0].field_name
                    dataBinding.cityTv.text =
                        it.data.vendor_attributes.addressInformation[1].field_name
                    dataBinding.postalcodeTv.text =
                        it.data.vendor_attributes.addressInformation[2].field_name
                    dataBinding.statetv.text =
                        it.data.vendor_attributes.addressInformation[3].field_name
                    dataBinding.countrytv.text =
                        it.data.vendor_attributes.addressInformation[4].field_name
                    (dataBinding.etAaddress as TextView).text =
                        it.data.vendor_attributes.addressInformation[0].saved_value
                    (dataBinding.etCity as TextView).text =
                        it.data.vendor_attributes.addressInformation[1].saved_value
                    (dataBinding.etPostalcode as TextView).text =
                        it.data.vendor_attributes.addressInformation[2].saved_value


                    val arrayAdapterGender =
                        ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                            it.data.vendor_attributes.generalInformation[2].options)


                    *//* for (customer in it.data.vendor_attributes.generalInformation[2].options) {
                         if (customer.label.equals("label")) {

                             Toast.makeText(this,
                                 "key is " + it.data.success.toString(),
                                 Toast.LENGTH_LONG).show()
                         }
                     }*//*

                    dataBinding.genderSpinner.adapter = arrayAdapterGender


                    val arrayAdapterState =
                        ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                            it.data.vendor_attributes.addressInformation[3].options)

                    dataBinding.stateSpinner.adapter = arrayAdapterState

                    val arrayAdapterCountry =
                        ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                            it.data.vendor_attributes.addressInformation[4].options)

                    dataBinding.countrySpinner.adapter = arrayAdapterCountry


                    dataBinding.layoutallLl.setVisibility(View.GONE);*/
                    progressBar.dismiss();
                } else {
                    Toast.makeText(this,
                        "key is " + it.data.success.toString(),
                        Toast.LENGTH_LONG).show()
                }


            } else {
                Toast.makeText(this,
                    "no data",
                    Toast.LENGTH_LONG).show()
            }
        })


    }


    private fun fetchData2(){

        viewModel.getProfileFieldsData()

        viewModel.setProfileDetailsLiveData.observe(this, Observer {

            if (it != null) {

                if (it.data.success == true) {



                    for (i in 0 until it.data.vendor_attributes.addressInformation.count()
                    ) {
                        //   type = it.data.vendor_attributes.generalInformation[i].type
                        if (it.data.vendor_attributes.addressInformation[i].type == "text") {

                            /*  Toast.makeText(this@MainActivity, "xyz", Toast.LENGTH_LONG)
                                  .show()*/
                         //   val hiddenLayout = findViewById<View>(R.id.root_layoutt)
                         //   if (hiddenLayout == null) {


                                //Inflate the Hidden Layout Information View
                                // val myLayout = dataBinding.linearLayout2
                                val hiddenInfo: View =
                                    layoutInflater.inflate(R.layout.edittext_item, null, false)

                                var etName:EditText= hiddenInfo.findViewById(R.id.et_name)
                                (etName as TextView).text =  it.data.vendor_attributes.addressInformation[i].saved_value


                                var tvName:TextView= hiddenInfo.findViewById(R.id.name_tv)
                                tvName.text =  it.data.vendor_attributes.addressInformation[i].field_name
                                dataBinding.linearLayout2?.addView(hiddenInfo)
                      //      }


                        }
                        else if (it.data.vendor_attributes.addressInformation[i].type == "image") {

                            val view = LayoutInflater.from(this@MainActivity)
                                .inflate(R.layout.image_item, null, false)
                            val imgview = view.findViewById<ImageView>(R.id.profilepics_iv)
                            Glide.with(this@MainActivity)
                                .load( it.data.vendor_attributes.addressInformation[i].saved_value)
                                .placeholder(R.drawable.ic_launcher_foreground)
                                .error(R.drawable.ic_launcher_foreground)
                                .into(imgview)

                            view.findViewById<TextView>(R.id.profilepicc_tv).setText(
                                it.data.vendor_attributes.addressInformation[i].field_name
                            )
                            dataBinding.linearLayout2?.addView(view)

                        }

                        else if (it.data.vendor_attributes.addressInformation[i].type == "select") {
                            var categroy =
                                it.data.vendor_attributes.addressInformation[i].options
                            //   general_Information.getJSONObject(i).getJSONArray("options")
                            Log.v("spinner", "my data is" + categroy)

                            /*  for (i in 0 until categroy.count()) {
                                  arraylist_genralinformation.add(
                                      categroy.getJSONObject(i).getString("label")
                                  )


                                  Log.v("selgen", "is" + arraylist_genralinformation)
                              }*/

                            var view = LayoutInflater.from(this@MainActivity)
                                .inflate(R.layout.spinner_item, null, false)

                            val arrayAdapter = ArrayAdapter(
                                this@MainActivity,
                                android.R.layout.simple_spinner_dropdown_item,
                                categroy
                            )

                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                            view.findViewById<Spinner>(R.id.genderssSpinner)
                                .setAdapter(arrayAdapter)


                            /*    val arrayAdapterState =
                                    ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                                        it.data.vendor_attributes.addressInformation[3].options)

                                dataBinding.stateSpinner.adapter = arrayAdapterState*/



                            view.findViewById<TextView>(R.id.gendersstv).setText(
                                it.data.vendor_attributes.addressInformation[i].field_name
                            )

                            dataBinding.linearLayout2?.addView(view)

                            Log.d("spi", "is " + arrayAdapter)


                        }


                        /*else if (type.equals("select")) {
                            var categroy =
                                general_Information.getJSONObject(i).getJSONArray("options")
                            Log.v("spinner", "my data is" + categroy)

                            for (i in 0 until categroy.length()) {
                                arraylist_genralinformation.add(
                                    categroy.getJSONObject(i).getString("label")
                                )


                                Log.v("selgen", "is" + arraylist_genralinformation)
                            }

                            var view = LayoutInflater.from(this@MainActivity)
                                .inflate(R.layout.spinner_vendor, null, false)

                            val arrayAdapter = ArrayAdapter(
                                this@MainActivity,
                                android.R.layout.simple_spinner_item,
                                arraylist_genralinformation
                            )

                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                            view.findViewById<Spinner>(R.id.spiner_vendors)
                                .setAdapter(arrayAdapter)

                            parentLinearLayout?.addView(view)

                            Log.d("spi", "is " + arrayAdapter)


                        }*/

                        /* else if (type.equals("image")) {

                             val view = LayoutInflater.from(this@MainActivity)
                                 .inflate(R.layout.vendor_images, null, false)
                             val imgview = view.findViewById<ImageView>(R.id.img_vendor)
                             Glide.with(this@MainActivity)
                                 .load(general_Information.getJSONObject(i).getString("saved_value"))
                                 .placeholder(R.drawable.ic_launcher_foreground)
                                 .error(R.drawable.ic_launcher_foreground)
                                 .into(imgview)

                             view.findViewById<TextView>(R.id.tv_img_vendor).setText(
                                 general_Information.getJSONObject(i).getString("field_name")
                             )
                             parentLinearLayout?.addView(view)

                         }
                         else if (type.equals("select")) {
                             var categroy =
                                 general_Information.getJSONObject(i).getJSONArray("options")
                             Log.v("spinner", "my data is" + categroy)

                             for (i in 0 until categroy.length()) {
                                 arraylist_genralinformation.add(
                                     categroy.getJSONObject(i).getString("label")
                                 )


                                 Log.v("selgen", "is" + arraylist_genralinformation)
                             }

                             var view = LayoutInflater.from(this@MainActivity)
                                 .inflate(R.layout.spinner_vendor, null, false)

                             val arrayAdapter = ArrayAdapter(
                                 this@MainActivity,
                                 android.R.layout.simple_spinner_item,
                                 arraylist_genralinformation
                             )

                             arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                             view.findViewById<Spinner>(R.id.spiner_vendors)
                                 .setAdapter(arrayAdapter)

                             parentLinearLayout?.addView(view)

                             Log.d("spi", "is " + arrayAdapter)


                         }*/ else {

                          /*     Toast.makeText(this@MainActivity, "error show", Toast.LENGTH_LONG)
                                .show()*/
                        }
                    }


                }

            }

        })


    }


    private fun initViewModel() {

        viewModel = ViewModelProvider(this).get(ProfileFieldViewModel::class.java)

        viewModel.getProfileFieldsDataObserver().observe(this, Observer<ProfileFieldsResponse?> {
            if (it == null) {

                Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()

            }
        })
    }

}

/*  private fun fetchData(){
      viewModel.getProfileFieldsData()


      // Get the LayoutInflater from Context
      val layoutInflater: LayoutInflater = LayoutInflater.from(applicationContext)

      viewModel.setProfileDetailsLiveData.observe(this, Observer {
          if (it!= null){
              Toast.makeText(applicationContext, "yes", Toast.LENGTH_LONG).show()

          }
      })

      // Inflate the layout using LayoutInflater
      val view: View = layoutInflater.inflate(
          R.layout.edittext_item, // Custom view/ layout
          root_layout, // Root layout to attach the view
          false // Attach with root layout or not
      )
  }
*/
