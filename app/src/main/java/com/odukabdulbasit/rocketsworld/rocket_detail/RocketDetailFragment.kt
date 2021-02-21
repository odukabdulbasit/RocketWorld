package com.odukabdulbasit.rocketsworld.rocket_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.odukabdulbasit.rocketsworld.R
import com.odukabdulbasit.rocketsworld.api.AllRocketApi
import com.odukabdulbasit.rocketsworld.rocket_list.model.AllRocketsProperties
import retrofit2.Call
import retrofit2.Response

class RocketDetailFragment : Fragment() {

    lateinit var myDetailView: View
    lateinit var detailNameTv: TextView
    lateinit var detaildescriptionTv: TextView
    lateinit var detailImageView: ImageView
    var id: Int? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myDetailView = inflater.inflate(R.layout.fragment_rocket_detail, container, false)

        id = arguments?.getInt("id")
        detailNameTv = myDetailView.findViewById(R.id.detailNameTv)
        detaildescriptionTv = myDetailView.findViewById(R.id.detailDescriptionTv)
        detailImageView = myDetailView.findViewById(R.id.detailImageView)
        getData()
        return myDetailView
    }

    fun getData() {

        AllRocketApi.RETROFIT_SERVICE_ALL.getProperties()
            .enqueue(object : retrofit2.Callback<List<AllRocketsProperties>> {

                override fun onFailure(call: Call<List<AllRocketsProperties>>, t: Throwable) {
                    Toast.makeText(
                        context!!.applicationContext,
                        "Failure: $t.message",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onResponse(
                    call: Call<List<AllRocketsProperties>>,
                    response: Response<List<AllRocketsProperties>>
                ) {
                    for (rocketItem in response.body()!!) {
                        if (rocketItem.id == id) {
                            setDetailData(rocketItem)
                        }
                    }

                }
            })
    }

    fun setDetailData(rocketItem: AllRocketsProperties) {
        var description = rocketItem?.description
        var rocket_name = rocketItem?.rocket_name
        var url = rocketItem?.flickr_images!!.get(0)
        detailNameTv.text = rocket_name
        detaildescriptionTv.text = description

        Glide.with(myDetailView.context)
            .load(url)
            .into(detailImageView)
    }
}
