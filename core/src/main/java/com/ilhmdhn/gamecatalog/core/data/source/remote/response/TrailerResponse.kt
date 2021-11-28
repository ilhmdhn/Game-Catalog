package com.ilhmdhn.imageslider.model

import com.google.gson.annotations.SerializedName

data class TrailerResponse(

	@field:SerializedName("next")
	val next: Any,

	@field:SerializedName("previous")
	val previous: Any,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("results")
	val results: List<ResultsItemMovie>
)

data class DataMovie(

	@field:SerializedName("max")
	val max: String,

	@field:SerializedName("480")
	val jsonMember480: String
)

data class ResultsItemMovie(

	@field:SerializedName("preview")
	val preview: String,

	@field:SerializedName("data")
	val dataMovie: DataMovie,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)
