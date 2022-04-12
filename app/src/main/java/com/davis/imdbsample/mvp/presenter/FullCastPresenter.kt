package com.davis.imdbsample.mvp.presenter

import android.util.Log
import com.davis.imdbsample.mvp.model.RequestModel
import com.davis.imdbsample.mvp.model.ResponseListener
import com.davis.imdbsample.mvp.model.Top250Data
import com.davis.imdbsample.mvp.view.MoviesContract
import com.davis.imdbsample.mvp.view.MoviesContract.presenter
import com.davis.imdbsample.mvp.view.MoviesContract.view
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class FullCastPresenter(var requestModel: RequestModel, var view: MoviesContract.fullCastView) : presenter {
    var endpoints= listOf<String>("FullCast")
    override fun getData(params: String) {
        val responseListener: ResponseListener = object : ResponseListener {
            override fun onSuccess(jsonObject: Any) {
                Log.d("jsonError", "presenter " + jsonObject)

                try {
//                    val accounts: List<BankAccount> = gson.fromJson<List<BankAccount>>(accountsListString, object : TypeToken<ArrayList<BankAccount?>?>() {}.type)
                    val jsonObject1 = JSONObject(jsonObject.toString())
                    var items = jsonObject1.getJSONArray("items")
                    var fullresponse = Gson().fromJson<Top250Data>(jsonObject.toString(), Top250Data::class.java)
                    Log.d("jsonResponse"," ${fullresponse.Items.get(0).toString()}")
                    when(params){
                        endpoints[0] -> {
                            for (i in 0 until items.length()) {
                                var singleString = items.optJSONObject(i)
                                var jsonObj = Gson().fromJson<Top250Data.Top250DataDetail>(singleString.toString(), Top250Data.Top250DataDetail::class.java)

                            }
                            //view.onCastLoaded(jsonObject1)
                        }
                    }
                } catch (e: JSONException) {
                    Log.d("jsonError", "presenter " + e.toString())
                }
            }

            override fun onError(jsonObject: String) {
                view.onError(jsonObject)
            }
        }
        requestModel.makeRequest(params, responseListener)
    }
}