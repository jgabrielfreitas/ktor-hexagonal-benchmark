package com.quick.tor.infrastructure.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun <T> Gson.fromJson(json: String): T = this.fromJson<T>(json, object : TypeToken<T>() {}.type)
fun <T> Gson.toCommnad(json: String): T = this.fromJson<T>(json, object : TypeToken<T>() {}.type)
