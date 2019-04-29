package models

import java.io.FileReader
import play.api.libs.json._


import com.opencsv.CSVReader


/**
  * ArticleReader is the utility object to collect all the funtions to handling ArticleEntry
  */

object ArticleReader {

  /**
    * This is help method takes a file path string to read a csv file and parse it and return a list of Array[String]
    * @param csvfilePath the relative file path to csv file
    * @return  List[Array[String]]
    */
  def getList ( csvfilePath: String): List[Array[String]] = {
    val reader = new CSVReader(new FileReader(csvfilePath), ',', '\"', 1 )
    val records = reader.readAll()
    records.asInstanceOf[List[Array[String]]]
  }

  /**
    * This method takes a file path string to read a csv file and parse it and return a list of Array[String]
    * @param csvfilePath the relative file path to csv file
    * @return  List[ArticleEntry]
    */

  def getArticles ( csvfilePath: String): List[ArticleEntry] = {
    val articleList = getList(csvfilePath)
    val articles = articleList.map(line => ArticleEntry(line(0), line(1), line(2), line(3), line(4), line(5), line(6),line(7)))
    articles
  }


  /**
    * This method takes a file path string to read a csv file and parse it and return a list of Array[String]
    * @param articles the relative file path to csv file
    * @return  List[ArticleEntry]
    */

  def sortArticles ( articles: List[ArticleEntry]): List[ArticleEntry] = {
    articles.sortWith( ((a1, a2) => a1.getCombineKey > a2.getCombineKey))
  }

  /**
    * This method is the action on request then read CSV file
    * generate List[ArticleEntry]
    * sorted the List[ArticleEntry]
    * generate a Jason object to return
    * @param csvPath the relative file path to csv file
    * @return  JsonObj
    */

  def getArticlesJson ( csvPath: String)= {

      val articleList = getArticles(csvPath)
      val sortedArticleList = sortArticles(articleList)
      val articleJsonList = sortedArticleList.map( a => Json.toJson(a)).toSeq
      val articlesJson = Json.obj(
        "Articles" -> articleJsonList
      )
      articlesJson
  }

}
