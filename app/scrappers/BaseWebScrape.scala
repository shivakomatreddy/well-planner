package scrappers

import org.jsoup.Jsoup
import java.net.URLDecoder


class BaseWebScrape(url: String) {

  private val decodedUrl = URLDecoder.decode(url, "utf-8")

  protected  val thisDocument = Jsoup.connect(decodedUrl).get()

  def title: String = {
    thisDocument
      .title()
  }

  def images: Seq[String] = {
    var listOfImages = Seq.empty[String]
    thisDocument.body().getElementsByTag("img")
      .forEach (element => {
        val imgLink = element.attr("src")
//         if(imgLink.contains("540x360") || imgLink.contains("-540x360/")) listOfImages = listOfImages ++ Seq(imgLink)
        if(imgLink.contains("/media/")) {
          listOfImages = listOfImages ++ Seq(imgLink)
        }
      })
    listOfImages
  }
}
