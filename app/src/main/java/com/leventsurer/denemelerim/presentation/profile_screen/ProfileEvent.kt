package com.leventsurer.denemelerim.presentation.profile_screen



sealed class ProfileEvent{

    data class GetUserProfileInfo(val userUid:String):ProfileEvent()

}
