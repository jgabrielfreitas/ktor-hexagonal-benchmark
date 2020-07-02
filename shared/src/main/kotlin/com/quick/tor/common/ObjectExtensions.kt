package com.quick.tor.common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun Any.toJson(): String = Gson().toJson(this)
fun <T> Gson.fromJson(json: String): T = this.fromJson<T>(json, object : TypeToken<T>() {}.type)
fun <T> Gson.toCommnad(json: String): T = this.fromJson<T>(json, object : TypeToken<T>() {}.type)
