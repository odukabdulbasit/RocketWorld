package com.odukabdulbasit.rocketsworld.rocket_list.model

data class AllRocketsProperties(
    val rocket_id : String,
    val id : Int,
    val rocket_name : String,
    val first_flight : String,
    val description : String,
    val flickr_images : List<String>
)