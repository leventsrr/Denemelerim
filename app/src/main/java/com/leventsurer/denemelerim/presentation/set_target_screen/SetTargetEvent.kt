package com.leventsurer.denemelerim.presentation.set_target_screen



sealed class SetTargetEvent{

    data class SetTarget(val university:String,val department:String,val userUid:String):SetTargetEvent()

}
