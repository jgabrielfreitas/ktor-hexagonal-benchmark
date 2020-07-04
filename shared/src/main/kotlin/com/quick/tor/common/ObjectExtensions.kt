package com.quick.tor.common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun Any.toJson(): String = Gson().toJson(this)
inline fun<reified T> String.fromJson(): T = Gson().fromJson<T>(this, object: TypeToken<T>() {}.type)
