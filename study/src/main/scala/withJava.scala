import java.util.{Date, Locale}
import java.text.DateFormat._
// 패키지에 속한 모든 것 가져올 때 별표가 아닌 밑줄 사용

object withJava {
  def main(args: Array[String]): Unit = {
    //Unit 타입은 c++에서 void와 유사
    val now = new Date
    val df = getDateInstance(LONG, Locale.FRANCE)
    println(df.format(now))
    //df format now 와 df.format(now)는 똑같이 실행된다
  }
}

