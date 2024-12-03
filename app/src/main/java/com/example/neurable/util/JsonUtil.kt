package com.example.neurable.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

object JsonUtil {
    fun <T> parseJsonToDataObject(jsonString: String, serializer: KSerializer<T>): List<T> {
        val json = Json { ignoreUnknownKeys = true }
        return json.decodeFromString(ListSerializer(serializer), jsonString)
    }
}