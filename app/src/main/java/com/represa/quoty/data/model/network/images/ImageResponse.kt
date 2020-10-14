package com.represa.quoty.data.model.network.images

data class Image (
	var id : String,
	var urls : Urls,
)

data class ImageResponse (
	var images : ArrayList<Image>
)