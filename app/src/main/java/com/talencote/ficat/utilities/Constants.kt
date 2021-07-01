package com.talencote.ficat.utilities

object Constants {

    const val BASE_URL = "http://10.0.2.2:8080/api/"
    const val LOGIN_URL = "auth/signin"
    const val REG_URL = "auth/signup"
    const val ADD_FAVORITE_FANDOM = "profile/add_favorite_fandom"
    const val REMOVE_FAVORITE_FANDOM = "profile/remove_favorite_fandom"
    const val GET_POPULAR_FANFICS = "fanfic/popular/{page}"
    const val GET_FANFIC = "fanfic/findById/{id}"
    const val TEST = "profile/test_token"
    const val GET_FANDOMS_STRING = "profile/{id}/favorite_fandoms_string"
    const val POST_FANDOMS_STRING = "profile/{id}/favorite_fandoms_string/{fandoms}"
}