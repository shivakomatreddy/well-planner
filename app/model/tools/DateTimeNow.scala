package model.tools

import java.util.Calendar
import java.text.SimpleDateFormat

object DateTimeNow {
  private val calendar = Calendar.getInstance
  private val formatter = new SimpleDateFormat("yyyyMMdd")

  def getCurrent: Int =
    formatter.format(calendar.getTime).toInt
}


object PasswordProtector {

  def md5HashString(s: String): String = {
    import java.security.MessageDigest
    import java.math.BigInteger
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(s.getBytes)
    val bigInt = new BigInteger(1,digest)
    val hashedString = bigInt.toString(16)
    hashedString
  }

}