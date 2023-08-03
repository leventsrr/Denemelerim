package com.leventsurer.denemelerim.domain.repository

interface SetTargetRepository {

    suspend fun setTarget(university:String,department:String):Boolean
}