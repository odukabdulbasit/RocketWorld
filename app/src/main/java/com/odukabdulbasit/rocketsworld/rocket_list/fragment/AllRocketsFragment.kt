package com.odukabdulbasit.rocketsworld.rocket_list.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.odukabdulbasit.rocketsworld.R
import com.odukabdulbasit.rocketsworld.api.AllRocketApi
import com.odukabdulbasit.rocketsworld.rocket_list.adapter.AdapterAllRocketsList
import com.odukabdulbasit.rocketsworld.rocket_list.model.AllRocketsProperties
import retrofit2.Call
import retrofit2.Response


class AllRocketsFragment : Fragment() {

    lateinit var myView: View
    var data: ArrayList<AllRocketsProperties> = ArrayList()
    lateinit var allrocketRcyclerView: RecyclerView
    lateinit var adapter: AdapterAllRocketsList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_all_rockets, container, false)

        allrocketRcyclerView = myView.findViewById(R.id.allRocketsRclrvList)
        getData()

        adapter =
            AdapterAllRocketsList(
                data
            )
        allrocketRcyclerView.adapter = adapter

        allrocketRcyclerView.addOnItemClickListener(object :
            OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                var id = data.get(position).id
                openNext(view,id)
            }
        })

        return myView
    }


    fun openNext(view: View,id:Int) {
        var bundle = bundleOf("id" to id)
        Navigation.findNavController(view)
            .navigate(R.id.action_allRocketsFragment_to_rocketDetailFragment, bundle)
    }

    fun getData() {
        data.clear()
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

                    for (item in response.body()!!) {
                        var body = response.body()
                        val name = item.rocket_name
                        val type = item.first_flight
                        val rocketId = item.rocket_id
                        val id = item.id
                        val description = item.description
                        val images = item.flickr_images

                        data.add(
                            AllRocketsProperties(
                                rocketId,
                                id!!,
                                name,
                                type,
                                description,
                                images
                            )
                        )
                        adapter.notifyDataSetChanged()
                    }
                }
            })
    }
}


interface OnItemClickListener {
    fun onItemClicked(position: Int, view: View)
}

fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
    this.addOnChildAttachStateChangeListener(object :
        RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewDetachedFromWindow(view: View) {
            view?.setOnClickListener(null)
        }

        override fun onChildViewAttachedToWindow(view: View) {

            view?.setOnClickListener{
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.adapterPosition, view)
            }
        }

    })
}