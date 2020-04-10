package controllers.util


import play.api.libs.json.Json._
import play.api.mvc.Results._
import play.api.mvc.Result
import Constants._
import play.api.libs.json.{JsNull, JsValue}


object ResponseTypes {

  def errorResponse(status: Int, messages: Seq[String]): Result =
    new Status(status)(obj("status" -> status ,"msg" -> messages.mkString(",")))

  def successResponseWithOptionalData(status: Int, data: Option[JsValue] = None, messages: Seq[String]): Result =
    data match {
      case Some(json) => successResponse(status, json, messages)
      case None => successResponse(status, JsNull, messages)
    }

  def successResponse(status: Int, data: JsValue, messages: Seq[String]): Result =
    new Status(status)(obj("status" -> SUCCESS, "data" -> data, "msg" -> messages.mkString(",")))

}
