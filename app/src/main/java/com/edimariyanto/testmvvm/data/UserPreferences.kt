package com.edimariyanto.testmvvm.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(context: Context) {

    private val applicationContext = context.applicationContext
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


    val authToken: Flow<String?>
    get() = applicationContext.dataStore.data.map {
        it[KEY_AUTH]
    }

    suspend fun saveAuthToken(authToken: String){
        applicationContext.dataStore.edit {
            it[KEY_AUTH] = authToken
        }
    }

    suspend fun clear(){
        applicationContext.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object{
        private val KEY_AUTH = stringPreferencesKey("key_auth")
    }

}