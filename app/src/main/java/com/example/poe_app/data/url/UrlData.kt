package com.example.poe_app.data.url


import com.example.poe_app.data.url.models.Mission
import com.example.poe_app.data.url.models.Title
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.CopyOnWriteArrayList
import java.util.regex.Pattern
import javax.net.ssl.HttpsURLConnection

object UrlData {


    private const val POE_URL = "https://%s.pathofexile.com/account/view-profile/%s/challenges"
    private const val POE_URL_EN = "https://www.pathofexile.com/account/view-profile/%s/challenges"


    val titles: MutableList<Title> = CopyOnWriteArrayList()
    val missions: MutableList<Mission> = CopyOnWriteArrayList()

     suspend fun getData(userName: String, language: String) = coroutineScope{
         withContext(Dispatchers.IO) {
             val content = async {
                 val builder = StringBuilder()
                 val url = URL(String.format(POE_URL, language, userName))
                 val connection = url.openConnection() as HttpsURLConnection
                 try {
                     connection.inputStream.bufferedReader().use { reader ->
                         var line = reader.readLine()
                         while (line != null) {
                             builder.append(line)
                             line = reader.readLine()
                         }
                     }
                 } catch (e: MalformedURLException) {
                     e.printStackTrace()
                 } catch (e: IOException) {
                     e.printStackTrace()
                 } finally {
                     connection.disconnect()
                 }
                 return@async builder.toString()
             }.await()

             val splitContent = async {
                 val start = "<div class=\"achievement-list\">"
                 val end = "<div class=\"profile-details\">"
                 val pattern: Pattern = Pattern.compile("$start(.*?)$end")
                 val matcher = pattern.matcher(content)
                 var split = ""
                 while (matcher.find()) {
                     split = matcher.group(1)!!
                 }
                 return@async split
             }.await()

             async {
                 val titlePattern = Pattern.compile(
                     "(?:clearfix(| incomplete)?\".*?\"></a>.+?<h2 class=.+?>(.+?)<.*?completion\")",
                     Pattern.DOTALL
                 )
                 val titleMatcher = titlePattern.matcher(splitContent)
                 var titleId = 0
                 var missionId = 0
                 while (titleMatcher.find()) {

                     //ОПИСАНИЕ
                     val descriptionPattern = Pattern.compile("<span class=\"text\">(.*?)</span>")
                     val descriptionMatcher = descriptionPattern.matcher(titleMatcher.group())
                     while (descriptionMatcher.find()) {

                         //ТАЙТЛЫ
                         val completed = titleMatcher.group(1) != " incomplete"
                         titles.add(Title(
                             titleId,
                             titleMatcher.group(2)!!,
                             descriptionMatcher.group(1)!!,
                             completed
                         ))
                     }

                     //МИССИИ
                     val missionPattern = Pattern.compile("<li class=\"(|finished)?\">(.+?)</li>")
                     val missionMatcher = missionPattern.matcher(titleMatcher.group())
                     while (missionMatcher.find()) {
                         val isClicked = missionMatcher.group(1) == "finished"
                         missions.add(Mission(missionId, missionMatcher.group(2)!!, isClicked, titleId))
                         missionId++
                     }

                     titleId++
                 }
             }.await()

         }
     }
}