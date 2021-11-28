package com.ilhmdhn.imageslider.model

import com.google.gson.annotations.SerializedName

data class ImageResponse(

	@field:SerializedName("next")
	val next: Any,

	@field:SerializedName("previous")
	val previous: Any,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("results")
	val results: List<ResultsImageItem>
)

data class ResultsImageItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("is_deleted")
	val isDeleted: Boolean,

	@field:SerializedName("width")
	val width: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("height")
	val height: Int
)
