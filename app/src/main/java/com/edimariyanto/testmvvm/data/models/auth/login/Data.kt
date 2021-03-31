package com.edimariyanto.testmvvm.data.models.auth.login

data class Data(
    val access_token: String,
    val access_token_expired: Int,
    val condition: List<Any>,
    val default_menu_mobile: String,
    val detail_site: List<Any>,
    val language: Any,
    val max_meter_radius: Int,
    val menu: List<Menu>,
    val mobile_ar_detail_item_image_compression: Int,
    val mobile_ar_detail_item_image_max_height: Int,
    val mobile_ar_detail_item_image_max_qty: Int,
    val mobile_ar_detail_item_image_max_width: Int,
    val privilege_edit: List<Any>,
    val privilege_movement: List<Any>,
    val privilege_so: List<Any>,
    val privilege_view: List<Any>,
    val profile_url: Any,
    val real_name: String,
    val refresh_token: String,
    val refresh_token_expired: Int,
    val sub_location: List<Any>,
    val type_token: String,
    val user_group_id: String,
    val user_group_name: String,
    val user_id: String,
    val user_name: String
)