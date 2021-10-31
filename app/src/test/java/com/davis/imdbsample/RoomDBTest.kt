package com.davis.imdbsample

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.davis.imdbsample.mvp.db.AppDatabase
import com.davis.imdbsample.mvp.db.MostPopularDao
import com.davis.imdbsample.mvp.model.MostPopularData
import com.google.gson.Gson
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RoomDBTest {
    private var mostPopularDao: MostPopularDao? = null
    private var db: AppDatabase? = null

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries().build()
        mostPopularDao = db!!.mostPopularDao();
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db!!.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        var sample = MostPopularData();
        var list = ArrayList<MostPopularData.MostPopularDataDetail>()
        var item = MostPopularData.MostPopularDataDetail()
        var jsonAsString = "{\"id\":\"tt7740510\",\"title\":\"Antlers\",\"fullTitle\":\"Antlers (2021)\",\"year\":\"2021\",\"releaseState\":\"Opening This Week - October 29\",\"image\":\"https://m.media-amazon.com/images/M/MV5BY2UzODAyNjktN2MwYy00M2RkLThiOTEtMjU1MTgxY2EzM2YyXkEyXkFqcGdeQXVyODk5MDA0MDU@._V1_UX128_CR0,4,128,176_AL_.jpg\",\"runtimeMins\":\"99\",\"runtimeStr\":\"1h 39mins\",\"plot\":\"In an isolated Oregon town, a middle-school teacher and her sheriff brother become embroiled with her enigmatic student, whose dark secrets lead to terrifying encounters with a legendary ancestral creature who came before them.\",\"contentRating\":\"R\",\"imDbRating\":\"\",\"imDbRatingCount\":\"\",\"metacriticRating\":\"58\",\"genres\":\"Drama, Horror, Mystery\",\"genreList\":[{\"key\":\"Drama\",\"value\":\"Drama\"},{\"key\":\"Horror\",\"value\":\"Horror\"},{\"key\":\"Mystery\",\"value\":\"Mystery\"}],\"directors\":\"Scott Cooper\",\"directorList\":[{\"id\":\"nm0178376\",\"name\":\"Scott Cooper\"}],\"stars\":\"Keri Russell, Jesse Plemons, Jeremy T. Thomas, Graham Greene\",\"starList\":[{\"id\":\"nm0005392\",\"name\":\"Keri Russell\"},{\"id\":\"nm0687146\",\"name\":\"Jesse Plemons\"},{\"id\":\"nm8864596\",\"name\":\"Jeremy T. Thomas\"},{\"id\":\"nm0001295\",\"name\":\"Graham Greene\"}]}"
        var gson =Gson().fromJson<MostPopularData.MostPopularDataDetail>(jsonAsString,MostPopularData.MostPopularDataDetail::class.java)
        item = gson
        list.add(item)
        sample.ErrorMessage=""
        sample.Items = list
        mostPopularDao!!.insertAll(sample.Items)
        val byName  = mostPopularDao!!.findByName("Antlers","Antlers (2021)")
        println("items ${mostPopularDao!!.countMostPopular()}")
        println(byName)
        println(item)
        assertThat(byName.toString(), equalTo(item.toString()))
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}