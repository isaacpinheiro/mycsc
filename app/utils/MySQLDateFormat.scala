package utils

import java.util.Date

object MySQLDateFormat {

  def format(d: Date): String = {

    val year = (d.getYear() + 1900).toString
    val month = (d.getMonth() + 1).toString
    val date = d.getDate().toString
    val hours = d.getHours().toString
    val minutes = d.getMinutes().toString
    val seconds = d.getSeconds().toString

    val res = year + "-" + month + "-" + date + " " + hours + ":" + minutes + ":" + seconds
    res

  }

}
