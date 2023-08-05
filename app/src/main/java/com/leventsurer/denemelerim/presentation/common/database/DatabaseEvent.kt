package com.leventsurer.denemelerim.presentation.common.database






sealed class DatabaseEvent{

    data class AddNewUser(val userUid:String):DatabaseEvent()

}